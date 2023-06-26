package cs3500.pa04;

import cs3500.pa03.controller.GameController;
import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.Player;
import cs3500.pa03.view.UserDisplay;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

/**
 * This is the main driver of this project.
 */
public class Driver {

  /**
   * The main entrypoint into the code as the Client. Given a host and port as parameters, the
   * game connects to the server. If no args are given, the game is played in the local player's
   * terminal. If there is an issue with the client or connecting, an error message will thrown
   * and printed.
   *
   * @param args The expected parameters are the server's host and port or an empty list
   */
  public static void main(String[] args) {
    GameType gameType;
    String host;
    int port;
    Player aiPlayer = new AiPlayer(new Random());
    if (args.length == 0) {
      Readable input = new InputStreamReader(System.in);
      Appendable output = new PrintStream(System.out);
      UserDisplay ud = new UserDisplay(output, input);
      Player manualPlayer = new ManualPlayer(ud, new Random());
      GameController gameController = new GameController(manualPlayer, aiPlayer, ud);
      gameController.run();
    } else if (args.length == 2) {
      gameType = GameType.SINGLE;
      host = args[0];
      try {
        port = Integer.parseInt(args[1]);
        Socket server = new Socket(host, port);
        ProxyController proxyPlayer = new ProxyController(server, aiPlayer, gameType);
        proxyPlayer.run();
      } catch (NumberFormatException exc) {
        throw new NumberFormatException("Invalid port number.");
      } catch (IOException e) {
        throw new IllegalStateException("There was a communication issue with the server.");
      }
    }
  }
}