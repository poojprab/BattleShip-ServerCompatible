package cs3500.pa03.controller;

/**
 * Sample output to be used in GameController test
 */
public class RunSampleOutput {

  RunSampleOutput() {}

  public static final String runLose = """
      ----------------------------------------------------------------
      Hello! Welcome to the OOD BattleSalvo Game!\s
      Please enter a valid height and width below:\s
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
      Opponent Board Data:\s
      - - - - - -\s
      - - - - - -\s
      - - - - - -\s
      - - - - - -\s
      - - - - - -\s
      - - - - - -\s
      Your Board:\s
      S - - S S S\s
      S S - - - S\s
      S S - - - S\s
      S S - - - S\s
      S S - - - S\s
      S S - - - -\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M - - - - -\s
      H - - - - -\s
      H - - - - -\s
      H - - - - -\s
      - - - - - -\s
      - - - - - -\s
      Your Board:\s
      S - - S H S\s
      S S - - - S\s
      S S - - - S\s
      S S - - M S\s
      S S - - - S\s
      S S - - - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - - - -\s
      H M - - - -\s
      H - - - - -\s
      H - - - - -\s
      M - - - - -\s
      M - - - - -\s
      Your Board:\s
      S - - S H S\s
      H S - - - H\s
      S S - - - S\s
      S S - - M S\s
      S S - - M S\s
      S S - - - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - - - -\s
      H M - - - -\s
      H M - - - -\s
      H M - - - -\s
      M M - - - -\s
      M M - - - -\s
      Your Board:\s
      S - - S H S\s
      H S - - - H\s
      S S - - - S\s
      S S M - M S\s
      S S M - M S\s
      S S M - - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - - - -\s
      H M - - - -\s
      H M - - - -\s
      H M - - - -\s
      M M - - - -\s
      M M - - - -\s
      Your Board:\s
      S - - S H S\s
      H S - - - H\s
      H S - M - S\s
      S S M - M S\s
      S S M - M S\s
      S H M - - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - M - -\s
      H M - H - -\s
      H M - - - -\s
      H M - - - -\s
      M M - - - -\s
      M M - - - -\s
      Your Board:\s
      S - - S H S\s
      H S - M - H\s
      H S - M - S\s
      S S M M M S\s
      S S M - M H\s
      S H M - - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - M - -\s
      H M - H - -\s
      H M - H - -\s
      H M - H - -\s
      M M - H - -\s
      M M - H - -\s
      Your Board:\s
      S - - S H S\s
      H S - M - H\s
      H S - M - S\s
      S S M M M H\s
      S S M - M H\s
      S H M M - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - M - -\s
      H M - H - -\s
      H M - H - -\s
      H M - H - -\s
      M M - H - -\s
      M M - H - -\s
      Your Board:\s
      S - - S H S\s
      H S M M - H\s
      H S - M - S\s
      H S M M M H\s
      S S M - M H\s
      S H M M - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - M - -\s
      H M - H - -\s
      H M - H - -\s
      H M - H - -\s
      M M - H - -\s
      M M - H - -\s
      Your Board:\s
      H - - S H S\s
      H S M M - H\s
      H S - M - S\s
      H S M M M H\s
      S S M - M H\s
      S H M M M M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - M - -\s
      H M - H - -\s
      H M - H - -\s
      H M - H - -\s
      M M - H - -\s
      M M - H - -\s
      Your Board:\s
      H M - S H S\s
      H S M M - H\s
      H S - M - S\s
      H H M M M H\s
      S S M - M H\s
      S H M M M M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - M - -\s
      H M - H - -\s
      H M - H - -\s
      H M - H - -\s
      M M - H - -\s
      M M - H - -\s
      Your Board:\s
      H M - S H S\s
      H H M M M H\s
      H S - M - S\s
      H H M M M H\s
      S S M - M H\s
      S H M M M M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - M - -\s
      H M - H - -\s
      H M - H - -\s
      H M - H - -\s
      M M - H - -\s
      M M - H - -\s
      Your Board:\s
      H M - S H S\s
      H H M M M H\s
      H S - M M S\s
      H H M M M H\s
      S S M M M H\s
      S H M M M M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - M - -\s
      H M - H - -\s
      H M - H - -\s
      H M - H - -\s
      M M - H - -\s
      M M - H - -\s
      Your Board:\s
      H M - S H S\s
      H H M M M H\s
      H S M M M H\s
      H H M M M H\s
      S S M M M H\s
      S H M M M M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 3 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - M - -\s
      H M - H - -\s
      H M - H - -\s
      H M - H - -\s
      M M - H - -\s
      M M - H - -\s
      Your Board:\s
      H M - S H S\s
      H H M M M H\s
      H S M M M H\s
      H H M M M H\s
      S H M M M H\s
      H H M M M M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 3 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - M - -\s
      H M - H - -\s
      H M - H - -\s
      H M - H - -\s
      M M - H - -\s
      M M - H - -\s
      Your Board:\s
      H M M S H H\s
      H H M M M H\s
      H S M M M H\s
      H H M M M H\s
      S H M M M H\s
      H H M M M M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 3 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      M M - M - -\s
      H M - H - -\s
      H M - H - -\s
      H M - H - -\s
      M M - H - -\s
      M M - H - -\s
      Your Board:\s
      H M M S H H\s
      H H M M M H\s
      H H M M M H\s
      H H M M M H\s
      H H M M M H\s
      H H M M M M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 1 shots:\s
      ----------------------------------------------------------------
      You LOSE""";

  public static final String runWin = """
      ----------------------------------------------------------------
      Hello! Welcome to the OOD BattleSalvo Game!\s
      Please enter a valid height and width below:\s
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
      Opponent Board Data:\s
      - - - - - -\s
      - - - - - -\s
      - - - - - -\s
      - - - - - -\s
      - - - - - -\s
      - - - - - -\s
      Your Board:\s
      - - S - S -\s
      S - S S S -\s
      S - S S S -\s
      S - S S S -\s
      - - S S - -\s
      - - S S - -\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      H - - - - -\s
      H - - - - -\s
      H - - - - -\s
      H - - - - -\s
      - - - - - -\s
      - - - - - -\s
      Your Board:\s
      - - S - S -\s
      S - S S S -\s
      H - S S S -\s
      S - S S S M\s
      - - S S - -\s
      M - S S - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 4 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      H H - - - -\s
      H M - - - -\s
      H - - - - -\s
      H - - - - -\s
      H - - - - -\s
      H - - - - -\s
      Your Board:\s
      - - S - S -\s
      H - S S S -\s
      H - S S H -\s
      H - S S S M\s
      - - S S - -\s
      M - S S - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 3 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      H H - - - -\s
      H M - - - -\s
      H M - - - -\s
      H M - - - -\s
      H M - - - -\s
      H - - - - -\s
      Your Board:\s
      - M S - H -\s
      H M S S S -\s
      H - S S H -\s
      H - S S S M\s
      - - S S - -\s
      M - S S - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 3 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      H H H - - -\s
      H M H - - -\s
      H M - - - -\s
      H M - - - -\s
      H M - - - -\s
      H M - - - -\s
      Your Board:\s
      M M S M H -\s
      H M S S S -\s
      H - S S H -\s
      H M S S S M\s
      - - S S - -\s
      M - S S - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 3 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      H H H - - -\s
      H M H - - -\s
      H M H - - -\s
      H M H - - -\s
      H M H - - -\s
      H M - - - -\s
      Your Board:\s
      M M S M H -\s
      H M S S H -\s
      H - S S H -\s
      H M S S H M\s
      - M S S - -\s
      M - S S - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 2 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      H H H H - -\s
      H M H - - -\s
      H M H - - -\s
      H M H - - -\s
      H M H - - -\s
      H M H - - -\s
      Your Board:\s
      M M S M H -\s
      H M S S H -\s
      H - S S H -\s
      H M S S H M\s
      - M S S - M\s
      M - S S - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 2 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      H H H H - -\s
      H M H M - -\s
      H M H M - -\s
      H M H - - -\s
      H M H - - -\s
      H M H - - -\s
      Your Board:\s
      M M S M H -\s
      H M S S H -\s
      H - S S H M\s
      H M S S H M\s
      - M S S - M\s
      M - S S - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 2 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      H H H H - -\s
      H M H M - -\s
      H M H M - -\s
      H M H M - -\s
      H M H M - -\s
      H M H - - -\s
      Your Board:\s
      M M S M H -\s
      H M S S H -\s
      H - S H H M\s
      H M S S H M\s
      - M S S - M\s
      M - S S - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 2 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      H H H H M -\s
      H M H M - -\s
      H M H M - -\s
      H M H M - -\s
      H M H M - -\s
      H M H M - -\s
      Your Board:\s
      M M S M H -\s
      H M S S H -\s
      H M S H H M\s
      H M S S H M\s
      - M S S - M\s
      M - S S - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 2 shots:\s
      ----------------------------------------------------------------
      Opponent Board Data:\s
      H H H H M -\s
      H M H M H -\s
      H M H M H -\s
      H M H M - -\s
      H M H M - -\s
      H M H M - -\s
      Your Board:\s
      M M S M H -\s
      H M S S H -\s
      H M S H H M\s
      H M S S H M\s
      - M H S - M\s
      M - S S - M\s
      The board is set up so that the top left corner is 0 0 and moving one right would be 1 0,\s
      while moving one down would be 0 1. Please Enter 2 shots:\s
      ----------------------------------------------------------------
      You WIN""";

}
