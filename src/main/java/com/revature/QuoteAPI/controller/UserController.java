package com.revature.QuoteAPI.controller;

import com.revature.QuoteAPI.model.Quote;
import com.revature.QuoteAPI.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // Find user by ID
        // return userService.findById(id)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        // Update existing user
        // return userService.update(id, user)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/favorites")
    public ResponseEntity<Page<Quote>> getFavoriteQuotes(@PathVariable Long id, Pageable pageable) {
        // Get user's favorite quotes
        // if (!userService.existsById(id)) {
        //     return ResponseEntity.notFound().build();
        // }
        // return ResponseEntity.ok(userService.findFavoriteQuotes(id, pageable));
        return ResponseEntity.ok(Page.empty(pageable));
    }

    @PostMapping("/{id}/favorites/{quoteId}")
    public ResponseEntity<Void> addFavoriteQuote(@PathVariable Long id, @PathVariable Long quoteId) {
        // Add quote to favorites
        // try {
        //     userService.addFavorite(id, quoteId);
        //     return ResponseEntity.status(HttpStatus.CREATED).build();
        // } catch (Exception e) {
        //     return ResponseEntity.notFound().build();
        // }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/favorites/{quoteId}")
    public ResponseEntity<Void> removeFavoriteQuote(@PathVariable Long id, @PathVariable Long quoteId) {
        // Remove quote from favorites
        // try {
        //     userService.removeFavorite(id, quoteId);
        //     return ResponseEntity.noContent().build();
        // } catch (Exception e) {
        //     return ResponseEntity.notFound().build();
        // }
        return ResponseEntity.noContent().build();
    }
}