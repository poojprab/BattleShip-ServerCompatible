package cs3500.pa03.view;

import cs3500.pa03.model.Coord;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * View class to display and take in input
 */
public class UserDisplay implements View {

  /**
   * Appendable to print output
   */
  private final Appendable out;
  /**
   * Readable to take input
   */
  private final Readable in;

  /**
   * Scanner to read input
   */
  private final Scanner scanner;

  /**
   * Constructor for UserDisplay
   *
   * @param out appendable to write output
   * @param in  readable to get input
   */
  public UserDisplay(Appendable out, Readable in) {
    this.out = out;
    this.in = in;
    this.scanner = new Scanner(this.in);
  }


  /**
   * Asks user for board dimensions
   *
   * @param message message to be outputted
   * @return list of integers representing the board dimensions
   */
  public List<Integer> requestBoardDimension(String message) {
    List<Integer> boardDimensions = new ArrayList<>();
    try {
      out.append("----------------------------------------------------------------\n")
          .append(message)
          .append("----------------------------------------------------------------\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append.");
    }
    try {
      boardDimensions.add(scanner.nextInt());
      boardDimensions.add(scanner.nextInt());
    } catch (InputMismatchException e) {
      scanner.nextLine();
      return requestBoardDimension(message);
    }
    return boardDimensions;
  }


  /**
   * Asks user for ship numbers
   *
   * @param message message to be outputted
   * @return list of integers representing the ships
   */
  public List<Integer> requestShips(String message) {
    List<Integer> ships = new ArrayList<>();
    try {
      out.append("\n----------------------------------------------------------------\n")
          .append(message)
          .append("----------------------------------------------------------------\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append.");
    }
    for (int i = 0; i < 4; i++) {
      try {
        ships.add(scanner.nextInt());
      } catch (InputMismatchException e) {
        scanner.nextLine();
        return requestShips(message);
      }
    }
    return ships;
  }

  /**
   * Asks user for user shots
   *
   * @param numOfShotsAllowed number of inputs needed
   * @return list of integers representing the users shots
   */
  public List<Integer> requestShots(int numOfShotsAllowed) {
    List<Integer> coordinates = new ArrayList<>();
    try {
      out.append(PromptType.BATTLE.getMessage()).append(String.valueOf(numOfShotsAllowed))
          .append(" shots: ")
          .append("\n----------------------------------------------------------------\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append.");
    }
    for (int i = 0; i < numOfShotsAllowed; i++) {
      try {
        if (scanner.hasNext()) {
          coordinates.add(scanner.nextInt());
          coordinates.add(scanner.nextInt());
        }
      } catch (InputMismatchException e) {
        scanner.nextLine();
        return requestShots(numOfShotsAllowed);
      }
    }
    return coordinates;
  }


  /**
   * Asks for the board to be displayed
   *
   * @param myBoard  the manual user's board
   * @param oppBoard the manual user's view of the opponents board
   */
  public void requestBoard(Coord[][] myBoard, Coord[][] oppBoard) {
    try {
      out.append("Opponent Board Data: " + "\n");
      for (Coord[] c : oppBoard) {
        for (Coord e : c) {
          out.append(e.getCoordType().getType()).append(" ");
        }
        out.append("\n");
      }

      out.append("Your Board: " + "\n");
      for (Coord[] c : myBoard) {
        for (Coord e : c) {
          out.append(e.getCoordType().getType()).append(" ");
        }
        out.append("\n");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append.");
    }
  }

  /**
   * Closes the scanner and outputs game over
   *
   * @param message string to be outputted
   */
  public void gameOver(String message) {
    try {
      out.append(message);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append.");
    }
    this.scanner.close();
  }
}
