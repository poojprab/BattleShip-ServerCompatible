package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Tests for Driver
 */
public class DriverTest {

  /**
   * Test to call main with invalid command line arguments
   */
  @Test
  public void testDriverNumberFormat() {
    String[] args = new String[2];
    args[0] = "0.0.0.0";
    args[1] = "NOT A NUMBER";
    NumberFormatException nfe = assertThrows(NumberFormatException.class, () -> Driver.main(args));
    assertEquals("Invalid port number.", nfe.getMessage());
  }

  /**
   * Test to call main with invalid command line arguments
   */
  @Test
  public void testExceptionServerConnection() {
    String[] args = new String[2];
    args[0] = "0.0....1.1";
    args[1] = "35001";
    IllegalStateException nfe = assertThrows(IllegalStateException.class, () -> Driver.main(args));
    assertEquals("There was a communication issue with the server.", nfe.getMessage());
  }

  /**
   * Test to call main through main with two command arguments
   */
  @Test
  public void testDriveRunManual() {
    String[] args = new String[0];
    try {
      Driver.main(args);
    } catch (NoSuchElementException e) { // NoSuchElementException because no user input is inputted
      System.out.println("Able to start manual Game-Play!");
    }
  }
}