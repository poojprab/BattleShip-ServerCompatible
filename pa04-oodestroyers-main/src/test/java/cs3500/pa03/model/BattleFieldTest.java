package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.model.BattleField;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.CoordType;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for class BattleField
 */
class BattleFieldTest {

  BattleField bf;

  Random random;

  List<Coord> successShots;

  List<Coord> correctCoords;

  List<Coord> oppShots;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    bf = new BattleField(6, 7);
    bf.fillBoard(6, 7);
    random = new Random(1);

    HashMap<ShipType, Integer> ships = new LinkedHashMap<>();
    ships.put(ShipType.CARRIER, 2);

    bf.setupShips(ships, random);

    successShots = new ArrayList<>(Arrays.asList(
        new Coord(0, 0, CoordType.EMPTY),
        new Coord(1, 2, CoordType.EMPTY)
    ));

    oppShots = new ArrayList<>(Arrays.asList(
        new Coord(0, 0, CoordType.HIT),
        new Coord(1, 2, CoordType.HIT),
        new Coord(0, 2, CoordType.HIT)
    ));
  }

  /**
   * Test for method fillBoard()
   */
  @Test
  public void testFillBoard() {
    BattleField bf1 = new BattleField(3, 2);
    bf1.fillBoard(3, 2);

    for (Coord[] c : bf1.getMyBoard()) {
      for (Coord e : c) {
        assertEquals(e.getCoordType(), CoordType.EMPTY);
      }
    }

    for (Coord[] c : bf1.getOpponentBoard()) {
      for (Coord e : c) {
        assertEquals(e.getCoordType(), CoordType.EMPTY);
      }
    }
  }

  /**
   * test for method updateMyBoard()
   */
  @Test
  public void testUpdateMyBoard() {
    correctCoords = bf.updateMyBoard(oppShots);
    for (int i = 0; i < correctCoords.size(); i++) {
      assertEquals(correctCoords.get(i).getXcoord(),
          successShots.get(i).getXcoord());
      assertEquals(correctCoords.get(i).getYcoord(),
          successShots.get(i).getYcoord());
      assertEquals(correctCoords.get(i).getCoordType(),
          successShots.get(i).getCoordType());
    }
  }

  /**
   * test for method updateOppBoard()
   */
  @Test
  public void testUpdateOppBoard() {
    bf.updateOppBoard(oppShots, CoordType.HIT);
    for (int i = 0; i < oppShots.size(); i++) {
      assertEquals(bf.getOpponentBoard()
          [oppShots.get(i).getXcoord()][oppShots.get(i).getYcoord()]
          .getCoordType(), CoordType.HIT);
    }

    oppShots.add(new Coord(1, 1, CoordType.MISS));
    bf.getOpponentBoard()[1][1].setCoordType(CoordType.HIT);
    bf.updateOppBoard(oppShots, CoordType.HIT);
    assertEquals(bf.getOpponentBoard()
        [oppShots.get(3).getXcoord()][oppShots.get(3).getYcoord()]
        .getCoordType(), CoordType.HIT);
  }

  /**
   * test for method updateMyShips()
   */
  @Test
  public void testUpdateShips() {
    bf.getMyBoard()[0][5].setCoordType(CoordType.HIT);
    bf.getMyBoard()[1][5].setCoordType(CoordType.HIT);
    bf.getMyBoard()[2][5].setCoordType(CoordType.HIT);
    bf.getMyBoard()[3][5].setCoordType(CoordType.HIT);
    bf.getMyBoard()[4][5].setCoordType(CoordType.HIT);
    bf.getMyBoard()[5][5].setCoordType(CoordType.HIT);

    bf.updateMyShips();

    assertEquals(bf.getMyShips().size(), 1);
  }

  @Test
  public void testAlreadyHit() {
    bf.getOpponentBoard()[1][5].setCoordType(CoordType.HIT);
    bf.getOpponentBoard()[1][6].setCoordType(CoordType.HIT);
    assertTrue(bf.alreadyHit(1, 5));
    assertTrue(bf.alreadyHit(1, 6));
    assertFalse(bf.alreadyHit(0, 0));
    assertFalse(bf.alreadyHit(0, 1));
  }

  @Test
  public void testGetMyBoard() {
    BattleField bf1Expected = new BattleField(6, 7);
    bf1Expected.fillBoard(6, 7);
    for (int i = 0; i < 6; i++) {
      bf1Expected.getMyBoard()[i][5].setCoordType(CoordType.SHIP);
    }
    for (int j = 0; j < 6; j++) {
      bf1Expected.getMyBoard()[j][6].setCoordType(CoordType.SHIP);
    }

    for (int x = 0; x < bf1Expected.getMyBoard().length; x++) {
      for (int y = 0; y < bf1Expected.getMyBoard()[0].length; y++) {
        assertEquals(bf1Expected.getMyBoard()[x][y].getCoordType(),
            bf.getMyBoard()[x][y].getCoordType());
      }
    }
  }

  @Test
  public void testGetOppBoard() {
    BattleField bf1Expected = new BattleField(6, 7);
    bf1Expected.fillBoard(6, 7);

    bf.getOpponentBoard()[1][5].setCoordType(CoordType.HIT);
    bf.getOpponentBoard()[1][6].setCoordType(CoordType.HIT);

    bf1Expected.getOpponentBoard()[1][5].setCoordType(CoordType.HIT);
    bf1Expected.getOpponentBoard()[1][6].setCoordType(CoordType.HIT);
    assertTrue(bf.alreadyHit(1, 5));
    assertTrue(bf.alreadyHit(1, 6));
    assertFalse(bf.alreadyHit(0, 0));
    assertFalse(bf.alreadyHit(0, 1));
  }
}