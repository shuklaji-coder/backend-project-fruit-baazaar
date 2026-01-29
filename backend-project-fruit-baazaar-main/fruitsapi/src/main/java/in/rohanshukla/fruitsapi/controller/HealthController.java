package in.rohanshukla.fruitsapi.controller;

import com.mongodb.client.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {
    
    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);
    
    @Autowired
    private MongoClient mongoClient;
    
    @GetMapping("/mongodb")
    public ResponseEntity<String> checkMongoHealth() {
        try {
            mongoClient.getDatabase("admin").runCommand(new org.bson.Document("ping", 1));
            logger.info("MongoDB health check: SUCCESS");
            return ResponseEntity.ok("MongoDB connection: HEALTHY");
        } catch (Exception e) {
            logger.error("MongoDB health check: FAILED - {}", e.getMessage());
            return ResponseEntity.status(503).body("MongoDB connection: FAILED - " + e.getMessage());
        }
    }
}
