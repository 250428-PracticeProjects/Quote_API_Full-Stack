package com.revature.QuoteAPI.controller;

import com.revature.QuoteAPI.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // Register new user
        // return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        // Authenticate user and return JWT token
        // try {
        //     String token = authService.login(loginRequest);
        //     return ResponseEntity.ok(token);
        // } catch (Exception e) {
        //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        // }
        return ResponseEntity.ok("JWT token would be returned here");
    }
}