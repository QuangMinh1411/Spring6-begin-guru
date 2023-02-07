package com.heaven.spring6webapp.repositories;

import com.heaven.spring6webapp.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}