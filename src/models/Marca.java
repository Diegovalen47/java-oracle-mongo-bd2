package models;

import java.util.ArrayList;
import java.util.Collection;

public class Marca {
    private final String nombre;
    private final ArrayList<VentaDetail> ventaDetail;
    private final int granTotal;

    public Marca(String nombre, ArrayList<VentaDetail> ventaDetail) {
        this.nombre = nombre;
        this.ventaDetail = ventaDetail;
        granTotal = CalculatedGranTotal();
    }

    private int CalculatedGranTotal() {
        int granTotal = 0;
        for(VentaDetail temp : ventaDetail){
            granTotal += temp.getTotalUni();
        }
        return granTotal;
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

    @Override
    public String toString() {
        return "Marca{" +
                "nombre='" + nombre + '\'' +
                ", ventaDetail=" + ventaDetail +
                ", granTotal=" + granTotal +
                '}';
    }
}
