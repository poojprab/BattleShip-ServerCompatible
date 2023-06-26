package cs3500.pa04;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.CoordType;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.Ship;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.CoordinatesJson;
import cs3500.pa04.json.EndGameJson;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.PlayerJson;
import cs3500.pa04.json.SetupJson;
import cs3500.pa04.json.ShipJson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * This class uses the Proxy Pattern to talk to the Server and dispatch methods to the Player.
 */
public class ProxyController {

  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final Player player;
  private final ObjectMapper mapper;
  private final GameType gameType;

  /**
   * Construct an instance of a ProxyController.
   *
   * @param server the socket connection to the server
   * @param player the instance of the player
   * @throws IOException if
   */
  public ProxyController(Socket server, Player player, GameType gameType) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = player;
    this.mapper = new ObjectMapper();
    this.gameType = gameType;
  }


  /**
   * Listens for messages from the server as JSON in the format of a MessageJSON. When a complete
   * message is sent by the server, the message is parsed and then delegated to the corresponding
   * helper method for each message. This method stops when the connection to the server is closed
   * or an IOException is thrown from parsing malformed JSON.
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
      System.out.println("Disconnected from the server");
    }
  }


  /**
   * Determines the type of request the server has sent and delegates to the
   * corresponding helper method with the message arguments.
   *
   * @param message the MessageJSON used to determine what the server has sent
   */
  private void delegateMessage(MessageJson message) {
    String msg = message.messageName();
    JsonNode arguments = message.arguments();

    if (Message.JOIN.getMsg().equals(msg)) {
      handleJoin();
    } else if (Message.SETUP.getMsg().equals(msg)) {
      handleSetup(arguments);
    } else if (Message.TAKESHOTS.getMsg().equals(msg)) {
      handleTakeShots();
    } else if (Message.REPORTDAMAGE.getMsg().equals(msg)) {
      handleReportDamage(arguments);
    } else if (Message.SUCCESSFULHITS.getMsg().equals(msg)) {
      handleSuccessfulHits(arguments);
    } else if (Message.ENDGAME.getMsg().equals(msg)) {
      handleEndGame(arguments);
    } else {
      throw new IllegalStateException("Invalid message name");
    }
  }

  /**
   * handles the "join" server request
   */
  private void handleJoin() {
    PlayerJson playerJson = new PlayerJson(this.player.name(), this.gameType.toString());
    MessageJson response = new MessageJson("join",
        this.mapper.convertValue(playerJson, JsonNode.class));
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    this.out.println(jsonResponse);
  }

  /**
   * handles the "setup" server request
   *
   * @param arguments the setup arguments sent by the server
   */
  private void handleSetup(JsonNode arguments) {
    SetupJson setupArgs = this.mapper.convertValue(arguments, SetupJson.class);

    //create methods later
    List<Ship> fleet = this.player.setup(setupArgs.height(),
        setupArgs.width(), setupArgs.fleetSpec());

    //write a helper to turn this into a fleet
    FleetJson fleetJson = new FleetJson(this.createFleet(fleet));

    MessageJson response = new MessageJson("setup",
        this.mapper.convertValue(fleetJson, JsonNode.class));

    JsonNode jsonResponse = JsonUtils.serializeRecord(response);

    this.out.println(jsonResponse);
  }

  /**
   * creates a list of ShipJson given a list of Ship
   *
   * @param fleet the given list of Ship
   * @return the created list of ShipJson
   */
  private List<ShipJson> createFleet(List<Ship> fleet) {
    List<ShipJson> jsonFleet = new ArrayList<>();
    for (Ship s : fleet) {
      jsonFleet.add(new ShipJson(new CoordJson(s.getPosition().get(0).getYcoord(),
          s.getPosition().get(0).getXcoord()), s.getPosition().size(),
          s.getOrientation().toString()));
    }
    return jsonFleet;
  }

  /**
   * handles the "take-shots" server request
   */
  private void handleTakeShots() {

    List<Coord> coords = this.player.takeShots();

    List<CoordJson> coordJsons = this.createCoordsJson(coords);

    CoordinatesJson coordinatesJson = new CoordinatesJson(coordJsons);

    MessageJson response = new MessageJson("take-shots",
        this.mapper.convertValue(coordinatesJson, JsonNode.class));

    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    this.out.println(jsonResponse);
  }

  /**
   * creates a list of CoordJson given a list of Coord
   *
   * @param coords the given list of Coord
   * @return the created list of CoordJson
   */
  private List<CoordJson> createCoordsJson(List<Coord> coords) {
    List<CoordJson> coordJsons = new ArrayList<>();

    for (Coord c : coords) {
      coordJsons.add(new CoordJson(c.getYcoord(), c.getXcoord()));
    }

    return coordJsons;
  }

  /**
   * handles the "report-damage" server request
   *
   * @param arguments the reportDamage arguments sent by the server
   */
  private void handleReportDamage(JsonNode arguments) {
    CoordinatesJson coordinatesArgs = this.mapper.convertValue(arguments, CoordinatesJson.class);

    List<Coord> coords = this.createCoords(coordinatesArgs);

    CoordinatesJson coordinatesJson =
        new CoordinatesJson(this.createCoordsJson(this.player.reportDamage(coords)));

    MessageJson response = new MessageJson("report-damage",
        this.mapper.convertValue(coordinatesJson, JsonNode.class));

    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    this.out.println(jsonResponse);
  }

  /**
   * creates a list of Coord given a CoordinatesJson
   *
   * @param coordinates the given CoordinatesJson
   * @return the created list of Coord
   */
  public List<Coord> createCoords(CoordinatesJson coordinates) {
    List<Coord> coords = new ArrayList<>();
    for (CoordJson cj : coordinates.coordinates()) {
      coords.add(new Coord(cj.y(), cj.x(), CoordType.MISS));
    }
    return coords;
  }

  /**
   * handles the "successful-hits" server request
   *
   * @param arguments the successfulHits arguments sent by the server
   */
  private void handleSuccessfulHits(JsonNode arguments) {
    CoordinatesJson coordinatesArgs = this.mapper.convertValue(arguments, CoordinatesJson.class);

    List<Coord> coords = this.createCoords(coordinatesArgs);

    this.player.successfulHits(coords);

    MessageJson response = new MessageJson("successful-hits", mapper.createObjectNode());

    JsonNode jsonResponse = JsonUtils.serializeRecord(response);

    this.out.println(jsonResponse);
  }

  /**
   * handles the "end-game" server request
   *
   * @param arguments the endGame arguments sent by the server
   */
  private void handleEndGame(JsonNode arguments) {
    EndGameJson endGameArgs = this.mapper.convertValue(arguments, EndGameJson.class);

    this.player.endGame(endGameArgs.gameResult(), endGameArgs.reason());

    MessageJson response = new MessageJson("end-game", mapper.createObjectNode());

    JsonNode jsonResponse = JsonUtils.serializeRecord(response);

    this.out.println(jsonResponse);
  }
}
