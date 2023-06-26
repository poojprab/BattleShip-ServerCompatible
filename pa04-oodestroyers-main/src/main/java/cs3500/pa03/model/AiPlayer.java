package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the Ai Player
 */
public class AiPlayer extends AbstractPlayer {

  private final List<Coord> alreadyHitCoords;

  /**
   * Constructor for Ai Player
   *
   * @param random Random variable to randomize the board
   */
  public AiPlayer(Random random) {
    super(random);
    alreadyHitCoords = new ArrayList<>();
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {

    List<Coord> myShots = new ArrayList<>();
    int height = this.gameBoard.getMyBoard().length;
    int width = this.gameBoard.getMyBoard()[0].length;
    int guessHeight = this.random.nextInt(height);
    int guessWidth = this.random.nextInt(width);

    int numShotsLeft = Math.min(this.spacesAvailable(), this.gameBoard.getMyShips().size());

    for (int i = 0; i < numShotsLeft; i++) {
      while (this.alreadyHit(guessHeight, guessWidth)) {
        guessHeight = this.random.nextInt(height);
        guessWidth = this.random.nextInt(width);
      }
      Coord hitCoord = new Coord(guessHeight, guessWidth, CoordType.EMPTY);
      myShots.add(hitCoord);
      this.alreadyHitCoords.add(hitCoord);
    }

    if (numShotsLeft == 0) {
      return new ArrayList<>();
    }

    this.gameBoard.updateOppBoard(myShots, CoordType.MISS);

    return myShots;
  }

  private boolean alreadyHit(int guessHeight, int guessWidth) {
    for (Coord c : this.alreadyHitCoords) {
      if (c.getXcoord() == guessHeight && c.getYcoord() == guessWidth) {
        return true;
      }
    }
    return false;
  }

  private int spacesAvailable() {
    int count = 0;
    for (Coord[] c : this.gameBoard.getOpponentBoard()) {
      for (Coord e : c) {
        if (e.getCoordType().equals(CoordType.EMPTY)) {
          count++;
        }
      }
    }
    return count;
  }

}
