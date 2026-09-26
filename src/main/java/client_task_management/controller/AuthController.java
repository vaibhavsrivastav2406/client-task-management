package client_task_management.controller;

import client_task_management.dto.LoginRequest;
import client_task_management.dto.RegisterRequest;
import client_task_management.entity.User;
import client_task_management.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public Map<String, String> register(
            @RequestBody RegisterRequest request) {

        Map<String, String> response = new HashMap<>();

        if (userRepository.existsByEmail(request.getEmail())) {
            response.put("message", "Email already registered");
            return response;
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);

        response.put("message", "Registration successful");
        response.put("token", "demo-token");

        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody LoginRequest request) {

        Map<String, String> response = new HashMap<>();

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            response.put("message", "Invalid email or password");
            return response;
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            response.put("message", "Invalid email or password");
            return response;
        }

        response.put("message", "Login successful");
        response.put("token", "demo-token");

        return response;
    }
}