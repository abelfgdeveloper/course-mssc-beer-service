package guru.springframework.msscbeerservice.service.inventory.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BeerInventoryDto {
  private String id;
  private OffsetDateTime createdDate;
  private OffsetDateTime lastModifiedDate;
  private UUID beerId;
  private Integer quantityOnHand;
}
