package com.voiceassistant.backend.controller;

package com.voiceassistant.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    // Simple in-memory user store for demo purposes
    private Map<String, String> users = new java.util.concurrent.ConcurrentHashMap<>();

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Missing fields");
        }
        if (users.containsKey(username)) {
            return ResponseEntity.status(409).body("User already exists");
        }
        users.put(username, password);
        return ResponseEntity.ok().body("Signup successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Missing fields");
        }
        if (!users.containsKey(username) || !users.get(username).equals(password)) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        // For demo, return a fake token
        return ResponseEntity.ok().body(Map.of("token", "fake-jwt-token"));
    }
}
