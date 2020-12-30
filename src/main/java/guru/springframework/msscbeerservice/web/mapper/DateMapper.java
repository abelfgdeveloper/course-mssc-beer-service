package guru.springframework.msscbeerservice.web.mapper;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Component;

@Component
public class DateMapper {

  public OffsetDateTime asOffsetDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return OffsetDateTime.of(
        timestamp.toLocalDateTime().getYear(),
        timestamp.toLocalDateTime().getMonthValue(),
        timestamp.toLocalDateTime().getDayOfMonth(),
        timestamp.toLocalDateTime().getHour(),
        timestamp.toLocalDateTime().getMinute(),
        timestamp.toLocalDateTime().getSecond(),
        timestamp.toLocalDateTime().getNano(),
        ZoneOffset.UTC);
  }

  public Timestamp asTimestamp(OffsetDateTime offsetDateTime) {
    if (offsetDateTime == null) {
      return null;
    }
    return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
  }
}
