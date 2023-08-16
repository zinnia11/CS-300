//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Tank 3000 TankObject parent class
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

import processing.core.PImage;

/**
 * This class defines the basic methods of an interactive Object that will be going into the 
 * FishTank. It has constructors, getter and setter methods for the fields of an Object in the 
 * FishTank and also implements the TankListener interface's methods. 
 */
public class TankObject implements TankListener{
  
  protected static FishTank tank; // PApplet object which represents
                                  //the display window
  protected PImage image; // image of this tank object
  
  private float x; // x-position of this tank in the display window
  
  private float y; // y-position of this tank in the display window
  
  private boolean isDragging; // indicates whether this tank object
                              //is being dragged or not
  private static int oldMouseX; // old x-position of the mouse
  
  private static int oldMouseY; // old y-position of the mouse
  
  /********** CONSTRUCTORS ***********/
  
  /**
   * Creates a new TankObject located at (x, y) of the screen with the image given as argument
   * <p>
   * @param x x-position of the TankObject
   * @param y y-position of the TankObject
   * @param imageFileName file name of the image of the TankObject
   */
  public TankObject(float x, float y, String imageFileName) {
    this.x = x;
    this.y = y;
    
    this.image = tank.loadImage(imageFileName);
    
    this.isDragging = false;
  }
  
  /*********** ACCESSORS ***********/
  
  /**
   * Getter of the x-position of this TankObject
   * <p>
   * @return the x-position of this TankObject in the display window
   */
  public float getX() {
    return this.x;
  }
  
  /**
   * Getter of the y-position of this TankObject
   * <p>
   * @return the y-position of this TankObject in the display window
   */
  public float getY() {
    return this.y;
  }
  
  /**
   * Getter of the image instance field
   * <p>
   * @return the image of type PImage of this TankObject
   */
  public PImage getImage() {
    return this.image;
  }
  
  /**
   * Getter for the isDragging instance field. Checks whether this Fish is being dragged.
   * <p>
   * @return boolean representing the dragging state of the Fish
   */
  public boolean isDragging() {
    return this.isDragging;
  }
  
  /********** MUTATORS ***********/
  
  /**
   * Sets the PApplet graphic display window for all TankObjects
   * @param tank the PApplet that will be drawn on
   */
  public static void setProcessing(FishTank tank) {
    TankObject.tank = tank;
  }
  
  /**
   * Sets the x-position of this object
   * @param x that is the new x-position of the TankObject
   */
  public void setX(float x) {
    this.x = x;
  }
  
  /**
   * Sets the y-position of this object
   * @param y that is the new y-position of the TankObject
   */
  public void setY(float y) {
    this.y = y;
  }
  
  /**
   * Moves this TankObject by dx and dy
   * <p>
   * @param dx is how much the TankObject should move horizontally
   * @param dy is how much the TankObject should move vertically
   */
  public void move(float dx, float dy) {
    // adds dx move to the x-position of this fish
    this.x = (this.x+dx+tank.width)%tank.width;
    // adds dy move to the y-position of this fish
    if (this.y>tank.height) {
      this.y = tank.height-1;
    } else if (this.y<0) {
      this.y = 1;
    }
    this.y += dy;
  }
  
  /**
   * Starts dragging this TankObject
   */
  public void startDragging() {
    // sets oldMouseX data field to the current x-position of the mouse
    oldMouseX = tank.mouseX;
    // sets oldMouseY data field to the current y-position of the mouse
    oldMouseY = tank.mouseY;
    // sets the isDragging data field of this decoration to true
    this.isDragging = true;
  }
  
  /**
   * Stops dragging this TankObject
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /*********** OTHER METHODS ************/
  
  /**
   * Draws this TankObject to the display window. Sets the position of the TankObject to follow 
   * the mouse if it is being dragged.
   */
  public void draw() {
    // follows the moves of the mouse by moving with dx, dy
    if (isDragging == true) {
      int dx = tank.mouseX - oldMouseX;
      int dy = tank.mouseY - oldMouseY;
      this.move(dx, dy);
      // make sure to update oldMouseX and oldMouseY after moving the decoration
      oldMouseX = tank.mouseX;
      oldMouseY = tank.mouseY;
    }
    
    // draw this TankObject to the display window by calling processing.image() method
    tank.image(image, x, y);
  }
  
  /**
   * Defines what will happen when the mouse is pressed.
   */
  public void mousePressed() {
    this.startDragging();
  }

  /**
   * Defines what will happen when the mouse is released. 
   */
  public void mouseReleased() {
    this.stopDragging();
  }
  
  /**
   * Checks if the mouse is over a specific Fish who calls this method. 
   * <p>
   * @return true if the mouse if over the image of the specific Fish, false otherwise
   */
  public boolean isMouseOver() {
    // find the X coordinate where the decoration image starts by subtracting half of the width 
    // and the X coordinate where the decoration image ends by adding half the width to the 
    // X coordinate of the center
    double imageXStart = this.getX() - (this.getImage().width / 2);
    double imageXEnd = this.getX() + (this.getImage().width / 2);
    // find the Y coordinate where the decoration image starts by subtracting half of the height 
    // and the Y coordinate where the decoration image ends by adding half the height to the 
    // Y coordinate of the center
    double imageYStart = this.getY() - (this.getImage().height / 2);
    double imageYEnd = this.getY() + (this.getImage().height / 2);

    if ((imageXStart <= tank.mouseX) && (tank.mouseX <= imageXEnd)) {
      if ((imageYStart <= tank.mouseY) && (tank.mouseY <= imageYEnd)) {
        return true;
      }
    }

    return false;
  }
 
  
}
