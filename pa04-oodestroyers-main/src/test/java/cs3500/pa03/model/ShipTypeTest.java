package cs3500.pa03.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.ShipType;
import org.junit.jupiter.api.Test;

/**
 * Tests for enum ShipType
 */
class ShipTypeTest {

  /**
   * test for method getSize()
   */
  @Test
  public void testGetSize() {
    assertEquals(ShipType.CARRIER.getSize(), 6);
    assertEquals(ShipType.BATTLESHIP.getSize(), 5);
    assertEquals(ShipType.DESTROYER.getSize(), 4);
    assertEquals(ShipType.SUBMARINE.getSize(), 3);
  }

}