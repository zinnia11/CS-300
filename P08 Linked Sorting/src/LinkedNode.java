//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Linking Sorting: LinkedNode instantiable class
// Course:   CS 300 Fall 2021
//
// Author:   Zinnia Nie
// Email:    zznie@wisc.edu
// Lecturer: Hobbes LeGault
//
////////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Rose Giefer
// Partner Email:   rgiefer@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Class that defines the fields of a generic LinkedNode<T> object. Contains constructors, 
 * accessors, and mutators related to the LinkedNode<T>
 * <p>
 * @param <T> makes the LinkedNode able to contain any type of data
 * <p>
 * @author Rose and Zinnia
 */
public class LinkedNode <T> {
  
  /******** DATA FIELDS ********/
  
  private T data; // what the node stores
  
  private LinkedNode<T> next; // next node in list
  
  /******* CONSTRUCTORS *******/
  
  /**
   * Creates a new LinkedNode object defining the data it contains
   * <p>
   * @param data information stored in the node
   */
  public LinkedNode(T data) {
    this.data = data;
  }
  
  /**
   * Creates a new LinkedNode object defining the data it contains and the next node it points to
   * <p>
   * @param data information stored in the node
   * @param next the next node
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }
  
  /******* ACCESSORS ********/
  
  /**
   * Gets the reference to the next node in the list
   * <p>
   * @return a LinkedNode<T> that is what is next in the list
   */
  public LinkedNode<T> getNext() {
    return this.next;
  }
  
  /**
   * Gets the data this node contains
   * <p>
   * @return data the node contains of type T
   */
  public T getData() {
    return this.data;
  }
  
  /**
   * Changes the data this node contains into a String
   * <p>
   * @return a string representation of the node's data
   */
  public String toString() {
    return data.toString();
  }
  
  /******* MUTATORS *******/
  
  /**
   * Sets the next field to the new provided node
   * <p>
   * @param next LinkedNode this one will point to
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }
  
}
