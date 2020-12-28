package guru.springframework.msscbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = Shape.STRING)
  @Null
  private OffsetDateTime createdDate;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = Shape.STRING)
  @Null
  private OffsetDateTime lastModifiedDate;

  @NotBlank
  @Size(min = 3, max = 100)
  private String beerName;

  @NotNull private BeerStyleEnum beerStyle;

  @NotNull private String upc;

  @JsonFormat(shape = Shape.STRING)
  @Positive
  @NotNull
  private BigDecimal price;

  private Integer quantityOnHand;
}
