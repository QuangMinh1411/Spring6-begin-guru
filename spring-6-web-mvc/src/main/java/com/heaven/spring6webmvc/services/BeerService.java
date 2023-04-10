package com.heaven.spring6webmvc.services;

import com.heaven.spring6webmvc.model.BeerDTO;
import com.heaven.spring6webmvc.model.BeerStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle,Boolean showInventory);
    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDTO);

    Boolean deleteById(UUID id);

    Optional<BeerDTO> pathBeerById(UUID beerId, BeerDTO beerDTO);
}
