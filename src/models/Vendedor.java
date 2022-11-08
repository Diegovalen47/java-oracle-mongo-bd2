package models;

import java.util.ArrayList;

public class Vendedor {
    private int codigo;
    private String nombre;

    private ArrayList<VentaDetail> misVentas;

    private int granTotal;

    public Vendedor() {
        this.misVentas = new ArrayList<>();
        this.granTotal = 0;
    }

    public Vendedor(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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
        return granTotal;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addVentaDetail(VentaDetail ventaDetail) {
        this.misVentas.add(ventaDetail);
        this.granTotal += ventaDetail.getTotalUni();
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

