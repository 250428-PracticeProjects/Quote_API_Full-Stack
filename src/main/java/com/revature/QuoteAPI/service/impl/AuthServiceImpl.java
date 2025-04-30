package com.revature.QuoteAPI.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.QuoteAPI.dto.AuthRequest;
import com.revature.QuoteAPI.model.User;
import com.revature.QuoteAPI.repository.UserRepository;
import com.revature.QuoteAPI.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User register(User user) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        // In a real application, password would be encrypted here using BCrypt
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Set creation date
        user.setCreatedAt(LocalDateTime.now());
        
        return userRepository.save(user);
    }

    @Override
    public String login(AuthRequest authRequest) {
        // Find user by username
        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        
        // In a real application, password would be verified using BCrypt
        // if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
        //     throw new RuntimeException("Invalid username or password");
        // }
        
        // Simple password check (for demonstration only - NOT secure)
        if (!user.getPassword().equals(authRequest.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        
        // In a real application, a JWT token would be generated here
        // return jwtUtil.generateToken(user.getUsername());
        
        // For demonstration purposes only
        return "dummy-jwt-token-" + user.getId() + "-" + System.currentTimeMillis();
    }

    @Override
    public boolean validateToken(String token) {
        // In a real application, the JWT token would be validated here
        // return jwtUtil.validateToken(token);
        
        // For demonstration purposes only
        return token != null && token.startsWith("dummy-jwt-token-");
    }
}