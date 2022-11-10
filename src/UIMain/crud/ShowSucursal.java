package UIMain.crud;

import models.Sucursal;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;
import oraclecrud.DataAcces.SucursalDAO;

import java.util.Collection;

public class ShowSucursal {
    /*
     * Clase para imprimir los datos de la tabla sucursal
     * */
    public ShowSucursal() {
    }

    public String printSucursal() throws NoDataException, GlobalException {

        String finalText = "";

        SucursalDAO sdao = new SucursalDAO();
        Collection<Sucursal> sucursales = sdao.findAllSucursal();

        for(Sucursal s: sucursales) {
            finalText = finalText + s.toString();
            finalText = finalText + "\n";
        }

        return finalText;

    }
}
