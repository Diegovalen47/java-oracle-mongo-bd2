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
    private static final String FIND_STATISTICS_MARCA = "select s.NOMBRE as sucursal, sum(v.NRO_UNIDADES) as totalUni ,p.marca, p.TIPO from PRODUCTO p join venta v on p.CODIGO = v.PRODUCTO join SUCURSAL s on  v.SUCURSAL = s.CODIGO group by s.nombre,p.TIPO, p.marca order by p.marca";
    private ArrayList<Marca> collectionMarca = new ArrayList<>();
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
        VentaDetail tmpVentaDetail;
        Marca tmpMarca;
        String marcaName;

        try{
            query = conn.createStatement();
            result = query.executeQuery(FIND_STATISTICS_MARCA);

            while (result.next()){
                marcaName  = result.getString("marca");
                tmpMarca = searchGender(marcaName);
                tmpVentaDetail = new VentaDetail(
                        result.getString("sucursal"),
                        result.getString("tipo"),
                        result.getInt("TotalUni")
                );
                tmpMarca.addVentaDetail(tmpVentaDetail);

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
        if(collectionMarca.size()==0){
            throw new NoDataException("No hay datos");
        }
        return collectionMarca;
    }
    private Marca searchGender(String marcaTxt){
        Marca tmpMarca = new Marca();
        for (Marca m: collectionMarca){
            if(m.getNombre().equals(marcaTxt)){
                return m;
            }
        }
        tmpMarca.setNombre(marcaTxt);
        collectionMarca.add(tmpMarca);
        return tmpMarca;
    }
}
