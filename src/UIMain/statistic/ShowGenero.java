package UIMain.statistic;

import com.mongodb.client.FindIterable;
import mongodbcrud.GeneroDAC;
import oraclecrud.DataAcces.NoDataException;
import org.bson.Document;

import java.util.ArrayList;

public class ShowGenero {
    /*
     * Clase para imprimir los datos de la coleccion genero
     * */

    public ShowGenero() {
    }

    public String printGenero() {

        GeneroDAC generoDAC = new GeneroDAC();
        FindIterable<Document> generos;

        int granGranTotal = 0;

        try {
            generos = generoDAC.findAll();
        } catch (NoDataException ex) {
            throw new RuntimeException(ex);
        }

        String finalText = "";

        for(Document d : generos) {

            finalText = finalText + "------------------------------------------------------\n";

            finalText = finalText + "Genero: " + d.getString("genero") + "\n";

            finalText = finalText + "------------------------------------------------------\n";

            finalText = finalText + "Mis Ventas:\n";

            finalText = finalText + "\n";

            ArrayList<Document> list = new ArrayList<>((ArrayList<Document>) d.get("misVentas"));
            for (Document item: list) {

                finalText = finalText + "  " + "nomsucursal: " + item.getString("nomsucursal") + "\n";
                finalText = finalText + "  " + "TipoProd: " + item.getString("TipoProd") + "\n";
                finalText = finalText + "  " + "TotalUni: " + item.getInteger("TotalUni") + "\n";
                finalText = finalText + "\n";

            }

            finalText = finalText + "------------------------------------------------------\n";
            finalText = finalText + "Gran Total: " + d.getInteger("granTotal") + "\n";
            finalText = finalText + "------------------------------------------------------\n";
            finalText = finalText + "\n";
            finalText = finalText + "===============================\n";
            finalText = finalText + "\n";

            granGranTotal = granGranTotal + d.getInteger("granTotal");

        }

        finalText = finalText + "Super Gran Total: " + String.valueOf(granGranTotal) + "\n";


        return finalText;
    }
}
