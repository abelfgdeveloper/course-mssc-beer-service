package guru.springframework.msscbeerservice.service.impl;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;
import guru.springframework.msscbeerservice.service.BeerService;
import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.mapper.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("beerService")
public class BeerServiceImpl implements BeerService {

  private final BeerRepository beerRepository;
  private final BeerMapper beerMapper;

  @Override
  public BeerDto findById(UUID id) {
    return beerMapper.beerToBeerDto(
        beerRepository.findById(id).orElseThrow(NotFoundException::new));
  }

  @Override
  public BeerDto create(BeerDto beer) {
    return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beer)));
  }

  @Override
  public BeerDto update(UUID id, BeerDto beer) {
    Beer beerInDataBase = beerRepository.findById(id).orElseThrow(NotFoundException::new);
    beerInDataBase.setBeerName(beer.getBeerName());
    beerInDataBase.setBeerStyle(beer.getBeerStyle().name());
    beerInDataBase.setPrice(beer.getPrice());
    beerInDataBase.setUpc(beer.getUpc());
    return beerMapper.beerToBeerDto(beerRepository.save(beerInDataBase));
  }
}
