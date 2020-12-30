package guru.springframework.msscbeerservice.web.mapper;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerMapper {

  private final DateMapper dateMapper;

  public BeerDto beerToBeerDto(Beer beer) {
    return BeerDto.builder()
        .id(beer.getId())
        .version(beer.getVersion())
        .createdDate(dateMapper.asOffsetDateTime(beer.getCreatedDate()))
        .lastModifiedDate(dateMapper.asOffsetDateTime(beer.getLastModifiedDate()))
        .beerName(beer.getBeerName())
        .beerStyle(BeerStyleEnum.valueOf(beer.getBeerStyle()))
        .upc(beer.getUpc())
        .price(beer.getPrice())
        .build();
  }

  public BeerDto beerToBeerDtoWithInventory(Beer beer) {
    return BeerDto.builder()
        .id(beer.getId())
        .version(beer.getVersion())
        .createdDate(dateMapper.asOffsetDateTime(beer.getCreatedDate()))
        .lastModifiedDate(dateMapper.asOffsetDateTime(beer.getLastModifiedDate()))
        .beerName(beer.getBeerName())
        .beerStyle(BeerStyleEnum.valueOf(beer.getBeerStyle()))
        .upc(beer.getUpc())
        .price(beer.getPrice())
        .build();
  }

  public Beer beerDtoToBeer(BeerDto beer) {
    return Beer.builder()
        .id(beer.getId())
        .version(beer.getVersion())
        .createdDate(dateMapper.asTimestamp(beer.getCreatedDate()))
        .lastModifiedDate(dateMapper.asTimestamp(beer.getLastModifiedDate()))
        .beerName(beer.getBeerName())
        .beerStyle(beer.getBeerStyle().name())
        .upc(beer.getUpc())
        .price(beer.getPrice())
        .build();
  }
}
