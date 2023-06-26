package cs3500.pa04;

/**
 * an enumeration to represent messages of a BattleSalvo game played in the server
 */
public enum Message {
  JOIN("join"),
  SETUP("setup"),
  TAKESHOTS("take-shots"),
  REPORTDAMAGE("report-damage"),
  SUCCESSFULHITS("successful-hits"),
  ENDGAME("end-game");

  private String msg;

  /**
   * constructor for the Message enum
   *
   * @param msg the message of the enum
   */
  Message(String msg) {
    this.msg = msg;
  }

  /**
   * gets this message's message
   *
   * @return this message's msg field
   */
  public String getMsg() {
    return this.msg;
  }
}
