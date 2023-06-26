package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * a JSON record to represent a list of CoordJsons
 *
 * @param coordinates the list of CoordJson
 */
public record CoordinatesJson(
    @JsonProperty("coordinates") List<CoordJson> coordinates
) {
}
