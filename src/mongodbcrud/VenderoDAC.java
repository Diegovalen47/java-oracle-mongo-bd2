package mongodbcrud;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.Vendedor;
import models.VentaDetail;
import oraclecrud.DataAcces.GlobalException;
import oraclecrud.DataAcces.NoDataException;
import oraclecrud.DataAcces.Statistics.VendedorSDAO;
import org.bson.Document;

import java.util.ArrayList;

public class VenderoDAC extends ConnectionMongo{
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

        for(Vendedor seller:statisticsIn){
            statisticsOut.add(getDocument(seller));
        }


        if(statisticsOut.size()==0){
            throw new NoDataException("No hay nada Pa");
        }
        collection.insertMany(statisticsOut);

    }

    private Document getDocument(Vendedor seller) {

        String SUCURSAL = "nomsucursal";
        String TOTALUN = "TotalUni";

        ArrayList<Document> misVentas = new ArrayList<>();
        Document doc1 = new Document();

        doc1.append(CODIGO_VENDEDOR,seller.getCodigo());
        doc1.append(NOMBRE_VENDEDOR,seller.getNombre());

        for(VentaDetail vd : seller.getMisVentas()){
            Document doc2 = new Document();
            doc2.append(SUCURSAL,vd.getNomSucursal());
            doc2.append(TOTALUN,vd.getTotalUni());
            misVentas.add(doc2);
        }
        doc1.append(MISVENTAS,misVentas);
        doc1.append(GRANTOTAL, seller.getGrantotal());
        return doc1;
    }
}
