package cs3500.pa03.model;

import cs3500.pa04.Orientation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents a game board consisting of the current user's view of it's own board and it's
 * opponents board
 */
public class BattleField {

  /**
   * The current user's board
   */
  private final Coord[][] myBoard;

  /**
   * The current user's view of its opponent's board
   */
  private final Coord[][] opponentBoard;

  /**
   * The current user's ships
   */
  private List<Ship> myShips;

  /**
   * Constructor for BattleField class
   *
   * @param height height of the board
   * @param width width of the board
   */
  public BattleField(int height, int width) {
    myBoard = this.fillBoard(height, width);
    opponentBoard = this.fillBoard(height, width);
  }

  /**
   * Creates a blank board with empty coordinates
   *
   * @param height height of the board
   * @param width width of the board
   * @return A Coord[][] to represent each game board
   */
  public Coord[][] fillBoard(int height, int width) {
    Coord[][] temp = new Coord[height][width];
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp[0].length; j++) {
        temp[i][j] = new Coord(i, j, CoordType.EMPTY);
      }
    }
    return temp;
  }

  /**
   * Sets up the board and randomizes the ships
   *
   * @param specifications Map representing a ship type and number of ships needed for that type
   * @param random Random variable to randomize ship placements
   * @return A list of ships that represent the total ships on the board
   */
  public List<Ship> setupShips(Map<ShipType, Integer> specifications, Random random) {
    List<Ship> fleet = new ArrayList<>();
    for (Map.Entry<ShipType, Integer> set : specifications.entrySet()) {
      for (int i = 0; i < set.getValue(); i++) {
        fleet.add(this.validShipParams(set.getKey(), random));
      }
    }
    this.myShips = fleet;
    return myShips;
  }

  /**
   * Passes on the parameters of a possible ship after validating that it would not overlap other
   * ships and stay within bounds of the board
   *
   * @param shipType Type of the current ship we are creating
   * @param random Random variable to randomize placement of the ships
   * @return A ship that would be added to the list of ships
   */
  private Ship validShipParams(ShipType shipType, Random random) {
    int x;
    int y;

    boolean isVertical;

    while (true) {
      isVertical = random.nextBoolean();
      x = random.nextInt(this.myBoard.length);
      y = random.nextInt(this.myBoard[0].length);
      if (isVertical) {
        if ((x + shipType.getSize() <= this.myBoard.length
            && this.noShipsV(x, shipType.getSize(), y))) {
          break;
        }
      } else {
        if ((y + shipType.getSize() <= this.myBoard[0].length
            && this.noShipsH(y, shipType.getSize(), x))) {
          break;
        }
      }
    }

    return this.createShip(shipType, isVertical, x, y);
  }

  /**
   * Creates the ship to be added on the board and to the list of ships
   *
   * @param shipType Type of the ship to be added
   * @param isVertical Whether the ship will be vertically oriented or horizontally
   * @param x The head of the ship's x coordinate
   * @param y The head of the ship's y coordinate
   * @return A ship to be added
   */
  private Ship createShip(ShipType shipType, boolean isVertical, int x, int y) {
    List<Coord> tempCoords = new ArrayList<>();
    if (isVertical) {
      for (int i = x; i < x + shipType.getSize(); i++) {
        this.myBoard[i][y].setCoordType(CoordType.SHIP);
        tempCoords.add(this.myBoard[i][y]);
      }
      return new Ship(shipType, tempCoords, Orientation.VERTICAL);
    } else {
      for (int i = y; i < y + shipType.getSize(); i++) {
        this.myBoard[x][i].setCoordType(CoordType.SHIP);
        tempCoords.add(this.myBoard[x][i]);
      }
      return new Ship(shipType, tempCoords, Orientation.HORIZONTAL);
    }
  }

  /**
   * Checks if there are any ships vertically ahead of the current position
   *
   * @param start Represents the head of the possible ship
   * @param end Represents the size of the possible ship
   * @param y Represents the row of the possible ship
   * @return A boolean representing if the possible ship would overlap with existing ships
   */
  private boolean noShipsV(int start, int end, int y) {
    for (int i = start; i < start + end; i++) {
      if (this.myBoard[i][y].getCoordType().equals(CoordType.SHIP)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if there are any ships vertically ahead of the current position
   *
   * @param start Represents the head of the possible ship
   * @param end Represents the size of the possible ship
   * @param x Represents the row of the possible ship
   * @return A boolean representing if the possible ship would overlap with existing ships
   */
  private boolean noShipsH(int start, int end, int x) {
    for (int i = start; i < start + end; i++) {
      if (this.myBoard[x][i].getCoordType().equals(CoordType.SHIP)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Updates the current user's view of their own board
   *
   * @param opponentShotsOnBoard A list of shots that the opponents hit on the current user's board
   * @return A list of coordinates that hit actual ships
   */
  public List<Coord> updateMyBoard(List<Coord> opponentShotsOnBoard) {
    List<Coord> temp = new ArrayList<>();
    for (Coord c : opponentShotsOnBoard) {
      Coord posOnMyBoard = this.myBoard[c.getXcoord()][c.getYcoord()];
      if (posOnMyBoard.getCoordType().equals(CoordType.SHIP)) {
        posOnMyBoard.setCoordType(CoordType.HIT);
        temp.add(c);
      } else {
        posOnMyBoard.setCoordType(CoordType.MISS);
      }
    }

    //updates my ships
    this.updateMyShips();

    return temp;
  }

  /**
   * Checks to see if any ships in the current list of ships are sunk, and if so, removes them from
   * the list of ships
   */
  public void updateMyShips() {
    for (int i = 0; i < this.myShips.size(); i++) {
      if (myShips.get(i).isSunk()) {
        this.myShips.remove(myShips.get(i));
        i--;
      }
    }
  }

  /**
   * Updates the current user's view of its opponents board
   *
   * @param myShots The current user's shots
   * @param coordType The type of Coordinate the user is changing the current Coord to
   */
  public void updateOppBoard(List<Coord> myShots, CoordType coordType) {
    for (Coord c : myShots) {
      Coord posOnOppBoard = this.opponentBoard[c.getXcoord()][c.getYcoord()];
      if (!posOnOppBoard.getCoordType().equals(CoordType.HIT)) {
        posOnOppBoard.setCoordType(coordType);
      }
    }
  }

  /**
   * Checks to see if the given coordinates contain a ship that has already been hit
   *
   * @param x x coordinate
   * @param y y coordinate
   * @return A boolean representing if the coordinate isn't empty
   */
  public boolean alreadyHit(int x, int y) {
    return !opponentBoard[x][y].getCoordType().equals(CoordType.EMPTY);
  }

  /**
   * Gets the current user's board
   *
   * @return the Current user's board
   */
  public Coord[][] getMyBoard() {
    return myBoard;
  }

  /**
   * Gets the current user's view of the opponent board
   *
   * @return the current user's view of its opponent's board
   */
  public Coord[][] getOpponentBoard() {
    return opponentBoard;
  }

  /**
   * Gets the current user's ships
   *
   * @return List of the user's ships
   */
  public List<Ship> getMyShips() {
    return this.myShips;
  }

}
