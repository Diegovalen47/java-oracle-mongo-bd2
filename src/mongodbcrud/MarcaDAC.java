package mongodbcrud;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.Marca;
import models.VentaDetail;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;
import oraclecrud.DataAcces.Statistics.MarcaDAO;
import org.bson.Document;

import java.util.ArrayList;

public class MarcaDAC extends ConnectionMongo {
    MongoDatabase database;
    private final String DATA_BASE_NAME = "sales_statistics";
    private final String COLLECTION_NAME = "marcas";
    private final String NOMBRE_MARCA = "nombreMarca";
    private final String MISVENTAS = "misVentas";
    private final String GRANTOTAL = "granTotal";

    private ArrayList<Marca> statisticsIn;
    private final ArrayList<Document> statisticsOut = new ArrayList<>();
    private final MarcaDAO dao = new MarcaDAO();
    public void saveStatistics() throws MongoException, NoDataException {

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

        for(Marca m:statisticsIn){
            statisticsOut.add(getDocument(m));
        }


        if(statisticsOut.size()==0){
            throw new NoDataException("No hay nada Pa");
        }
        collection.insertMany(statisticsOut);

    }

    private Document getDocument(Marca m) {

        String SUCURSAL = "nomsucursal";
        String TIPOPRODUCTO = "TipoProd";
        String TOTALUN = "TotalUni";

        ArrayList<Document> misVentas = new ArrayList<>();
        Document doc1 = new Document();

        doc1.append(NOMBRE_MARCA,m.getNombre());

        for(VentaDetail vd : m.getVentaDetail()){
            Document doc2 = new Document();
            doc2.append(SUCURSAL,vd.getNomSucursal());
            doc2.append(TIPOPRODUCTO,vd.getTipoProd());
            doc2.append(TOTALUN,vd.getTotalUni());
            misVentas.add(doc2);
        }
        doc1.append(MISVENTAS,misVentas);
        doc1.append(GRANTOTAL, m.getGranTotal());

        return doc1;

    }

}
