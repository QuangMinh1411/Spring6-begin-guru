package com.heaven.spring6webapp.repositories;

import com.heaven.spring6webapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
