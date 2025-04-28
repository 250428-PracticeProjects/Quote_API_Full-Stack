package com.revature.QuoteAPI.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.QuoteAPI.model.Favorite;
import com.revature.QuoteAPI.model.Quote;
import com.revature.QuoteAPI.model.User;
import com.revature.QuoteAPI.repository.FavoriteRepository;
import com.revature.QuoteAPI.repository.QuoteRepository;
import com.revature.QuoteAPI.repository.UserRepository;
import com.revature.QuoteAPI.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, 
                          QuoteRepository quoteRepository, 
                          FavoriteRepository favoriteRepository) {
        this.userRepository = userRepository;
        this.quoteRepository = quoteRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        // Ensure createdAt is set
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> update(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            // Update fields but preserve ID, password, and creation date
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setEmail(userDetails.getEmail());
            
            // Only update password if provided
            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                // In a real application, password should be encrypted here
                existingUser.setPassword(userDetails.getPassword());
            }
            
            return userRepository.save(existingUser);
        });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Page<Quote> findFavoriteQuotes(Long userId, Pageable pageable) {
        return favoriteRepository.findQuotesByUserId(userId, pageable);
    }

    @Override
    @Transactional
    public void addFavorite(Long userId, Long quoteId) {
        // Check if already a favorite
        if (favoriteRepository.existsByUserIdAndQuoteId(userId, quoteId)) {
            return; // Already a favorite, nothing to do
        }
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));
        
        Favorite favorite = Favorite.builder()
                .user(user)
                .quote(quote)
                .createdAt(LocalDateTime.now())
                .build();
        
        favoriteRepository.save(favorite);
    }

    @Override
    @Transactional
    public void removeFavorite(Long userId, Long quoteId) {
        favoriteRepository.deleteByUserIdAndQuoteId(userId, quoteId);
    }

    @Override
    public boolean isFavorite(Long userId, Long quoteId) {
        return favoriteRepository.existsByUserIdAndQuoteId(userId, quoteId);
    }
}