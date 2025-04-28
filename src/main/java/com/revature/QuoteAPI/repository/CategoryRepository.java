package com.revature.QuoteAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.QuoteAPI.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    // Find category by name
    Category findByNameIgnoreCase(String name);
    
    // Check if a category is empty (has no quotes)
    @Query("SELECT COUNT(q) = 0 FROM Quote q WHERE q.category.id = :categoryId")
    boolean isCategoryEmpty(@Param("categoryId") Long categoryId);
}