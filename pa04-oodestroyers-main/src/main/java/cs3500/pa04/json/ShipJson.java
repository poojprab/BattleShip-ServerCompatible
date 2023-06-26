package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * a JSON record to represent a Ship
 *
 * @param coord the leading coordinate of the Ship (the first coord of the Ship's position field
 * @param length the length of the Ship
 * @param direction the direction of the Ship's placement
 */
public record ShipJson(
    @JsonProperty("coord") CoordJson coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction
) {
}
