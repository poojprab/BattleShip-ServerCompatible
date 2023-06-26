package cs3500.pa03.view;

import cs3500.pa03.model.Coord;
import java.util.List;

/**
 * Interface to represent a view
 */
public interface View {

  /**
   * Asks user for board dimensions
   *
   * @param message message to be outputted
   * @return list of integers representing the board dimensions
   */
  List<Integer> requestBoardDimension(String message);


  /**
   * Asks user for ship numbers
   *
   * @param message message to be outputted
   * @return list of integers representing the ships
   */
  List<Integer> requestShips(String message);

  /**
   * Asks user for user shots
   *
   * @param numOfShotsAllowed number of inputs needed
   * @return list of integers representing the users shots
   */
  List<Integer> requestShots(int numOfShotsAllowed);


  /**
   * Asks for the board to be displayed
   *
   * @param myBoard the manual user's board
   * @param oppBoard the manual user's view of the opponents board
   */
  void requestBoard(Coord[][] myBoard, Coord[][] oppBoard);

  /**
   * Closes the scanner and outputs game over
   *
   * @param message string to be outputted
   */
  void gameOver(String message);
}
