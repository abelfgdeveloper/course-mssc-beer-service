package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.service.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BeerController {

  private static final Integer DEFAULT_PAGE_NUMBER = 0;
  private static final Integer DEFAULT_PAGE_SIZE = 25;

  private final BeerService beerService;

  @GetMapping("/beer")
  public ResponseEntity<BeerPagedList> listBeers(
      @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
      @RequestParam(value = "pageSize", required = false) Integer pageSize,
      @RequestParam(value = "beerName", required = false) String beerName,
      @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
      @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

    if (showInventoryOnHand == null) {
      showInventoryOnHand = false;
    }

    if (pageNumber == null || pageNumber < 0) {
      pageNumber = DEFAULT_PAGE_NUMBER;
    }

    if (pageSize == null || pageSize < 1) {
      pageSize = DEFAULT_PAGE_SIZE;
    }

    BeerPagedList beerList =
        beerService.listBeers(
            beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

    return new ResponseEntity<>(beerList, HttpStatus.OK);
  }

  @GetMapping("/beer/{beerId}")
  public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") String beerId) {

    return new ResponseEntity<>(beerService.findById(beerId), HttpStatus.OK);
  }

  @PostMapping("/beer")
  public ResponseEntity<BeerDto> saveNewBeer(@Validated @RequestBody BeerDto beerDto) {
    return new ResponseEntity<>(beerService.create(beerDto), HttpStatus.CREATED);
  }

  @PutMapping("/beer/{beerId}")
  public ResponseEntity<BeerDto> updatedBeer(
      @PathVariable("beerId") String beerId, @Validated @RequestBody BeerDto beerDto) {
    beerService.update(beerId, beerDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/berrUpc/{upc}")
  public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc) {

    return new ResponseEntity<>(beerService.getBeerByUpc(upc), HttpStatus.OK);
  }
}
