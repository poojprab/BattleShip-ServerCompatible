package cs3500.pa03.model;

/**
 * Class representing a single coordinate
 */
public class Coord {

  /**
   * Represents the x coordinate
   */
  private final int xcoord;
  /**
   * Represents the y coordinate
   */
  private final int ycoord;
  /**
   * Represents the type of coordinate
   */
  private CoordType coordType;

  /**
   * Constructor for Coord
   *
   * @param x x coordinate
   * @param y y coordinate
   * @param coordType type of coordinate
   */
  public Coord(int x, int y, CoordType coordType) {
    this.xcoord = x;
    this.ycoord = y;
    this.coordType = coordType;
  }

  /**
   * Sets the coordtype of the current coord
   *
   * @param coordType the CoordType to be set
   */
  public void setCoordType(CoordType coordType) {
    this.coordType = coordType;
  }

  /**
   * Gets the current coordtype
   *
   * @return the current coords coordType
   */
  public CoordType getCoordType() {
    return coordType;
  }

  /**
   * Gets the x coordinate
   *
   * @return the int representing x coordinate
   */
  public int getXcoord() {
    return this.xcoord;
  }

  /**
   * Gets the y coordinate
   *
   * @return the int representing y coordinate
   */
  public int getYcoord() {
    return this.ycoord;
  }

}
