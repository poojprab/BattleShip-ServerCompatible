package cs3500.pa03.model;

/**
 * Enum represnting a type of coordinate
 */
public enum CoordType {
  /**
   * represents type hit
   */
  HIT("H"),
  /**
   * represents type miss
   */
  MISS("M"),
  /**
   * represents type empty
   */
  EMPTY("-"),
  /**
   * represents type ship
   */
  SHIP("S");

  /**
   * represents the string that represents the enum to be displayed
   */
  private final String type;

  CoordType(String type) {
    this.type = type;
  }

  /**
   * Gets the string representation of the given enum
   *
   * @return the string representing the enum
   */
  public String getType() {
    return type;
  }
}
