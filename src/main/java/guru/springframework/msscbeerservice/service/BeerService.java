package guru.springframework.msscbeerservice.service;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

public interface BeerService {

  BeerPagedList listBeers(
      String beerName,
      BeerStyleEnum beerStyle,
      PageRequest pageRequest,
      Boolean showInventoryOnHand);

  BeerDto findById(String id);

  BeerDto create(BeerDto beer);

  BeerDto update(String id, BeerDto beer);

  BeerDto getBeerByUpc(String upc);
}
