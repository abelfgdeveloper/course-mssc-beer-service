package guru.springframework.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BeerDto {

  @Null private UUID id;

  @Null private Integer version;

  @Null private OffsetDateTime createdDate;

  @Null private OffsetDateTime lastModifiedDate;

  @NotBlank private String beerName;

  @NotNull private BeerStyleEnum beerStyle;

  @Positive @NotNull private Long upc;

  @Positive @NotNull private BigDecimal price;
  private Integer quantityOnHand;
}
