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
    ArrayList<Vendedor> collectionSeller = new ArrayList<>();

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
        VentaDetail tmpVentaDetail;
        Vendedor tmpSeller;
        int code;

        try{
            query = conn.createStatement();
            result = query.executeQuery(FIND_STATISTICS_SELLER);

            while (result.next()){
                code  = result.getInt("codigo");
                tmpSeller = searchSeller(code);
                tmpSeller.setNombre(result.getString("nombre"));
                tmpVentaDetail = new VentaDetail(
                        result.getString("sucursal"),
                        result.getInt("TotalUni")
                );
                tmpSeller.addVentaDetail(tmpVentaDetail);
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
    private Vendedor searchSeller(int code){
        Vendedor tmpSeller = new Vendedor();
        for (Vendedor s: collectionSeller){
            if(s.getCodigo() == code){
                return s;
            }
        }
        tmpSeller.setCodigo(code);
        collectionSeller.add(tmpSeller);
        return tmpSeller;
    }
}
