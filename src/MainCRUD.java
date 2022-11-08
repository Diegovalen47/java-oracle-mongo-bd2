import oraclecrud.DataAcces.*;
import oraclecrud.DataAcces.Statistics.MarcaDAO;

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
            MarcaDAO tmp = new MarcaDAO();
            System.out.println(tmp.findStatistics());
        }catch (GlobalException e){
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
