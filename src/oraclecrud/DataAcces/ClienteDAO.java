package oraclecrud.DataAcces;

import models.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO extends  ConnectionOracle{
    private static final String FINDALL_CLIENTE = "SELECT * FROM cliente";
    private static final String FINDONE_CLIENTE = "SELECT * FROM cliente WHERE codigo =";

    public ArrayList<Cliente> findAllCliente() throws NoDataException, GlobalException{

        try{
            Connect();
        }catch (ClassNotFoundException e){
            throw new GlobalException("No se pudo cargar el driver JDBC");
        }catch (SQLException e ){
            throw new GlobalException("No hay conexion con la base de datos");
        }

        ResultSet result = null;
        Statement query;
        ArrayList<Cliente> collection  = new ArrayList<>();
        Cliente tmpCliente;

        try{
            query = conn.createStatement();
            result = query.executeQuery(FINDALL_CLIENTE);
            while (result.next()){
                tmpCliente = new Cliente(
                        result.getInt("codigo"),
                        result.getString("nombre"),
                        result.getString("genero")
                );
                collection.add(tmpCliente);
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

    public Cliente findCliente(int codigo) throws NoDataException, GlobalException{

        try{
            Connect();
        }catch (ClassNotFoundException e){
            throw new GlobalException("No se pudo cargar el driver JDBC");
        }catch (SQLException e ){
            throw new GlobalException("No hay conexion con la base de datos");
        }

        ResultSet result = null;
        Statement query = null;
        Cliente cliente = null;

        try{
            query = conn.createStatement();
            // TODO:¿Se pude quitar ValueOf?
            result = query.executeQuery(FINDONE_CLIENTE + String.valueOf(codigo));
            while (result.next()){
                cliente = new Cliente(
                        result.getInt("codigo"),
                        result.getString("nombre"),
                        result.getString("genero")
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

        return cliente;
    }

}
