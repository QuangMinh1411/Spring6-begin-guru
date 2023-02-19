package com.heaven.spring6webapp.services;

import com.heaven.spring6webapp.domain.Author;

public interface AuthorService {
    Iterable<Author> findAll();
}
