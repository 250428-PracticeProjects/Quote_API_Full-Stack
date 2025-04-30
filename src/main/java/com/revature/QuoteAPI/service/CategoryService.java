package com.revature.QuoteAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.QuoteAPI.model.Category;
import com.revature.QuoteAPI.model.Quote;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    Optional<Category> update(Long id, Category category);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean isEmptyCategory(Long id);
    Page<Quote> findQuotesByCategoryId(Long categoryId, Pageable pageable);
}