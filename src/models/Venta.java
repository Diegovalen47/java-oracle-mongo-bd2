package models;

public class Venta {
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
        return "\nVenta{" +
                "codigo=" + codigo + "\n"+
                "sucursal{" + sucursal.getCodigo() +
                "," + sucursal.getNombre() + "}\n" +
                "vendedor{" + vendedor.getCodigo() +
                "," + vendedor.getNombre() + "}\n" +
                "cliente{" + cliente.getCodigo() +
                "," + cliente.getNombre() + "}\n" +
                "producto{" + producto.getCodigo() +
                "," + producto.getNombre() + "}\n" +
                "nro_unidades=" + nro_unidades +
                "}\n";
    }
}
