package models;

public class Venta {
    /*
    * Esta clase representa una venta
    * dondes se agrupan la sucursal donde se realizo
    * El vendedor que la realizo
    * El cliente que la realizo
    * El producto que se vendio
    * Las unidades del productos vendidos
    * */
    private final int codigo;
    private final Sucursal sucursal;
    private final Vendedor vendedor;
    private final Cliente cliente;
    private final Producto producto;
    private final int nro_unidades;

    public Venta(int codigo, Sucursal sucursal, Vendedor vendedor, Cliente cliente, Producto producto, int nro_unidades) {
        this.codigo = codigo;
        this.sucursal = sucursal;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.producto = producto;
        this.nro_unidades = nro_unidades;
    }

    public int getCodigo() {
        return codigo;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getNro_unidades() {
        return nro_unidades;
    }

    @Override
    public String toString() {
        return "Venta {\n" +
                "    codigo = " + codigo + "\n" +
                "    sucursal {\n" +
                "        codigo = " + sucursal.getCodigo() + "\n" +
                "        nombre =" + sucursal.getNombre() + "\n" +
                "    }\n" +
                "    vendedor {\n" +
                "        codigo = " + vendedor.getCodigo() + "\n" +
                "        nombre =" + vendedor.getNombre() + "\n" +
                "    }\n" +
                "    cliente {\n" +
                "        codigo = " + cliente.getCodigo() + "\n" +
                "        nombre = " + cliente.getNombre() + "\n" +
                "        genero = " + (cliente.getGenero() == null ? "Unknown" : cliente.getGenero()) + "\n" +
                "    }\n" +
                "    producto {\n" +
                "        codigo = " + producto.getCodigo() + "\n" +
                "        nombre = " + producto.getNombre() + "\n" +
                "        tipo = " + producto.getTipo() + "\n" +
                "        marca = " + producto.getMarca() + "\n" +
                "    }\n" +
                "    numero unidades =" + nro_unidades + "\n" +
                "}\n";
    }
}
