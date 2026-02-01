package in.rohanshukla.fruitsapi.controller;

import in.rohanshukla.fruitsapi.dto.AuthenticationRequest;
import in.rohanshukla.fruitsapi.dto.GoogleAuthRequest;
import in.rohanshukla.fruitsapi.entity.User;
import in.rohanshukla.fruitsapi.repository.UserRepository;
import in.rohanshukla.fruitsapi.security.JwtUtil;
import in.rohanshukla.fruitsapi.service.FirebaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FirebaseService firebaseService;

    // ✅ FINAL LOGIN (FIXED 🔥)
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody AuthenticationRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("email", user.getEmail());

        response.put("role", user.getRole());


        user.setPassword(null);
        response.put("user", user);

        return response;
    }

    // ✅ SIGNUP
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
        return "User registered successfully";
    }

    // 🔥 GOOGLE LOGIN
    @PostMapping("/verify-google-token")
    public Map<String, Object> verifyGoogleToken(@RequestBody GoogleAuthRequest request) {
        try {
            log.info("🔥 Processing Google token verification request");
            
            // Verify Firebase token and extract user info
            Map<String, Object> firebaseUser = firebaseService.verifyGoogleToken(request.getIdToken());
            String email = (String) firebaseUser.get("email");
            String name = (String) firebaseUser.get("name");
            String picture = (String) firebaseUser.get("picture");
            String uid = (String) firebaseUser.get("uid");
            
            log.info("📧 Verified user email: {}", email);
            
            // Check if user exists
            User user = userRepository.findByEmail(email)
                    .orElse(null);
            
            if (user == null) {
                // Create new user
                log.info("👤 Creating new Google user for email: {}", email);
                user = new User();
                user.setEmail(email);
                user.setName(name);
                user.setProvider("GOOGLE");
                user.setAvatar(picture);
                user.setFirebaseUid(uid);
                user.setRole("USER");
                // No password for Google users
                user.setPassword(null);
                
                user = userRepository.save(user);
                log.info("✅ New Google user created successfully");
            } else {
                // Update existing user with Google info if needed
                boolean updated = false;
                if (user.getProvider() == null || !user.getProvider().equals("GOOGLE")) {
                    user.setProvider("GOOGLE");
                    updated = true;
                }
                if (user.getAvatar() == null || user.getAvatar().isEmpty()) {
                    user.setAvatar(picture);
                    updated = true;
                }
                if (user.getFirebaseUid() == null || user.getFirebaseUid().isEmpty()) {
                    user.setFirebaseUid(uid);
                    updated = true;
                }
                if (updated) {
                    user = userRepository.save(user);
                    log.info("🔄 Updated existing user with Google info");
                }
            }
            
            // Generate JWT token
            String token = jwtUtil.generateToken(user.getEmail());
            
            // Prepare response
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);
            
            log.info("🎉 Google login successful for: {}", email);
            return response;
            
        } catch (Exception e) {
            log.error("❌ Google token verification failed: {}", e.getMessage());
            throw new RuntimeException("Google token verification failed: " + e.getMessage(), e);
        }
    }
}
