package models;

public class VentaDetail {
    /*
    * Esta clase se encarga de representar el detalle de una venta
    * con el fin de mostar de una venta, la sucursan donde se realizo
    * el tipo de producto y el total de unidades,
    * La finalidad de este modelo es mantener la estructura de misVentas que
    * sera agregada en la base de datos MongoDB
    * */
    private final  String nomSucursal;
    private final String tipoProd;
    private final int totalUni;

    public VentaDetail(String nomSucursal, int totalUni) {
        this.nomSucursal = nomSucursal;
        this.tipoProd = "";
        this.totalUni = totalUni;
    }

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
