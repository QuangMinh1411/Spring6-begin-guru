package com.heaven.spring6webmvc.services;

import com.heaven.spring6webmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    List<Beer> listBeers();
    Beer getBeerById(UUID id);

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    void deleteById(UUID id);

    void pathBeerById(UUID beerId, Beer beer);
}
