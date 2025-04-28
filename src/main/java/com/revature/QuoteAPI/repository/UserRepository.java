package com.revature.QuoteAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.QuoteAPI.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find user by username
    Optional<User> findByUsername(String username);
    
    // Find user by email
    Optional<User> findByEmail(String email);
    
    // Check if user exists by username
    boolean existsByUsername(String username);
    
    // Check if user exists by email
    boolean existsByEmail(String email);
}