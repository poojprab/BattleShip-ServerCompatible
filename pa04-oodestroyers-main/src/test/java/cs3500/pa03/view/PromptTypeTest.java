package cs3500.pa03.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.view.PromptType;
import org.junit.jupiter.api.Test;

/**
 * Tests for enum PromptType
 */
class PromptTypeTest {

  /**
   * test for method getMessage() for each enum
   */
  @Test
  public void testGetMessage() {
    assertEquals(PromptType.WELCOMEBOARD.getMessage(), """
        Hello! Welcome to the OOD BattleSalvo Game!\s
        Please enter a valid height and width below:\s
        """);
    assertEquals(PromptType.BATTLE.getMessage(), "The board is set up so that the top left "
        + "corner is 0 0 and moving one right would be 1 0, \n"
        + "while moving one down would be 0 1. Please Enter ");

    assertEquals(PromptType.INVALIDBOARDSIZE.getMessage(),
        "Uh Oh! You've entered invalid dimensions. "
            + "Please remember that the height and width\n"
            + "of the game must be in the range (6, 15), inclusive. Try again!\n");
    assertEquals(PromptType.NUMOFSHIPS.getMessage(),
        "Please enter your fleet in the order "
            + "[Carrier, Battleship, Destroyer, Submarine].\n"
            + "Remember, your fleet may not exceed size ");
    assertEquals(PromptType.INVALIDNUMOFSHIPS.getMessage(),
        "Uh Oh! You've entered invalid fleet sizes.\n"
            + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
            + "Remember, your fleet may not exceed size ");
  }

}