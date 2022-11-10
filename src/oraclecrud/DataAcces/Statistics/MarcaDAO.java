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
    // Query para obtener las estadisticas agrupadas por marca
    private static final String FIND_STATISTICS_MARCA = "select s.NOMBRE as sucursal, sum(v.NRO_UNIDADES) as totalUni ,p.marca, p.TIPO from PRODUCTO p join venta v on p.CODIGO = v.PRODUCTO join SUCURSAL s on  v.SUCURSAL = s.CODIGO group by s.nombre,p.TIPO, p.marca order by p.marca";
    private ArrayList<Marca> collectionMarca = new ArrayList<>();
    public ArrayList<Marca> findStatistics() throws NoDataException, GlobalException {
        /*
         * Metodo encargado de calcular las estadisticas agrupadas por marca
         * */
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
            // Se recorren las filas obtenidas agrupadas por marca, para
            // a cada marca agruparle sus ventas
            while (result.next()) {

                marcaName  = result.getString("marca");
                tmpMarca = searchGender(marcaName);
                // Se crea un nuevo objeto de detalle de venta (misVentas en coleccion de marcas)
                tmpVentaDetail = new VentaDetail(
                    result.getString("sucursal"),
                    result.getString("tipo"),
                    result.getInt("TotalUni")
                );
                // Se agrega este detalle de venta a la marca actual
                tmpMarca.addVentaDetail(tmpVentaDetail);

            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new GlobalException("Sentencia SQL invalida");
        } finally {
            try {
                if(result!= null){
                    // Se cierra el cursor
                    result.close();
                }
                // Nos desconectamos de la base de datos
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
        /*
         * Funcion para buscar una Marca en la lista de marcas
         * */
        Marca tmpMarca = new Marca();
        for (Marca m: collectionMarca){
            // Si la marca ya existe en la lista, se retorna la marca encontrada
            if(m.getNombre().equals(marcaTxt)){
                return m;
            }
        }
        tmpMarca.setNombre(marcaTxt);
        // En otro caso, se agrega la marca a la lista y se retorna
        collectionMarca.add(tmpMarca);
        return tmpMarca;
    }
}
