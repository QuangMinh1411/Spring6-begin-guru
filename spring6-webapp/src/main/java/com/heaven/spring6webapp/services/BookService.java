package com.heaven.spring6webapp.services;

import com.heaven.spring6webapp.domain.Book;
import org.springframework.stereotype.Service;

public interface BookService {
    Iterable<Book> findAll();
}
