package com.revature.QuoteAPI.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.QuoteAPI.model.Quote;
import com.revature.QuoteAPI.model.Tag;
import com.revature.QuoteAPI.repository.QuoteRepository;
import com.revature.QuoteAPI.repository.TagRepository;
import com.revature.QuoteAPI.service.QuoteService;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final TagRepository tagRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository, TagRepository tagRepository) {
        this.quoteRepository = quoteRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Quote> findAll(Pageable pageable) {
        return quoteRepository.findAll(pageable);
    }

    @Override
    public Optional<Quote> findById(Long id) {
        return quoteRepository.findById(id);
    }

    @Override
    @Transactional
    public Quote save(Quote quote) {
        // Ensure createdAt is set
        if (quote.getCreatedAt() == null) {
            quote.setCreatedAt(LocalDateTime.now());
        }
        return quoteRepository.save(quote);
    }

    @Override
    @Transactional
    public Optional<Quote> update(Long id, Quote quoteDetails) {
        return quoteRepository.findById(id).map(existingQuote -> {
            // Update fields but preserve ID and creation date
            existingQuote.setContent(quoteDetails.getContent());
            existingQuote.setAuthor(quoteDetails.getAuthor());
            existingQuote.setCategory(quoteDetails.getCategory());
            
            // Update tags if provided
            if (quoteDetails.getTags() != null) {
                existingQuote.setTags(quoteDetails.getTags());
            }
            
            return quoteRepository.save(existingQuote);
        });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        quoteRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return quoteRepository.existsById(id);
    }

    @Override
    public Optional<Quote> findRandom() {
        Quote randomQuote = quoteRepository.findRandomQuote();
        return Optional.ofNullable(randomQuote);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Quote> search(String query, Pageable pageable) {
        return quoteRepository.findByContentContainingIgnoreCase(query, pageable);
    }

    @Override
    @Transactional
    public Quote addTagToQuote(Long quoteId, Long tagId) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));
        
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        
        Set<Tag> tags = quote.getTags();
        if (tags == null) {
            tags = new HashSet<>();
            quote.setTags(tags);
        }
        
        tags.add(tag);
        return quoteRepository.save(quote);
    }

    @Override
    @Transactional
    public Quote removeTagFromQuote(Long quoteId, Long tagId) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));
        
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        
        Set<Tag> tags = quote.getTags();
        if (tags != null) {
            tags.remove(tag);
        }
        
        return quoteRepository.save(quote);
    }
}