package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * a JSON record to represent a list of ShipJsons
 *
 * @param fleet the list of ShipJson
 */
public record FleetJson(
    @JsonProperty("fleet") List<ShipJson> fleet
) {
}
