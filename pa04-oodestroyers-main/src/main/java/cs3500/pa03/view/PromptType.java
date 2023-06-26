package cs3500.pa03.view;

/**
 * Enum representing each type of prompt
 */
public enum PromptType {

  /**
   * Represeents the welcome message
   */
  WELCOMEBOARD("""
      Hello! Welcome to the OOD BattleSalvo Game!\s
      Please enter a valid height and width below:\s
      """),

  /**
   * Represeents the battle message
   */
  BATTLE("The board is set up so that the top left corner is 0 0 and moving one right would"
      + " be 1 0, \n"
      + "while moving one down would be 0 1. Please Enter "),

  /**
   * Represeents the invalid board message
   */
  INVALIDBOARDSIZE("Uh Oh! You've entered invalid dimensions. "
      + "Please remember that the height and width\n"
      + "of the game must be in the range (6, 15), inclusive. Try again!\n"),


  /**
   * Represeents the number of ships input message
   */
  NUMOFSHIPS("Please enter your fleet in the order "
      + "[Carrier, Battleship, Destroyer, Submarine].\n"
      + "Remember, your fleet may not exceed size "),

  /**
   * Represeents the invalid ships message
   */
  INVALIDNUMOFSHIPS("Uh Oh! You've entered invalid fleet sizes.\n"
      + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
      + "Remember, your fleet may not exceed size ");

  /**
   * represents the message representing each enum
   */
  private final String message;

  PromptType(String message) {
    this.message = message;
  }

  /**
   * Gets the string representation of the enum
   *
   * @return The string representing the given enum
   */
  public String getMessage() {
    return this.message;
  }
}
