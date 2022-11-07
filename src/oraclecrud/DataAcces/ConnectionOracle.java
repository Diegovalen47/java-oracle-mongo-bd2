package oraclecrud.DataAcces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOracle {
    protected Connection conn = null;

    public ConnectionOracle() {}
    protected void Connect() throws SQLException, ClassNotFoundException {

        String HOST = "ASUSCT";
        String PORT = "1521";
        String SID = "xe";
        String USER = "sacastrot";
        String PASS = "admin";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + SID, USER, PASS);
    }
    protected void Disconnect() throws SQLException {
        if (!conn.isClosed()) {
            conn.close();
        }
    }
}