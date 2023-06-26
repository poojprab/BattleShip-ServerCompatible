package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa03.model.AiPlayer;
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
 * Tests for class AiPlayer
 */
class AiPlayerTest {
  AiPlayer aiPlayer;

  Random random;

  /**
   * setup for testing
   */
  @BeforeEach
  public void setup() {
    random = new Random(1);
    aiPlayer = new AiPlayer(random);
    HashMap<ShipType, Integer> ships = new LinkedHashMap<>();
    ships.put(ShipType.CARRIER, 2);
    ships.put(ShipType.BATTLESHIP, 1);
    aiPlayer.setup(7, 8, ships);
  }

  /**
   * test for method takeShots() for the ai
   */
  @Test
  public void testTakeShots() {
    List<Coord> actualShots = new ArrayList<>();
    try {
      actualShots = aiPlayer.takeShots();
    } catch (Exception e) {
      fail();
    }
    List<Coord> shots =
        new ArrayList<>(Arrays.asList(new Coord(2, 4, CoordType.EMPTY),
            new Coord(3, 7, CoordType.EMPTY),
            new Coord(0, 2, CoordType.EMPTY)));
    for (int i = 0; i < actualShots.size(); i++) {
      assertEquals(actualShots.get(i).getXcoord(), shots.get(i).getXcoord());
      assertEquals(actualShots.get(i).getYcoord(), shots.get(i).getYcoord());
      assertEquals(actualShots.get(i).getCoordType(), shots.get(i).getCoordType());
    }
  }

  /**
   * test for method takeShots() given coordinates that have already been hit
   */
  @Test
  public void testTakeShotsAlreadyHit() {
    aiPlayer.gameBoard.getOpponentBoard()[0][0].setCoordType(CoordType.HIT);
    List<Coord> actualShots = new ArrayList<>();
    try {
      actualShots = aiPlayer.takeShots();
    } catch (Exception e) {
      fail();
    }
    List<Coord> shots =
        new ArrayList<>(Arrays.asList(
            new Coord(2, 4, CoordType.EMPTY),
            new Coord(3, 7, CoordType.EMPTY),
            new Coord(0, 2, CoordType.EMPTY)));
    for (int i = 0; i < actualShots.size(); i++) {
      assertEquals(actualShots.get(i).getXcoord(), shots.get(i).getXcoord());
      assertEquals(actualShots.get(i).getYcoord(), shots.get(i).getYcoord());
      assertEquals(actualShots.get(i).getCoordType(), shots.get(i).getCoordType());
    }
  }
}