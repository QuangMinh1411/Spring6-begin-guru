package com.heaven.spring6webmvc.controller;

import com.heaven.spring6webmvc.exception.NotFoundException;
import com.heaven.spring6webmvc.model.BeerDTO;
import com.heaven.spring6webmvc.model.BeerStyle;
import com.heaven.spring6webmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController

public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";
    private final BeerService beerService;




    @PutMapping(value = BEER_PATH_ID)
    public ResponseEntity<?> updateById(@PathVariable("beerId") UUID beerId,@Validated @RequestBody BeerDTO beerDTO){
       if (beerService.updateBeerById(beerId, beerDTO).isEmpty()){
           throw new NotFoundException();
       };
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PostMapping(value = BEER_PATH)
    public ResponseEntity<?> handlePost(@Validated @RequestBody BeerDTO beerDTO){
        BeerDTO savedBeerDTO = beerService.saveNewBeer(beerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/beer/"+ savedBeerDTO.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = BEER_PATH)
    public Page<BeerDTO> listBeers(@RequestParam(value = "beerName",required = false) String beerName,
                                   @RequestParam(value = "beerStyle",required = false) BeerStyle beerStyle,
                                   @RequestParam(required = false) Boolean showInventory,
                                   @RequestParam(required = false) Integer pageNumber,
                                   @RequestParam(required = false) Integer pageSize){

        return beerService.listBeers(beerName,beerStyle,showInventory, pageNumber, pageSize);
    }

    @GetMapping(value = BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable("beerId") UUID id){
        log.debug("Get Beer by ID - in controller change");
        return beerService.getBeerById(id).orElseThrow(NotFoundException::new);
    }


    @DeleteMapping(value = BEER_PATH_ID)
    public ResponseEntity<?> deleteById(@PathVariable("beerId") UUID id){
        if(!beerService.deleteById(id)){
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = BEER_PATH_ID)
    public ResponseEntity<?> updateBeerPathById(@PathVariable("beerId") UUID beerId,@RequestBody BeerDTO beerDTO){
        beerService.pathBeerById(beerId, beerDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
