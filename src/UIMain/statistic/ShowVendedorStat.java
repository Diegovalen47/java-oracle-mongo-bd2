package UIMain.statistic;

import com.mongodb.client.FindIterable;
import mongodbcrud.VendedorDAC;
import oraclecrud.DataAcces.NoDataException;
import org.bson.Document;

import java.util.ArrayList;

public class ShowVendedorStat {
    /*
     * Clase para imprimir los datos de la coleccion vendedor
     * */

    public ShowVendedorStat() {
    }

    public String printVendedor() {

        VendedorDAC vendedorDAC = new VendedorDAC();
        FindIterable<Document> vendedores;

        int granGranTotal = 0;

        try {
            vendedores = vendedorDAC.finAll();
        } catch (NoDataException ex) {
            throw new RuntimeException(ex);
        }

        String finalText = "";

        for(Document d : vendedores) {

            finalText = finalText + "------------------------------------------------------\n";

            finalText = finalText + "Codigo Vendedor: " + d.getInteger("codvendedor") + "\n";

            finalText = finalText + "------------------------------------------------------\n";

            finalText = finalText + "Nombre Vendedor: " + d.getString("nomvend") + "\n";

            finalText = finalText + "------------------------------------------------------\n";

            finalText = finalText + "Mis Ventas:\n";

            finalText = finalText + "\n";

            ArrayList<Document> list = new ArrayList<>((ArrayList<Document>) d.get("misVentas"));
            for (Document item: list) {

                finalText = finalText + "  " + "nomsucursal: " + item.getString("nomsucursal") + "\n";
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
