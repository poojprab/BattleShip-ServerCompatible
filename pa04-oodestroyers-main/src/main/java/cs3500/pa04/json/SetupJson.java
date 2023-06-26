package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.ShipType;
import java.util.LinkedHashMap;

/**
 * a JSON record to represent the setup method
 *
 * @param width the width of the BattleSalvo game board
 * @param height the height of the BattleSalvo game board
 * @param fleetSpec the fleet specifications for the ships in the BattleSalvo game
 */
public record SetupJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") LinkedHashMap<ShipType, Integer> fleetSpec
){
}

