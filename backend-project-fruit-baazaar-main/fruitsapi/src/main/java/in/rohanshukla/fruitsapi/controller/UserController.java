package in.rohanshukla.fruitsapi.controller;

import in.rohanshukla.fruitsapi.entity.User;
import in.rohanshukla.fruitsapi.repository.UserRepository;
import in.rohanshukla.fruitsapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ REGISTER ONLY
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);
        return "User Registered Successfully";
    }
}
