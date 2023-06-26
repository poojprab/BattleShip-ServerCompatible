package cs3500.pa03.model;

/**
 * Enum representing a type of ship
 */
public enum ShipType {

  /**
   * Represents the type of ship carrier
   */
  CARRIER(6),
  /**
   * Represents the type of ship battleship
   */
  BATTLESHIP(5),
  /**
   * Represents the type of ship destroyer
   */
  DESTROYER(4),
  /**
   * Represents the type of ship submarine
   */
  SUBMARINE(3);

  /**
   * Int representing the size of each ship type
   */
  private final int size;

  ShipType(int size) {
    this.size = size;
  }

  /**
   * Gets the size of the given ship type
   *
   * @return The ship size
   */
  public int getSize() {
    return this.size;
  }
}
