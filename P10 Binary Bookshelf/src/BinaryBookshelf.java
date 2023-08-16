//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Binary BookShelf: Binary Bookshelf class
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

import java.util.ArrayList;

/**
 * Class outlining a BinaryBookshelf using a Binary Search Tree data structure 
 * and the related methods. Most of the methods to do with the BST are implemented recursively. 
 * <p>
 * @author zinnianie
 */
public class BinaryBookshelf {
  
  /***** DATA FIELDS *****/
  
  private TreeNode<Book> root;
  private int size;
  private Attribute[] sortList;
  
  /***** CONSTRUCTORS *****/
  
  /**
   * Constructor that initializes an empty BinaryBookshelf
   * <p>
   * @param sortList an ordered array of Attributes that must begin with AUTHOR and have 4 unique 
   * elements
   * @throws IllegalArgumentException if sortList does not have 4 elements exactly, doesn't begin 
   * with the AUTHOR Attribute, or has duplicate elements
   */
  public BinaryBookshelf(Attribute[] sortList) throws IllegalArgumentException {
    if (sortList.length != 4 || sortList == null) {
      throw new IllegalArgumentException("sortList is not a 4 element array");
    }
    if (!sortList[0].equals(Attribute.AUTHOR)) {
      throw new IllegalArgumentException("sortList doesn't have Author as the first element");
    }
    for (int i=0; i<3; i++) {
      for (int j=i+1; j<4; j++) {
        if (sortList[i].equals(sortList[j])) {
          throw new IllegalArgumentException("Duplicates are not allowed in the Attributes array");
        }
      }
    }
    
    this.sortList = sortList;
  }
  
  /***** ACCESSORS *****/
  
  /**
   * Getter for the number of nodes in the BinaryBookshelf
   * <p>
   * O(1) time complexity <br>
   * There is only one return statement in the method, because size is a data field that is 
   * maintained independently.
   * <p>
   * @return the size of the BinaryBookshelf as the number of nodes
   */
  public int size() {
    return this.size;
  }
  
  /**
   * Determine whether the bookshelf is empty or not
   * <p>
   * O(1) time complexity <br>
   * To test if the tree is empty or not, you only have to use the already existing data fields.
   * Like check if size is 0 and the root node is null. There are no loops or recursion involved.
   * <p>
   * @return true if the tree is empty, false otherwise
   */
  public boolean isEmpty() {
    if (this.size == 0 && root == null) {
      return true;
    }
    return false;
  }
  
  /**
   * Gets a formatted list of the sortList of Attributes in the order they are used
   * ex: "1:AUTHOR 2:PAGECOUNT 3:TITLE 4:ID"
   * <p>
   * @return a formatted String or the list of sorting Attributes
   */
  public String getAttributeOrder() {
    String toReturn = "";
    
    for (int i=0; i<sortList.length; i++) {
      toReturn += (i+1) + ":" + sortList[i];
      if (i != 3) {
        toReturn += " ";
      }
    }
    return toReturn;
  }
  
  /***** OTHER METHODS *****/
  
  /**
   * Searches for the input book in the bookshelf recursively
   * <p>
   * O(N) time complexity <br> 
   * Worst case complexity would be a poorly built tree that is just a linked list.
   * <p>
   * @param book to search for in the bookshelf
   * @return true if that book exists in the bookshelf, false otherwise
   */
  public boolean contains​(Book book) {
    // empty shelf returns false with no exception
    if (this.isEmpty()) {
      return false;
    }
    // else return the result of the recursive helper
    return containsHelper​(book, root);
  }
  
  /**
   * Helper method to implement the recursion of the contains() method
   * <p>
   * @param book to search for in the bookshelf
   * @param current the current root node of the subtree
   * @return true if the book exists in the bookshelf, false otherwise
   */
  protected boolean containsHelper​(Book book, TreeNode<Book> current) {
    if (current != null) {
      // base case: found the node
      if (current.getData().equals(book)) {
        return true;
      } else if (this.compareToHelper​(book, current.getData()) < 0) { // recursive on left child
        return containsHelper​(book, current.getLeft());
      } else { // recursive case of right child
        return containsHelper​(book, current.getRight());
      }
    }
    return false;
  }
  
  /**
   * Return a list of books written by the given author
   * <p>
   * @param authorName the author that is searched in the format LastName, FirstName
   * @return an ArrayList of Books 
   */
  public ArrayList<Book> getBooksByAuthor​(String authorName){
    ArrayList<Book> returner = new ArrayList<Book>();
    if (this.isEmpty()) {
      return returner;
    }
    
    return this.getBooksByAuthorHelper​(authorName, root);
  }
  
  /**
   * Helper method to implement the recursion of the getBooksByAuthor() method
   * <p>
   * @param authorName the author that is searched in the format LastName, FirstName
   * @param current the current root node of the subtree
   * @return an ArrayList of Books that has the given author name in the subtree
   */
  protected ArrayList<Book> getBooksByAuthorHelper​(String authorName, TreeNode<Book> current) {
    ArrayList<Book> returner = new ArrayList<Book>();
    
    if (current.getLeft() != null) { // recursive on left subtree
      returner.addAll(this.getBooksByAuthorHelper​(authorName, current.getLeft()));
    }
    if (current.getData().getAuthor().equals(authorName)) { // base case: author is the same
      returner.add(current.getData());
    }
    if (current.getRight() != null) { // recursive on right subtree
      returner.addAll(this.getBooksByAuthorHelper​(authorName, current.getRight()));
    }
    
    return returner;
  }
  
  /**
   * Returns a String that is the in-order traversal of the Binary Bookshelf, with each book on a
   * separate line
   * <p>
   * O(N) time complexity <br>
   * This method visits every node of the tree once and adds it to a formatted String. N in this 
   * case is the number of nodes, so the time complexity would only change based on the number 
   * of nodes.
   * <p>
   * @return a formatted String with the books in-order from the shelf
   */
  @Override
  public String toString() {
    String returner = "";
    if (this.isEmpty()) {
      return returner;
    }
    
    return toStringHelper​(root);
  }
  
  /**
   * Helper method to implement in recursion of the toString() method 
   * <p>
   * @param current the current root of the subtree
   * @return a formatted String of books from the in-order traversal of the current subtree
   */
  protected String toStringHelper​(TreeNode<Book> current) {
    String returner = "";
    if (current.getLeft() != null) {
      returner += toStringHelper​(current.getLeft());
    }
    returner += current.toString() + "\n";
    if (current.getRight() != null) {
      returner += toStringHelper​(current.getRight());
    }
    
    return returner;
  }
  
  /**
   * Returns a shallow copy of the root node so that trees can be built manually in the testers
   * <p>
   * @return the TreeNode<Book> at the root of the bookshelf
   */
  protected TreeNode<Book> getRoot() {
    return this.root;
  }
  
  /**
   * Reset the BinaryBookshelf to empty
   */
  public void clear() {
    this.root = null;
    this.size = 0;
  }
  
  /**
   * Add a new book to the BinaryBookshelf in sorted order relative to the order of the Attributes
   * in sortList
   * <p>
   * @param book to be added to the BinaryBookshelf
   * @throws IllegalArgumentException when the book already exists in the shelf
   */
  public void insertBook​(Book book) throws IllegalArgumentException{
    if (this.root == null) {
      this.root = new TreeNode<Book>(book);
      size ++;
    } else {
      insertBookHelper​(book, this.root);
    }
  }

  /**
   * Helper method to implement the recursion of the insertBook() method
   * <p>
   * @param book to be added to the BinaryBookshelf
   * @param current the current root node of the subtree
   * @throws IllegalArgumentException when the book already exists in the shelf
   */
  protected void insertBookHelper​(Book book, TreeNode<Book> current) 
      throws IllegalArgumentException{
    if (this.contains​(book)) {
      throw new IllegalArgumentException("This book is already in the bookshelf");
    }
    
    if (this.compareToHelper​(book, current.getData()) < 0) {
      if (current.getLeft() == null) {
        current.setLeft​(new TreeNode<Book>(book));
        size ++;
      } else {
        insertBookHelper​(book, current.getLeft());
      }
    } else {
      if (current.getRight() == null) {
        current.setRight​(new TreeNode<Book>(book));
        size ++;
      } else {
        insertBookHelper​(book, current.getRight());
      }
    }
  }
  
  /**
   * Helper method to compare two books according to the Attribute order in sortList
   * <p>
   * @param one first book
   * @param two book that is compared to the first book
   * @return negative value if one is less than two, positive value if one is greater than two, and
   * zero if the books are equal
   */
  protected int compareToHelper​(Book one, Book two) {
    for (int i=0; i<4; i++) {
      if (one.compareTo(two, sortList[i]) != 0) {
        return one.compareTo(two, sortList[i]);
      }
    }
    return 0;
  }
  
}
