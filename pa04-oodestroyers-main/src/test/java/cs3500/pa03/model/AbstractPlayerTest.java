package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.AbstractPlayer;
import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.CoordType;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.view.UserDisplay;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for class AbstractPlayer
 */
class AbstractPlayerTest {

  AbstractPlayer ab;

  AbstractPlayer ab2;

  Random random;

  UserDisplay userDisplay;

  String userInput;

  StringReader input;

  StringBuilder output;


  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    userInput = "6 7";
    input = new StringReader(userInput);
    output = new StringBuilder();
    userDisplay = new UserDisplay(output, input);
    random = new Random(1);
    ab = new ManualPlayer(userDisplay, random);
    ab2 = new AiPlayer(random);
    HashMap<ShipType, Integer> ships = new LinkedHashMap<>();
    ships.put(ShipType.CARRIER, 2);
    ab.setup(6, 7, ships);
  }

  /**
   * test for method name()
   */
  @Test
  public void testName() {
    assertEquals(ab.name(), "poojprab");
  }

  /**
   * test for method reportDamage()
   */
  @Test
  public void testReportDamage() {
    List<Coord> oppHits = new ArrayList<>(Arrays.asList(new Coord(0, 1, CoordType.MISS),
        new Coord(2, 2, CoordType.MISS)));
    ab.gameBoard.getMyBoard()[0][1].setCoordType(CoordType.SHIP);
    List<Coord> actualHits = ab.reportDamage(oppHits);
    List<Coord> expectedHits = new ArrayList<>(List.of(new Coord(0, 1, CoordType.MISS)));

    for (int i = 0; i < actualHits.size(); i++) {
      assertEquals(actualHits.get(i).getXcoord(), expectedHits.get(i).getXcoord());
      assertEquals(actualHits.get(i).getYcoord(), expectedHits.get(i).getYcoord());
    }
  }

  /**
   * test for method successfulHits()
   */
  @Test
  public void testSuccessfulHits() {
    List<Coord> successHits = new ArrayList<>(Arrays.asList(new Coord(0, 1, CoordType.HIT),
        new Coord(0, 2, CoordType.HIT), new Coord(0, 3, CoordType.HIT),
        new Coord(0, 4, CoordType.HIT), new Coord(0, 5, CoordType.HIT)));
    ab.successfulHits(successHits);
    assertEquals(ab.gameBoard.getOpponentBoard()[0][1].getCoordType(), CoordType.HIT);
    assertEquals(ab.gameBoard.getOpponentBoard()[0][2].getCoordType(), CoordType.HIT);
    assertEquals(ab.gameBoard.getOpponentBoard()[0][3].getCoordType(), CoordType.HIT);
    assertEquals(ab.gameBoard.getOpponentBoard()[0][4].getCoordType(), CoordType.HIT);
    assertEquals(ab.gameBoard.getOpponentBoard()[0][5].getCoordType(), CoordType.HIT);
  }

}