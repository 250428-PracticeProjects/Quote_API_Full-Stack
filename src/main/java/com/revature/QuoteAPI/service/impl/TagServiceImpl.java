package com.revature.QuoteAPI.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.QuoteAPI.model.Quote;
import com.revature.QuoteAPI.model.Tag;
import com.revature.QuoteAPI.repository.TagRepository;
import com.revature.QuoteAPI.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    @Transactional
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public Optional<Tag> update(Long id, Tag tagDetails) {
        return tagRepository.findById(id).map(existingTag -> {
            existingTag.setName(tagDetails.getName());
            return tagRepository.save(existingTag);
        });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return tagRepository.existsById(id);
    }

    @Override
    public boolean isUnusedTag(Long id) {
        return tagRepository.isTagUnused(id);
    }

    @Override
    public Page<Quote> findQuotesByTagId(Long tagId, Pageable pageable) {
        return tagRepository.findQuotesByTagId(tagId, pageable);
    }
}