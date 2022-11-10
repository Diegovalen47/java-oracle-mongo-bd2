package mongodbcrud;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.Vendedor;
import models.VentaDetail;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;
import oraclecrud.DataAcces.Statistics.VendedorSDAO;
import org.bson.Document;

import java.util.ArrayList;

public class VendedorDAC extends ConnectionMongo{
    MongoDatabase database;
    private final String DATA_BASE_NAME = "sales_statistics";
    private final String COLLECTION_NAME = "vendedores";
    private final String CODIGO_VENDEDOR = "codvendedor";
    private final String NOMBRE_VENDEDOR = "nomvend";
    private final String MISVENTAS = "misVentas";
    private final String GRANTOTAL = "granTotal";

    private ArrayList<Vendedor> statisticsIn;
    private final ArrayList<Document> statisticsOut = new ArrayList<>();
    private final VendedorSDAO dao = new VendedorSDAO();
    public void saveStatistics() throws MongoException, NoDataException {
        /*
         * este metodo se encarga de guardar las estadisticas
         * agupando por vendedor en la base de datos de MongoDB
         * */

        try {
            Connect();
        }catch (MongoException me){
            System.err.println("An error occurred while attempting to run a command: " + me);
        }
        database = client.getDatabase(DATA_BASE_NAME);

        // Create collection if doesn't exits
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        // Se borra la collecion cada vez que se vuelvan a ingreaar los datos
        collection.drop();

        try{
            // Obtenemos las estadisticas en un arreglo de vendedores
            statisticsIn = new ArrayList<>(dao.findStatistics());
        }catch (GlobalException | NoDataException ge){
            System.out.println(ge);
        }

        // Se recorre el arreglo de vendedores para crear un arreglo de documentos
        // compatible con MongoDB y sus colecciones
        for(Vendedor seller:statisticsIn){
            statisticsOut.add(getDocument(seller));
        }

        if(statisticsOut.size()==0){
            throw new NoDataException("No hay nada Pa");
        }

        // Se insertan los documentos en la coleccion
        collection.insertMany(statisticsOut);

    }

    private Document getDocument(Vendedor seller) {
        /*
         * Este metodo se encarga de convertir un objeto de tipo Vendedor en un documento de tipo Document
         * con el formato esperado en la collecion de vendedores en MongoDB
         * */
        String SUCURSAL = "nomsucursal";
        String TOTALUN = "TotalUni";

        ArrayList<Document> misVentas = new ArrayList<>();
        Document doc1 = new Document();

        doc1.append(CODIGO_VENDEDOR,seller.getCodigo());
        doc1.append(NOMBRE_VENDEDOR,seller.getNombre());

        for(VentaDetail vd : seller.getMisVentas()) {

            Document doc2 = new Document();

            doc2.append(SUCURSAL,vd.getNomSucursal());
            doc2.append(TOTALUN,vd.getTotalUni());

            misVentas.add(doc2);

        }

        doc1.append(MISVENTAS,misVentas);
        doc1.append(GRANTOTAL, seller.getGrantotal());

        return doc1;
    }

    public FindIterable<Document> finAll() throws MongoException, NoDataException {
        /*
         * Metodo encargado de retornar todos los documetos
         * de la coleccion Vendedores en un iterable
         * */

        try{
            Connect();
        }catch (MongoException e){
            System.out.println(e);
        }
        database = client.getDatabase(DATA_BASE_NAME);

        // Obtenemos la collecion de Vendedores en MongoDB
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        // Se obtiene el tamano de la colleccion
        int vendedoresCount = (int) collection.count();

        if(vendedoresCount==0){
            throw new NoDataException("No hay nada Pa");
        }

        // Obtenemos todos los documentos de la collecion en un iterable
        FindIterable<Document> vendedores  = collection.find();

        return vendedores;

    }
}
