package oraclecrud.DataAcces;

import models.Vendedor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class VendedorDAO extends ConnectionOracle{
    private static final String FINDALL_VENDEDOR = "SELECT * FROM vendedor";
    private static final String FINDONE_VENDEDOR = "SELECT * FROM vendedor WHERE codigo =";

    public Collection<Vendedor> findAllVendedor() throws NoDataException, GlobalException{

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
            while (result.next()){
                tmpVendedor = new Vendedor(
                        result.getInt("codigo"),
                        result.getString("nombre")
                );
                collection.add(tmpVendedor);
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
    public Vendedor findVendedor(int codigo) throws GlobalException{

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
            //TODO:¿Se puede quitar ValueOf?
            result = query.executeQuery(FINDONE_VENDEDOR + String.valueOf(codigo));
            while (result.next()){
                vendedor = new Vendedor(
                        result.getInt("codigo"),
                        result.getString("nombre")
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

        return vendedor;
    }
}
