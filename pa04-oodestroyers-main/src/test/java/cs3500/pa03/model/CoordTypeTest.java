package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.CoordType;
import org.junit.jupiter.api.Test;

/**
 * Tests for enum CoordType
 */
class CoordTypeTest {
  /**
   * test for method getType()
   */
  @Test
  public void testGetType() {
    assertEquals(CoordType.EMPTY.getType(), "-");
    assertEquals(CoordType.MISS.getType(), "M");
    assertEquals(CoordType.SHIP.getType(), "S");
    assertEquals(CoordType.HIT.getType(), "H");
  }

}