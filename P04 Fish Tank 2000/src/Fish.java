//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Object Class
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
import processing.core.PApplet;
import processing.core.PImage;

public class Fish {
  /*********** DATA FIELDS ************/

  private static PApplet processing;

  private PImage image;

  private float x; // x-position of the Fish Object

  private float y; // y-position of the Fish Object

  private int speed; // swimming speed of the Fish

  private boolean isDragging;

  private boolean isSwimming;

  private static int oldMouseX; // previous x-position of the mouse

  private static int oldMouseY; // previous y-position of the mouse

  /*********** CONSTRUCTORS ************/
  
  /**
   * Creates a new Fish object located at a specific (x, y) position of the display window
   * <p>
   * @param processing PApplet object that represents the display window
   * @param x x-position of the image of this Fish in the display window
   * @param y y-position of the image of this Fish in the display window
   * @param speed the swimming speed of this Fish
   * @param fishImageFileName file name of the image of the Fish to be created
   */
  public Fish(PApplet processing, float x, float y, int speed, String fishImageFileName) {
    Fish.processing = processing;

    this.x = x;
    this.y = y;

    this.speed = speed;

    image = processing.loadImage(fishImageFileName);

    // The created fish won’t be dragging nor swimming
    this.isDragging = false;
    this.isSwimming = false;
  }
  
  /**
   * Creates a new Fish object positioned at the center of the display window
   * <p>
   * @param processing PApplet object that represents the display window
   */
  public Fish(PApplet processing) {
    Fish.processing = processing;

    // This constructor sets the image instance field to
    // a PImage whose file name is "images" + File.separator + "orange.png"
    image = processing.loadImage("images" + File.separator + "orange.png");

    // Sets the x and y position of the fish to the center of the display window
    this.x = processing.width / 2;
    this.y = processing.height / 2;

    // Sets speed instance field to 5
    this.speed = 5;

    // The created fish won’t be dragging nor swimming
    this.isDragging = false;
    this.isSwimming = false;
  }
  
  /*********** ACCESSORS ************/
  
  /**
   * Getter of the image instance field
   * <p>
   * @return the image of type PImage of this Fish
   */
  public PImage getImage() {
    return image;
  }
  
  /**
   * Getter of the x-position of this Fish
   * <p>
   * @return the x-position of this Fish in the display window
   */
  public float getPositionX() {
    return x;
  }
  
  /**
   * Getter of the y-position of this Fish
   * <p>
   * @return the y-position of this Fish in the display window
   */
  public float getPositionY() {
    return y;
  }
  
  /**
   * Getter for the isDragging instance field. Checks whether this Fish is being dragged.
   * <p>
   * @return boolean representing the dragging state of the Fish
   */
  public boolean isDragging() {
    return isDragging;
  }
  
  /*********** MUTATORS ************/

  /**
   * Starts dragging this Fish
   */
  public void startDragging() {
    // sets oldMouseX data field to the current x-position of the mouse
    oldMouseX = processing.mouseX;
    // sets oldMouseY data field to the current y-position of the mouse
    oldMouseY = processing.mouseY;
    // sets the isDragging data field of this fish to true
    isDragging = true;
  }

  /**
   * Stops dragging this Fish
   */
  public void stopDragging() {
    isDragging = false;
  }
  
  /**
   * Starts swimming this Fish
   */
  public void startSwimming() {
    // 1. stops dragging the fish
    isDragging = false;
    // 2. sets the isSwimming instance field to true
    isSwimming = true;
  }
  
  /**
   * Stops swimming this Fish
   */
  public void stopSwimming() {
    isSwimming = false;
  }
  
  /*********** OTHER METHODS ************/
  
  /**
   * Moves this Fish by dx and dy
   * <p>
   * @param dx is how much the Fish should move horizontally
   * @param dy is how much the Fish should move vertically
   */
  public void move(int dx, int dy) {
    // adds dx move to the x-position of this fish
    if (this.x > 0) {
      this.x = (this.x+dx)%processing.width;
    } else {
      this.x = processing.width;
    }
    // adds dy move to the y-position of this fish
    if (this.y>processing.height) {
      this.y = processing.height-1;
    } else if (this.y<0) {
      this.y = 1;
    }
    this.y += dy;
  }
  
  /**
   * Moves horizontally the Fish one speed step from left to right
   */
  public void swim() {
    // The speed step is the instance field speed defined for each fish
    // Swimming is bound by the edges of the window
    this.x = (this.x+speed)%processing.width;
  }
  
  /**
   * Draws this Fish to the display window. Also sets the position of the Fish to follow the 
   * mouse if it is being dragged and starts swimming the Fish if its is supposed to swim
   */
  public void draw() {
    if (isDragging == true) {
      // move Fish with (dx, dy) to follow the moves of the mouse
      int dx = processing.mouseX - oldMouseX;
      int dy = processing.mouseY - oldMouseY;
      this.move(dx, dy);
      // Make sure to update oldMouseX and oldMouseY after moving the fish
      oldMouseX = processing.mouseX;
      oldMouseY = processing.mouseY;
    }
    
    // swims the fish
    if (isSwimming == true) {
      this.swim();
    }
    
    // draw this fish to the display window by calling processing.image() method
    processing.image(image, x, y);
  }
  
  /**
   * Checks if the mouse is over a specific Fish who calls this method
   * <p>
   * @return true if the mouse if over the image of the specific Fish, false otherwise
   */
  public boolean isMouseOver() {
    // find the X coordinate where the fish image starts by subtracting half of the width and
    // the X coordinate where the fish image ends by adding half the width to the X coordinate of
    // the center
    double imageXStart = this.getPositionX() - (this.getImage().width / 2);
    double imageXEnd = this.getPositionX() + (this.getImage().width / 2);
    // find the Y coordinate where the fish image starts by subtracting half of the height and
    // the Y coordinate where the fish image ends by adding half the height to the Y coordinate of
    // the center
    double imageYStart = this.getPositionY() - (this.getImage().height / 2);
    double imageYEnd = this.getPositionY() + (this.getImage().height / 2);

    if ((imageXStart <= processing.mouseX) && (processing.mouseX <= imageXEnd)) {
      if ((imageYStart <= processing.mouseY) && (processing.mouseY <= imageYEnd)) {
        return true;
      }
    }

    return false;
  }

}
