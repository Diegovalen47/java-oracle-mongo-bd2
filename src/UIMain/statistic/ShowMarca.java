package UIMain.statistic;

import com.mongodb.client.FindIterable;
import mongodbcrud.MarcaDAC;
import oraclecrud.DataAcces.NoDataException;
import org.bson.Document;

import java.util.ArrayList;

public class ShowMarca {

    public ShowMarca() {
    }

    public String printMarca() {

        MarcaDAC marcaDAC = new MarcaDAC();
        FindIterable<Document> marcas;

        int granGranTotal = 0;

        try {
            marcas = marcaDAC.finAll();
        } catch (NoDataException ex) {
            throw new RuntimeException(ex);
        }

        String finalText = "";

        for(Document d : marcas) {

            finalText = finalText + "-----------------------------------------------------\n";

            finalText = finalText + "Nombre Marca: " + d.getString("nombreMarca") + "\n";

            finalText = finalText + "-----------------------------------------------------\n";

            finalText = finalText + "Mis Ventas:\n";

            finalText = finalText + "\n";

            ArrayList<Document> list = new ArrayList<>((ArrayList<Document>) d.get("misVentas"));
            for (Document item: list) {

                finalText = finalText + "  " + "nomsucursal: " + item.getString("nomsucursal") + "\n";
                finalText = finalText + "  " + "TipoProd: " + item.getString("TipoProd") + "\n";
                finalText = finalText + "  " + "TotalUni: " + item.getInteger("TotalUni") + "\n";
                finalText = finalText + "\n";

            }

            finalText = finalText + "-----------------------------------------------------\n";
            finalText = finalText + "Gran Total: " + d.getInteger("granTotal") + "\n";
            finalText = finalText + "-----------------------------------------------------\n";
            finalText = finalText + "\n";
            finalText = finalText + "===============================\n";
            finalText = finalText + "\n";

            granGranTotal = granGranTotal + d.getInteger("granTotal");

        }

        finalText = finalText + "Gran Gran Total: " + String.valueOf(granGranTotal) + "\n";

        return finalText;
    }
}
