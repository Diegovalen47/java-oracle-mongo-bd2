package models;

public class Producto {
    private final int codigo;
    private final String nombre;
    private final String tipo;
    private final String marca;

    public Producto(int codigo, String nombre, String tipo, String marca) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }
}
