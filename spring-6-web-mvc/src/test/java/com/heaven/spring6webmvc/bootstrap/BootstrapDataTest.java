package com.heaven.spring6webmvc.bootstrap;

import com.heaven.spring6webmvc.repositories.BeerRepository;
import com.heaven.spring6webmvc.repositories.CustomerRepository;
import com.heaven.spring6webmvc.services.BeerCsvService;
import com.heaven.spring6webmvc.services.BeerCsvServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BootstrapDataTest {
    @Autowired
    private BeerRepository beerRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private BeerCsvService csvService;
    BootstrapData bootstrapData;
    @BeforeEach
    void setUp() {
        csvService = new BeerCsvServiceImpl();
        bootstrapData = new BootstrapData(beerRepository,customerRepository,csvService);
    }
    @Test
    void Tesrun() throws Exception {
        bootstrapData.run(null);
        assertThat(beerRepository.count()).isEqualTo(3);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}