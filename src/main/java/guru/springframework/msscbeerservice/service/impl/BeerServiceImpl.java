package guru.springframework.msscbeerservice.service.impl;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;
import guru.springframework.msscbeerservice.service.BeerService;
import guru.springframework.msscbeerservice.service.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.mapper.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service("beerService")
public class BeerServiceImpl implements BeerService {

  private final BeerRepository beerRepository;
  private final BeerMapper beerMapper;
  private final BeerInventoryService beerInventoryService;

  @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
  @Override
  public BeerPagedList listBeers(
      String beerName,
      BeerStyleEnum beerStyle,
      PageRequest pageRequest,
      Boolean showInventoryOnHand) {

    BeerPagedList beerPagedList;
    Page<Beer> beerPage;

    if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
      // search both
      beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
    } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
      // search beer_service name
      beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
    } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
      // search beer_service style
      beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
    } else {
      beerPage = beerRepository.findAll(pageRequest);
    }

    if (showInventoryOnHand) {
      beerPagedList =
          new BeerPagedList(
              beerPage
                  .getContent()
                  .stream()
                  .map(beerMapper::beerToBeerDtoWithInventory)
                  .collect(Collectors.toList()),
              PageRequest.of(
                  beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
              beerPage.getTotalElements());

      beerPagedList
          .stream()
          .forEach(
              beer -> {
                beer.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
              });

    } else {
      beerPagedList =
          new BeerPagedList(
              beerPage
                  .getContent()
                  .stream()
                  .map(beerMapper::beerToBeerDto)
                  .collect(Collectors.toList()),
              PageRequest.of(
                  beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
              beerPage.getTotalElements());
    }

    return beerPagedList;
  }

  @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
  @Override
  public BeerDto findById(String beerId) {
    return beerMapper.beerToBeerDto(
        beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
  }

  @Override
  public BeerDto create(BeerDto beer) {
    return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beer)));
  }

  @Override
  public BeerDto update(String id, BeerDto beer) {
    Beer beerInDataBase = beerRepository.findById(id).orElseThrow(NotFoundException::new);
    beerInDataBase.setBeerName(beer.getBeerName());
    beerInDataBase.setBeerStyle(beer.getBeerStyle().name());
    beerInDataBase.setPrice(beer.getPrice());
    beerInDataBase.setUpc(beer.getUpc());
    return beerMapper.beerToBeerDto(beerRepository.save(beerInDataBase));
  }

  @Cacheable(cacheNames = "beerUpcCache")
  @Override
  public BeerDto getBeerByUpc(String upc) {
    return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
  }
}
