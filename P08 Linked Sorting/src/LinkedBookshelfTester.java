//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Linking Sorting: LinkedNode instantiable class
// Course: CS 300 Fall 2021
//
// Author: Zinnia Nie
// Email: zznie@wisc.edu
// Lecturer: Hobbes LeGault
//
////////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Rose Giefer
// Partner Email: rgiefer@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: N/A
// Online Sources: N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Tester class for LinkedBookshelf. Contains tests for various methods in various cases of the
 * LinkedBookshelf class
 * <p>
 * @author Rose and Zinnia
 */
public class LinkedBookshelfTester {
  /**
   * Tests various scenarios involving the generic class LinkedNode<T>
   * <p>
   * @return true if all tests pass, false if any fail
   */
  public static boolean testLinkedNode() {
    Book.resetGenerator();
    // constructor 1
    LinkedNode<Integer> node1 = new LinkedNode<Integer>(5);

    // check getData()
    if (node1.getData() != 5) {
      return false;
    }

    // check setNext() and getNext()
    LinkedNode<Integer> node2 = new LinkedNode<Integer>(10);
    node1.setNext(node2);
    if (node1.getNext() != node2) {
      return false;
    }
    if (node1.getNext().getData() != 10) {
      return false;
    }

    // check constructor 2 and longer list
    LinkedNode<Integer> node3 = new LinkedNode<Integer>(11, node2);
    node1.setNext(node3);
    if (node1.getNext().getNext() != node2) {
      return false;
    }
    if (node1.getNext().getNext().getData() != 10) {
      return false;
    }

    // check LinkedNode<Book>
    Book book1 = new Book("My Book", 100);
    Book book2 = new Book("My Book 2", 123);
    LinkedNode<Book> bookNode1 = new LinkedNode<Book>(book1);
    LinkedNode<Book> bookNode2 = new LinkedNode<Book>(book2, bookNode1);
    if (bookNode2.getNext().getData() != book1) {
      return false;
    }
    if (!bookNode2.toString().equals(book2.toString())) {
      return false;
    }

    return true;
  }

  /**
   * Tests various scenarios of LinkedBookshelf with the appendBook(Book) method
   * <p>
   * @return true if all tests pass, false if any test fails
   */
  public static boolean testAddBooks() {
    Book.resetGenerator();
    Book book1 = new Book("animal farm", 30);
    Book book2 = new Book("boris's vacation", 10);
    Book book3 = new Book("cable network schedule", 100);

    LinkedBookshelf shelf = new LinkedBookshelf();

    // Scenario 1: add three books and makes sure the head and tail of the bookshelf is correct
    try {
      shelf.appendBook(book1);
      shelf.appendBook(book2);
      shelf.appendBook(book3);

      // check book at head
      if (shelf.getFirst() != book1) {
        return false;
      }
      // check book at tail
      if (shelf.getLast() != book3) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace(); // should not throw exception
      return false;
    }

    // Scenario 2: add a fourth book and check is the tail is updated
    try {
      Book book4 = new Book("Daisy", 35);
      shelf.appendBook(book4);
      // check book at tail
      if (shelf.getLast() != book4) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }

    // Scenario 3: test the books in the middle are in the order we want
    // also testing get() and getNode()
    try {
      // check books in middle
      if (shelf.getNode(1).getData() != book2 && shelf.getNode(2).getData() != book3) {
        System.out.println(shelf.getNode(0));
        return false;
      }
      if (shelf.getNode(1).getNext().getData() != book3) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }

    // Scenario 4: testing an out of bounds index for get() and getNode()
    try {
      shelf.get(5); // throws exception
      return false;
    } catch (Exception e) {
      // expected behavior
    }
    try {
      shelf.getNode(5); // throws exception
      return false;
    } catch (Exception e) {
      // expected behavior
    }

    LinkedBookshelf shelf2 = new LinkedBookshelf();

    // Scenario 5: empty shelf
    try {
      shelf2.getFirst();
      return false; // should throw exception
    } catch (Exception e) {
      // expected behavior
    }
    try {
      shelf2.getLast();
      return false; // should throw exception
    } catch (Exception e) {
      // expected behavior
    }

    return true;
  }

  /**
   * Tests various scenarios of a LinkedBookshelf with the insertBook(Book) method
   * <p>
   * @return true if all tests pass, false if any test fails
   */
  public static boolean testInsertBooks() {
    Book.resetGenerator();
    // create a sorted shelf
    Book book1 = new Book("animal farm", 30);
    Book book2 = new Book("boris's vacation", 10);
    Book book3 = new Book("cable network schedule", 100);
    Book book5 = new Book("something", 5);

    LinkedBookshelf shelf = new LinkedBookshelf();

    shelf.appendBook(book1);
    shelf.appendBook(book2);
    shelf.appendBook(book3);
    shelf.appendBook(book5);

    LinkedBookshelf.sort(shelf, Attribute.PAGECOUNT);

    // Scenario 1: insert in middle of list
    Book book4 = new Book("daisy", 25);
    try {
      shelf.insertBook(book4);
      if (!shelf.toString()
          .equals("PAGECOUNT\n" + "3: \"something\", ,  (5)\n"
              + "1: \"boris's vacation\", ,  (10)\n" + "4: \"daisy\", ,  (25)\n"
              + "0: \"animal farm\", ,  (30)\n" + "2: \"cable network schedule\", ,  (100)\n")) {
        System.out.println("failed 1");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // do not want exception
    }

    // Scenario 2: insert at end of list
    Book book6 = new Book("anything", 120);
    try {
      shelf.insertBook(book6);
      if (!shelf.toString().equals(
          "PAGECOUNT\n" + "3: \"something\", ,  (5)\n" + "1: \"boris's vacation\", ,  (10)\n"
              + "4: \"daisy\", ,  (25)\n" + "0: \"animal farm\", ,  (30)\n"
              + "2: \"cable network schedule\", ,  (100)\n" + "5: \"anything\", ,  (120)\n")) {
        System.out.println("failed 2");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // do not want exception
    }

    // Scenario 3: insert at beginning of list
    Book book7 = new Book("thing", 2);
    try {
      shelf.insertBook(book7);
      if (!shelf.toString()
          .equals("PAGECOUNT\n" + "6: \"thing\", ,  (2)\n" + "3: \"something\", ,  (5)\n"
              + "1: \"boris's vacation\", ,  (10)\n" + "4: \"daisy\", ,  (25)\n"
              + "0: \"animal farm\", ,  (30)\n" + "2: \"cable network schedule\", ,  (100)\n"
              + "5: \"anything\", ,  (120)\n")) {
        System.out.println("failed 3");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // do not want exception
    }

    // Scenario 4: insert in empty list
    shelf.clear();
    Book book8 = new Book("precious", 45);
    try {
      shelf.insertBook(book8);
      if (!shelf.toString().equals("PAGECOUNT\n" + "7: \"precious\", ,  (45)\n")) {
        System.out.println("failed 4");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // do not want exception
    }

    return true;
  }

  /**
   * Tests various implementations of the sort() method from the LinkedBookshelf class.
   * <p>
   * @return boolean true if sort returns the expected values, false otherwise
   */
  public static boolean testSortBooks() {
    Book.resetGenerator();
    
    // create an unsorted shelf
    Book book1 = new Book("animal farm", 30, "a", "red");
    Book book2 = new Book("boris's vacation", 10, "c", "yellow");
    Book book3 = new Book("cable network schedule", 100, "d", "green");
    Book book4 = new Book("something", 5, "b", "orange");
    Book book5 = new Book("zzz", 17, "e", "purple"); 

    LinkedBookshelf shelf = new LinkedBookshelf();
    
    // Scenario 1: already sorted list
    shelf.appendBook(book1);
    shelf.appendBook(book2);
    shelf.appendBook(book3);
    shelf.appendBook(book4);
    shelf.appendBook(book5);
    try {
      LinkedBookshelf.sort(shelf, Attribute.TITLE);
      if (!shelf.toString().equals("TITLE\n"
          + "0: \"animal farm\", a, red (30)\n"
          + "1: \"boris's vacation\", c, yellow (10)\n"
          + "2: \"cable network schedule\", d, green (100)\n"
          + "3: \"something\", b, orange (5)\n"
          + "4: \"zzz\", e, purple (17)\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 2: swap one adjacent pair
    shelf.clear();
    shelf.appendBook(book1);
    shelf.appendBook(book3);
    shelf.appendBook(book2);
    shelf.appendBook(book4);
    shelf.appendBook(book5);
    try {
      LinkedBookshelf.sort(shelf, Attribute.TITLE);
      if (!shelf.toString().equals("TITLE\n"
          + "0: \"animal farm\", a, red (30)\n"
          + "1: \"boris's vacation\", c, yellow (10)\n"
          + "2: \"cable network schedule\", d, green (100)\n"
          + "3: \"something\", b, orange (5)\n"
          + "4: \"zzz\", e, purple (17)\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 3.a: randomly ordered list
    shelf.clear();
    shelf.appendBook(book4);
    shelf.appendBook(book3);
    shelf.appendBook(book2);
    shelf.appendBook(book5);
    shelf.appendBook(book1);
    try {
      LinkedBookshelf.sort(shelf, Attribute.TITLE);
      if (!shelf.toString().equals("TITLE\n"
          + "0: \"animal farm\", a, red (30)\n"
          + "1: \"boris's vacation\", c, yellow (10)\n"
          + "2: \"cable network schedule\", d, green (100)\n"
          + "3: \"something\", b, orange (5)\n"
          + "4: \"zzz\", e, purple (17)\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    // Scenario 3.b: sort by ID
    shelf.clear();
    shelf.appendBook(book4);
    shelf.appendBook(book3);
    shelf.appendBook(book2);
    shelf.appendBook(book5);
    shelf.appendBook(book1);
    try {
      LinkedBookshelf.sort(shelf, Attribute.ID);
      if (!shelf.toString().equals("ID\n"
          + "0: \"animal farm\", a, red (30)\n"
          + "1: \"boris's vacation\", c, yellow (10)\n"
          + "2: \"cable network schedule\", d, green (100)\n"
          + "3: \"something\", b, orange (5)\n"
          + "4: \"zzz\", e, purple (17)\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    // Scenario 3.c: sort by pagecount
    shelf.clear();
    shelf.appendBook(book4);
    shelf.appendBook(book3);
    shelf.appendBook(book2);
    shelf.appendBook(book5);
    shelf.appendBook(book1);
    try {
      LinkedBookshelf.sort(shelf, Attribute.PAGECOUNT);
      if (!shelf.toString().equals("PAGECOUNT\n"
          + "3: \"something\", b, orange (5)\n"
          + "1: \"boris's vacation\", c, yellow (10)\n"
          + "4: \"zzz\", e, purple (17)\n"
          + "0: \"animal farm\", a, red (30)\n"
          + "2: \"cable network schedule\", d, green (100)\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    // Scenario 3.d: sort by author
    shelf.clear();
    shelf.appendBook(book4);
    shelf.appendBook(book3);
    shelf.appendBook(book2);
    shelf.appendBook(book5);
    shelf.appendBook(book1);
    try {
      LinkedBookshelf.sort(shelf, Attribute.AUTHOR);
      if (!shelf.toString().equals("AUTHOR\n"
          + "0: \"animal farm\", a, red (30)\n"
          + "3: \"something\", b, orange (5)\n"
          + "1: \"boris's vacation\", c, yellow (10)\n"
          + "2: \"cable network schedule\", d, green (100)\n"
          + "4: \"zzz\", e, purple (17)\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 4: head and tail swapped
    shelf.clear();
    shelf.appendBook(book5);
    shelf.appendBook(book2);
    shelf.appendBook(book3);
    shelf.appendBook(book4);
    shelf.appendBook(book1);
    try {
      LinkedBookshelf.sort(shelf, Attribute.TITLE);
      if (!shelf.toString().equals("TITLE\n"
          + "0: \"animal farm\", a, red (30)\n"
          + "1: \"boris's vacation\", c, yellow (10)\n"
          + "2: \"cable network schedule\", d, green (100)\n"
          + "3: \"something\", b, orange (5)\n"
          + "4: \"zzz\", e, purple (17)\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 5: empty list
    shelf.clear();
    try {
      LinkedBookshelf.sort(shelf, Attribute.TITLE);
      if (!shelf.toString().equals("TITLE\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    // Scenario 6: one element
    shelf.clear();
    shelf.appendBook(book3);
    try {
      LinkedBookshelf.sort(shelf, Attribute.TITLE);
      if (!shelf.toString().equals("TITLE\n"
          + "2: \"cable network schedule\", d, green (100)\n")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; // should not throw exception
    }
    
    return true;
  }
  
  /**
   * Tests various implementations of the clear method from the LinkedBookshelf class.
   * <p>
   * 
   * @return boolean true if clear returns the expected values, false otherwise
   */
  public static boolean testClear() {
    Book.resetGenerator();
    System.out.println("\n\n >>testers for clear method <<\n");

    Book book1 = new Book("animal farm", 30);
    Book book2 = new Book("boris's vacation", 10);
    Book book3 = new Book("cable network schedule", 100);
    try {
      // *********************************************************************
      // Scenario 1: Linked list with a couple nodes (normal case)
      LinkedBookshelf normalShelf = new LinkedBookshelf();
      // System.out.println("**********\ntestClear() #1: A normal list\n");


      normalShelf.appendBook(book1);
      normalShelf.appendBook(book2);
      normalShelf.appendBook(book3);

      String expected = "ID\n";
      String actual = "";

      try {
        normalShelf.clear();
        actual = normalShelf.toString();
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      // System.out.println("expected: " + expected);
      // System.out.println("actual: " + actual);
      if (!expected.equals(actual)) {
        System.out.println("uh oh: testClear() with a normal list printed an unexpected result");
        return false;
      }
      try {
        if (!(normalShelf.getFirst() == null && normalShelf.getLast() == null
            && normalShelf.size() == 0)) {
          System.out
              .println("uh oh: testClear() w/ a normal list did not update list vars correctly");
          return false;
        }
      } catch (Exception e) {
      } // expected

      System.out.println("TRUE\n");
      // *********************************************************************
      // Scenario 2: Empty linked list
      LinkedBookshelf emptyShelf = new LinkedBookshelf();
      // System.out.println("**********\ntestClear() #2: An empty list\n");

      expected = "ID\n";
      actual = "";

      try {
        emptyShelf.clear();
        actual = emptyShelf.toString();
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      // System.out.println("expected:\n" + expected);
      // System.out.println("actual:\n" + actual);
      if (!expected.equals(actual) || normalShelf.size() != 0) {
        System.out.println("uh oh: testClear() with an empty list gave an unexpected result");
        return false;
      }
      try {
        if (!(emptyShelf.getFirst() == null && emptyShelf.getLast() == null
            && emptyShelf.size() == 0)) {
          System.out
              .println("uhoh:testClear() w/ an empty list did not update list vars correctly");
          return false;
        }
      } catch (Exception e) {
      } // expected

      System.out.println("TRUE\n");
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // *********************************************************************
    System.out.println("testClear() status: TRUE\n\n*******************************");
    return true;

  } // end testClear

  /**
   * Runs all of the test methods in the LinkedBookshelfTester class
   * <p>
   * @return true if all tests pass, false if any test fails
   */
  public static boolean runAllTests() {
    if (!testLinkedNode()) {
      return false;
    }
    if (!testClear()) {
      return false;
    }
    if (!testAddBooks()) {
      return false;
    }
    if (!testInsertBooks()) {
      return false;
    }
    if (!testSortBooks()) {
      return false;
    }
    return true;
  }

  /**
   * Main method of the LinkedBookshelfTester class. It is used for testing.
   * <p>
   * @param args is the command line arguments
   */
  public static void main(String[] args) {
    // System.out.println(testLinkedNode());
    // System.out.println(testAddBooks());
    // System.out.println(testClear());
    // System.out.println(testInsertBooks());
    // System.out.println(testSortBooks());
    System.out.println(runAllTests());


    /*
     * Book.resetGenerator(); Book book1 = new Book("animal farm", 35); Book book2 = new
     * Book("boris's vacation", 10); Book book3 = new Book("cable network schedule", 100); Book
     * book5 = new Book("something", 5);
     * 
     * LinkedBookshelf shelf = new LinkedBookshelf();
     * 
     * shelf.appendBook(book1); shelf.appendBook(book2); shelf.appendBook(book3);
     * shelf.appendBook(book5);
     * 
     * LinkedBookshelf.sort(shelf, Attribute.PAGECOUNT);
     * 
     * //System.out.println(shelf.toString());
     * 
     * Book book4 = new Book("daisy", 25); Book book6 = new Book("anything", 120); Book book7 = new
     * Book("thing", 2);
     * 
     * shelf.insertBook(book4); shelf.insertBook(book6); shelf.insertBook(book7);
     * 
     * //System.out.println(shelf.toString());
     * 
     * //shelf.clear(); //System.out.println(shelf.toString()); //shelf.insertBook(book7);
     * //System.out.println(shelf.toString());
     */
  }

}
