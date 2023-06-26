package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.CoordType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for class Coord
 */
class CoordTest {

  Coord coord1;
  Coord coord2;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    coord1 = new Coord(2, 3, CoordType.SHIP);
    coord2 = new Coord(4, 6, CoordType.HIT);
  }

  /**
   * test for methods setCoordType() and getCoordType()
   */
  @Test
  public void testSetGetCoordType() {
    coord1.setCoordType(CoordType.MISS);
    coord2.setCoordType(CoordType.EMPTY);

    assertEquals(coord1.getCoordType(), CoordType.MISS);
    assertEquals(coord2.getCoordType(), CoordType.EMPTY);
  }

  /**
   * test for methods getXCoord() and getYCoord()
   */
  @Test
  public void testGetxCoord() {
    assertEquals(coord1.getXcoord(), 2);
    assertEquals(coord1.getYcoord(), 3);
    assertEquals(coord2.getXcoord(), 4);
    assertEquals(coord2.getYcoord(), 6);
  }

}