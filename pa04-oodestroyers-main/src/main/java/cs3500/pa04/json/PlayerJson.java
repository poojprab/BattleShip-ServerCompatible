package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * a JSON record to represent a player and the response to a join method-name call
 *
 * @param name the name of the player
 * @param gameType the GameType of the BattleSalvo game in the form of a string
 */
public record PlayerJson(
    @JsonProperty("name") String name,
    @JsonProperty("game-type") String gameType
) {
}
