package mongodbcrud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoException;

public class ConnectionMongo {
    /*
    * Clase para realizar la conexion a la base de datos MongoDB
    * */
    MongoClient client = null;
    public ConnectionMongo() {
    }
    void Connect() throws MongoException{
        String uri = "mongodb://localhost:27017";
        client = MongoClients.create(uri);
    }
}

