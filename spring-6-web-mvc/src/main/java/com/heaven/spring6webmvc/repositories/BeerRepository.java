package com.heaven.spring6webmvc.repositories;

import com.heaven.spring6webmvc.entities.Beer;
import com.heaven.spring6webmvc.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findBeersByBeerNameIsLikeIgnoreCase(String beerName, Pageable pageable);
    Page<Beer> findBeersByBeerStyle(BeerStyle beerStyle, Pageable pageable);
    Page<Beer> findBeersByBeerNameIsLikeIgnoreCaseAndBeerStyle(String beerName, BeerStyle beerStyle, Pageable pageable);
}
