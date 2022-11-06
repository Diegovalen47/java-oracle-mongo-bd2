import java.sql.*;

public class ConnectionOracleDB {

    public static void main(String[] args) {

        Connection conn = null;
        Statement sentencia = null;
        ResultSet resultado;

        String HOST = "ASUSCT";
        String PORT = "1521";
        String SID = "xe";
        String USER = "sacastrot";
        String PASS = "admin";

        System.out.println("Conectando a la base de datos...");

        try { // Cargar el driver

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (Exception e) {

            System.out.println("No se pudo cargar el driver JDBC");

        }

        try {

            conn = DriverManager.getConnection("jdbc:oracle:thin:@"+HOST+":"+PORT+":"+SID, USER, PASS);
            sentencia = conn.createStatement();

        } catch (Exception e) {

            System.out.println("No hay conexion con la base de datos");

        }

        try {

            System.out.println("Seleccionando...");

            resultado = sentencia.executeQuery("select table_name from cols");

            // Se recorren las tuplas retornadas
            while (resultado.next()) {
                System.out.println(
                        resultado.getInt("codigo") + "---" + resultado.getString("nom") + "---" + resultado.getInt("salario")
                );
            }

            conn.close();

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Consulta finalizada");

    }
}