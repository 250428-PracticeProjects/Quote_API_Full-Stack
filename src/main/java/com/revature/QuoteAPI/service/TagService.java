package com.revature.QuoteAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.QuoteAPI.model.Quote;
import com.revature.QuoteAPI.model.Tag;

public interface TagService {
    List<Tag> findAll();
    Optional<Tag> findById(Long id);
    Tag save(Tag tag);
    Optional<Tag> update(Long id, Tag tag);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean isUnusedTag(Long id);
    Page<Quote> findQuotesByTagId(Long tagId, Pageable pageable);
}