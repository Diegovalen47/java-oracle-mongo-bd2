package UIMain.crud;

import models.Cliente;
import oraclecrud.DataAcces.ClienteDAO;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;

import java.util.Collection;

public class ShowCliente {

    public ShowCliente() {
    }

    public String printCliente() throws NoDataException, GlobalException {

        String finalText = "";

        ClienteDAO sdao = new ClienteDAO();
        Collection<Cliente> clientes = sdao.findAllCliente();

        for(Cliente s: clientes) {
            finalText = finalText + s.toString();
            finalText = finalText + "\n";
        }

        return finalText;
    }
}
