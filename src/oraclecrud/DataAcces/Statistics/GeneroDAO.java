package oraclecrud.DataAcces.Statistics;

import models.Genero;
import models.VentaDetail;
import oraclecrud.DataAcces.ConnectionOracle;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GeneroDAO extends ConnectionOracle {

    private static final String FIND_STATISTICS_GENERO = "select c.genero, s.nombre, p.tipo, sum(nro_unidades) as TotalUni from cliente c join venta v on c.codigo = v.cliente join producto p on p.codigo = v.producto join sucursal s on s.codigo = v.sucursal group by c.genero, s.nombre, p.tipo order by c.genero";
    ArrayList<Genero> collectionGender = new ArrayList<>();

    public ArrayList<Genero> findStatistics() throws GlobalException,NoDataException{

        try{
            Connect();
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }

        ResultSet result = null;
        Statement query;
        VentaDetail tmpVentaDetail;
        Genero tmpGender;
        String generoTxt;

        try{
            query = conn.createStatement();
            result = query.executeQuery(FIND_STATISTICS_GENERO);
            while (result.next()){
                generoTxt  = result.getString("genero") == null ? "Unknown" : result.getString("genero");
                tmpGender = searchGender(generoTxt);
                tmpVentaDetail = new VentaDetail(
                        result.getString("nombre"),
                        result.getString("tipo"),
                        result.getInt("TotalUni")
                );
                tmpGender.addVentaDetail(tmpVentaDetail);
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new GlobalException("Sentencia SQL invalida");
        } finally {
            try {
                if(result!= null){
                    result.close();
                }
                Disconnect();
            }catch (SQLException e){
                throw new GlobalException("Estados invalidos");
            }
        }
        if(collectionGender.size()==0){
            throw new NoDataException("No hay datos");
        }
        return collectionGender;
    }

    private Genero searchGender(String genderTxt){
        Genero tmpGender = new Genero();
        for (Genero g: collectionGender){
            if(g.getGenero().equals(genderTxt)){
                return g;
            }
        }
        tmpGender.setGenero(genderTxt);
        collectionGender.add(tmpGender);
        return tmpGender;
    }

}
