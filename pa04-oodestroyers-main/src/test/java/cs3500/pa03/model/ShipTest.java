package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.CoordType;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import cs3500.pa04.Orientation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for class Ship
 */
class ShipTest {

  Ship ship1;
  Ship ship2;

  List<Coord> position1;
  List<Coord> position2;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    position1 = new ArrayList<>(Arrays.asList(new Coord(2, 3, CoordType.SHIP),
        new Coord(3, 4, CoordType.SHIP), new Coord(4, 7, CoordType.SHIP)));
    position2 = new ArrayList<>(Arrays.asList(new Coord(2, 3, CoordType.HIT),
        new Coord(3, 4, CoordType.HIT), new Coord(4, 7, CoordType.HIT)));
    ship1 = new Ship(ShipType.CARRIER, position1, Orientation.HORIZONTAL);
    ship2 = new Ship(ShipType.SUBMARINE, position2, Orientation.VERTICAL);
  }

  /**
   * test for method isSunk()
   */
  @Test
  public void testisSunk() {
    assertFalse(ship1.isSunk());
    assertTrue(ship2.isSunk());
  }

}