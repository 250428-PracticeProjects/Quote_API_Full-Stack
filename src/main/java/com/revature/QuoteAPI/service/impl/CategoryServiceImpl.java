package com.revature.QuoteAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.QuoteAPI.model.Category;
import com.revature.QuoteAPI.model.Quote;
import com.revature.QuoteAPI.repository.CategoryRepository;
import com.revature.QuoteAPI.repository.QuoteRepository;
import com.revature.QuoteAPI.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final QuoteRepository quoteRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, QuoteRepository quoteRepository) {
        this.categoryRepository = categoryRepository;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Optional<Category> update(Long id, Category categoryDetails) {
        return categoryRepository.findById(id).map(existingCategory -> {
            existingCategory.setName(categoryDetails.getName());
            existingCategory.setDescription(categoryDetails.getDescription());
            return categoryRepository.save(existingCategory);
        });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public boolean isEmptyCategory(Long id) {
        return categoryRepository.isCategoryEmpty(id);
    }

    @Override
    public Page<Quote> findQuotesByCategoryId(Long categoryId, Pageable pageable) {
        return quoteRepository.findByCategoryId(categoryId, pageable);
    }
}