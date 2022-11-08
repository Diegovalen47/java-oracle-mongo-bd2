package mongodbcrud;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.Genero;
import models.VentaDetail;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;
import oraclecrud.DataAcces.Statistics.GeneroDAO;
import org.bson.Document;

import java.util.ArrayList;

public class GeneroDAC extends ConnectionMongo {
    MongoDatabase database;
    private final String DATA_BASE_NAME = "sales_statistics";
    private final String COLLECTION_NAME = "genero";
    private final String NOMBRE_GENERO = "genero";
    private final String MIS_VENTAS = "misVentas";
    private final String GRANTOTAL = "granTotal";

    private ArrayList<Genero> statisticsIn;
    private final ArrayList<Document> statisticsOut = new ArrayList<>();
    private final GeneroDAO dao = new GeneroDAO();

    public void saveStatistics() throws MongoException, NoDataException {
        /*
        * este metodo se encarga de guardar las estadisticas
        * agupando por genero en la base de datos de MongoDB
        * */

        try {
            Connect();
        }catch (MongoException me){
            System.err.println("An error occurred while attempting to run a command: " + me);
        }
        database = client.getDatabase(DATA_BASE_NAME);

        // Create collection if doesn't exits
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        collection.drop();

        try{
            statisticsIn = new ArrayList<>(dao.findStatistics());
        }catch (GlobalException | NoDataException ge){
            System.out.println(ge);
        }

        for(Genero genero:statisticsIn){
            statisticsOut.add(getDocument(genero));
        }


        if(statisticsOut.size()==0){
            throw new NoDataException("No hay nada Pa");
        }
        collection.insertMany(statisticsOut);

    }

    private Document getDocument(Genero genero) {
        /*
        * Este metodo se encarga de convertir un objeto de tipo Genero en un documento de tipo Document
        * @param genero: objeto de tipo Genero
        *
        * */
        String SUCURSAL = "nomsucursal";
        String TIPOPRODUCTO = "TipoProd";
        String TOTALUN = "TotalUni";

        ArrayList<Document> misVentas = new ArrayList<>();
        Document doc1 = new Document();

        doc1.append(NOMBRE_GENERO,genero.getGenero());

        for(VentaDetail vd : genero.getVentaDetail()){
            Document doc2 = new Document();
            doc2.append(SUCURSAL,vd.getNomSucursal());
            doc2.append(TIPOPRODUCTO,vd.getTipoProd());
            doc2.append(TOTALUN,vd.getTotalUni());
            misVentas.add(doc2);
        }
        doc1.append(MIS_VENTAS,misVentas);
        doc1.append(GRANTOTAL, genero.getGranTotal());

        return doc1;
    }

    public FindIterable<Document> finAll() throws MongoException, NoDataException {
        /*
        * Metodo encargado de retornar todos los documetos
        * de la coleccion genero en un iterable
        * */
        try{
            Connect();
        }catch (MongoException e){
            System.out.println(e);
        }
        database = client.getDatabase(DATA_BASE_NAME);

        // Create collection if doesn't exits
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        int gendersCount = (int) collection.count();

        if(gendersCount==0){
            throw new NoDataException("No hay nada Pa");
        }

        FindIterable<Document> genders  = collection.find();

        return genders;

    }
}
