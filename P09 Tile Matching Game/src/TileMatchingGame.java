//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Tile Matching Game: the actual game
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

/**
 * This class defines methods to play a simple tile matching game using an array of stacks
 * <p>
 * @author zinnianie
 */
public class TileMatchingGame {
  
  /***** DATA FIELDS *****/
  
  TileStack[] columns; // the board for the game
  
  /***** CONSTRUCTOR *****/
  
  /**
   * Creates a new TileMatchingGame with the specified number of columns
   * <p>
   * @param colCount number of columns the game will have
   * @throws IllegalArgumentException when the inputed number of columns in less that or equal to 0
   */
  public TileMatchingGame(int colCount) throws IllegalArgumentException {
    if (colCount <= 0) {
      throw new IllegalArgumentException("Size of the game cannot be less than or equal to 0");
    }
    
    columns = new TileStack[colCount];
    for (int i=0; i<colCount; i++) {
      columns[i] = new TileStack();
    }
  }
  
  /***** METHODS *****/
  
  /**
   * Gets the number of columns in this TileMatchingGame
   * @return number that is number of columns in the game
   */
  public int getColumnsNumber() {
    return this.columns.length;
  }
  
  /**
   * Drops a tile at the top of the stack located at the given column index. If tile will be 
   * dropped at the top of an equal tile (of same color), both tiles will be removed from the stack
   * of tiles at column index.
   * <p>
   * @param tile that will be dropped at the given spot
   * @param index of the column the tile will be dropped
   * @throws IndexOutOfBoundsException if the index provided is outside the array of TileStacks
   */
  public void dropTile​(Tile tile, int index) {
    if (columns[index].isEmpty()) {
      columns[index].push(tile);
    } else {
      if (columns[index].peek().equals(tile)) {
        columns[index].pop();
      } else {
        columns[index].push(tile);
      }
    }
  }
  
  /**
   * Removes all the tiles from a column with a given index
   * <p>
   * @param index of the columns that will be cleared
   * @throws IndexOutOfBoundsException if the index provided is outside the array of TileStacks
   */
  public void clearColumn​(int index) {
    while (!columns[index].isEmpty()) {
      columns[index].pop();
    }
  }
  
  /**
   * Restarts the game. All stacks of tiles in the different columns of this game will be empty.
   */
  public void restartGame() {
    //int colCount = this.getColumnsNumber();
    //columns = new TileStack[colCount];
    for (int i=0; i<this.getColumnsNumber(); i++) {
      this.clearColumn​(i);
    }
  }
  
  /**
   * Returns a string representation of the stack of tiles at a given column index, and an empty 
   * string "" if the stack is empty.
   * @param index of the column that will be printed out
   * @return String representation of the column
   * @throws IndexOutOfBoundsException if the index provided is outside the array of TileStacks
   */
  public String column​(int index) {
    String store = "";
    
    for (Tile t : columns[index]) {
      store += t.toString();
      store += " ";
    }
    
    return store;
  }

  /**
   * Returns a string representation of this tile matching game
   * <p>
   * @return String representation of this TileMatchingGame
   */
  public String toString() {
    String store = "GAME COLUMNS:\n";
    
    for (int i=0; i<this.getColumnsNumber(); i++) {
      store += i + ":";
      for (Tile t : columns[i]) {
        store += " " + t.toString();
      }
      store += "\n";
    }
    
    return store;
  }
  
}
