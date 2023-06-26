package cs3500.pa03.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.Player;
import cs3500.pa03.view.UserDisplay;
import java.io.StringReader;
import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for class GameController
 */
class GameControllerTest {

  ManualPlayer manualPlayer;
  ManualPlayer manualPlayer1;

  AiPlayer aiPlayer;

  Random random;

  UserDisplay userDisplay;
  UserDisplay userDisplay1;

  String userInput;

  StringReader input;

  String userInput1;

  StringReader input1;

  StringBuilder output;
  GameController gameController;
  GameController gameController1;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    userInput = "6 7\n1 1 1 1\n";
    input = new StringReader(userInput);

    userInput1 = "3 7\n6 7\n1 0 1 1\n1 1 1 1\n";
    input1 = new StringReader(userInput1);

    output = new StringBuilder();
    userDisplay = new UserDisplay(output, input);
    userDisplay1 = new UserDisplay(output, input1);

    random = new Random(1);

    manualPlayer = new ManualPlayer(userDisplay, random);
    manualPlayer1 = new ManualPlayer(userDisplay1, random);
    aiPlayer = new AiPlayer(random);
    gameController = new GameController(manualPlayer, aiPlayer, userDisplay);
    gameController1 = new GameController(manualPlayer1, aiPlayer, userDisplay1);
  }

  /**
   * test for method controllerSetup() with valid input
   */
  @Test
  public void controllerSetupTest() {
    try {
      gameController.controllerSetup();
    } catch (Exception e) {
      fail();
    }

    assertEquals(output.toString(),
        """
            ----------------------------------------------------------------
            Hello! Welcome to the OOD BattleSalvo Game!\s
            Please enter a valid height and width below:\s
            ----------------------------------------------------------------

            ----------------------------------------------------------------
            Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
            Remember, your fleet may not exceed size 6
            ----------------------------------------------------------------
            """);
  }

  /**
   * test for method controllerSetup() when the user entered the wrong input
   */
  @Test
  public void controllerSetupTestFail() {
    try {
      gameController1.controllerSetup();
    } catch (Exception e) {
      fail();
    }

    assertEquals(output.toString(),
        """
            ----------------------------------------------------------------
            Hello! Welcome to the OOD BattleSalvo Game!\s
            Please enter a valid height and width below:\s
            ----------------------------------------------------------------
            ----------------------------------------------------------------
            Uh Oh! You've entered invalid dimensions. Please remember that the height and width
            of the game must be in the range (6, 15), inclusive. Try again!
            ----------------------------------------------------------------

            ----------------------------------------------------------------
            Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
            Remember, your fleet may not exceed size 6
            ----------------------------------------------------------------

            ----------------------------------------------------------------
            Uh Oh! You've entered invalid fleet sizes.
            Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
            Remember, your fleet may not exceed size 6
            ----------------------------------------------------------------
            """);
  }

  /**
   * Test for method run() where user loses
   */
  @Test
  public void testUserLoseRun() {

    userInput = """
        6 6\s
        0 0 0 0\s
        1 1 1 1\s
        0 0 0 1 0 2 0 3 0 4 0 5
        1 0 1 1 1 2 1 3 1 4 1 5
        0 0 0 0 0 0 0 0 0 0 0 0
        3 0 3 1 3 2 3 3 3 4 3 5
        0 0 0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0 0 0""";

    input = new StringReader(userInput);
    output = new StringBuilder();
    Scanner scan = new Scanner(input);

    userDisplay = new UserDisplay(output, input);


    Random randomP1 = new Random(3);
    Random randomP2 = new Random(2);

    Player p1 = new ManualPlayer(userDisplay, randomP1);
    Player p2 = new AiPlayer(randomP2);

    gameController = new GameController(p1, p2, userDisplay);

    gameController.run();


    assertEquals(RunSampleOutput.runLose, output.toString());
  }

  /**
   * test for method run() where user wins
   */
  @Test
  public void testUserWinRun() {

    userInput = """
        6 6\s
        0 0 0 0\s
        1 1 1 1\s
        0 0 0 1 0 2 0 3 0 4 0 5
        1 0 1 1 1 2 1 3 1 4 1 5
        2 0 2 1 2 2 2 3 2 4 2 5
        3 0 3 1 3 2 3 3 3 4 3 5
        4 0 4 1 4 2 4 3 4 4 4 5
        5 0 5 1 5 2 5 3 5 4 5 5""";

    input = new StringReader(userInput);
    output = new StringBuilder();
    Scanner scan = new Scanner(input);

    userDisplay = new UserDisplay(output, input);

    Random randomP1 = new Random(2);
    Random randomP2 = new Random(1);

    Player p1 = new ManualPlayer(userDisplay, randomP1);
    Player p2 = new AiPlayer(randomP2);

    gameController = new GameController(p1, p2, userDisplay);

    gameController.run();

    assertEquals(RunSampleOutput.runWin, output.toString());
  }

}