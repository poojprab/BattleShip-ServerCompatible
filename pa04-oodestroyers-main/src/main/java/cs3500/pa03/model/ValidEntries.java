package cs3500.pa03.model;

import java.util.List;

/**
 * Class to validate entries given by the user
 */
public class ValidEntries {

  /**
   * Constructor for ValidEntries
   */
  public ValidEntries() {}

  /**
   * Validates board dimensions
   *
   * @param dimensions given user dimensions
   * @return whether the dimensions are valid
   */
  public boolean isDimensionValid(List<Integer> dimensions) {
    return ((dimensions.get(0) >= 6 && dimensions.get(0) <= 15)
        && (dimensions.get(1) >= 6 && dimensions.get(1) <= 15));
  }

  /**
   * Validates ship number
   *
   * @param ships given number of ships by the user
   * @param min minimum number of ships allowed
   * @return whether the number of ships is valid
   */
  public boolean isShipValid(List<Integer> ships, int min) {
    int sum = 0;
    for (Integer i : ships) {
      if (i > 0) {
        sum += i;
      } else {
        return false;
      }
    }
    return sum <= min;
  }

  /**
   * Validates the user inputted shots
   *
   * @param inputtedShots list of shots inputted by the user
   * @param board the current board
   * @return whether the shots are all valid
   */
  public boolean isShotValid(List<Integer> inputtedShots, Coord[][] board) {
    for (int i = 0; i < inputtedShots.size(); i += 2) {
      if (inputtedShots.get(i + 1) >= board.length
          || inputtedShots.get(i) >= board[0].length
          || inputtedShots.get(i + 1) < 0
          || inputtedShots.get(i) < 0) {
        return false;
      }
    }
    return true;
  }
}
