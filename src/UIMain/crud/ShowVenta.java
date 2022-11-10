package UIMain.crud;

import models.Venta;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;
import oraclecrud.DataAcces.VentaDAO;

import java.util.Collection;

public class ShowVenta {
    /*
     * Clase para imprimir los datos de la tabla venta
     * */
    public ShowVenta() {
    }

    public String printVenta() throws NoDataException, GlobalException {

        String finalText = "";

        VentaDAO sdao = new VentaDAO();
        Collection<Venta> ventas = sdao.findAllVentas();

        for(Venta s: ventas) {
            finalText = finalText + s.toString();
            finalText = finalText + "\n";
        }

        return finalText;
    }
}
