package in.rohanshukla.fruitsapi.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class FirebaseService {

    public Map<String, Object> verifyGoogleToken(String idToken) {
        try {
            log.info("🔍 Verifying Firebase ID token...");
            
            // Verify the ID token
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            
            // Extract user information
            String email = decodedToken.getEmail();
            String name = decodedToken.getName();
            String picture = decodedToken.getPicture();
            String uid = decodedToken.getUid();
            
            log.info("✅ Token verified successfully for email: {}", email);
            
            // Return user info
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("email", email);
            userInfo.put("name", name);
            userInfo.put("picture", picture);
            userInfo.put("uid", uid);
            
            return userInfo;
            
        } catch (FirebaseAuthException e) {
            log.error("❌ Firebase token verification failed: {}", e.getMessage());
            throw new RuntimeException("Invalid Firebase ID token: " + e.getMessage(), e);
        }
    }
}
