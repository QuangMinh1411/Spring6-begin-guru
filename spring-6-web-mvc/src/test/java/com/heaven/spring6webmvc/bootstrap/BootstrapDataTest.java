package com.heaven.spring6webmvc.bootstrap;

import com.heaven.spring6webmvc.repositories.BeerRepository;
import com.heaven.spring6webmvc.repositories.CustomerRepository;
import com.heaven.spring6webmvc.services.BeerCsvService;
import com.heaven.spring6webmvc.services.BeerCsvServiceImpl;
import com.heaven.spring6webmvc.services.BeerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(BeerCsvServiceImpl.class)
class BootstrapDataTest {
    @Autowired
    private BeerRepository beerRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BeerCsvService csvService;
    BootstrapData bootstrapData;
    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(beerRepository,customerRepository,csvService);
    }
    @Test
    void Tesrun() throws Exception {
        bootstrapData.run(null);
        assertThat(beerRepository.count()).isEqualTo(2413);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}