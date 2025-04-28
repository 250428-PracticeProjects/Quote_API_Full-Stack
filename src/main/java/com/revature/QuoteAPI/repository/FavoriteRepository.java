package com.revature.QuoteAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.QuoteAPI.model.Favorite;
import com.revature.QuoteAPI.model.Quote;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    
    // Find all favorites by user ID
    Page<Favorite> findByUserId(Long userId, Pageable pageable);
    
    // Find quotes favorited by a user
    Page<Quote> findQuotesByUserId(Long userId, Pageable pageable);
    
    // Check if a quote is favorited by a user
    boolean existsByUserIdAndQuoteId(Long userId, Long quoteId);
    
    // Delete a favorite by user ID and quote ID
    void deleteByUserIdAndQuoteId(Long userId, Long quoteId);
}