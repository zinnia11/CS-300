//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Assignment Planner: Priority Queue for Assignment Planner
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
 * Array-based heap implementation of a priority queue containing Assignments. Guarantees the
 * min-heap invariant, so that the Assignment at the root should have the earliest due date, and
 * children always have a due date after or at the same time as their parent. The root of a
 * non-empty queue is always at index 0 of this array-heap.
 */
public class AssignmentQueue implements PriorityQueueADT<Assignment>, Iterable<Assignment> {

  /**** DATA FIELDS *****/

  private Assignment[] queue; // array min-heap of assignments representing this priority queue
  private int size; // size of this priority queue

  /***** CONSTRUCTORS *****/

  /**
   * Creates a new empty AssignmentQueue with the given capacity
   * 
   * @param capacity Capacity of this AssignmentQueue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public AssignmentQueue(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be a positive integer");
    }
    // oversized array
    this.size = 0; // initial size of the priority queue is 0
    queue = new Assignment[capacity]; // capacity of the array is a set number
  }

  /***** ACCESSORS *****/

  /**
   * Checks whether this AssignmentQueue is empty
   * 
   * @return {@code true} if this AssignmentQueue is empty
   */
  @Override
  public boolean isEmpty() {
    if (size == 0 && queue[0] == null) {
      return true;
    }
    return false;
  }

  /**
   * Returns the size of this AssignmentQueue, which is how many assignments are in the queue right
   * now
   * 
   * @return the size of this AssignmentQueue
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Returns the capacity of this AssignmentQueue, which is the total length of the Assignment array
   * 
   * @return the capacity of this AssignmentQueue
   */
  public int capacity() {
    return this.queue.length;
  }

  /**
   * Removes all elements from this AssignmentQueue
   */
  @Override
  public void clear() {
    queue = new Assignment[this.capacity()];
    this.size = 0;
  }

  /**
   * Returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment with the
   * earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException if this AssignmentQueue is empty
   */
  @Override
  public Assignment peek() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("The queue is currently empty.");
    }
    return queue[0];
  }

  /**
   * Adds the given Assignment to this AssignmentQueue at the correct position based on the min-heap
   * ordering. This queue should maintain the min-heap invariant, so that the Assignment at each
   * index has an earlier or equivalent due-date than the Assignments in its child nodes.
   * Assignments should be compared using the Assignment.compareTo() method.
   * 
   * @param e Assignment to add to this AssignmentQueue
   * @throws NullPointerException  if the given Assignment is null
   * @throws IllegalStateException with a descriptive error message if this AssignmentQueue is full
   */
  @Override
  public void enqueue(Assignment e) throws NullPointerException, IllegalStateException {
    if (e == null) {
      throw new NullPointerException("The Assignment is null");
    }
    if (this.size == queue.length) {
      throw new IllegalStateException(
          "There is no more room left in the queue. Please do some " + "of your assignments.");
    }
    // if list is empty, add as the root
    if (size == 0) {
      queue[0] = e;
      size++;
    } else {
      // add the assignment to the end of the array
      // percolate up
      queue[size] = e;
      percolateUp(size);
      size++;
    }
  }

  /**
   * Removes and returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment
   * with the earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException with a descriptive error message if this AssignmentQueue is
   *                                empty
   */
  @Override
  public Assignment dequeue() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("The queue is currently empty.");
    }

    Assignment returner = queue[0];
    if (size == 1) {
      queue[0] = null;
      size--;
    } else if (size == 2) {
      queue[0] = queue[1];
      queue[1] = null;
      size--;
    } else {
      queue[0] = queue[size - 1]; // index of last element is size-1
      queue[size - 1] = null; // nullify the last element
      percolateDown(0);
      size--;
    }
    return returner;
  }

  /**
   * Recursive implementation of percolateDown() method. Restores the min-heap invariant of a given
   * subtree by percolating its root down the tree. If the element at the given index does not
   * violate the min-heap invariant (it is due before its children), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
   * correct child and continue percolating the element down the heap.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int i) {
    // provide the worst-case runtime complexity of this method assuming that the problem size
    // N is the size of this queue
    // Time complexity: O(logN)

    Assignment toMove = queue[i];
    Assignment smallest;
    int indexChild;

    if (i * 2 + 1 < this.size - 1) {
      Assignment leftChild = queue[i * 2 + 1];
      if (i * 2 + 2 < this.size - 1) { // both children exist
        Assignment rightChild = queue[i * 2 + 2];
        smallest = rightChild;
        indexChild = i * 2 + 2;
        if (leftChild.compareTo(rightChild) < 0) { // the left child is the smaller of the two
          smallest = leftChild;
          indexChild = i * 2 + 1;
        }
        if (toMove.compareTo(smallest) > 0) { // assignment to add is greater than smallest child
          // swap the two values
          queue[indexChild] = toMove;
          queue[i] = smallest;
          // continue to percolate
          percolateDown(indexChild);
        }
      } else {
        // only left child exists, if it is less, then swap them
        indexChild = i * 2 + 1;
        smallest = leftChild;
        if (toMove.compareTo(smallest) > 0) { // assignment to add is greater than smallest child
          // swap the two values
          queue[indexChild] = toMove;
          queue[i] = smallest;
          // continue to percolate
          percolateDown(indexChild);
        }
      }
    }
    // else, the index is already a leaf node or at its proper position, stop the recursion
  }

  /**
   * Recursive implementation of percolateUp() method. Restores the min-heap invariant of the tree
   * by percolating a leaf up the tree. If the element at the given index does not violate the
   * min-heap invariant (it occurs after its parent), then this method does not modify the heap.
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int i) {
    // provide the worst-case runtime complexity of this method assuming that the problem size
    // N is the size of this queue
    // Time complexity: O(logN)

    if (i >= 0) { // not already the root node
      int parent = (i - 1) / 2;
      if (queue[i].compareTo(queue[parent]) < 0) { // assignment to add is less than its parent
        // swap the two values
        Assignment temp = queue[parent];
        queue[parent] = queue[i];
        queue[i] = temp;
        // continue to percolate
        this.percolateUp(parent);
      }
    }
    // else the assignment to add is greater than the parent and then nothing should be done and
    // the recursion should end (base case)
  }

  /**
   * Returns a deep copy of this AssignmentQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * assignments. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this AssignmentQueue. The returned new assignment queue has the same
   *         length and size as this queue.
   */
  public AssignmentQueue deepCopy() {
    AssignmentQueue returner = new AssignmentQueue(queue.length);
    for (int i = 0; i < size; i++) {
      returner.enqueue(queue[i]);
    }

    return returner;
  }

  /*
  /**
   * Returns a String representing this AssignmentQueue, where each element (assignment) of the
   * queue is listed on a separate line, in order from earliest to latest.
   * 
   * @see Assignment#toString()
   * @see AssignmentIterator
   * @return a String representing this AssignmentQueue
   */
  /*
  public String toStringInOrder() {
    StringBuilder val = new StringBuilder();

    for (Assignment a : this) {
      val.append(a).append("\n");
    }

    return val.toString();
  }
  */
  
  /**
   * Returns a String representing this AssignmentQueue, where each element (assignment) of the
   * queue is listed on a separate line, in order of the heap.
   * 
   * @return a String representing this AssignmentQueue    
   */
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (int i = 0; i < size; i++) {
      val.append(queue[i]).append("\n");
    }
    return val.toString();
  }

  /**
   * Returns an Iterator for this AssignmentQueue which proceeds from the earliest to the latest
   * Assignment in the queue.
   * 
   * @see AssignmentIterator
   * @return an Iterator for this AssignmentQueue
   */
  @Override
  public Iterator<Assignment> iterator() {
    return new AssignmentIterator(this);
  }

}
