package in.rohanshukla.fruitsapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "🍎 Fruit & Vegetable API is Running!");
        response.put("status", "ACTIVE");
        response.put("timestamp", LocalDateTime.now());
        response.put("version", "1.0.0");
        response.put("endpoints", Map.of(
            "health", "/api/health/mongodb",
            "fruits", "/api/fruit/",
            "auth", "/api/auth/",
            "orders", "/api/orders/"
        ));
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/api")
    public ResponseEntity<Map<String, Object>> apiInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("api", "Fruit & Vegetable Backend");
        response.put("status", "Running");
        response.put("database", "MongoDB Atlas Connected");
        
        return ResponseEntity.ok(response);
    }
}
