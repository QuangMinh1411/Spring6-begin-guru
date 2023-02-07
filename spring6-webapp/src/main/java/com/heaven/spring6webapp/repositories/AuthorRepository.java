package com.heaven.spring6webapp.repositories;

import com.heaven.spring6webapp.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
