package com.revature.QuoteAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.QuoteAPI.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByNameIgnoreCase(String name);
    
    boolean existsByNameIgnoreCase(String name);
}