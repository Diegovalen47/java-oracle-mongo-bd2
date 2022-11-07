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
    private static final String FIND_STATISTICS_SELLER = "select vd.codigo, vd.nombre, s.nombre as sucursal, sum(nro_unidades) as TotalUni from vendedor vd join venta v on v.vendedor = vd.codigo join sucursal s on v.sucursal = s.codigo group by vd.codigo, vd.nombre, s.nombre order by vd.CODIGO";
    private static final String FIND_SIZE = "SELECT count(*) as rowCount from (" + FIND_STATISTICS_SELLER + ")";

    public ArrayList<Vendedor> findStatistics() throws NoDataException, GlobalException {

        try{
            Connect();
        }catch (ClassNotFoundException e){
            throw new GlobalException("No se pudo cargar el driver JDBC");
        }catch (SQLException e ){
            throw new GlobalException("No hay conexion con la base de datos");
        }

        ResultSet result = null;
        Statement query;
        ArrayList<Vendedor> collectionSeller = new ArrayList<>();
        VentaDetail tmpVentaDetail;
        Vendedor tmpSeller;
        int code = -1;
        int counter = 0;
        String nameSeller = "";

        try{
            query = conn.createStatement();

            result = query.executeQuery(FIND_SIZE);
            result.next();
            int size = result.getInt("rowCount");

            result = query.executeQuery(FIND_STATISTICS_SELLER);

            ArrayList<VentaDetail> mySales = new ArrayList<>();

            while (result.next()){
                if(counter == 0){
                    tmpVentaDetail = new VentaDetail(
                            result.getString("sucursal"),
                            result.getInt("totalUni")
                    );
                    mySales.add(tmpVentaDetail);
                    code = result.getInt("codigo");
                    nameSeller = result.getString("nombre");
                    counter += 1;
                }else{
                    if(result.getInt("codigo")== code){
                        tmpVentaDetail = new VentaDetail(
                                result.getString("sucursal"),
                                result.getInt("totalUni")
                        );
                        mySales.add(tmpVentaDetail);
                        counter += 1;
                        if (counter == size){
                            tmpSeller = new Vendedor(code,nameSeller, new ArrayList<>(mySales));
                            collectionSeller.add(tmpSeller);
                        }
                    }else{
                        tmpSeller = new Vendedor(code,nameSeller, new ArrayList<>(mySales));
                        collectionSeller.add(tmpSeller);
                        mySales.clear();

                        tmpVentaDetail = new VentaDetail(
                                result.getString("sucursal"),
                                result.getInt("totalUni")
                        );
                        mySales.add(tmpVentaDetail);
                        code = result.getInt("codigo");
                        nameSeller = result.getString("nombre");
                        counter += 1;
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
        if(collectionSeller.size()==0){
            throw new NoDataException("No hay datos");
        }
        return collectionSeller;
    }
}
