package com.heaven.spring6webmvc.repositories;

import com.heaven.spring6webmvc.entities.Beer;
import com.heaven.spring6webmvc.model.BeerStyle;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
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