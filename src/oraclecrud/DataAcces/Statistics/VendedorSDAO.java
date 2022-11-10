package oraclecrud.DataAcces.Statistics;

import models.Vendedor;
import models.VentaDetail;
import oraclecrud.DataAcces.ConnectionOracle;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VendedorSDAO extends ConnectionOracle {
    // Query para obtener las estadisticas agrupadas por vendedor
    private static final String FIND_STATISTICS_SELLER = "select vd.codigo, vd.nombre, s.nombre as sucursal, sum(nro_unidades) as TotalUni from vendedor vd join venta v on v.vendedor = vd.codigo join sucursal s on v.sucursal = s.codigo group by vd.codigo, vd.nombre, s.nombre order by vd.CODIGO";
    ArrayList<Vendedor> collectionSeller = new ArrayList<>();

    public ArrayList<Vendedor> findStatistics() throws NoDataException, GlobalException {
        /*
         * Metodo encargado de calcular las estadisticas agrupadas por vendedor
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
        Vendedor tmpSeller;

        int code;

        try{
            query = conn.createStatement();
            result = query.executeQuery(FIND_STATISTICS_SELLER);
            // Se recorren las filas obtenidas agrupadas por vendedor, para
            // a cada vendedor agruparle sus ventas
            while (result.next()){

                code  = result.getInt("codigo");

                tmpSeller = searchSeller(code);

                tmpSeller.setNombre(result.getString("nombre"));
                // Se crea un nuevo objeto de detalle de venta (misVentas en coleccion de vendedores)
                tmpVentaDetail = new VentaDetail(
                    result.getString("sucursal"),
                    result.getInt("TotalUni")
                );
                // Se agrega este detalle de venta al vendedor actual
                tmpSeller.addVentaDetail(tmpVentaDetail);
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
                // Se cierra la conexion
                Disconnect();
            }catch (SQLException e){
                throw new GlobalException("Estados invalidos");
            }
        }
        if(collectionSeller.size()==0){
            throw new NoDataException("No hay datos");
        }
        return collectionSeller;
    }
    private Vendedor searchSeller(int code){
        /*
         * Funcion para buscar un vendedor en la lista de vendedores
         * */
        Vendedor tmpSeller = new Vendedor();
        for (Vendedor s: collectionSeller){
            // Si el vendedor ya existe en la lista, se retorna el vendedor encontrado
            if(s.getCodigo() == code){
                return s;
            }
        }
        tmpSeller.setCodigo(code);
        // En otro caso, se agrega el vendedor a la lista y se retorna
        collectionSeller.add(tmpSeller);
        return tmpSeller;
    }
}
