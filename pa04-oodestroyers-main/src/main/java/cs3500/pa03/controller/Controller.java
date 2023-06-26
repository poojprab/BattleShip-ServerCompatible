package cs3500.pa03.controller;

/**
 * Controller interface to represent a controller
 */
public interface Controller {


  /**
   * runs the main program
   *
   */
  void run();

  /**
   * calls methods in both model and view to set up the game board and ships
   */
  void controllerSetup();

}
