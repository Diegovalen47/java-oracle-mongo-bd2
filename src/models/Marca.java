package models;

import java.util.ArrayList;
import java.util.Collection;

public class Marca {
    /*
    * Esta clase representa a una marca
    * donde se agrupan los detalles
    * de venta agurpados en una marca
    * */
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
    /*
    * Metodo para agregar un detalle de venta
    * incrementando el gran total de venta de las marcas
    * */
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
