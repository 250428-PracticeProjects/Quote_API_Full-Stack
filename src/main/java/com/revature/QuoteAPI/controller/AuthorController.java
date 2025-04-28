package com.revature.QuoteAPI.controller;

import com.revature.QuoteAPI.model.Author;
import com.revature.QuoteAPI.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    // Repository or Service will be injected here
    // private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<Page<Author>> getAllAuthors(Pageable pageable) {
        // Return all authors (paginated)
        // return ResponseEntity.ok(authorService.findAll(pageable));
        return ResponseEntity.ok(Page.empty(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        // Find author by ID
        // return authorService.findById(id)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        // Create new author
        // return ResponseEntity.status(HttpStatus.CREATED).body(authorService.save(author));
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        // Update existing author
        // return authorService.update(id, author)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        // Delete author
        // if (authorService.existsById(id)) {
        //     authorService.deleteById(id);
        //     return ResponseEntity.noContent().build();
        // }
        // return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/quotes")
    public ResponseEntity<Page<Quote>> getQuotesByAuthor(@PathVariable Long id, Pageable pageable) {
        // Get quotes by author
        // if (!authorService.existsById(id)) {
        //     return ResponseEntity.notFound().build();
        // }
        // return ResponseEntity.ok(authorService.findQuotesByAuthorId(id, pageable));
        return ResponseEntity.ok(Page.empty(pageable));
    }
}