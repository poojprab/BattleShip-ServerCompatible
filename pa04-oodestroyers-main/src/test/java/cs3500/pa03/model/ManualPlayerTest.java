package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
 * Test for class ManualPlayer
 */
class ManualPlayerTest {

  ManualPlayer mp1;

  UserDisplay ud1;

  Random random;

  String userInput1;

  StringReader input1;

  StringBuilder output1;

  ManualPlayer mp2;

  UserDisplay ud2;

  String userInput2;

  StringReader input2;

  StringBuilder output2;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    random = new Random(1);
    userInput1 = "7 6\n2 3";
    input1 = new StringReader(userInput1);
    output1 = new StringBuilder();
    ud1 = new UserDisplay(output1, input1);
    mp1 = new ManualPlayer(ud1, random);

    userInput2 = "9 9\n2 3";
    input2 = new StringReader(userInput2);
    output2 = new StringBuilder();
    ud2 = new UserDisplay(output2, input2);
    mp2 = new ManualPlayer(ud2, random);
  }

  /**
   * test for method takeShots() given invalid user input
   */
  @Test
  public void testTakeShotsFail() {
    HashMap<ShipType, Integer> ships = new LinkedHashMap<>();
    ships.put(ShipType.CARRIER, 1);
    mp2.setup(7, 8, ships);
    List<Coord> actualShots = new ArrayList<>();
    try {
      actualShots = mp2.takeShots();
    } catch (Exception e) {
      fail();
    }

    //in a 2-D array, the x and y value is flipped, which is why my coordinate is the opposite of
    //what the user inputted
    List<Coord> shots =
        new ArrayList<>(List.of(new Coord(3, 2, CoordType.MISS)));
    assertEquals(actualShots.get(0).getXcoord(), shots.get(0).getXcoord());
    assertEquals(actualShots.get(0).getYcoord(), shots.get(0).getYcoord());
    assertEquals(actualShots.get(0).getCoordType(), shots.get(0).getCoordType());
  }

  /**
   * test for method takeShots() given valid user input
   */
  @Test
  public void testTakeShots() {
    HashMap<ShipType, Integer> ships = new LinkedHashMap<>();
    ships.put(ShipType.CARRIER, 2);
    mp1.setup(7, 8, ships);
    List<Coord> actualShots = new ArrayList<>();
    try {
      actualShots = mp1.takeShots();
    } catch (Exception e) {
      fail();
    }

    //in a 2-D array, the x and y value is flipped, which is why my coordinate is the opposite of
    //what the user inputted
    List<Coord> shots =
        new ArrayList<>(Arrays.asList(new Coord(6, 7, CoordType.MISS),
            new Coord(3, 2, CoordType.MISS)));
    assertEquals(actualShots.get(0).getXcoord(), shots.get(0).getXcoord());
    assertEquals(actualShots.get(0).getYcoord(), shots.get(0).getYcoord());
    assertEquals(actualShots.get(0).getCoordType(), shots.get(0).getCoordType());

    assertEquals(actualShots.get(1).getXcoord(), shots.get(1).getXcoord());
    assertEquals(actualShots.get(1).getYcoord(), shots.get(1).getYcoord());
    assertEquals(actualShots.get(1).getCoordType(), shots.get(1).getCoordType());
  }

}