package models;

import java.util.ArrayList;
import java.util.Collection;

public class Marca {
    private String nombre;
    private ArrayList<VentaDetail> ventaDetail;
    private int granTotal;

    public Marca() {
        this.nombre = "";
        this.granTotal = 0;
        this.ventaDetail = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Collection<VentaDetail> getVentaDetail() {
        return ventaDetail;
    }

    public int getGranTotal() {
        return granTotal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addVentaDetail(VentaDetail ventaDetail) {
        this.ventaDetail.add(ventaDetail);
        this.granTotal += ventaDetail.getTotalUni();
    }

    @Override
    public String toString() {
        return "Marca{" +
                "nombre='" + nombre + '\'' +
                ", ventaDetail=" + ventaDetail +
                ", granTotal=" + granTotal +
                '}';
    }
}
