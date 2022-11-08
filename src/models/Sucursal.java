package models;

public class Sucursal {
    /*
    * Esta clase representa a una sucursal
    * con su codigo y nombre
    * */
    private final int codigo;
    private final String nombre;

    public Sucursal(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Sucursal {\n" +
                "    codigo = " + codigo + "\n" +
                "    nombre = " + nombre + "\n" +
                '}';
    }
}
