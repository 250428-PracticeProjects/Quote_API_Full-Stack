package com.revature.QuoteAPI.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.QuoteAPI.model.Quote;
import com.revature.QuoteAPI.model.Tag;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    // Repository or Service will be injected here
    // private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        // Return all tags
        // return ResponseEntity.ok(tagService.findAll());
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        // Find tag by ID
        // return tagService.findById(id)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        // Create new tag
        // return ResponseEntity.status(HttpStatus.CREATED).body(tagService.save(tag));
        return ResponseEntity.status(HttpStatus.CREATED).body(tag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        // Update existing tag
        // return tagService.update(id, tag)
        //     .map(ResponseEntity::ok)
        //     .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.ok(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        // Delete unused tag
        // if (tagService.isUnusedTag(id)) {
        //     tagService.deleteById(id);
        //     return ResponseEntity.noContent().build();
        // } else if (!tagService.existsById(id)) {
        //     return ResponseEntity.notFound().build();
        // }
        // return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/quotes")
    public ResponseEntity<Page<Quote>> getQuotesByTag(@PathVariable Long id, Pageable pageable) {
        // Get quotes by tag
        // if (!tagService.existsById(id)) {
        //     return ResponseEntity.notFound().build();
        // }
        // return ResponseEntity.ok(tagService.findQuotesByTagId(id, pageable));
        return ResponseEntity.ok(Page.empty(pageable));
    }
}