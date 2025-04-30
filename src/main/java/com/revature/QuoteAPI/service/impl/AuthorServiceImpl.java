package com.revature.QuoteAPI.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.QuoteAPI.model.Author;
import com.revature.QuoteAPI.model.Quote;
import com.revature.QuoteAPI.repository.AuthorRepository;
import com.revature.QuoteAPI.repository.QuoteRepository;
import com.revature.QuoteAPI.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final QuoteRepository quoteRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, QuoteRepository quoteRepository) {
        this.authorRepository = authorRepository;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Optional<Author> update(Long id, Author authorDetails) {
        return authorRepository.findById(id).map(existingAuthor -> {
            existingAuthor.setName(authorDetails.getName());
            existingAuthor.setBiography(authorDetails.getBiography());
            existingAuthor.setBirthDate(authorDetails.getBirthDate());
            return authorRepository.save(existingAuthor);
        });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public Page<Quote> findQuotesByAuthorId(Long authorId, Pageable pageable) {
        return quoteRepository.findByAuthorId(authorId, pageable);
    }
}