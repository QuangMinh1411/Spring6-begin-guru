package com.heaven.spring6webmvc.repositories;

import com.heaven.spring6webmvc.bootstrap.BootstrapData;
import com.heaven.spring6webmvc.entities.Beer;
import com.heaven.spring6webmvc.model.BeerStyle;
import com.heaven.spring6webmvc.services.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerTooLong() {
        assertThrows(ConstraintViolationException.class,()->{
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My Beer My Beer My Beer My Beer My Beer My Beer My Beer My Beer My Beer My Beer ")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("123456789")
                    .price(new BigDecimal("11.99")).build());
            beerRepository.flush();
        });


    }

    @Test
    void testGetBeerListByName(){
        List<Beer> list = beerRepository.findBeersByBeerNameIsLikeIgnoreCase("%IPA%");
        assertThat(list.size()).isEqualTo(336);

    }

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder().beerName("My Beer")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456789")
                .price(new BigDecimal("11.99")).build());
        beerRepository.flush();
        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}