package oraclecrud.DataAcces.Statistics;

import models.Marca;
import models.VentaDetail;
import oraclecrud.DataAcces.ConnectionOracle;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MarcaDAO extends ConnectionOracle {
    private static final String FIND_STATISTICS_CLIENTE = "select s.NOMBRE, sum(v.NRO_UNIDADES) as totalUni ,p.marca, p.TIPO from PRODUCTO p join venta v on p.CODIGO = v.PRODUCTO join SUCURSAL s on  v.SUCURSAL = s.CODIGO group by s.nombre,p.TIPO, p.marca";
    private static final String FIND_SIZE = "SELECT count(*) as rowCount from (" + FIND_STATISTICS_CLIENTE + ")";
    public ArrayList<Marca> findStatistics() throws NoDataException, GlobalException {
        try{
            Connect();
        }catch (ClassNotFoundException e){
            throw new GlobalException("No se pudo cargar el driver JDBC");
        }catch (SQLException e ){
            throw new GlobalException("No hay conexion con la base de datos");
        }

        ResultSet result = null;
        Statement query;
        ArrayList<Marca> marcas = new ArrayList<>();
        VentaDetail tmpVentaDetail;
        Marca tmpMarca;
        String marcaName = "";
        int cont = 0;

        try{
            query = conn.createStatement();

            result = query.executeQuery(FIND_SIZE);
            result.next();
            int size = result.getInt("rowCount");

            result = query.executeQuery(FIND_STATISTICS_CLIENTE);

            ArrayList<VentaDetail> misVentas = new ArrayList<>();

            while (result.next()){
                if(cont == 0){
                    tmpVentaDetail = new VentaDetail(
                            result.getString("nombre"),
                            result.getString("tipo"),
                            result.getInt("totalUni")
                    );
                    misVentas.add(tmpVentaDetail);
                    marcaName = result.getString("marca");
                    cont += 1;
                }else{
                    if(result.getString("marca").equals(marcaName)){
                        tmpVentaDetail = new VentaDetail(
                                result.getString("nombre"),
                                result.getString("tipo"),
                                result.getInt("totalUni")
                        );
                        misVentas.add(tmpVentaDetail);
                        cont += 1;
                        if (cont == size){
                            tmpMarca = new Marca(marcaName,new ArrayList<>(misVentas));
                            marcas.add(tmpMarca);
                        }
                    }else{
                        tmpMarca = new Marca(marcaName,new ArrayList<>(misVentas));
                        marcas.add(tmpMarca);
                        misVentas.clear();

                        tmpVentaDetail = new VentaDetail(
                                result.getString("nombre"),
                                result.getString("tipo"),
                                result.getInt("totalUni")
                        );
                        misVentas.add(tmpVentaDetail);
                        marcaName = result.getString("marca");
                        cont += 1;
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new GlobalException("Sentencia SQL invalida");
        } finally {
            try {
                if(result!= null){
                    result.close();
                }
                Disconnect();
            }catch (SQLException e){
                throw new GlobalException("Estados invalidos");
            }
        }
        if(marcas.size()==0){
            throw new NoDataException("No hay datos");
        }
        return marcas;
    }
}
