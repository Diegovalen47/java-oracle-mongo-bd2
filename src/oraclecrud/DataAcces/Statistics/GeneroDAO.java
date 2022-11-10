package oraclecrud.DataAcces.Statistics;

import models.Genero;
import models.VentaDetail;
import oraclecrud.DataAcces.ConnectionOracle;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GeneroDAO extends ConnectionOracle {

    // Query para obtener las estadisticas agrupadas por genero
    private static final String FIND_STATISTICS_GENERO = "select c.genero, s.nombre, p.tipo, sum(nro_unidades) as TotalUni from cliente c join venta v on c.codigo = v.cliente join producto p on p.codigo = v.producto join sucursal s on s.codigo = v.sucursal group by c.genero, s.nombre, p.tipo order by c.genero";
    ArrayList<Genero> collectionGender = new ArrayList<>();

    public ArrayList<Genero> findStatistics() throws GlobalException,NoDataException{
        /*
        * Metodo encargado de calcular las estadisticas agrupadas por genero
        * */

        try{
            Connect();
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }

        ResultSet result = null;
        Statement query;
        VentaDetail tmpVentaDetail;
        Genero tmpGender;
        String generoTxt;

        try{
            query = conn.createStatement();
            result = query.executeQuery(FIND_STATISTICS_GENERO);
            // Se recorren las filas obtenidas agrupadas por genero, para
            // a cada genero agruparle sus ventas
            while (result.next()) {

                // Se obtiene el genero de la fila, y en caso de ser nulo, se coloca que es un genero desconocido
                generoTxt  = result.getString("genero") == null ? "Unknown" : result.getString("genero");

                tmpGender = searchGender(generoTxt);
                // Se crea un nuevo objeto de detalle de venta (misVentas en coleccion de generos)
                tmpVentaDetail = new VentaDetail(
                    result.getString("nombre"),
                    result.getString("tipo"),
                    result.getInt("TotalUni")
                );
                // Se agrega este detalle de venta al genero actual
                tmpGender.addVentaDetail(tmpVentaDetail);

            }

        }catch (SQLException e){
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
        if(collectionGender.size()==0){
            throw new NoDataException("No hay datos");
        }
        return collectionGender;
    }

    private Genero searchGender(String genderTxt){
        /*
        * Funcion para buscar un genero en la lista de generos
        * */
        Genero tmpGender = new Genero();
        for (Genero g: collectionGender){
            // Si el genero ya existe en la lista, se retorna el genero encontrado
            if(g.getGenero().equals(genderTxt)){
                return g;
            }
        }
        tmpGender.setGenero(genderTxt);
        // En otro caso, se agrega el genero a la lista y se retorna
        collectionGender.add(tmpGender);
        return tmpGender;
    }

}
