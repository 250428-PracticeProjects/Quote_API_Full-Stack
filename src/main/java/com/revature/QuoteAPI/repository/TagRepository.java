package com.revature.QuoteAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.QuoteAPI.model.Quote;
import com.revature.QuoteAPI.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    
    // Find tag by name
    Tag findByNameIgnoreCase(String name);
    
    // Check if a tag exists by name
    boolean existsByNameIgnoreCase(String name);
    
    // Find quotes by tag ID
    @Query("SELECT q FROM Quote q JOIN q.tags t WHERE t.id = :tagId")
    Page<Quote> findQuotesByTagId(@Param("tagId") Long tagId, Pageable pageable);
    
    // Check if a tag is unused (has no quotes)
    @Query("SELECT COUNT(q) = 0 FROM Quote q JOIN q.tags t WHERE t.id = :tagId")
    boolean isTagUnused(@Param("tagId") Long tagId);
}