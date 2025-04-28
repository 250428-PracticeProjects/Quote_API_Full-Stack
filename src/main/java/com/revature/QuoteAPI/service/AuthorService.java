package com.revature.QuoteAPI.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.QuoteAPI.model.Author;
import com.revature.QuoteAPI.model.Quote;

public interface AuthorService {
    Page<Author> findAll(Pageable pageable);
    Optional<Author> findById(Long id);
    Author save(Author author);
    Optional<Author> update(Long id, Author author);
    void deleteById(Long id);
    boolean existsById(Long id);
    Page<Quote> findQuotesByAuthorId(Long authorId, Pageable pageable);
}