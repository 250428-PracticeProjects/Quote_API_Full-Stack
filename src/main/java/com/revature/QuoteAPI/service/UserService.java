package com.revature.QuoteAPI.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.QuoteAPI.model.Quote;
import com.revature.QuoteAPI.model.User;

public interface UserService {
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    User save(User user);
    Optional<User> update(Long id, User user);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Page<Quote> findFavoriteQuotes(Long userId, Pageable pageable);
    void addFavorite(Long userId, Long quoteId);
    void removeFavorite(Long userId, Long quoteId);
    boolean isFavorite(Long userId, Long quoteId);
}