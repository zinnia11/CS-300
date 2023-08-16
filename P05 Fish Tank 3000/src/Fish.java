//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Tank 3000 Fish class
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
 * This class defines basic methods for a Fish Object. It has constructors for a Fish object and 
 * methods that make the Fish swim. It extends the TankObject class and overrides the draw() method
 * in that class. 
 */
public class Fish extends TankObject {
  
  private int speed;
  
  private boolean isSwimming;
  
  /******** CONSTRUCTORS ********/
  
  /**
   * Creates a new Fish object at a random position on the screen with a given image that has a 
   * given positive speed
   * <p>
   * @param speed how fast the Fish is swimming
   * @param fishImageFileName file name of the image of the Fish
   * @throws IllegalArgumentException when speed is negative
   */
  public Fish(int speed, String fishImageFileName) throws IllegalArgumentException {
    // call super class's constructor
    super((float) tank.randGen.nextInt(tank.width),
        (float) tank.randGen.nextInt(tank.height), fishImageFileName);
    
    if (speed <= 0) {
      throw new IllegalArgumentException("Warning: speed cannot be negative");
    }
    this.speed = speed;
  }
  
  /**
   * No argument constructor for a Fish object. Creates a new Fish object at a random position of 
   * the screen with speed of 5 and using the orange fish image.
   */
  public Fish(){
    // call the constructor in this class
    this(5, "images" + File.separator + "orange.png");
  }
  
  /******* MUTATORS ********/
  
  /**
   * Starts swimming this Fish
   */
  public void startSwimming() { 
    super.stopDragging();
    this.isSwimming = true;
  }
  
  /**
   * Stops swimming this Fish
   */
  public void stopSwimming() {
    this.isSwimming = false;
  }
  
  /******** ACCESSORS *********/
  
  /**
   * Checks whether this Fish is swimming
   * <p>
   * @return boolean describing whether the Fish is swimming or not
   */
  public boolean isSwimming() { 
    return this.isSwimming;
  }

  /**
   * Gets the speed of this Fish
   * @return int that is the speed of the Fish
   */
  public int speed() { 
    return this.speed;
  }
  
  /********* OTHER METHODS ********/
  
  /**
   * Swims the Fish by moving horizontally the Fish one speed step from left to right.
   */
  public void swim() { 
    super.move(speed, 0);
  }
  
  /**
   * Draws this Fish to the display window. Sets the position of the Fish to follow the mouse 
   * if it is dragging and calls the swim() method if the Fish should be swimming. 
   * Overrides the draw() method in the TankObject class.
   */
  @Override
  public void draw() { 
    // swims the fish
    if (isSwimming == true) {
      this.swim();
    }
    
    super.draw();
  }
  
}
