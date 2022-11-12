package oraclecrud.DataAcces;

import models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class VentaDAO extends ConnectionOracle{
    /*
     * Clase para el manejo de la tabla venta
     * */
    private static final String FINDALL_VENTA = "SELECT * FROM venta";
    public Collection<Venta> findAllVentas() throws NoDataException, GlobalException{

        try{
            Connect();
        }catch (ClassNotFoundException e){
            throw new GlobalException("No se pudo cargar el driver JDBC");
        }catch (SQLException e ){
            throw new GlobalException("No hay conexion con la base de datos");
        }

        Sucursal tempSucursal;
        SucursalDAO tempSucursalDAO = new SucursalDAO();

        Vendedor tempVendedor;
        VendedorDAO tempVendedorDAO = new VendedorDAO();

        Cliente tempCliente;
        ClienteDAO tempClienteDAO = new ClienteDAO();

        Producto tempProducto;
        ProductoDAO tempProductoDAO = new ProductoDAO();


        ResultSet result = null;
        Statement query;
        ArrayList<Venta> collection  = new ArrayList<>();
        Venta tempVenta;

        try{
            query = conn.createStatement();
            result = query.executeQuery(FINDALL_VENTA);
            while (result.next()){

                tempSucursal = tempSucursalDAO.findSucursal(result.getInt("sucursal"), conn);
                tempVendedor = tempVendedorDAO.findVendedor(result.getInt("vendedor"), conn);
                tempCliente = tempClienteDAO.findCliente(result.getInt("cliente"), conn);
                tempProducto = tempProductoDAO.findProducto(result.getInt("producto"), conn);
                tempVenta = new Venta(
                    result.getInt("codventa"),
                    tempSucursal,
                    tempVendedor,
                    tempCliente,
                    tempProducto,
                    result.getInt("nro_unidades")

                );

                collection.add(tempVenta);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new GlobalException("Sentencia SQL invalida");
        } finally {
            try {
                if(result!= null){
                    result.close();
                }
                Disconnect();
            }catch (SQLException e){
                throw new GlobalException("Estados invalidos");
            }
        }
        if(collection.size()==0){
            throw new NoDataException("No hay datos");
        }
        return collection;
    }
}
