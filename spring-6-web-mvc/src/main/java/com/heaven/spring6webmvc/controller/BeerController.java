package com.heaven.spring6webmvc.controller;

import com.heaven.spring6webmvc.model.Beer;
import com.heaven.spring6webmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;
    @PutMapping("/{beerId}")
    public ResponseEntity<?> updateById(@PathVariable("beerId") UUID beerId,@RequestBody Beer beer){
        beerService.updateBeerById(beerId,beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<?> handlePost(@RequestBody Beer beer){
        Beer savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","api/v1/beer/"+savedBeer.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Beer> listBeers(){
        return beerService.listBeer();
    }

    @GetMapping("/{beerId}")
    public Beer getBeerById(@PathVariable("beerId") UUID id){
        log.debug("Get Beer by ID - in controller change");
        return beerService.getBeerById(id);
    }


    @DeleteMapping("/{beerId}")
    public ResponseEntity<?> deleteById(@PathVariable("beerId") UUID id){
        beerService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{beerId}")
    public ResponseEntity<?> updateBeerPathById(@PathVariable("beerId") UUID beerId,@RequestBody Beer beer){
        beerService.pathBeerById(beerId,beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
