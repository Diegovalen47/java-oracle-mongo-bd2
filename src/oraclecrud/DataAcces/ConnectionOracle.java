package oraclecrud.DataAcces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOracle {
    protected Connection conn = null;

    public ConnectionOracle() {}
    protected void Connect() throws SQLException, ClassNotFoundException {

        String HOST = "San-Valen-PC";
        String PORT = "1521";
        String SID = "xe";
        String USER = "usuario";
        String PASS = "password";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + SID, USER, PASS);
    }
    protected void Disconnect() throws SQLException {
        if (!conn.isClosed()) {
            conn.close();
        }
    }
}