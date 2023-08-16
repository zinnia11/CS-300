//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Tile Matching Game: the Tester class
// Course: CS 300 Fall 2021
//
// Author: Zinnia Nie
// Email: zznie@wisc.edu
// Lecturer: Hobbes LeGault
//
////////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: N/A
// Partner Email: N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: N/A
// Online Sources: N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Tester class for P09: Tile Matching Game. It tests the .equals method in the Tile class, and the 
 * TileListIterator, TileStack, and TileMatchingGame classes in various scenarios
 * <p>
 * @author zinnianie
 */
public class TileMatchingTester {
  
  /**
   * Tester method for the equals(Object) method in the Tile class. It takes a Tile object and 
   * checks if it is equal to another Object.
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean tileEqualsTester() {
    Tile tile1 = new Tile(Color.BLUE);
    Tile tile2 = new Tile(Color.BLACK);
    Tile tile3 = new Tile(Color.BLUE);
    Tile tile4 = null;
    String not = new String("BLUE");
    
    // Scenario 1: equals against different tile of same color
    try {
      if(!tile1.equals(tile3)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // not supposed to throw exception
    }
    
    // Scenario 2: equals against different tile of different color
    try {
      if(tile1.equals(tile2)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // not supposed to throw exception
    }
    
    // Scenario 3: equals against same tile
    try {
      if(!tile1.equals(tile1)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // not supposed to throw exception
    }
    
    // Scenario 4: equals null
    try {
      if(tile1.equals(tile4)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // not supposed to throw exception
    }
    
    // Scenario 5.a: equals object that isn't a Tile
    try {
      if(tile1.equals(not)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // not supposed to throw exception
    }
    // Scenario 5.b: equals object that isn't a Tile
    try {
      if(tile1.equals(13)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // not supposed to throw exception
    }
    
    return true;
  }
  
  /**
   * Tester method for the TileListIterator class. Creates a new iterator, and iterates through a 
   * list of Tile Nodes and tests the next() and hastNext() methods. 
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean tileListIteratorTester() {
    // top of the list is node1, last element is node 4
    Node node4 = new Node(new Tile(Color.BLACK));
    Node node3 = new Node(new Tile(Color.BLUE), node4);
    Node node2 = new Node(new Tile(Color.ORANGE), node3);
    Node node1 = new Node(new Tile(Color.YELLOW), node2);
    Tile testTo1 = new Tile(Color.YELLOW);
    Tile testTo2 = new Tile(Color.ORANGE);
    Tile testTo3 = new Tile(Color.BLUE);
    Tile testTo4 = new Tile(Color.BLACK);
    
    TileListIterator tester = new TileListIterator(node1);
    // First iteration
    try {
      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(testTo1)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Second iteration
    try {
      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(testTo2)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Third iteration
    try {
      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(testTo3)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Fourth iteration
    try {
      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(testTo4)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Fifth iteration: no items left
    try {
      if (tester.hasNext()) {
        return false;
      }
      tester.next();
    } catch (NoSuchElementException nse) {
      // expected behavior
    } catch (Exception e) {
      e.printStackTrace();
      return false; // not expected exception
    }
    
    return true;
  }

  /**
   * Tester method for the TileStack class
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean tileStackTester() {
    if (!testPush()) {
      return false;
    }
    if (!testSizeAndIsEmpty()) {
      return false;
    }
    if (!testPop()) {
      return false;
    }
    if (!testIterator()) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the push() and peek() methods in the TileStack class
   * <p>
   * @return true if all tests pass, false if any fail
   */
  private static boolean testPush() {
    Tile tile1 = new Tile(Color.YELLOW);
    Tile tile2 = new Tile(Color.ORANGE);
    Tile tile3 = new Tile(Color.BLUE);
    Tile tile4 = new Tile(Color.BLACK);
    Tile tile5 = null;
    
    TileStack tester = new TileStack();
    
    // Scenario 1: peek into an empty stack
    try {
      tester.peek();
    } catch (EmptyStackException ese) {
      // expected behavior
    } catch (Exception e) {
      return false; // should not throw other exceptions
    }
    
    // Scenario 2: pushing 2 tiles and checking if the head is what is updated
    try {
      tester.push(tile1);
      if(tester.peek().getColor() != Color.YELLOW) {
        return false;
      }
      tester.push(tile2);
      if(tester.peek().getColor() != Color.ORANGE) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 3: pushing until size is 4 tiles and checking if the head is what is updated
    try {
      tester.push(tile3);
      tester.push(tile4);
      if(tester.peek().getColor() != Color.BLACK) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 4: push a null Tile
    try {
      tester.push(tile5);
    } catch (IllegalArgumentException iae) {
      // expected behavior
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw other exceptions
    }
    
    return true;
  }
  
  /**
   * Test the pop() and peek() methods in the TileStack class
   * <p>
   * @return true if all tests pass, false if any fail
   */
  private static boolean testPop() {
    Tile tile1 = new Tile(Color.YELLOW);
    Tile tile2 = new Tile(Color.ORANGE);
    Tile tile3 = new Tile(Color.BLUE);
    Tile tile4 = new Tile(Color.BLACK);
    
    TileStack tester = new TileStack();
    
    tester.push(tile4);
    tester.push(tile3);
    tester.push(tile2);
    tester.push(tile1);
    
    // Scenario 1: popping one element
    try {
      Tile store = tester.pop();
      if (store.getColor() != Color.YELLOW) {
        return false;
      }
      if (tester.peek().getColor() != Color.ORANGE) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 2: popping more elements
    try {
      Tile store = tester.pop();
      Tile store2 = tester.pop();
      if (store.getColor() != Color.ORANGE) {
        return false;
      }
      if (store2.getColor() != Color.BLUE) {
        return false;
      }
      if (tester.peek().getColor() != Color.BLACK) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 3: popping last element, peek should throw exception
    try {
      Tile store = tester.pop();
      if (store.getColor() != Color.BLACK) {
        return false;
      }
      tester.peek();
    } catch (EmptyStackException ese) {
      // expected behavior
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw other exception
    }
    
    // Scenario 4: popping in empty stack
    try {
      tester.pop();
    } catch (EmptyStackException ese) {
      // expected behavior
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw other exception
    }
    
    return true;
  }
  
  /**
   * Tests the size() and isEmpty() methods in the TileStack class
   * <p>
   * @return true if all tests pass, false if any fail
   */
  private static boolean testSizeAndIsEmpty() {
    Tile tile1 = new Tile(Color.YELLOW);
    Tile tile2 = new Tile(Color.ORANGE);
    Tile tile3 = new Tile(Color.BLUE);
    Tile tile4 = new Tile(Color.BLACK);
    
    TileStack tester = new TileStack();
    
    // Scenario 1: empty stack
    try {
      if (tester.size() != 0) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    try {
      if (!tester.isEmpty()) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    tester.push(tile4);
    tester.push(tile3);
    tester.push(tile2);
    tester.push(tile1);
    // Scenario 2: array of 4 elements
    try {
      if (tester.size() != 4) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    try {
      if (tester.isEmpty()) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    tester.pop();
    tester.pop();
    // Scenario 3: after popping element, check size is updated
    try {
      if (tester.size() != 2) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    try {
      if (tester.isEmpty()) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    tester.pop();
    tester.pop();
    // Scenario 4: popping to empty stack
    try {
      if (tester.size() != 0) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    try {
      if (!tester.isEmpty()) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    return true;
  }
  
  /**
   * Tests the iterator created in the TileStack class
   * <p>
   * @return true if all tests pass, false if any fail
   */
  private static boolean testIterator() {
    // tester is pretty much the same as the one in tileListIteratorTester()
    Tile tile1 = new Tile(Color.YELLOW);
    Tile tile2 = new Tile(Color.ORANGE);
    Tile tile3 = new Tile(Color.BLUE);
    Tile tile4 = new Tile(Color.BLACK);
    Tile testTo1 = new Tile(Color.YELLOW);
    Tile testTo2 = new Tile(Color.ORANGE);
    Tile testTo3 = new Tile(Color.BLUE);
    Tile testTo4 = new Tile(Color.BLACK);
    
    TileStack hold = new TileStack();
    hold.push(tile4);
    hold.push(tile3);
    hold.push(tile2);
    hold.push(tile1);
    
    Iterator<Tile> tester = hold.iterator();
    
    // First iteration
    try {
      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(testTo1)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Second iteration
    try {
      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(testTo2)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Third iteration
    try {
      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(testTo3)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Fourth iteration
    try {
      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(testTo4)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Fifth iteration: no items left
    try {
      if (tester.hasNext()) {
        return false;
      }
      tester.next();
    } catch (NoSuchElementException nse) {
      // expected behavior
    } catch (Exception e) {
      e.printStackTrace();
      return false; // not expected exception
    }
    
    return true;
  }
  
  /**
   * Tester for the TileMatchingGame class
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean tileMatchingGameTester() {
    if (!testBasicGame()) {
      return false;
    }
    if (!testRestartandClear()) {
      return false;
    }
    if (!testToString()) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the basic game functions, like creating the game and the functionality of dropping tiles
   * <p>
   * @return true if all tests pass, false if any fail
   */
  private static boolean testBasicGame() {
    Tile tile1 = new Tile(Color.YELLOW);
    Tile tile2 = new Tile(Color.ORANGE);
    Tile tile3 = new Tile(Color.BLUE);
    Tile tile4 = new Tile(Color.BLACK);
    Tile tile5 = new Tile(Color.YELLOW);
    Tile tile6 = new Tile(Color.ORANGE);
    
    // Scenario 1: create an empty game
    try {
      TileMatchingGame tester = new TileMatchingGame(3);
      if (tester.getColumnsNumber() != 3) {
        return false;
      }
      if (!tester.column​(0).equals("")) {
        return false;
      }
      if (!tester.column​(1).equals("")) {
        return false;
      }
      if (!tester.column​(2).equals("")) {
        return false;
      }
      // Scenario 1.a: out of bounds column
      try {
        tester.column​(3);
        return false; // should not pass without exception
      } catch (IndexOutOfBoundsException iobe) {
        // expected behavior
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    TileMatchingGame tester = new TileMatchingGame(3);
    // Scenario 2: dropping one tile
    try {
      if (tester.getColumnsNumber() != 3) {
        return false;
      }
      tester.dropTile​(tile1, 0);
      if (!tester.column​(0).equals("YELLOW ")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 3: dropping all unique tiles to the game
    try {
      tester.dropTile​(tile2, 1);
      tester.dropTile​(tile3, 1);
      tester.dropTile​(tile4, 2);
      if (!tester.column​(0).equals("YELLOW ")) {
        return false;
      }
      if (!tester.column​(1).equals("BLUE ORANGE ")) {
        return false;
      }
      if (!tester.column​(2).equals("BLACK ")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 4: drop a tile that is at top of stack
    try {
      tester.dropTile​(tile5, 0);
      if (!tester.column​(0).equals("")) {
        return false;
      }
      if (!tester.column​(1).equals("BLUE ORANGE ")) {
        return false;
      }
      if (!tester.column​(2).equals("BLACK ")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }

    // Scenario 5: drop a tile that is in a stack but not at the top
    try {
      tester.dropTile​(tile6, 1);
      if (!tester.column​(0).equals("")) {
        return false;
      }
      if (!tester.column​(1).equals("ORANGE BLUE ORANGE ")) {
        return false;
      }
      if (!tester.column​(2).equals("BLACK ")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 6: dropping null tile
    try {
      tester.dropTile​(null, 0);
      return false;
    } catch (IllegalArgumentException iae) {
      // expected behavior
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw other exceptions
    }
    
    // Scenario 7.a: drop at an invalid index
    try {
      tester.dropTile​(tile6, 9);
      return false;
    } catch (IndexOutOfBoundsException iae) {
      // expected behavior
    }
    try {
      tester.dropTile​(tile6, -1);
      return false;
    } catch (IndexOutOfBoundsException iae) {
      // expected behavior
    }
    
    // Scenario 8.a: invalid game (columns is less than or equal to 0)
    try {
      TileMatchingGame bad = new TileMatchingGame(-3);
      return false;
    } catch (IllegalArgumentException iae) {
      // expected behavior
    }
    // Scenario 8.b: invalid game
    try {
      TileMatchingGame bad = new TileMatchingGame(0);
      return false;
    } catch (IllegalArgumentException iae) {
      // expected behavior
    }
    
    return true;
  }
  
  /**
   * Tests the restartGame() and clearColumn() methods in the TileMatchingGame class
   * <p>
   * @return true if all tests pass, false if any fail
   */
  private static boolean testRestartandClear() {
    Tile tile1 = new Tile(Color.YELLOW);
    Tile tile2 = new Tile(Color.ORANGE);
    Tile tile3 = new Tile(Color.BLUE);
    Tile tile4 = new Tile(Color.BLACK);
    Tile tile5 = new Tile(Color.YELLOW);
    Tile tile6 = new Tile(Color.ORANGE);
    
    TileMatchingGame tester = new TileMatchingGame(3);
    tester.dropTile​(tile1, 0);
    tester.dropTile​(tile2, 1);
    tester.dropTile​(tile3, 1);
    tester.dropTile​(tile4, 2);
    tester.dropTile​(tile5, 2);
    tester.dropTile​(tile6, 1);
    
    // Make sure game is set up correctly
    try {
      if (!tester.toString().equals("GAME COLUMNS:\n"
          + "0: YELLOW\n"
          + "1: ORANGE BLUE ORANGE\n"
          + "2: YELLOW BLACK\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 1: clear first column
    try {
      tester.clearColumn​(0);
      if (!tester.toString().equals("GAME COLUMNS:\n"
          + "0:\n"
          + "1: ORANGE BLUE ORANGE\n"
          + "2: YELLOW BLACK\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 2.a: clear already empty column
    try {
      tester.clearColumn​(0);
      if (!tester.toString().equals("GAME COLUMNS:\n"
          + "0:\n"
          + "1: ORANGE BLUE ORANGE\n"
          + "2: YELLOW BLACK\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    // Scenario 2.b: clear a different column
    try {
      tester.clearColumn​(2);
      if (!tester.toString().equals("GAME COLUMNS:\n"
          + "0:\n"
          + "1: ORANGE BLUE ORANGE\n"
          + "2:\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 3.a: clear invalid column
    try {
      tester.clearColumn​(9);
      return false;
    } catch (IndexOutOfBoundsException iobe) {
      //expected behavior
    }
    // Scenario 3.b: clear invalid column
    try {
      tester.clearColumn​(-3);
      return false;
    } catch (IndexOutOfBoundsException iobe) {
      //expected behavior
    }
    
    // Scenario 4: restart the game
    try {
      tester.restartGame();
      if (!tester.toString().equals("GAME COLUMNS:\n"
          + "0:\n"
          + "1:\n"
          + "2:\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    return true;
  }
  
  /**
   * Tests the toString() and column() methods in the TileMatchingGame class
   * <p>
   * @return true if all tests pass, false if any fail
   */
  private static boolean testToString() {
    Tile tile1 = new Tile(Color.YELLOW);
    Tile tile2 = new Tile(Color.ORANGE);
    Tile tile3 = new Tile(Color.BLUE);
    Tile tile4 = new Tile(Color.BLACK);
    Tile tile5 = new Tile(Color.YELLOW);
    Tile tile6 = new Tile(Color.ORANGE);
    
    TileMatchingGame tester = new TileMatchingGame(3);
    
    // Scenario 1: new game
    try {
      String store = tester.toString();
      String anyCol = tester.column​(2);
      if (!store.equals("GAME COLUMNS:\n"
          + "0:\n"
          + "1:\n"
          + "2:\n")) {
        return false;
      }
      if (!anyCol.equals("")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 2: dropping one tile
    tester.dropTile​(tile1, 0);
    try {
      String store = tester.toString();
      String anyCol = tester.column​(0);
      String anyCol2 = tester.column​(2);
      if(!store.equals("GAME COLUMNS:\n"
          + "0: YELLOW\n"
          + "1:\n"
          + "2:\n")) {
        return false;
      }
      if (!anyCol.equals("YELLOW ")) {
        return false;
      }
      if (!anyCol2.equals("")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 3: dropping all tiles into one column
    tester.restartGame();
    tester.dropTile​(tile1, 0);
    tester.dropTile​(tile2, 0);
    tester.dropTile​(tile3, 0);
    tester.dropTile​(tile4, 0);
    tester.dropTile​(tile5, 0);
    tester.dropTile​(tile6, 0);
    try {
      String store = tester.toString();
      String anyCol = tester.column​(0);
      String anyCol2 = tester.column​(1);
      if(!store.equals("GAME COLUMNS:\n"
          + "0: ORANGE YELLOW BLACK BLUE ORANGE YELLOW\n"
          + "1:\n"
          + "2:\n")) {
        return false;
      }
      if (!anyCol.equals("ORANGE YELLOW BLACK BLUE ORANGE YELLOW ")) {
        return false;
      }
      if (!anyCol2.equals("")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 4: drop tiles to remove
    tester.dropTile​(tile6, 0);
    tester.dropTile​(tile5, 0);
    tester.dropTile​(tile4, 0);
    tester.dropTile​(tile6, 2);
    try {
      String store = tester.toString();
      String anyCol = tester.column​(0);
      String anyCol2 = tester.column​(1);
      String anyCol3 = tester.column​(2);
      if(!store.equals("GAME COLUMNS:\n"
          + "0: BLUE ORANGE YELLOW\n"
          + "1:\n"
          + "2: ORANGE\n")) {
        return false;
      }
      if (!anyCol.equals("BLUE ORANGE YELLOW ")) {
        return false;
      }
      if (!anyCol2.equals("")) {
        return false;
      }
      if (!anyCol3.equals("ORANGE ")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 5.a: out of bounds column
    try {
      String anyCol = tester.column​(9);
      return false;
    } catch (IndexOutOfBoundsException iobe) {
      // expected behavior
    }
    // Scenario 5.b: out of bounds column
    try {
      String anyCol = tester.column​(-3);
      return false;
    } catch (IndexOutOfBoundsException iobe) {
      // expected behavior
    }
    
    return true;
  }
  
  /**
   * Runs all of the tester methods
   * <p>
   * @return true if all testers pass, false if any fail
   */
  public static boolean runAllTests() {
    if (!tileEqualsTester()) {
      return false;
    }
    if (!tileListIteratorTester()) {
      return false;
    }
    if (!tileStackTester()) {
      return false;
    }
    if (!tileMatchingGameTester()) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Main method in the TileMatchingTester class
   * <p>
   * @param args is the command line arguments
   */
  public static void main(String[] args) {
    //System.out.println(tileEqualsTester());
    //System.out.println(tileListIteratorTester());
    //System.out.println(tileStackTester());
    //System.out.println(tileMatchingGameTester());
    System.out.println(runAllTests());
    
    /*
    Tile tile1 = new Tile(Color.YELLOW);
    Tile tile2 = new Tile(Color.ORANGE);
    Tile tile3 = new Tile(Color.BLUE);
    Tile tile4 = new Tile(Color.BLACK);
    Tile tile5 = new Tile(Color.YELLOW);
    Tile tile6 = new Tile(Color.ORANGE);
    
    TileMatchingGame tester = new TileMatchingGame(3);
    tester.dropTile​(tile1, 0);
    tester.dropTile​(tile2, 0);
    tester.dropTile​(tile3, 0);
    tester.dropTile​(tile4, 0);
    tester.dropTile​(tile5, 0);
    tester.dropTile​(tile6, 0);
    //tester.restartGame();;
    //System.out.println(tester.column​(0));
    //System.out.println(tester.toString());
    //System.out.println(tester.column​(1));
     */
  }

}
