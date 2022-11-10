package oraclecrud.DataAcces;

import models.Vendedor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class VendedorDAO extends ConnectionOracle{
    /*
     * Clase para el manejo de la tabla vendedor
     * */
    // Query para obtener todos los vendedores
    private static final String FINDALL_VENDEDOR = "SELECT * FROM vendedor";
    // Query para obtener un vendedor por su codigo
    private static final String FINDONE_VENDEDOR = "SELECT * FROM vendedor WHERE codigo =";

    public Collection<Vendedor> findAllVendedor() throws NoDataException, GlobalException{
        /*
        * Metodo encargado de obtener todos los vendedores y retornarlos en una lista con
        * sus ventas asociadas
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
        ArrayList<Vendedor> collection  = new ArrayList<>();
        Vendedor tmpVendedor;

        try{
            query = conn.createStatement();
            result = query.executeQuery(FINDALL_VENDEDOR);
            // Se recorren las filas obtenidas y se crean los objetos de vendedor
            while (result.next()) {

                tmpVendedor = new Vendedor(
                    result.getInt("codigo"),
                    result.getString("nombre")
                );

                // Se agrega el vendedor a la lista
                collection.add(tmpVendedor);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new GlobalException("Sentencia SQL invalida");
        } finally {
            try {
                if(result!= null) {
                    // Se cierra el cursor
                    result.close();
                }
                // Nos desconectamos de la base de datos
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
    public Vendedor findVendedor(int codigo) throws GlobalException{
        /*
         * Funcion para buscar un vendedor por su codigo en Oracle
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
        Vendedor vendedor = null;

        try{
            query = conn.createStatement();
            result = query.executeQuery(FINDONE_VENDEDOR + String.valueOf(codigo));
            // Se recorren las filas obtenidas y se crea el objeto de vendedor
            while (result.next()){
                vendedor = new Vendedor(
                    result.getInt("codigo"),
                    result.getString("nombre")
                );
            }
        }catch (SQLException e) {
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

        return vendedor;
    }
}
