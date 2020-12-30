package guru.springframework.msscbeerservice.service.inventory.impl;

import guru.springframework.msscbeerservice.service.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.service.inventory.model.BeerInventoryDto;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@ConfigurationProperties("sfg.brewery")
@Service
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

  public static final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
  private final RestTemplate restTemplate;

  private String beerInventoryServiceHost;

  public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
    this.beerInventoryServiceHost = beerInventoryServiceHost;
  }

  public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @Override
  public Integer getOnHandInventory(String beerId) {

    log.debug("Calling Inventory Service");

    ResponseEntity<List<BeerInventoryDto>> responseEntity =
        restTemplate.exchange(
            beerInventoryServiceHost + INVENTORY_PATH,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<BeerInventoryDto>>() {},
            (Object) beerId);

    // sum from inventory list
    Integer onHand =
        Objects.requireNonNull(responseEntity.getBody())
            .stream()
            .mapToInt(BeerInventoryDto::getQuantityOnHand)
            .sum();

    return onHand;
  }
}
