package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.GameResult;

/**
 * a JSON record to represent the EndGame method
 *
 * @param gameResult the GameResult of the BattleSalvo game
 * @param reason the reason (as a string) for the gameResult of the BattleSalvo game
 */
public record EndGameJson(
    @JsonProperty("result") GameResult gameResult,
    @JsonProperty("reason") String reason
) {
}
