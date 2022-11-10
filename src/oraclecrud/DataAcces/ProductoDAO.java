package oraclecrud.DataAcces;

import models.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class ProductoDAO extends ConnectionOracle{
    /*
     * Clase para el manejo de la tabla producto
     * */
    private static final String FINDALL_PRODUCTO = "SELECT * FROM producto";
    private static final String FINDONE_PRODUCTO = "SELECT * FROM producto WHERE codigo =";

    public Collection<Producto> findAllProducto() throws NoDataException, GlobalException{

        try{
            Connect();
        }catch (ClassNotFoundException e){
            throw new GlobalException("No se pudo cargar el driver JDBC");
        }catch (SQLException e ){
            throw new GlobalException("No hay conexion con la base de datos");
        }

        ResultSet result = null;
        Statement query;
        ArrayList<Producto> collection  = new ArrayList<>();
        Producto tmpProducto;

        try{

            query = conn.createStatement();
            result = query.executeQuery(FINDALL_PRODUCTO);

            while (result.next()){

                tmpProducto = new Producto(
                    result.getInt("codigo"),
                    result.getString("nombre"),
                    result.getString("tipo"),
                    result.getString("marca")
                );

                collection.add(tmpProducto);

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
        if(collection.size()==0){
            throw new NoDataException("No hay datos");
        }
        return collection;
    }

    public Producto findProducto(int codigo) throws GlobalException{

        try{
            Connect();
        }catch (ClassNotFoundException e){
            throw new GlobalException("No se pudo cargar el driver JDBC");
        }catch (SQLException e ){
            throw new GlobalException("No hay conexion con la base de datos");
        }

        ResultSet result = null;
        Statement query;
        Producto producto = null;

        try{
            query = conn.createStatement();
            //TODO:¿Se puede quitar ValueOf?
            result = query.executeQuery(FINDONE_PRODUCTO + String.valueOf(codigo));
            while (result.next()){
                producto = new Producto(
                        result.getInt("codigo"),
                        result.getString("nombre"),
                        result.getString("tipo"),
                        result.getString("marca")
                );
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



        return producto;
    }
}
