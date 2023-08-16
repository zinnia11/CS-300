//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Tile Matching Game: Tile Iterator 
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that defines an Iterator for a stack of Tile objects. Implements the Iterator<Tile> class
 * and related methods
 * <p>
 * @author zinnianie
 */
public class TileListIterator implements Iterator<Tile> { 
  
  /***** DATA FIELDS *****/
  
  private Node nextElem;
  
  /***** CONSTRUCTORS *****/
  
  /**
   * Creates a new TileListIterator with the head of the list it will iterate through
   * <p>
   * @param head the Node the iterator will start on
   */
  public TileListIterator(Node head) {
    this.nextElem = head;
  }

  /***** METHODS *****/
  
  /**
   * Returns whether the iterator has more items in it
   * <p>
   * @return true if the iterator has more elements
   */
  @Override
  public boolean hasNext() {
    if (nextElem != null) {
      return true;
    }
    return false;
  }

  /**
   * Returns the next Tile in the sequence
   * <p>
   * @return the next Tile
   * @throws NoSuchElementException when there are no more elements in the sequence
   */
  @Override
  public Tile next() throws NoSuchElementException {
    if (!this.hasNext()) {
      throw new NoSuchElementException("No tiles left to iterate");
    }
    
    Tile retur = this.nextElem.getTile();
    nextElem = this.nextElem.getNext();
    return retur;
  }
  
}
