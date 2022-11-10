package UIMain.crud;

import models.Producto;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;
import oraclecrud.DataAcces.ProductoDAO;

import java.util.Collection;

public class ShowProducto {
    /*
     * Clase para imprimir los datos de la tabla producto
     * */
    public ShowProducto() {
    }

    public String printProducto() throws NoDataException, GlobalException {

        String finalText = "";

        ProductoDAO sdao = new ProductoDAO();
        Collection<Producto> productos = sdao.findAllProducto();

        for(Producto s: productos) {
            finalText = finalText + s.toString();
            finalText = finalText + "\n";
        }

        return finalText;
    }
}
