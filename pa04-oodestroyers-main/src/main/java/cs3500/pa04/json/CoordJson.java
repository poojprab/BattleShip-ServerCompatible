package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * a JSON record to represent a Coord
 *
 * @param x the x value of a Coord
 * @param y the y value of a Coord
 */
public record CoordJson(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y
) {
}
