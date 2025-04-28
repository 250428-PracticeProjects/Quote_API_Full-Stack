package com.revature.QuoteAPI.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.QuoteAPI.model.Quote;

public interface QuoteService {
    Page<Quote> findAll(Pageable pageable);
    Optional<Quote> findById(Long id);
    Quote save(Quote quote);
    Optional<Quote> update(Long id, Quote quote);
    void deleteById(Long id);
    boolean existsById(Long id);
    Optional<Quote> findRandom();
    Page<Quote> search(String query, Pageable pageable);
    Quote addTagToQuote(Long quoteId, Long tagId);
    Quote removeTagFromQuote(Long quoteId, Long tagId);
}