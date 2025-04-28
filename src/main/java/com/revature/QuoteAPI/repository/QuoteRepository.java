package com.revature.QuoteAPI.repository;

import com.revature.QuoteAPI.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    
    // Find quotes by author ID
    Page<Quote> findByAuthorId(Long authorId, Pageable pageable);
    
    // Find quotes by category ID
    Page<Quote> findByCategoryId(Long categoryId, Pageable pageable);
    
    // Search quotes by content (case insensitive)
    Page<Quote> findByContentContainingIgnoreCase(String query, Pageable pageable);
    
    // Get a random quote
    @Query(value = "SELECT * FROM quotes ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote findRandomQuote();
}