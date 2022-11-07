package UIMain.crud;

import models.Sucursal;
import models.Vendedor;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;
import oraclecrud.DataAcces.SucursalDAO;
import oraclecrud.DataAcces.VendedorDAO;

import java.util.Collection;

public class ShowVendedor {

    public ShowVendedor() {
    }

    public String printVendedor() throws NoDataException, GlobalException {
        String finalText = "";

        VendedorDAO sdao = new VendedorDAO();
        Collection<Vendedor> vendedores = sdao.findAllVendedor();

        for(Vendedor s: vendedores) {
            finalText = finalText + s.toString();
            finalText = finalText + "\n";
        }

        return finalText;
    }
}
