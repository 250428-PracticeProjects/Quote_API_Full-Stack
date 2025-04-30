package com.revature.QuoteAPI.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.QuoteAPI.dto.QuoteDTO;
import com.revature.QuoteAPI.model.Author;
import com.revature.QuoteAPI.model.Category;
import com.revature.QuoteAPI.model.Quote;
import com.revature.QuoteAPI.model.Tag;
import com.revature.QuoteAPI.service.QuoteService;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public ResponseEntity<Page<QuoteDTO>> getAllQuotes(Pageable pageable) {
        Page<Quote> quotes = quoteService.findAll(pageable);
        Page<QuoteDTO> dtoPage = new PageImpl<>(
            quotes.getContent().stream().map(this::toDTO).collect(Collectors.toList()),
            pageable,
            quotes.getTotalElements()
        );
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteDTO> getQuoteById(@PathVariable Long id) {
        return quoteService.findById(id)
            .map(this::toDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<QuoteDTO> createQuote(@RequestBody QuoteDTO dto) {
        Quote saved = quoteService.save(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuoteDTO> updateQuote(
        @PathVariable Long id,
        @RequestBody QuoteDTO dto
    ) {
        return quoteService.update(id, toEntity(dto))
            .map(this::toDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        if (!quoteService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        quoteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/random")
    public ResponseEntity<QuoteDTO> getRandomQuote() {
        return quoteService.findRandom()
            .map(this::toDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Page<QuoteDTO>> searchQuotes(
        @RequestParam String query,
        Pageable pageable
    ) {
        Page<Quote> found = quoteService.search(query, pageable);
        Page<QuoteDTO> dtoPage = new PageImpl<>(
            found.getContent().stream().map(this::toDTO).collect(Collectors.toList()),
            pageable,
            found.getTotalElements()
        );
        return ResponseEntity.ok(dtoPage);
    }

    // --- Helpers ---
    private QuoteDTO toDTO(Quote q) {
        QuoteDTO dto = new QuoteDTO();
        dto.setId(q.getId());
        dto.setContent(q.getContent());
        dto.setCreatedAt(q.getCreatedAt());
        if (q.getAuthor() != null) {
            dto.setAuthor(new QuoteDTO.AuthorDTO(
                q.getAuthor().getId(),
                q.getAuthor().getName()
            ));
        }
        if (q.getCategory() != null) {
            dto.setCategory(new QuoteDTO.CategoryDTO(
                q.getCategory().getId(),
                q.getCategory().getName()
            ));
        }
        if (q.getTags() != null) {
            Set<QuoteDTO.TagDTO> tags = q.getTags().stream()
                .map(t -> new QuoteDTO.TagDTO(t.getId(), t.getName()))
                .collect(Collectors.toSet());
            dto.setTags(tags);
        }
        return dto;
    }

    private Quote toEntity(QuoteDTO dto) {
        Quote q = new Quote();
        q.setContent(dto.getContent());
        if (dto.getAuthor() != null) {
            Author a = new Author();
            a.setId(dto.getAuthor().getId());
            q.setAuthor(a);
        }
        if (dto.getCategory() != null) {
            Category c = new Category();
            c.setId(dto.getCategory().getId());
            q.setCategory(c);
        }
        if (dto.getTags() != null) {
            Set<Tag> tags = dto.getTags().stream()
                .map(tdto -> {
                    Tag t = new Tag();
                    t.setId(tdto.getId());
                    return t;
                })
                .collect(Collectors.toSet());
            q.setTags(tags);
        }
        return q;
    }
}