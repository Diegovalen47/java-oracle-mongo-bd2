package oraclecrud.DataAcces;

import models.Sucursal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class SucursalDAO extends ConnectionOracle {
    private static final String FINDALL_SUCURSAL = "SELECT * FROM sucursal";
    private static final String FINDONE_SUCURSAL = "SELECT * FROM sucursal WHERE codigo =";

    public Collection<Sucursal> findAllSucursal() throws NoDataException, GlobalException{

        try{
            Connect();
        }catch (ClassNotFoundException e){
            throw new GlobalException("No se pudo cargar el driver JDBC");
        }catch (SQLException e ){
            throw new GlobalException("No hay conexion con la base de datos");
        }

        ResultSet result = null;
        Statement query = null;
        ArrayList<Sucursal> collection  = new ArrayList<>();
        Sucursal sucursal = null;

        try{
            query = conn.createStatement();
            result = query.executeQuery(FINDALL_SUCURSAL);
            while (result.next()){
                sucursal = new Sucursal(
                        result.getInt("codigo"),
                        result.getString("nombre")
                );
                collection.add(sucursal);
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
    public Sucursal findSucursal(int codigo) throws NoDataException, GlobalException{

        try{
            Connect();
        }catch (ClassNotFoundException e){
            throw new GlobalException("No se pudo cargar el driver JDBC");
        }catch (SQLException e ){
            throw new GlobalException("No hay conexion con la base de datos");
        }

        ResultSet result = null;
        Statement query = null;
        Sucursal sucursal = null;

        try{
            query = conn.createStatement();
            result = query.executeQuery(FINDONE_SUCURSAL + String.valueOf(codigo));
            while (result.next()){
                sucursal = new Sucursal(
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

        return sucursal;
    }
}
