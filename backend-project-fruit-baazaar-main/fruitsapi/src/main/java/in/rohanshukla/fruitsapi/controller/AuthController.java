package in.rohanshukla.fruitsapi.controller;

import in.rohanshukla.fruitsapi.dto.AuthenticationRequest;
import in.rohanshukla.fruitsapi.entity.User;
import in.rohanshukla.fruitsapi.repository.UserRepository;
import in.rohanshukla.fruitsapi.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}
