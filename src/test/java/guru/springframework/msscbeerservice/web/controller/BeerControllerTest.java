package guru.springframework.msscbeerservice.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

  @Autowired MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @Test
  void test_getBeerById_ok() throws Exception {

    mockMvc
        .perform(
            get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void test_saveNewBeer_ok() throws Exception {
    BeerDto request = getValidDto();
    String requestAsJson = objectMapper.writeValueAsString(request);

    mockMvc
        .perform(
            post("/api/v1/beer").contentType(MediaType.APPLICATION_JSON).content(requestAsJson))
        .andExpect(status().isCreated());
  }

  @Test
  void test_updatedBeer_ok() throws Exception {
    BeerDto request = getValidDto();
    String requestAsJson = objectMapper.writeValueAsString(request);

    mockMvc
        .perform(
            put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestAsJson))
        .andExpect(status().isNoContent());
  }

  BeerDto getValidDto() {
    return BeerDto.builder()
        .beerName("Me Beer")
        .beerStyle(BeerStyleEnum.ALE)
        .price(new BigDecimal("2.90"))
        .upc(123123123123L)
        .build();
  }
}
