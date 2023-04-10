package com.heaven.spring6webmvc.repositories;

import com.heaven.spring6webmvc.entities.Beer;
import com.heaven.spring6webmvc.model.BeerStyle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
    List<Beer> findBeersByBeerNameIsLikeIgnoreCase(String beerName);
    List<Beer> findBeersByBeerStyle(BeerStyle beerStyle);
    List<Beer> findBeersByBeerNameIsLikeIgnoreCaseAndBeerStyle(String beerName,BeerStyle beerStyle);
}
