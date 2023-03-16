package com.heaven.spring6webmvc.services;

import com.heaven.spring6webmvc.model.Beer;

import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);
}
