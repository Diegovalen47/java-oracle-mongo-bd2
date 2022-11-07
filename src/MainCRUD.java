import com.mongodb.MongoException;
import models.Sucursal;
import mongodbcrud.MarcaCI;
import oraclecrud.DataAcces.*;
import oraclecrud.DataAcces.Statistics.MarcaDAO;

public class MainCRUD {
    public static void main(String[] args) {
        try{
            SucursalDAO sdao = new SucursalDAO();

            for(Sucursal s: sdao.finAllSucursal()){
                System.out.println(s);
            }
        }catch (GlobalException | NoDataException e){
            System.out.println(e);
        }

        try{
            MarcaDAO marcas = new MarcaDAO();
            System.out.println("Nueva consulta");
            System.out.println(marcas.findStatistics());
        }catch (GlobalException | NoDataException e){
            System.out.println(e);
        }

        try{
            MarcaCI tmp = new MarcaCI();
            System.out.println("Nueva consulta");
            tmp.saveStatistics();
        }catch (MongoException e){
            System.out.println(e);
        }catch (NoDataException e){
            System.out.println(e);
        }

    }
}
