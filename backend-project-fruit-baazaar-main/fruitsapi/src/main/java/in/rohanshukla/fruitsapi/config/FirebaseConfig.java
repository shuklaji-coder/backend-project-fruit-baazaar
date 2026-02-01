package in.rohanshukla.fruitsapi.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options;
                
                // Production: Load from environment variable
                String firebaseCredentials = System.getenv("FIREBASE_CREDENTIALS");
                if (firebaseCredentials != null && !firebaseCredentials.trim().isEmpty()) {
                    log.info("🔥 Initializing Firebase with environment credentials");
                    GoogleCredentials credentials = GoogleCredentials.fromStream(
                        new java.io.ByteArrayInputStream(firebaseCredentials.getBytes())
                    );
                    options = FirebaseOptions.builder()
                        .setCredentials(credentials)
                        .build();
                } 
                // Local: Load from service account JSON file
                else {
                    log.info("🔥 Initializing Firebase with service account file");
                    InputStream serviceAccount = new ClassPathResource("firebase-service-account.json").getInputStream();
                    GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
                    options = FirebaseOptions.builder()
                        .setCredentials(credentials)
                        .build();
                }

                FirebaseApp.initializeApp(options);
                log.info("✅ Firebase application initialized successfully");
            }
            return FirebaseApp.getInstance();
        } catch (IOException e) {
            log.error("❌ Firebase initialization failed", e);
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }
}
