package mongodbcrud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoException;

public class ConnectionMongo {
    MongoClient client = null;
    public ConnectionMongo() {
    }
    void Connect() throws MongoException{
        String uri = "mongodb://localhost:27017";
        client = MongoClients.create(uri);
    }
}

