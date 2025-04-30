package com.revature.QuoteAPI.controller;

import com.revature.QuoteAPI.model.Category;
import com.revature.QuoteAPI.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    // Repository or Service will be injected here
    // private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        // Return all categories
        // return ResponseEntity.ok(categoryService.findAll());
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        // Find category by ID
        // return categoryService.findById(id)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        // Create new category
        // return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        // Update existing category
        // return categoryService.update(id, category)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        // Delete category if empty
        // if (categoryService.isEmptyCategory(id)) {
        //     categoryService.deleteById(id);
        //     return ResponseEntity.noContent().build();
        // } else if (!categoryService.existsById(id)) {
        //     return ResponseEntity.notFound().build();
        // }
        // return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/quotes")
    public ResponseEntity<Page<Quote>> getQuotesByCategory(@PathVariable Long id, Pageable pageable) {
        // Get quotes by category
        // if (!categoryService.existsById(id)) {
        //     return ResponseEntity.notFound().build();
        // }
        // return ResponseEntity.ok(categoryService.findQuotesByCategoryId(id, pageable));
        return ResponseEntity.ok(Page.empty(pageable));
    }
}