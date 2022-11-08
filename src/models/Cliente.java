package models;

public class Cliente {
    /*
    * Esta clase representa a un cliente
    * con su nombre y su código
    * codigo: int  - Código del cliente
    * nombre: String - Nombre del cliente
    * genero: String - Genero del cliente
    *
    * */
    private final int codigo;
    private final String nombre;
    private final String genero;

    public Cliente(int codigo, String nombre, String genero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Cliente {\n" +
                "    codigo = " + codigo + "\n" +
                "    nombre = " + nombre + '\n' +
                "    genero = " + genero + '\n' +
                '}';
    }
}
