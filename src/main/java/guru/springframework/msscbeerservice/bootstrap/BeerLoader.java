package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

  public static final String BEER_1_UPC = "0631234200036";
  public static final String BEER_2_UPC = "0631234300019";
  public static final String BEER_3_UPC = "0083783375213";

  private final BeerRepository beerRepository;

  @Override
  public void run(String... args) throws Exception {

    if (beerRepository.count() == 0) {
      loadBeerObjects();
    }
  }

  private void loadBeerObjects() {
    Beer b1 =
        Beer.builder()
            .beerName("Mango Bobs")
            .beerStyle("IPA")
            .minOnHand(12)
            .quantityToBrew(200)
            .price(new BigDecimal("12.95"))
            .upc(BEER_1_UPC)
            .build();

    Beer b2 =
        Beer.builder()
            .beerName("Galaxy Cat")
            .beerStyle("PALE_ALE")
            .minOnHand(12)
            .quantityToBrew(200)
            .price(new BigDecimal("11.95"))
            .upc(BEER_2_UPC)
            .build();

    Beer b3 =
        Beer.builder()
            .beerName("Pinball Porter")
            .beerStyle("PALE_ALE")
            .minOnHand(12)
            .quantityToBrew(200)
            .price(new BigDecimal("10.95"))
            .upc(BEER_3_UPC)
            .build();

    System.out.println("Loaded Beers");
    beerRepository.save(b1);
    beerRepository.save(b2);
    beerRepository.save(b3);
  }
}
