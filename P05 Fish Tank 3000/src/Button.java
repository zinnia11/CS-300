//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Tank 3000 Button parent class
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

/**
 * This class defines basic methods and data fields for a Button Object. It implements the 
 * TankListener interface and its related methods.
 */
public class Button implements TankListener {
  
  /********* DATA FIELDS *********/

  private static final int WIDTH = 85; // Width of this Button

  private static final int HEIGHT = 32; // Height of this Button

  protected static FishTank tank; // PApplet object where this button will be displayed

  private float x; // x-position of this button in the display window

  private float y; // y-position of this button in the display window

  protected String label; // text/label which represents this button

  /********* CONSTRUCTORS *********/

  /**
   * Creates a new Button at the given position (x, y) and sets the label.
   * <p>
   * @param label of the button
   * @param x x-position of the Button
   * @param y y-position of the Button
   */
  public Button(String label, float x, float y) {
    this.x = x;
    this.y = y;
    this.label = label;
  }

  /********* MUTATORS *********/
  
  /**
   * sets the PApplet display window where this button is displayed and handled
   * @param tank PApplet the Button will operate on
   */
  public static void setProcessing(FishTank tank) {
    Button.tank = tank;
  }

  /**
   * Checks if the mouse is over a specific Button who calls this method. 
   * <p>
   * @return true if the mouse if over the image of this Button, false otherwise
   */
  public boolean isMouseOver() {
    // find the X coordinate where the Button starts by subtracting half of the width 
    // and the X coordinate where the Button ends by adding half the width to the 
    // X coordinate of the center
    double imageXStart = this.x - (Button.WIDTH / 2);
    double imageXEnd = this.x + (Button.WIDTH / 2);
    // find the Y coordinate where the Button starts by subtracting half of the height 
    // and the Y coordinate where the Button ends by adding half the height to the 
    // Y coordinate of the center
    double imageYStart = this.y - (Button.HEIGHT / 2);
    double imageYEnd = this.y + (Button.HEIGHT / 2);

    if ((imageXStart <= tank.mouseX) && (tank.mouseX <= imageXEnd)) {
      if ((imageYStart <= tank.mouseY) && (tank.mouseY <= imageYEnd)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Draws this button to the display window.
   */
  public void draw() {
    tank.stroke(0);// set line value to black

    // if the mouse is over this button, sets the fill color to dark gray.
    // Sets the fill color to light gray otherwise
    if (this.isMouseOver()) {
      tank.fill(100);
    } else {
      tank.fill(200);
    }

    // draw the button (rectangle with a centered text)
    tank.rect(x - WIDTH / 2.0f, y - HEIGHT / 2.0f, x + WIDTH / 2.0f, y + HEIGHT / 2.0f);
    tank.fill(0); // set the fill color to black
    tank.text(label, x, y); // display the text of the current button
  }

  /**
   * Implements the default behavior of this button when the mouse is pressed. This method must be
   * overridden in the sub-classes to implement a specific behavior if needed.
   */
  public void mousePressed() {
    // if the mouse is over this button, print
    // "A button was pressed." to the console
    if (this.isMouseOver()) {
      System.out.println("A button was pressed.");
    }
  }

  /**
   * Implements the default behavior of this button when the mouse is released.
   * This method must be overridden in the sub-classes to implement a specific behavior if needed.
   */
  public void mouseReleased() {
    // Leave this method empty
  }

}
