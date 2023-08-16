//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Tank 3000 AddBlueFishButton child class
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
 * This class defines specific methods for an AddBlueFishButton. It extends the Button class and 
 * overrides the mousePressed() method from that class to define a specific action for this child
 * class of Button.
 */
public class AddBlueFishButton extends Button {

  /******** CONSTRUCTORS ********/
  
  /**
   * Creates a new AddBlueFishButton at the location (x, y) and sets the text of the button to
   * "Add Blue"
   * <p>
   * @param x x-position of the button
   * @param y y-position of the button
   */
  public AddBlueFishButton(float x, float y) {
    super("Add Blue", x, y);
  }
  
  /**
   * Defines what will happen when the mouse is pressed. A new BlueFish object will be added to the
   * screen. Overrides the mousePressed() method in the Button class.
   */
  @Override
  public void mousePressed() {
    if (this.isMouseOver()) {
      Button.tank.objects.add(new BlueFish());
    }
  }
  
}
