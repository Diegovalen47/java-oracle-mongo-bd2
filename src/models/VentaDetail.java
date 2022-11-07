package models;

public class VentaDetail {
    private final  String nomSucursal;
    private final String tipoProd;
    private final int totalUni;

    public VentaDetail(String nomSucursal, String tipoProd, int totalUni) {
        this.nomSucursal = nomSucursal;
        this.tipoProd = tipoProd;
        this.totalUni = totalUni;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public String getTipoProd() {
        return tipoProd;
    }

    public int getTotalUni() {
        return totalUni;
    }

}
