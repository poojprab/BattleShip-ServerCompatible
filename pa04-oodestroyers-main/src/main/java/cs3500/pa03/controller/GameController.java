package cs3500.pa03.controller;

import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.model.ValidEntries;
import cs3500.pa03.view.PromptType;
import cs3500.pa03.view.UserDisplay;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * GameController class handles the play of the game
 */
public class GameController implements Controller {

  /**
   * represents one player in the game
   */
  private final Player player1;

  /**
   * represents one player in the game
   */
  private final Player player2;

  /**
   * A view object to be called when certain output needs to be printed
   */
  private final UserDisplay userDisplay;

  /**
   * A validEntries object to be called when input needs to be validated
   */
  private final ValidEntries validate;

  /**
   * List of ships that represents the number of shots left from player 1
   */
  private List<Ship> userShotsLeft;
  /**
   * List of ships that represent the number of shots left from player 2
   */
  private List<Ship> oppShotsLeft;

  /**
   * constructor for the GameController class
   *
   * @param player1 the first player in the game
   * @param player2 the second player in the game
   * @param userDisplay the UserDisplay to display and take in input
   */
  public GameController(Player player1, Player player2, UserDisplay userDisplay) {
    this.player1 = player1;
    this.player2 = player2;
    this.userDisplay = userDisplay;
    this.validate = new ValidEntries();
  }

  /**
   * runs the main program
   *
   */
  public void run() {
    this.controllerSetup();
    //gameplay
    while (this.oppShotsLeft.size() != 0 && this.userShotsLeft.size() != 0) {
      this.player1.successfulHits(this.player2.reportDamage(player1.takeShots()));
      this.player2.successfulHits(this.player1.reportDamage(player2.takeShots()));
    }

    if (this.oppShotsLeft.size() == 0 && this.userShotsLeft.size() == 0) {
      this.userDisplay.gameOver("You " + GameResult.TIE);
    }
    if (this.userShotsLeft.size() == 0) {
      this.userDisplay.gameOver("You " + GameResult.LOSE);
    } else {
      this.userDisplay.gameOver("You " + GameResult.WIN);
    }
  }

  /**
   * calls methods in both model and view to set up the game board and ships
   */
  public void controllerSetup() {
    List<Integer> dimensions =
        this.userDisplay.requestBoardDimension(PromptType.WELCOMEBOARD.getMessage());
    while (!validate.isDimensionValid(dimensions)) {
      dimensions = this.userDisplay.requestBoardDimension(PromptType.INVALIDBOARDSIZE.getMessage());
    }

    List<Integer> shipNums = this.userDisplay.requestShips(PromptType.NUMOFSHIPS.getMessage()
        + Math.min(dimensions.get(0), dimensions.get(1)) + "\n");

    while (!validate.isShipValid(shipNums, Math.min(dimensions.get(0), dimensions.get(1)))) {
      shipNums = this.userDisplay.requestShips(PromptType.INVALIDNUMOFSHIPS.getMessage()
       + Math.min(dimensions.get(0), dimensions.get(1)) + "\n");
    }

    userShotsLeft = this.player1.setup(dimensions.get(1), dimensions.get(0),
        this.createFleet(shipNums));
    oppShotsLeft = this.player2.setup(dimensions.get(1), dimensions.get(0),
        this.createFleet(shipNums));
  }

  /**
   * creates a fleet of ships given user input
   *
   * @param shipNums List of the number of ships the user wants for each shipType
   * @return A map of the number of ships wanted for each ship type
   */
  private Map<ShipType, Integer> createFleet(List<Integer> shipNums) {
    Map<ShipType, Integer> fleet = new LinkedHashMap<>();
    fleet.put(ShipType.CARRIER, shipNums.get(0));
    fleet.put(ShipType.BATTLESHIP, shipNums.get(1));
    fleet.put(ShipType.DESTROYER, shipNums.get(2));
    fleet.put(ShipType.SUBMARINE, shipNums.get(3));
    return fleet;
  }
}
