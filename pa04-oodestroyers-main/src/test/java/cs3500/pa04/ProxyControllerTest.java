package cs3500.pa04;

import static cs3500.pa04.GameType.SINGLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.ShipType;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.CoordinatesJson;
import cs3500.pa04.json.EndGameJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupJson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test correct responses for different requests from the socket using a Mock Socket (mocket)
 */
public class ProxyControllerTest {

  private ByteArrayOutputStream testLog;
  private ProxyController pc;
  private LinkedHashMap<ShipType, Integer> fleetSpec;


  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
    fleetSpec = new LinkedHashMap<>();
    fleetSpec.put(ShipType.CARRIER, 2);
    fleetSpec.put(ShipType.BATTLESHIP, 4);
    fleetSpec.put(ShipType.DESTROYER, 1);
    fleetSpec.put(ShipType.SUBMARINE, 3);
  }

  /**
   * test for method handleJoin() when given the request "join"
   */
  @Test
  public void testHandleJoin() {

    //creating what the server requests from us
    ObjectMapper mapper = new ObjectMapper();

    MessageJson messageJson = new MessageJson("join", mapper.createObjectNode());
    JsonNode response = JsonUtils.serializeRecord(messageJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(response.toString()));

    // Create a Controller
    try {
      this.pc = new ProxyController(socket, new AiPlayer(new Random(1)), SINGLE);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.pc.run();

    //what is expected that our program prints out
    String expected =
        "{\"method-name\":\"join\",\"arguments\":{\"name\":"
            + "\"poojprab\",\"game-type\":\"SINGLE\"}}";
    assertEquals(expected, logToString());
  }

  /**
   * test for method handleSetup() when given the request "setup"
   */
  @Test
  public void testHandSetup() {


    // Prepare sample message
    SetupJson setupJson = new SetupJson(10, 10, fleetSpec);
    JsonNode sampleMessage = createSampleMessage("setup", setupJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a Dealer
    try {
      this.pc = new ProxyController(socket, new AiPlayer(new Random(1)), SINGLE);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.pc.run();

    String expected = "{\"method-name\":\"setup\",\"arguments\":{\"fleet\":[{\"coord\":{\"x\":4,"
        + "\"y\":4},\"length\":6,\"direction\":\"HORIZONTAL\"},{\"coord\":{\"x\":2,\"y\":3},\""
        + "length\":6,\"direction\":\"VERTICAL\"},{\"coord\":{\"x\":0,\"y\":4},\"length\":5,\""
        + "direction\":\"VERTICAL\"},{\"coord\":{\"x\":5,\"y\":3},\"length\":5,\"direction\":\""
        + "HORIZONTAL\"},{\"coord\":{\"x\":5,\"y\":0},\"length\":5,\"direction\":\"HORIZONTAL\"},"
        + "{\"coord\":{\"x\":5,\"y\":6},\"length\":5,\"direction\":\"HORIZONTAL\"},"
        + "{\"coord\":{\"x\""
        + ":6,\"y\":8},\"length\":4,\"direction\":\"HORIZONTAL\"},{\"coord\":{\"x\":6,\"y\":9},\""
        + "length\":3,\"direction\":\"HORIZONTAL\"},{\"coord\":{\"x\":1,\"y\":2},\"length\":3,\""
        + "direction\":\"HORIZONTAL\"},{\"coord\":{\"x\":0,\"y\":9},\"length\":3,\"direction\":\""
        + "HORIZONTAL\"}]}}";
    assertEquals(expected, logToString());
  }

  /**
   * test for method handleTakeShots() when given the request "take-shots"
   */
  @Test
  public void testTakeShots() {

    //creating what the server requests from us
    ObjectMapper mapper = new ObjectMapper();

    MessageJson messageJson = new MessageJson("take-shots", mapper.createObjectNode());
    JsonNode response = JsonUtils.serializeRecord(messageJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(response.toString()));

    // Create a Dealer
    try {
      Player aiPlayer = new AiPlayer(new Random(1));
      this.pc = new ProxyController(socket, aiPlayer, SINGLE);
      aiPlayer.setup(10, 10, fleetSpec);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.pc.run();

    String expected =
        "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":6,\"y\":7},"
            + "{\"x\":0,\"y\":4},{\"x\":5,\"y\":3},{\"x\":1,\"y\":1},{\"x\":0,\"y\":6},"
            + "{\"x\":2,\"y\":4},{\"x\":5,\"y\":4},{\"x\":4,\"y\":4},{\"x\":3,\"y\":8},"
            + "{\"x\":7,\"y\":3}]}}";
    assertEquals(expected, logToString());
  }

  /**
   * test for method handleReportDamage() when given the request "report-damage"
   */
  @Test
  public void testReportDamage() {

    // Prepare sample message
    CoordinatesJson coordinatesJson = new CoordinatesJson(new ArrayList<>(Arrays.asList(
        new CoordJson(2, 3), new CoordJson(4, 4),
        new CoordJson(3, 3), new CoordJson(1, 1), new CoordJson(3, 2),
        new CoordJson(0, 0), new CoordJson(5, 5), new CoordJson(8, 8),
        new CoordJson(9, 8), new CoordJson(5, 6))));

    JsonNode sampleMessage = createSampleMessage("report-damage", coordinatesJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a Dealer
    try {
      Player aiPlayer = new AiPlayer(new Random(1));
      this.pc = new ProxyController(socket, aiPlayer, SINGLE);
      aiPlayer.setup(10, 10, fleetSpec);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.pc.run();

    String expected =
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":2,\"y\":3},"
            + "{\"x\":4,\"y\":4},{\"x\":3,\"y\":2},{\"x\":8,\"y\":8},"
            + "{\"x\":9,\"y\":8},{\"x\":5,\"y\":6}]}}";
    assertEquals(expected, logToString());
  }

  /**
   * test for method handleSuccessfulHits() when given the request "successful-hits"
   */
  @Test
  public void testSuccessfulHits() {

    // Prepare sample message
    CoordinatesJson coordinatesJson = new CoordinatesJson(new
        ArrayList<>(Arrays.asList(new CoordJson(2, 3), new CoordJson(4, 4),
          new CoordJson(3, 2), new CoordJson(8, 8))));

    JsonNode sampleMessage = createSampleMessage("successful-hits", coordinatesJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a Dealer
    try {
      Player aiPlayer = new AiPlayer(new Random(1));
      this.pc = new ProxyController(socket, aiPlayer, SINGLE);
      aiPlayer.setup(10, 10, fleetSpec);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.pc.run();

    String expected =
        "{\"method-name\":\"successful-hits\",\"arguments\":{}}";
    assertEquals(expected, logToString());
  }

  /**
   * test for method handleEndGame() when given the request "end-game"
   */
  @Test
  public void testEndGame() {

    // Prepare sample message
    EndGameJson endGameJson = new EndGameJson(GameResult.WIN, "Player 1 hit all of player"
        + " 2's ships");

    JsonNode sampleMessage = createSampleMessage("end-game", endGameJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a Dealer
    try {
      Player aiPlayer = new AiPlayer(new Random(1));
      this.pc = new ProxyController(socket, aiPlayer, SINGLE);
      aiPlayer.setup(10, 10, fleetSpec);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.pc.run();

    String expected =
        "{\"method-name\":\"end-game\",\"arguments\":{}}";
    assertEquals(expected, logToString());
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8).trim();
  }

  /**
   * Create a MessageJson for some name and arguments.
   *
   * @param messageName   name of the type of message; "hint" or "win"
   * @param messageObject object to embed in a message json
   * @return a MessageJson for the object
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson =
        new MessageJson(messageName, JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }

  /**
   * test for method delegateMessage() when given an invalid request
   */
  @Test
  public void testDelegateMessageThrows() {

    // Prepare sample message
    EndGameJson endGameJson = new EndGameJson(GameResult.WIN, "Player 1 hit all of player"
        + " 2's ships");

    JsonNode sampleMessage = createSampleMessage("HEHE", endGameJson);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a Dealer
    try {
      Player aiPlayer = new AiPlayer(new Random(1));
      this.pc = new ProxyController(socket, aiPlayer, SINGLE);
      aiPlayer.setup(10, 10, fleetSpec);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response

    IllegalStateException ise = assertThrows(IllegalStateException.class, () -> this.pc.run());
    assertEquals("Invalid message name", ise.getMessage());
  }
}
