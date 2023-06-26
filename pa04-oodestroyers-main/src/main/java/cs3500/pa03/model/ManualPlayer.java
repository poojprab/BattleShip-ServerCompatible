package cs3500.pa03.model;

import cs3500.pa03.view.UserDisplay;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the real user that plays via the terminal
 */
public class ManualPlayer extends AbstractPlayer {

  /**
   * view object to display the board
   */
  private final UserDisplay userDisplay;
  /**
   * ValidEntries object to validate the input
   */
  private final ValidEntries validate;

  /**
   * Constructor for Manual Player
   *
   * @param userDisplay view object to display output
   * @param random      random variable to randomize ships
   */
  public ManualPlayer(UserDisplay userDisplay, Random random) {
    super(random);
    this.userDisplay = userDisplay;
    this.validate = new ValidEntries();
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {

    this.userDisplay.requestBoard(this.gameBoard.getMyBoard(), this.gameBoard.getOpponentBoard());

    List<Integer> inputtedShots;
    List<Coord> myShots;

    inputtedShots = this.userDisplay.requestShots(this.gameBoard.getMyShips().size());
    while (!validate.isShotValid(inputtedShots, this.gameBoard.getMyBoard())) {
      inputtedShots = this.userDisplay.requestShots(this.gameBoard.getMyShips().size());
    }

    myShots = this.createCoords(inputtedShots);
    this.gameBoard.updateOppBoard(myShots, CoordType.MISS);
    return myShots;
  }

  /**
   * Creates a list of coords from a list of ints
   *
   * @param shots List of ints to be converted
   * @return List of coords representing the user input
   */
  private List<Coord> createCoords(List<Integer> shots) {
    List<Coord> tempShots = new ArrayList<>();
    for (int i = 0; i < shots.size(); i += 2) {
      tempShots.add(new Coord(shots.get(i + 1), shots.get(i), CoordType.MISS));
    }
    return tempShots;
  }

}
