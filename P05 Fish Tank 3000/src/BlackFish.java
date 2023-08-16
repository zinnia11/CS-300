//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Tank 3000 Black Fish child class
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
// Online Sources:  For 2 angle overlap - shorturl.at/drLVZ
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;

/**
 * This class defines specific methods for a BlackFish Object. It extends the Fish class and 
 * overrides the swim() method from that class.
 */
public class BlackFish extends Fish{
  
  /******* DATA FIELDS *********/
  
  private TankObject source;
  
  private TankObject destination;
  
  /******** CONSTRUCTORS *********/
  
  /**
   * Creates a new BlackFish object at a random position on the screen and sets the speed to 2 
   * and the image file to "black.png". Also sets the source and destination that the BlackFish 
   * will wander between.
   * <p>
   * @param source where the BlackFish starts moving
   * @param destination where the BlackFish wants to go
   */
  public BlackFish(TankObject source, TankObject destination) {
    super(2, "images" + File.separator + "black.png");
    
    this.source = source;
    this.destination = destination;
  }
  
  /**
   * Makes one speed move towards destination
   */
  public void moveTowardsDestination() { 
    double d = (int)Math.sqrt((this.destination.getX()-this.getX()) * 
        (this.destination.getX()-this.getX()) + (this.destination.getY()-this.getY()) * 
                                (this.destination.getY()-this.getY()));
    
    double newX = (this.getX() + ((this.speed() * (this.destination.getX()-this.getX())) / d));
    double newY = (this.getY() + ((this.speed() * (this.destination.getY()-this.getY())) / d));

    super.move((float)(newX-this.getX()), (float)(newY-this.getY()));
  }
  
  /**
   * Checks if this BlackFish is over another TankObject.
   * @param other the TankObject the BlackFish might be over
   * @return true if the BlackFish is over the other TankObject, false otherwise
   */
  public boolean isOver(TankObject other) {
    // fish corner points
    // upper left
    float X1 = this.getX()-(this.getImage().width/2);
    float Y1 = this.getY()-(this.getImage().height/2);
    // lower right
    float X2 = this.getX()+(this.getImage().width/2);
    float Y2 = this.getY()+(this.getImage().height/2);
    
    // other's corner points
    // upper left
    float X3 = other.getX()-(other.getImage().width/2);
    float Y3 = other.getY()-(other.getImage().height/2);
    // lower right
    float X4 = other.getX()+(other.getImage().width/2);
    float Y4 = other.getY()+(other.getImage().height/2);
    
    // conditions that mean they have overlapped
    if ((X1 < X4) && (X3 < X2) && (Y1 < Y4) && (Y3 < Y2)) {
      return true;
    }
    
    return false;
  }
  
  /**
   * Moves the BlackFish one speed step between the source and destination TankObjects.
   * Overrides the swim() method in the Fish class
   */
  @Override
  public void swim() {
    // if destination is reached (meaning this fish is over its destination)
    // switch source and destination
    if (this.isOver(destination)) {
      TankObject store = destination;
      this.destination = this.source;
      this.source = store;
    }
    
    // move the fish towards its destination
    this.moveTowardsDestination();
  }
  
}
