package com.revature.QuoteAPI.controller;

import com.revature.QuoteAPI.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    // Repository or Service will be injected here
    // private final QuoteService quoteService;

    @GetMapping
    public ResponseEntity<Page<Quote>> getAllQuotes(Pageable pageable) {
        // Return paginated list of quotes
        // return ResponseEntity.ok(quoteService.findAll(pageable));
        return ResponseEntity.ok(Page.empty(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id) {
        // Find quote by ID
        // return quoteService.findById(id)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
        // Create new quote
        // return ResponseEntity.status(HttpStatus.CREATED).body(quoteService.save(quote));
        return ResponseEntity.status(HttpStatus.CREATED).body(quote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long id, @RequestBody Quote quote) {
        // Update existing quote
        // return quoteService.update(id, quote)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.ok(quote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        // Delete quote
        // if (quoteService.existsById(id)) {
        //     quoteService.deleteById(id);
        //     return ResponseEntity.noContent().build();
        // }
        // return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/random")
    public ResponseEntity<Quote> getRandomQuote() {
        // Get random quote
        // return quoteService.findRandom()
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Quote>> searchQuotes(@RequestParam String query, Pageable pageable) {
        // Search quotes by content
        // return ResponseEntity.ok(quoteService.search(query, pageable));
        return ResponseEntity.ok(Page.empty(pageable));
    }
}