package cs3500.pa03.model;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Abstract Player class to represents two types of players with common methods
 */
public abstract class AbstractPlayer implements Player {

  /**
   * Representation of the game board consisting of the current user's view of it's board and the
   * opponents board
   */
  protected BattleField gameBoard;

  /**
   * Random variable used to randomize ship positions
   */
  protected final Random random;

  /**
   * Constructor for Abstract Player
   *
   * @param random Random variable to randomize the board
   */
  public AbstractPlayer(Random random) {

    this.random = random;
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  public String name() {
    //irrelevant for the local version of this game
    return "poojprab";
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.gameBoard = new BattleField(height, width);

    return this.gameBoard.setupShips(specifications, this.random);
  }


  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  public abstract List<Coord> takeShots();

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *         ship on this board
   */
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return this.gameBoard.updateMyBoard(opponentShotsOnBoard);
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    this.gameBoard.updateOppBoard(shotsThatHitOpponentShips, CoordType.HIT);
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  public void endGame(GameResult result, String reason) {
    return;
  }

}
