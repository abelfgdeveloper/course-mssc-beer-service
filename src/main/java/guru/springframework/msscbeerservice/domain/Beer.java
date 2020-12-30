package guru.springframework.msscbeerservice.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Beer {

  @Id
  @Column(length = 36, updatable = false, nullable = false)
  private String id;

  @Version private Integer version;

  @CreationTimestamp
  @Column(updatable = false)
  private Timestamp createdDate;

  @UpdateTimestamp private Timestamp lastModifiedDate;
  private String beerName;
  private String beerStyle;

  @Column(unique = true)
  private String upc;

  private BigDecimal price;
  private Integer minOnHand;
  private Integer quantityToBrew;

  @PrePersist
  private void prePersist() {
    this.id = UUID.randomUUID().toString();
  }
}
