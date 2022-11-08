package oraclecrud.DataAcces.Statistics;

import models.Genero;
import models.VentaDetail;
import oraclecrud.DataAcces.ConnectionOracle;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GeneroDAO extends ConnectionOracle {

    private static final String FIND_STATISTICS_GENERO = "select c.genero, s.nombre, p.tipo, sum(nro_unidades) as TotalUni from cliente c join venta v on c.codigo = v.cliente join producto p on p.codigo = v.producto join sucursal s on s.codigo = v.sucursal group by c.genero, s.nombre, p.tipo order by c.genero";
    private static final String FIND_SIZE = "SELECT count(*) as rowCount from (" + FIND_STATISTICS_GENERO + ")";

    public ArrayList<Genero> findStatistics() throws GlobalException,NoDataException{

        try{
            Connect();
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }

        ResultSet result = null;
        Statement query;
        ArrayList<Genero> collectionGender = new ArrayList<>();
        VentaDetail tmpVentaDetail;
        Genero tmpGender;
        String genderName = "";
        int cont = 0;

        try{
            query = conn.createStatement();

            result = query.executeQuery(FIND_SIZE);
            result.next();
            int size = result.getInt("rowCount");

            result = query.executeQuery(FIND_STATISTICS_GENERO);

            ArrayList<VentaDetail> misVentas = new ArrayList<>();

            String genderResult = "";

            while (result.next()){

                if(cont == 0){
                    tmpVentaDetail = new VentaDetail(
                            result.getString("nombre"),
                            result.getString("tipo"),
                            result.getInt("totalUni")
                    );
                    misVentas.add(tmpVentaDetail);
                    genderName = (result.getString("genero") == null) ? "Unknown" : result.getString("genero");
                    cont += 1;
                }else{

                    genderResult = (result.getString("genero") == null) ? "Unknown" : result.getString("genero");

                    if(genderResult.equals(genderName)){
                        tmpVentaDetail = new VentaDetail(
                                result.getString("nombre"),
                                result.getString("tipo"),
                                result.getInt("totalUni")
                        );
                        misVentas.add(tmpVentaDetail);
                        cont += 1;
                        if (cont == size){
                            tmpGender = new Genero(genderName,new ArrayList<>(misVentas));
                            collectionGender.add(tmpGender);
                        }
                    }else{
                        tmpGender = new Genero(genderName,new ArrayList<>(misVentas));
                        collectionGender.add(tmpGender);
                        misVentas.clear();

                        tmpVentaDetail = new VentaDetail(
                            result.getString("nombre"),
                            result.getString("tipo"),
                            result.getInt("totalUni")
                        );
                        misVentas.add(tmpVentaDetail);
                        genderName = (result.getString("genero") == null) ? "Unknown" : result.getString("genero");
                        cont += 1;
                        if (cont == size){
                            tmpGender = new Genero(genderName, new ArrayList<>(misVentas));
                            collectionGender.add(tmpGender);
                        }
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
        if(collectionGender.size()==0){
            throw new NoDataException("No hay datos");
        }
        return collectionGender;
    }

}
