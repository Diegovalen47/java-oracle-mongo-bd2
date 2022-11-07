import com.mongodb.MongoException;
import mongodbcrud.GeneroDAC;
import mongodbcrud.MarcaDAC;
import mongodbcrud.VendedorDAC;
import oraclecrud.DataAcces.*;
import oraclecrud.DataAcces.Statistics.GeneroDAO;

public class MainCRUD {
    public static void main(String[] args) {
//        try{
//            SucursalDAO sdao = new SucursalDAO();
//
//            for(Sucursal s: sdao.finAllSucursal()){
//                System.out.println(s);
//            }
//        }catch (GlobalException | NoDataException e){
//            System.out.println(e);
//        }
//
//        try{
//            VendedorSDAO dao = new VendedorSDAO();
//            System.out.println("Nueva consulta Genero DAO");
//            System.out.println(dao.findStatistics());
//            System.out.println("-----------------------------------------");
//        }catch (GlobalException | NoDataException e){
//            System.out.println(e);
//        }
//
        try{

            MarcaDAC tmp = new MarcaDAC();
            tmp.saveStatistics();

            VendedorDAC tmp2 = new VendedorDAC();
            tmp2.saveStatistics();

            GeneroDAC tmp3 = new GeneroDAC();
            tmp3.saveStatistics();

        }catch (MongoException e){
            System.out.println(e);
        }catch (NoDataException e){
            System.out.println(e);
        }
//        try{
//            GeneroDAC tmp = new GeneroDAC();
//            System.out.println("Nueva consulta Mongo");
//            tmp.finAll();
//        }catch (MongoException e){
//            System.out.println(e);
//        }catch (NoDataException e){
//            System.out.println(e);
//        }

    }
}
