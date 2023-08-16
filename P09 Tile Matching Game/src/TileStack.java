//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Tile Matching Game: Stack of Tiles
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

/**
 * Class that defines a TileStack which is a stack of Tile objects. Implements the Iterable<Tile>
 * and StackADT<Tile> interfaces and all related methods for stack functionality.
 * <p>
 * @author zinnianie
 */
public class TileStack implements Iterable<Tile>, StackADT<Tile>{
  
  /****** DATA FIELDS ******/
  
  private Node top; // top of the stack
  private int size; // size of the stack

  /****** CONSTRUCTORS ******/
  
  /**
   * Creates an empty stack of tiles
   */
  public TileStack() {
    // empty stack
    this.top = null;
    this.size = 0;
  }
  
  /**
   * Adds a Tile to the top of this stack
   * <p>
   * @param element Tile that will be added to this stack
   * @throws IllegalArgumentException when the input is null
   */
  @Override
  public void push(Tile element) throws IllegalArgumentException {
    if (element == null) {
      throw new IllegalArgumentException("Cannot push a null element");
    }
    
    Node add = new Node(element);
    
    add.setNext(this.top);
    top = add;
    size++;
  }

  /**
   * Removes and returns the Tile at the top of this stack 
   * <p>
   * @return Tile that is the at the top of this stack
   * @throws EmptyStackException when the stack is empty
   */
  @Override
  public Tile pop() throws EmptyStackException {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    
    Tile temp = top.getTile();
    
    top = top.getNext();
    size--;
    
    return temp;
  }

  /**
   * Returns but does not remove the Tile at the top of this stack
   * <p>
   * @return Tile that is at the top of this stack
   * @throws EmptyStackException when the stack is empty
   */
  @Override
  public Tile peek() throws EmptyStackException {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    
    Tile temp = top.getTile();
    
    return temp;
  }

  /**
   * Returns whether this stack is empty or not
   * <p>
   * @return true if there are no elements in this stack, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (this.top == null || this.size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Gets the number of Tiles in this stack
   * <p>
   * @return the size of this stack
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Creates and returns a new instance of TileListIterator
   * <p>
   * @return an iterator through this stack
   */
  @Override
  public Iterator<Tile> iterator() {
    return new TileListIterator(this.top);
  }

}
