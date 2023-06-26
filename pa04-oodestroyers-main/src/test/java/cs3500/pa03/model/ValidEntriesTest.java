package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ValidEntries;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for class ValidEntries
 */
class ValidEntriesTest {

  ValidEntries validEntries;

  /**
   * setup for tests
   */
  @BeforeEach
  public void setup() {
    validEntries = new ValidEntries();
  }

  /**
   * test for method isDimensionValid()
   */
  @Test
  public void testIsDimensionValid() {
    List<Integer> lofi1 = new ArrayList<>(Arrays.asList(1, 14));
    List<Integer> lofi2 = new ArrayList<>(Arrays.asList(6, 15));
    assertFalse(validEntries.isDimensionValid(lofi1));
    assertTrue(validEntries.isDimensionValid(lofi2));

    List<Integer> lofi3 = new ArrayList<>(Arrays.asList(19, 12));
    List<Integer> lofi4 = new ArrayList<>(Arrays.asList(12, 1));
    assertFalse(validEntries.isDimensionValid(lofi3));
    assertFalse(validEntries.isDimensionValid(lofi4));

    List<Integer> lofi5 = new ArrayList<>(Arrays.asList(12, 19));
    List<Integer> lofi6 = new ArrayList<>(Arrays.asList(1, 19));
    assertFalse(validEntries.isDimensionValid(lofi5));
    assertFalse(validEntries.isDimensionValid(lofi6));
  }

  /**
   * test for method isShipValid()
   */
  @Test
  public void testIsShipValid() {
    List<Integer> lofi1 = new ArrayList<>(Arrays.asList(1, 1, 1, 1));
    List<Integer> lofi2 = new ArrayList<>(Arrays.asList(1, 0, 1, 1));
    List<Integer> lofi3 = new ArrayList<>(Arrays.asList(1, 9, 1, 1));

    assertTrue(validEntries.isShipValid(lofi1, 6));
    assertFalse(validEntries.isShipValid(lofi2, 6));
    assertFalse(validEntries.isShipValid(lofi3, 6));
  }

  /**
   * test for method isShotValid()
   */
  @Test
  public void testIsShotValid() {
    Coord[][] board = new Coord[3][4];

    //in bounds
    List<Integer> lofi1 = new ArrayList<>(Arrays.asList(1, 2));
    assertTrue(validEntries.isShotValid(lofi1, board));

    //first coord out of bounds
    List<Integer> lofi2 = new ArrayList<>(Arrays.asList(4, 2));
    assertFalse(validEntries.isShotValid(lofi2, board));

    //second coord out of bounds
    List<Integer> lofi3 = new ArrayList<>(Arrays.asList(2, 5));
    assertFalse(validEntries.isShotValid(lofi3, board));

    //first coord negative
    List<Integer> lofi4 = new ArrayList<>(Arrays.asList(-2, 2));
    assertFalse(validEntries.isShotValid(lofi4, board));

    //second coord negative
    List<Integer> lofi6 = new ArrayList<>(Arrays.asList(2, -5));
    assertFalse(validEntries.isShotValid(lofi6, board));
  }

}