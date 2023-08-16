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
 * This class creates LinkedBookshelves filled with linked Books. These shelves can be modified by 
 * adding or removing books, or sorted by ID, title, author, or page number.
 * <p>
 * @author Rose and Zinnia
 */
public class LinkedBookshelf {

  /******** DATA FIELDS ********/

  private LinkedNode<Book> front; // book at front of list
  private LinkedNode<Book> back; // book at end of list
  private int size; // number of books on shelf
  private Attribute sortedBy = Attribute.ID;

  /******* CONSTRUCTORS *******/

  /**
   * LinkedBookshelf constructor: new LinkedBookshelf object, size zero, with default 
   * Attribute as ID
   */
  public LinkedBookshelf() {
    this.sortedBy = Attribute.ID; // just the default, written again
    this.front = null; // and therefore all null
    this.size = 0;
  }

  /******* ACCESSORS ********/

  /**
   * returns the size of this linked list
   * @return this.size
   */
  public int size() {
    return this.size;
  }

  /**
   * returns true if this linked list is empty, false otherwise
   * @return boolean true if the front of this list is null, false if not
   */
  public boolean isEmpty() {
    /*
     * if(this.size==0) return true; return false;
     */
    return front == null;
  }

  /**
   * returns this linked list as a String
   * @return String of linked list data
   */
  public String toString() {
    String shelfContents = sortedBy + "\n";
    
    for (int i = 0; i < this.size(); i++) {
      shelfContents += (this.get(i).toString() + "\n");
    }

    return shelfContents;
  }

  /**
   * returns the node at the param index
   * @return LinkedNode<Book>
   * @throws IndexOutOfBoundsException if the param index is less than 0 or greater than size-1.
   */
  public LinkedNode<Book> getNode(int index) {
    if (index > this.size - 1 || index < 0)
      throw new IndexOutOfBoundsException("index too high or low");

    LinkedNode<Book> indexBook = this.front;

    for (int i = 1; i <= index; i++) {
      indexBook = indexBook.getNext();
    }
    return indexBook;
  } // end getNode
  
  /**
   * returns the book at the param index
   * @return Book the data contained in this book
   */
  public Book get(int index) {
    return this.getNode(index).getData();
  }

  /**
   * returns the book at the front of the linked list (the head)
   * @return Book the data contained in the first node
   */
  public Book getFirst() {
    return this.front.getData();
  }

  /**
   * returns the book at the back of the linked list (the tail)
   * @return Book the data contained in the last node
   */
  public Book getLast() {
    return this.back.getData();
  }

  /******* MUTATORS *******/

  /**
   * clears the linked list, setting head and tail nodes to null, and size to zero.
   */
  public void clear() {
    this.front = null;
    this.back = null;
    this.size = 0;
  }

  /**
   * adds a Book to the end of the linked list, updating the list size and 
   * head/tail values accordingly
   * @param toAdd Book being added
   */
  public void appendBook(Book toAdd) {
    LinkedNode<Book> add = new LinkedNode<Book>(toAdd);
    // if the list is empty
    if (this.isEmpty()) {
      this.front = add;
      this.back = add;
    } else {
      // set the next of the last node to the new node
      this.back.setNext(add);
      // set the last of the bookshelf to the new node that is added
      this.back = add;
    }
    this.size++;
  }

  /**
   * adds a Book to the appropriate spot in a sorted linked list, updating the list size and 
   * head/tail values accordingly
   * @param toAdd Book being added
   */
  public void insertBook(Book toAdd) {
    LinkedNode<Book> add = new LinkedNode<Book>(toAdd);
    if (this.isEmpty()) {
      this.front = add;
      this.back = add;
      this.size++;
    } else {
      // search for the correct location of the new node
      for (int i = this.size()-1; i >= 0; i--) {
        // compare the data to find the node that has data that is less than the one inserting
        if (this.getNode(i).getData().compareTo(toAdd, sortedBy) < 0) {
          // if the node is the last one
          if (i==this.size()-1) {
            this.back.setNext(add);
            this.back = add;
            this.size++;
            break;
          } else { // otherwise the node is inserted in the middle somewhere
            LinkedNode<Book> temp = this.getNode(i).getNext();
            this.getNode(i).setNext(add);
            add.setNext(temp);
            this.size++;
            break;
          }
        }
        // if it gets down to index 0
        if (i==0) {
          add.setNext(this.front);
          this.front = add;
          this.size++;
          break;
        }
      }
    }
  }

  /**
   * use insertionSort on this list and sort it by param Attribute in ascending order, 
   * updating head and tail values accordingly.
   * @param b Bookshelf being sorted
   * @param sortedBy Attribute to sort by (ID, title, page number, author)
   */
  public static void sort(LinkedBookshelf b, Attribute sortedBy) {
    b.sortedBy = sortedBy;
    
    // if the array has more than 0 or 1 elements
    if (!(b.size() == 0 || b.size() == 1)) {
      // make the unsorted array
      LinkedBookshelf unsort = new LinkedBookshelf();
      unsort.front = b.getNode(1);
      unsort.size = b.size() - 1;
      // reset b to a just the first element, which is already sorted
      b.back = b.getNode(0);
      b.size = 1;
      for(int i = 0; i < unsort.size(); i++) {
        b.insertBook(unsort.get(i));
      }
    }
  } // end sort

} // end class
