//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Tank 3000 Blue Fish child class
// Course:   CS 300 Fall 2021
//
// Author:   Zinnia Nie
// Email:    zznie@wisc.edu
// Lecturer: Hobbes LeGault
//
////////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
// Partner Email:   None
// Partner Lecturer's Name: None
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;

/**
 * This class defines specific methods for a BlueFish Object. It extends the Fish class and 
 * overrides the swim() method from that class.
 */
public class BlueFish extends Fish{
  
  /******** CONSTRUCTORS *********/
  
  /**
   * No-argument constructor for BlueFish. Creates a new BlueFish object at a random position on 
   * the screen and sets the speed to 2 and the image file to "blue.png".
   */
  public BlueFish() {
    super(2, "images" + File.separator + "blue.png");
  }
  
  /******* OTHER METHODS *******/
  
  /**
   * Swims the BlueFish by moving horizontally the Fish one speed step from left to right.
   * Overrides the swim() method in Fish
   */
  @Override
  public void swim() {
    super.move(-this.speed(), 0);
  }

}
