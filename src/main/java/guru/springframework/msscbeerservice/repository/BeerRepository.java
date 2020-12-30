package guru.springframework.msscbeerservice.repository;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, String> {

  Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

  Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);

  Page<Beer> findAllByBeerNameAndBeerStyle(
      String beerName, BeerStyleEnum beerStyle, Pageable pageable);

  Beer findByUpc(String upc);
}
