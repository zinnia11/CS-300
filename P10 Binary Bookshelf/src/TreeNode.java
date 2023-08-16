//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Binary BookShelf: TreeNode class
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
 * Generic class that defines a TreeNode that contains data of generic type <T> and contains methods
 * involving the data of the TreeNode and its left and right children
 * <p>
 * @author zinnianie
 *
 * @param <T> allows the TreeNode to be able to contain any type of data
 */
public class TreeNode<T> {
  
  /***** DATA FIELDS *****/
  
  private T data;
  private TreeNode<T> left;
  private TreeNode<T> right;
  
  /***** CONSTRUCTORS *****/
  
  /**
   * Constructor for a TreeNode that has the given data and no children
   * <p>
   * @param data that will be stored in the TreeNode
   */
  public TreeNode(T data) {
    this.data = data;
  }
  
  /**
   * Constructor for a TreeNode that has the given data and the given right and left children
   * <p>
   * @param data that is contained in the TreeNode
   * @param left the TreeNode that is the left child
   * @param right the TreeNode that is the right child
   */
  public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }
  
  /***** ACCESSORS *****/
  
  /**
   * Getter for the left child
   * <p>
   * @return the left child of this TreeNode
   */
  public TreeNode<T> getLeft() {
    return this.left;
  }
  
  /**
   * Getter for the right child
   * <p>
   * @return the right child of this TreeNode
   */
  public TreeNode<T> getRight() {
    return this.right;
  }
  
  /**
   * Getter for the data in this TreeNode
   * <p>
   * @return the data contained in this TreeNode
   */
  public T getData() {
    return this.data;
  }
  
  /**
   * Returns the data in the TreeNode as a String
   * <p>
   * @return a string representation of this node's data
   */
  @Override
  public String toString() {
    return data.toString();
  }
  
  /***** MUTATORS *****/
  
  /**
   * Setter for the left child
   * <p>
   * @param left the TreeNode that will be the left child of this TreeNode
   */
  public void setLeft​(TreeNode<T> left) {
    this.left = left;
  }
  
  /**
   * Setter for the right child
   * <p>
   * @param right the TreeNode that will be the right child of this TreeNode
   */
  public void setRight​(TreeNode<T> right) {
    this.right = right;
  }
  
}
