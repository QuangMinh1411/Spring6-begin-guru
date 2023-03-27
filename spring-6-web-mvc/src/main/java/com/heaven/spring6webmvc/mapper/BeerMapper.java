package com.heaven.spring6webmvc.mapper;

import com.heaven.spring6webmvc.entities.Beer;
import com.heaven.spring6webmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDTO dto);
    BeerDTO beerToBeerDto(Beer beer);
}
