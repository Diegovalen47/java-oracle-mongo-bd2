package oraclecrud.DataAcces;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TablesDB extends ConnectionOracle{
    private static final String FINDALL_TABLES = "SELECT TNAME FROM TAB";

    public ArrayList<String> finAllTables() throws NoDataException, GlobalException{

        try{
            Connect();
        }catch (ClassNotFoundException e){
            throw new GlobalException("No se pudo cargar el driver JDBC");
        }catch (SQLException e ){
            throw new GlobalException("No hay conexion con la base de datos");
        }

        ResultSet result = null;
        Statement query;
        ArrayList<String> tableNames = new ArrayList<>();
        try{
            query = conn.createStatement();
            result = query.executeQuery(FINDALL_TABLES);
            while (result.next()){
                tableNames.add(result.getString("tname"));
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
        if(tableNames.size()==0){
            throw new NoDataException("No hay datos");
        }
        return tableNames;
    }
}
