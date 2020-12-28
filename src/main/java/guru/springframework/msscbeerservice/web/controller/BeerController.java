package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.service.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

  private final BeerService beerService;

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {

    return new ResponseEntity<>(beerService.findById(beerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<BeerDto> saveNewBeer(@Validated @RequestBody BeerDto beerDto) {
    return new ResponseEntity<>(beerService.create(beerDto), HttpStatus.CREATED);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity<BeerDto> updatedBeer(
      @PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDto beerDto) {
    beerService.update(beerId, beerDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
