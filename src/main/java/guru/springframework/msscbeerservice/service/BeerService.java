package guru.springframework.msscbeerservice.service;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import java.util.UUID;

public interface BeerService {

  BeerDto findById(UUID id);

  BeerDto create(BeerDto beer);

  BeerDto update(UUID id, BeerDto beer);
}
