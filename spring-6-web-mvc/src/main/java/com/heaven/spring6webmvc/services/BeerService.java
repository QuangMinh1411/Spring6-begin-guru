package com.heaven.spring6webmvc.services;

import com.heaven.spring6webmvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<BeerDTO> listBeers();
    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    void updateBeerById(UUID beerId, BeerDTO beerDTO);

    void deleteById(UUID id);

    void pathBeerById(UUID beerId, BeerDTO beerDTO);
}
