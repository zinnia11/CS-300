//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Binary BookShelf: Binary Bookshelf Tester class
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
 * Tester class for P10 Binary Bookshelf. Tests all of the classes and methods that I wrote in many 
 * different scenarios and cases, including any edge cases to make sure that their implementation 
 * works the way it is supposed to.
 * <p>
 * @author zinnianie
 */
public class BinaryBookshelfTester {
  
  /**
   * Tester for the TreeNode class. Tests all of the methods contained in the class in many 
   * different scenarios
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean testTreeNode() {
    // Scenario 1: Single TreeNode with no children
    try {
      TreeNode<Integer> tester = new TreeNode<Integer>(11);
      // children are null
      if (tester.getLeft() != null) {
        return false;
      }
      if (tester.getRight() != null) {
        return false;
      }
      if (tester.getData() != 11) {
        return false;
      }
      if (!tester.toString().equals("11")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 2.a: collection of TreeNodes
    try {
      TreeNode<Integer> root = new TreeNode<Integer>(11);
      TreeNode<Integer> left = new TreeNode<Integer>(10);
      
      // one child
      root.setLeft​(left);
      if (root.getLeft().getData() != 10) {
        return false;
      }
      if (root.getRight() != null) {
        return false;
      }
      
      // set back to null
      root.setLeft​(null);
      if (root.getLeft() != null) {
        return false;
      }     
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    // Scenario 2.b: a series of nodes
    try {
      TreeNode<Integer> root = new TreeNode<Integer>(11);
      TreeNode<Integer> left = new TreeNode<Integer>(10);
      TreeNode<Integer> right = new TreeNode<Integer>(9);
      TreeNode<Integer> rightLeft = new TreeNode<Integer>(2);
      TreeNode<Integer> rightRight = new TreeNode<Integer>(1);
      
      root.setLeft​(left);
      root.setRight​(right);
      right.setLeft​(rightLeft);
      right.setRight​(rightRight);
      if (root.getLeft().getData() != 10) {
        return false;
      }
      if (root.getRight().getData() != 9) {
        return false;
      }
      if (root.getRight().getLeft().getData() != 2) {
        return false;
      }
      if (root.getRight().getRight().getData() != 1) {
        return false;
      }    
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 3: multiple-arg constructor
    try {
      TreeNode<Integer> left = new TreeNode<Integer>(10);
      TreeNode<Integer> right = new TreeNode<Integer>(9);
      TreeNode<Integer> root = new TreeNode<Integer>(11, left, right);
      
      if (root.getLeft().getData() != 10) {
        return false;
      }
      if (root.getRight().getData() != 9) {
        return false;
      }
      if (!root.getLeft().toString().equals("10")) {
        return false;
      }
      if (!root.getRight().toString().equals("9")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    return true;
  }
  
  /**
   * Tester for the new equals(Book) method in the Book class. Tests that method in various 
   * scenarios
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean testEquals() {
    Book.resetGenerator();
    Book test = new Book("nothing", 12, "n", "n"); // ID is 0
    Book.resetGenerator(); // ID back to 0
    Book sameOne = new Book("nothing", 12, "n", "n"); 
    Book.resetGenerator();
    Book diffOne = new Book("anything", 13, "a", "a");
    Book diffByID = new Book("nothing", 13, "n", "n");
    Book.resetGenerator();
    Book diffByPage = new Book("nothing", 14, "n", "n");
    Book.resetGenerator();
    Book diffByTitle = new Book("Thing", 13, "n", "n");
    Book.resetGenerator();
    Book diffByAuthor = new Book("nothing", 13, "t", "t");
    
    // Scenario 1: same book
    try {
      if (!test.equals(sameOne)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Scenario 2.a: different book
    try {
      if (test.equals(diffOne)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // Scenario 2.b: book differs by ID only
    try {
      if (test.equals(diffByID)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // Scenario 2.c: book differs by page count only
    try {
      if (test.equals(diffByPage)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // Scenario 2.d: book differs by title only
    try {
      if (test.equals(diffByTitle)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // Scenario 2.e: book differs by author only
    try {
      if (test.equals(diffByAuthor)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Scenario 3: test to null
    try {
      if (test.equals(null)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Scenario 4: test to not a Book
    try {
      if (test.equals(new String("bleh"))) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tester for the constructor in the BinaryBookshelf Class. Makes several different trees in 
   * many different scenarios
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean testEmptyTree() {
    Book.resetGenerator();
    // Scenario 1: invalid constructor inputs
    try {
      // 1.a: empty attribute array
      Attribute[] list1 = new Attribute[0];
      BinaryBookshelf test1 = new BinaryBookshelf(list1);
      return false; // should never get here
    } catch (IllegalArgumentException iae) {
      // expected behavior
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw other exceptions
    }
    try {
      // 1.b attribute array of length greater than 4
      Attribute[] list2 = {Attribute.AUTHOR, Attribute.ID, Attribute.PAGECOUNT, Attribute.TITLE
          , Attribute.AUTHOR};
      BinaryBookshelf test2 = new BinaryBookshelf(list2);
      return false;
    } catch (IllegalArgumentException iae) {
      // expected behavior
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw other exceptions
    }
    try {
      // 1.c attribute array of length less than 4
      Attribute[] list3 = {Attribute.AUTHOR, Attribute.ID, Attribute.PAGECOUNT};
      BinaryBookshelf test3 = new BinaryBookshelf(list3);
      return false;
    } catch (IllegalArgumentException iae) {
      // expected behavior
    } catch (Exception e){
      e.printStackTrace();
      return false; // should not throw other exceptions
    }
    try {
      // 1.d elements of same attribute
      Attribute[] list4 = {Attribute.AUTHOR, Attribute.ID, Attribute.PAGECOUNT, Attribute.ID};
      BinaryBookshelf test4 = new BinaryBookshelf(list4);
      return false;
    } catch (IllegalArgumentException iae) {
      // expected behavior
    } catch (Exception e){
      e.printStackTrace();
      return false; // should not throw other exceptions
    }
    try {
      // 1.e author not first attribute
      Attribute[] list5 = {Attribute.ID, Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.TITLE};
      BinaryBookshelf test5 = new BinaryBookshelf(list5);
      return false;
    } catch (IllegalArgumentException iae) {
      // expected behavior
    } catch (Exception e){
      e.printStackTrace();
      return false; // should not throw other exceptions
    }
    
    // Scenario 2: valid input
    try {
      Attribute[] list = {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
      BinaryBookshelf test = new BinaryBookshelf(list);
      if (test.size() != 0) {
        return false;
      }
      if (!test.isEmpty()) {
        return false;
      }
      if (test.toString() != "") {
        return false;
      }
      if(test.getRoot() != null) {
        return false;
      }
      String testTo = "1:AUTHOR 2:TITLE 3:ID 4:PAGECOUNT";
      if (!test.getAttributeOrder().equals(testTo)) {
        return false;
      }
      if (test.contains​(new Book("a", 1))) {
        return false;
      }
      if (!test.getBooksByAuthor​("bleh").equals(new ArrayList<Book>())) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    return true;
  }
  
  /**
   * Tester for the insertBook() method in the BinaryBookshelf class. Tests this method in many 
   * different scenarios
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean testInsertBook() {
    Book.resetGenerator();
    Attribute[] list = {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
    BinaryBookshelf test = new BinaryBookshelf(list);
    Book book3 = new Book("nothing", 12, "n", "n");
    
    // Scenario 1: insert into empty tree
    try {
      if (!test.isEmpty()) {
        return false;
      }
      test.insertBook​(book3);
      if (test.isEmpty()) {
        return false;
      }
      if (!test.getRoot().getData().equals(book3)) {
        return false;
      }
      if (test.size() != 1) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Scenario 2: insert a smaller value
    Book book2 = new Book("anything", 13, "a", "a");
    try {
      test.insertBook​(book2);
      if (!test.getRoot().getLeft().getData().equals(book2)) {
        return false;
      }
      if (test.size() != 2) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Scenario 3: insert larger value with similar author attribute
    Book book1 = new Book("something", 14, "n", "n");
    try {
      test.insertBook​(book1);
      if (!test.getRoot().getRight().getData().equals(book1)) {
        return false;
      }
      if (test.size() != 3) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Scenario 4: insert duplicate value
    try {
      test.insertBook​(book2);
      return false;
    } catch (IllegalArgumentException iae) {
      // expected behavior
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Scenario 5: tree of 6 values
    Book book4 = new Book("everything", 14, "e", "e");
    Book book5 = new Book("thing", 13, "t", "t");
    Book book6 = new Book("other thing", 12, "o", "o");
    BinaryBookshelf test2 = new BinaryBookshelf(list);
    try {
      test2.insertBook​(book3);
      test2.insertBook​(book2);
      test2.insertBook​(book1);
      test2.insertBook​(book4);
      test2.insertBook​(book5);
      test2.insertBook​(book6);
      if (!test2.getRoot().getData().equals(book3)) {
        return false;
      }
      if (!test2.getRoot().getLeft().getData().equals(book2)) {
        return false;
      }
      if (!test2.getRoot().getLeft().getRight().getData().equals(book4)) {
        return false;
      }
      if (!test2.getRoot().getRight().getData().equals(book1)) {
        return false;
      }
      if (test2.size() != 6) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      test2.clear();
      test2.insertBook​(book6);
      test2.insertBook​(book5);
      test2.insertBook​(book4);
      test2.insertBook​(book3);
      test2.insertBook​(book2);
      test2.insertBook​(book1);
      if (!test2.getRoot().getData().equals(book6)) {
        return false;
      }
      if (!test2.getRoot().getLeft().getData().equals(book4)) {
        return false;
      }
      if (!test2.getRoot().getLeft().getRight().getData().equals(book3)) {
        return false;
      }
      if (!test2.getRoot().getRight().getData().equals(book5)) {
        return false;
      }
      if (test2.size() != 6) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tester for the contains() method in the BinaryBookshelf class. Tests this method in many 
   * different scenarios
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean testContains() {
    Book.resetGenerator();
    Attribute[] list = {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
    BinaryBookshelf test = new BinaryBookshelf(list);
    Book book1 = new Book("something", 14, "n", "n");
    test.insertBook​(book1);
    
    // Scenario 1: non-recursive case
    try {
      if (!test.contains​(book1)) {
        return false;
      }
      if (test.contains​(new Book("a", 12))) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    Book book2 = new Book("anything", 13, "a", "a");
    Book book3 = new Book("nothing", 12, "n", "n");
    Book book4 = new Book("everything", 14, "e", "e");
    Book book5 = new Book("thing", 13, "t", "t");
    Book book6 = new Book("other thing", 12, "o", "o");
    Book notIn = new Book("not in", 10, "x", "x");
    TreeNode<Book> left = new TreeNode<Book>(book4);
    TreeNode<Book> right = new TreeNode<Book>(book6);
    TreeNode<Book> rightRight = new TreeNode<Book>(book5);
    TreeNode<Book> leftLeft = new TreeNode<Book>(book2);
    TreeNode<Book> leftRight = new TreeNode<Book>(book3);
    
    // Scenario 2: Recursive case
    // building a tree from scratch
    test.getRoot().setLeft​(left);
    test.getRoot().setRight​(right);
    test.getRoot().getRight().setRight​(rightRight);
    test.getRoot().getLeft().setLeft​(leftLeft);
    test.getRoot().getLeft().setRight​(leftRight);
    try {
      // book at root
      if (!test.contains​(book1)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw other exception
    }
    try {
      // leaf node
      if (!test.contains​(book2)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw other exception
    }
    try {
      // internal node
      if (!test.contains​(book6)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw other exception
    }
    try {
      // book not in the tree
      if (test.contains​(notIn)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw other exception
    }
    
    return true;
  }

  /**
   * Tester for the getBooksByAuthor() method in the BinaryBookshelf class. Tests this method in
   * many different scenarios
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean testGetBooksByAuthor() {
    Book.resetGenerator();
    Attribute[] list = {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
    BinaryBookshelf test = new BinaryBookshelf(list);
    Book book1 = new Book("something", 14, "John", "N");
    test.insertBook​(book1);
    
    // Scenario 1: non recursive case
    try {
      ArrayList<Book> hold = test.getBooksByAuthor​("John, N");
      if (hold.size() != 1) {
        return false;
      }
      hold = test.getBooksByAuthor​("Not, here");
      if (hold.size() != 0) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    Book book2 = new Book("anything", 13, "John", "N");
    Book book3 = new Book("nothing", 12, "John", "N");
    Book book4 = new Book("everything", 14, "Abby", "B");
    Book book5 = new Book("thing", 13, "t", "t");
    Book book6 = new Book("other thing", 12, "o", "o");
    TreeNode<Book> left = new TreeNode<Book>(book2);
    TreeNode<Book> right = new TreeNode<Book>(book6);
    TreeNode<Book> rightRight = new TreeNode<Book>(book5);
    TreeNode<Book> leftLeft = new TreeNode<Book>(book4);
    TreeNode<Book> leftRight = new TreeNode<Book>(book3);
    
    // Scenario 2: Recursive cases
    // building a tree from scratch
    test.getRoot().setLeft​(left);
    test.getRoot().setRight​(right);
    test.getRoot().getRight().setRight​(rightRight);
    test.getRoot().getLeft().setLeft​(leftLeft);
    test.getRoot().getLeft().setRight​(leftRight);
    try {
      ArrayList<Book> hold = test.getBooksByAuthor​("Abby, B");
      if (hold.size() != 1) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ArrayList<Book> hold = test.getBooksByAuthor​("John, N");
      if (hold.size() != 3) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ArrayList<Book> hold = test.getBooksByAuthor​("Zoe, H");
      if (hold.size() != 0) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Runs all the tests in this tester class
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean runAllTests() {
    if (!testTreeNode()) {
      return false;
    }
    if (!testEquals()) {
      return false;
    }
    if (!testEmptyTree()) {
      return false;
    }
    if (!testInsertBook()) {
      return false;
    }
    if (!testContains()) {
      return false;
    }
    if (!testGetBooksByAuthor()) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Main method of the BinaryBookshelfTester class. It is used for testing.
   * <p>
   * @param args is the command line arguments
   */
  public static void main(String[] args) {
    Book.resetGenerator();
    System.out.println(testTreeNode());
    System.out.println(testEquals());
    System.out.println(testEmptyTree());
    System.out.println(testInsertBook());
    System.out.println(testContains());
    System.out.println(testGetBooksByAuthor());
    System.out.println(runAllTests());
    
    /*
    Book.resetGenerator();
    Book book1 = new Book("something", 14, "John", "N");
    Book book2 = new Book("anything", 13, "John", "N");
    Book book3 = new Book("nothing", 12, "John", "N");
    Book book4 = new Book("everything", 14, "Abby", "B");
    Book book5 = new Book("thing", 13, "t", "t");
    Book book6 = new Book("other thing", 12, "o", "o");
    
    Attribute[] list = {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
    
    BinaryBookshelf test = new BinaryBookshelf(list);
    
    test.insertBook​(book1);
    test.insertBook​(book2);
    test.insertBook​(book6);
    test.insertBook​(book5);
    test.insertBook​(book4);
    test.insertBook​(book3);
    
    System.out.print(test.getBooksByAuthor​("Zoe, H").toString());
    //System.out.println(test.toString());
    */
    
  }
}
