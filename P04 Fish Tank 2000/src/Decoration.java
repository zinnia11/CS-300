//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Decoration Object Class
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

import processing.core.PApplet;
import processing.core.PImage;

public class Decoration {
  /*********** DATA FIELDS ************/

  private static PApplet processing;

  private PImage image;

  private float x; // x-position of the Decoration Object

  private float y; // y-position of the Decoration Object

  private boolean isDragging;

  private static int oldMouseX; // previous x-position of the mouse

  private static int oldMouseY; // previous y-position of the mouse
  
  /*********** CONSTRUCTORS ************/
  
  /**
   * Creates a new Decoration object located at the (x, y) position of the display window
   * <p>
   * @param processing PApplet reference to the display window of the Fish Tank application
   * @param x x-position of this Decoration object
   * @param y y-position of this Decoration object
   * @param imageFileName filename of the image to be loaded for this object
   */
  public Decoration(PApplet processing, float x, float y, String imageFileName){
    Decoration.processing = processing;
    
    this.x = x;
    this.y = y;
    
    image = processing.loadImage(imageFileName);
    
    // the created decoration will not be dragging
    isDragging = false;
  }
  
  /*********** ACCESSORS ************/
  
  /**
   * Getter of the image instance field
   * <p>
   * @return image of type PImage of this Decoration
   */
  public PImage getImage() {
    return image;
  }

  /**
   * Getter of the x-position of this Decoration
   * <p>
   * @return the x-position of this Decoration in the display window
   */
  public float getPositionX() {
    return x;
  }

  /**
   * Getter of the y-position of this Decoration
   * <p>
   * @return the y-position of this Decoration in the display window
   */
  public float getPositionY() {
    return y;
  }
  
  /**
   * Getter for the isDragging instance field. Checks whether this Decoration is being dragged
   * <p>
   * @return boolean representing the dragging state of the Decoration
   */
  public boolean isDragging() {
    return isDragging;
  }
  
  /*********** MUTATORS ************/

  /**
   * Starts dragging this decoration
   */
  public void startDragging() {
    // sets oldMouseX data field to the current x-position of the mouse
    oldMouseX = processing.mouseX;
    // sets oldMouseY data field to the current y-position of the mouse
    oldMouseY = processing.mouseY;
    // sets the isDragging data field of this decoration to true
    isDragging = true;
  }

  /**
   * Stops dragging this decoration
   */
  public void stopDragging() {
    isDragging = false;
  }
  
  /*********** OTHER METHODS ************/
  
  /**
   * Moves this decoration with dx and dy
   * @param dx is how much the Decoration should move horizontally
   * @param dy is how much the Decoration should move vertically
   */
  public void move(int dx, int dy) {
    // adds dx move to the x-position of this decoration
    if (this.x>processing.width) {
      this.x = processing.width-1;
    } else if (this.x<0) {
      this.x = 1;
    }
    this.x += dx;
    // adds dy move to the y-position of this decoration
    if (this.y>processing.height) {
      this.y = processing.height-1;
    } else if (this.y<0) {
      this.y = 1;
    }
    this.y += dy;
  }
  
  /**
   * Draws this Decoration to the display window. This method sets also the position of this 
   * Decoration to follow the moves of the mouse if it is being dragged
   */
  public void draw() {
    // follows the moves of the mouse by moving with dx, dy
    if (isDragging == true) {
      int dx = processing.mouseX - oldMouseX;
      int dy = processing.mouseY - oldMouseY;
      this.move(dx, dy);
      // make sure to update oldMouseX and oldMouseY after moving the decoration
      oldMouseX = processing.mouseX;
      oldMouseY = processing.mouseY;
    }
    
    // draw this decoration to the display window by calling processing.image() method
    processing.image(image, x, y);
  }
  
  /**
   * Checks if the mouse is over a specific Decoration who calls this method
   * <p>
   * @return true if the mouse if over the image of the specific Decoration, false otherwise
   */
  public boolean isMouseOver() {
    // find the X coordinate where the decoration image starts by subtracting half of the width 
    // and the X coordinate where the decoration image ends by adding half the width to the 
    // X coordinate of the center
    double imageXStart = this.getPositionX() - (this.getImage().width / 2);
    double imageXEnd = this.getPositionX() + (this.getImage().width / 2);
    // find the Y coordinate where the decoration image starts by subtracting half of the height 
    // and the Y coordinate where the decoration image ends by adding half the height to the 
    // Y coordinate of the center
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
