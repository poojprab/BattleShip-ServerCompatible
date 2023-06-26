package cs3500.pa03.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa03.model.BattleField;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.CoordType;
import cs3500.pa03.model.GameResult;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for class UserDisplay
 */
class UserDisplayTest {

  UserDisplay userDisplay1;
  UserDisplay userDisplay2;
  UserDisplay userDisplay3;
  UserDisplay userDisplay4;

  String userInput1;
  String userInput2;
  String userInput3;
  String userInput4;

  StringReader input1;
  StringReader input2;
  StringReader input3;
  StringReader input4;

  Appendable output1;
  Appendable output2;
  Appendable output3;
  Appendable output4;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    userInput1 = "6 7";
    input1 = new StringReader(userInput1);
    output1 = new StringBuilder();
    userDisplay1 = new UserDisplay(output1, input1);

    userInput2 = "1 1 1 1";
    input2 = new StringReader(userInput2);
    output2 = new StringBuilder();
    userDisplay2 = new UserDisplay(output2, input2);

    userInput3 = "1 0\n0 1\n2 3\n4 4";
    input3 = new StringReader(userInput3);
    output3 = new StringBuilder();
    userDisplay3 = new UserDisplay(output3, input3);

    userInput4 = " ";
    input4 = new StringReader(userInput4);
    output4 = new StringBuilder();
    userDisplay4 = new UserDisplay(output4, input4);
  }

  /**
   * test for method requestBoardDimension()
   */
  @Test
  public void testBoardDimension() {
    List<Integer> boardDim = new ArrayList<>(Arrays.asList(6, 7));
    assertEquals(userDisplay1.requestBoardDimension(PromptType.WELCOMEBOARD.getMessage()),
        boardDim);


    UserDisplay userDisplayOne = new UserDisplay(new MockAppendable(), new StringReader("8"));
    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () ->
        userDisplayOne.requestBoardDimension(new MockAppendable().toString()));

    assertEquals("Could not append.", iae.getMessage());
  }

  /**
   * test for method requestBoardDimension() given strings instead of ints
   */
  @Test
  public void testBoardDimensionInvalid() {
    userInput1 = "s s\n6 7";
    input1 = new StringReader(userInput1);
    output1 = new StringBuilder();
    userDisplay1 = new UserDisplay(output1, input1);
    List<Integer> boardDim = new ArrayList<>(Arrays.asList(6, 7));
    assertEquals(userDisplay1.requestBoardDimension(PromptType.WELCOMEBOARD.getMessage()),
        boardDim);

    userInput1 = "6 7";
    input1 = new StringReader(userInput1);
    output1 = new MockAppendable();
    userDisplay1 = new UserDisplay(output1, input1);
    assertThrows(IllegalArgumentException.class, () ->
        userDisplay1.requestBoardDimension(PromptType.WELCOMEBOARD.getMessage()));
  }

  /**
   * test for method requestShips()
   */
  @Test
  public void testShips() {
    List<Integer> ships = new ArrayList<>(Arrays.asList(1, 1, 1, 1));
    assertEquals(userDisplay2.requestShips(PromptType.NUMOFSHIPS.getMessage()),
        ships);
    userInput2 = "1 1 1 1";
    input2 = new StringReader(userInput2);
    output2 = new MockAppendable();
    userDisplay2 = new UserDisplay(output2, input2);
    assertThrows(IllegalArgumentException.class, () ->
        userDisplay2.requestShips(PromptType.NUMOFSHIPS.getMessage()));
  }

  /**
   * test for method requestShips() given strings instead of ints
   */
  @Test
  public void testShipsInvalid() {
    userInput2 = "s s s s\n1 1 1 1";
    input2 = new StringReader(userInput2);
    output2 = new StringBuilder();
    userDisplay2 = new UserDisplay(output2, input2);
    List<Integer> ships = new ArrayList<>(Arrays.asList(1, 1, 1, 1));
    assertEquals(userDisplay2.requestShips(PromptType.NUMOFSHIPS.getMessage()),
        ships);
  }


  /**
   * test for method requestShots()
   */
  @Test
  public void testRequestShots() {
    List<Integer> shots = new ArrayList<>(Arrays.asList(1, 0, 0, 1, 2, 3, 4, 4));
    assertEquals((userDisplay3.requestShots(4)),
        shots);

    userInput3 = "1 0\n1 0\n0 1\n2 3\n4 4";
    input3 = new StringReader(userInput3);
    output3 = new MockAppendable();
    userDisplay3 = new UserDisplay(output3, input2);
    assertThrows(IllegalArgumentException.class, () ->
        userDisplay3.requestShots(4));
  }

  /**
   * test for method requestShots() given strings instead of ints
   */
  @Test
  public void testRequestShotsInvalid() {
    userInput3 = "s s\n1 0\n0 1\n2 3\n4 4";
    input3 = new StringReader(userInput3);
    output3 = new StringBuilder();
    userDisplay3 = new UserDisplay(output3, input3);
    List<Integer> shots = new ArrayList<>(Arrays.asList(1, 0, 0, 1, 2, 3, 4, 4));
    assertEquals((userDisplay3.requestShots(4)),
        shots);
  }

  /**
   * test for method requestBoard()
   */
  @Test
  public void testRequestBoard() {
    BattleField bf = new BattleField(2, 3);
    Coord[][] myBoard = bf.fillBoard(2, 3);
    Coord[][] oppBoard = bf.fillBoard(2, 3);
    oppBoard[0][0].setCoordType(CoordType.SHIP);
    oppBoard[1][0].setCoordType(CoordType.SHIP);
    userDisplay4.requestBoard(myBoard, oppBoard);
    assertEquals(output4.toString(),
        "Opponent Board Data: " + "\n"
            + "S - - \n" + "S - - \n" + "Your Board: " + "\n"
            + "- - - \n- - - \n");
  }

  /**
   * test the method requestBoard() with an appendable that can't be appended to
   */
  @Test
  public void testRequestBoardInvalid() {
    UserDisplay userDisplayOne = new UserDisplay(new MockAppendable(), new StringReader("8"));
    assertThrows(IllegalArgumentException.class, () ->
        userDisplayOne.requestBoard(new Coord[6][6], new Coord[6][6]));
  }

  /**
   * test for method gameOver()
   */
  @Test
  public void testGameOver() {
    userDisplay4.gameOver(GameResult.TIE.toString());
    assertEquals(output4.toString(),
        "TIE");
    userDisplay4.gameOver(GameResult.WIN.toString());
    assertEquals(output4.toString(),
        //stringbuilder is adding onto the previous tests
        "TIEWIN");
    userDisplay4.gameOver(GameResult.LOSE.toString());
    assertEquals(output4.toString(),
        //stringbuilder is adding onto the previous tests
        "TIEWINLOSE");
  }

  /**
   * test the method gameOver() with an appendable that can't be appended to
   */
  @Test
  public void testGameOverInvalid() {
    UserDisplay userDisplayOne = new UserDisplay(new MockAppendable(), new StringReader("8"));
    assertThrows(IllegalArgumentException.class, () ->
        userDisplayOne.gameOver("game over"));
  }

}