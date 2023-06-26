package cs3500.pa03.model;

import cs3500.pa04.Orientation;
import java.util.List;

/**
 * Represents one ship
 */
public class Ship {

  /**
   * The current type of ship
   */
  private final ShipType shipType;
  /**
   * A list of coordinate representing the position of the current ship
   */
  private final List<Coord> position;

  private final Orientation orientation;


  /**
   * Constructor for Ship
   *
   * @param shipType Type of ship
   * @param position List of coordinates for the position of the ship
   */
  public Ship(ShipType shipType, List<Coord> position, Orientation orientation) {
    this.shipType = shipType;
    this.position = position;
    this.orientation = orientation;
  }

  /**
   * Checks to see if the current ship is sunk
   *
   * @return A boolean representing if the current ship is sunk
   */
  public boolean isSunk() {
    for (Coord c : this.position) {
      if (!c.getCoordType().equals(CoordType.HIT)) {
        return false;
      }
    }
    return true;
  }

  public List<Coord> getPosition() {
    return this.position;
  }

  public Orientation getOrientation() {
    return this.orientation;

  }
}

