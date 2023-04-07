package com.heaven.spring6webmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heaven.spring6webmvc.entities.Beer;
import com.heaven.spring6webmvc.exception.NotFoundException;
import com.heaven.spring6webmvc.mapper.BeerMapper;
import com.heaven.spring6webmvc.model.BeerDTO;
import com.heaven.spring6webmvc.repositories.BeerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.core.Is.is;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BeerControllerIT {
    @Autowired
    BeerController controller;
    @Autowired
    BeerRepository beerRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    BeerMapper beerMapper;
    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void testPathBeerBadName() throws Exception {
        Beer beer = beerRepository.findAll().get(0);
        Map<String,Object> beerMap = new HashMap<>();
        beerMap.put("beerName","New Name New Name New Name New Name New Name New Name New Name New Name New Name");

       MvcResult result = mockMvc.perform(patch(BeerController.BEER_PATH_ID, beer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.length()",is(1)))
               .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void testBeerIdNotFound() {
        assertThrows(NotFoundException.class,()->{
            controller.getBeerById(UUID.randomUUID());
        });

    }
    @Rollback
    @Transactional
    @Test
    void saveNewBeerTest() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New Beer")
                .build();
        ResponseEntity responseEntity = controller.handlePost(beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);
        Beer beer = beerRepository.findById(savedUUID).get();
        assertThat(beer).isNotNull();
    }

    @Test
    void testGetById() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO dto = controller.getBeerById(beer.getId());
        assertThat(dto).isNotNull();
    }

    @Test
    void testListBeer() {
        List<BeerDTO> dtos = controller.listBeers();
        assertThat(dtos.size()).isEqualTo(2413);
    }
    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        beerRepository.deleteAll();
        List<BeerDTO> dtos = controller.listBeers();
        assertThat(dtos.size()).isEqualTo(0);
    }
    @Rollback
    @Transactional
    @Test
    void updateExistingBeer(){
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDto(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        beerDTO.setBeerName("UPDATED");
        ResponseEntity responseEntity = controller.updateById(beer.getId(),beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo("UPDATED");
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class,()->{
           controller.updateById(UUID.randomUUID(),BeerDTO.builder().build());
        });
    }
    @Rollback
    @Transactional
    @Test
    void deleteByIdFound() {
        Beer beer = beerRepository.findAll().get(0);
        ResponseEntity responseEntity = controller.deleteById(beer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(beerRepository.findById(beer.getId()).isEmpty());

    }
    @Test
    void deleteByIdNotFound() {
        assertThrows(NotFoundException.class,()->{
            controller.deleteById(UUID.randomUUID());
        });
    }
}