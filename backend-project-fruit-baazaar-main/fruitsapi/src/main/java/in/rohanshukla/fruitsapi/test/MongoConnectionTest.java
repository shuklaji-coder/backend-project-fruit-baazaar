package in.rohanshukla.fruitsapi.test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnectionTest {
    public static void main(String[] args) {
        String uri = "mongodb+srv://admin:Rohan%40243@cluster1.qq2uyyx.mongodb.net/fruitdb?retryWrites=true&w=majority";
        
        try {
            System.out.println("Testing MongoDB connection...");
            MongoClient mongoClient = MongoClients.create(uri);
            
            MongoDatabase database = mongoClient.getDatabase("fruitdb");
            System.out.println("✅ Connected to database: " + database.getName());
            
            // Test ping
            database.runCommand(new org.bson.Document("ping", 1));
            System.out.println("✅ MongoDB ping successful!");
            
            mongoClient.close();
            System.out.println("✅ Connection test completed successfully");
            
        } catch (Exception e) {
            System.err.println("❌ MongoDB connection failed:");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
