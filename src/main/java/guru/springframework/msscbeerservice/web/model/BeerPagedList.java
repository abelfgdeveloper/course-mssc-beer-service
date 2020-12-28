package guru.springframework.msscbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BeerPagedList extends PageImpl<BeerDto> {

  private static final long serialVersionUID = 1L;

  @JsonCreator(mode = Mode.PROPERTIES)
  public BeerPagedList(
      @JsonProperty("content") List<BeerDto> content,
      @JsonProperty("number") int number,
      @JsonProperty("size") int size,
      @JsonProperty("totalElements") Long totalElements,
      @JsonProperty("pageable") JsonNode pageable,
      @JsonProperty("last") int last,
      @JsonProperty("totalPages") int totalPages,
      @JsonProperty("sort") JsonNode sort,
      @JsonProperty("first") boolean first,
      @JsonProperty("numberOfElements") int numberOfElements) {
    super(content, PageRequest.of(number, size), totalElements);
  }

  public BeerPagedList(List<BeerDto> content) {
    super(content);
  }

  public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
    super(content, pageable, total);
  }
}
