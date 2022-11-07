package models;

import java.util.ArrayList;

public class Vendedor {
    private final int codigo;
    private final String nombre;

    private final ArrayList<VentaDetail> misVentas;

    private final int grantotal;

    public Vendedor(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        misVentas = null;
        grantotal = 0;
    }
    public Vendedor(int codigo, String nombre, ArrayList<VentaDetail> misVentas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.misVentas = misVentas;
        grantotal = calculatedGranTotal();

    }
    private int calculatedGranTotal() {
        int granTotal = 0;
        for(VentaDetail temp : misVentas){
            granTotal += temp.getTotalUni();
        }
        return granTotal;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<VentaDetail> getMisVentas() {
        return misVentas;
    }
    public int getGrantotal() {
        return grantotal;
    }
    @Override
    public String toString() {
        return "Vendedor{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", misVentas=" + misVentas +
                ", grantotal=" + grantotal +
                '}';
    }
}

