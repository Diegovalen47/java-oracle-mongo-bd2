package models;

import java.util.ArrayList;
import java.util.Collection;

public class Genero {
    private String genero;
    private ArrayList<VentaDetail> ventaDetail;
    private int granTotal;

    public Genero() {
        this.genero = "";
        this.granTotal = 0;
        this.ventaDetail = new ArrayList<>();
    }


    public String getGenero() {
        return genero;
    }

    public Collection<VentaDetail> getVentaDetail() {
        return ventaDetail;
    }

    public int getGranTotal() {
        return granTotal;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void addVentaDetail( VentaDetail ventaDetail) {
        this.ventaDetail.add(ventaDetail);
        this.granTotal += ventaDetail.getTotalUni();
    }

    @Override
    public String toString() {
        return "Genero{" +
                "nombre='" + genero + '\'' +
                ", ventaDetail=" + ventaDetail +
                ", granTotal=" + granTotal +
                '}';
    }
}
