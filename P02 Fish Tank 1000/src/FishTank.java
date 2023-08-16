//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Tank Graphical Application Main Method
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
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

public class FishTank {

  private static PApplet processing; // PApplet object that represents the graphic
                                     // interface of the JunglePark application
  private static PImage backgroundImage; // PImage object that represents the
                                         // background image
  private static Fish[] fishes; // perfect size array storing the different fish present
                                // in the fish tank. These fish can be of different species.
  private static Random randGen; // Generator of random numbers

  /**
   * Defines the initial environment properties of this application, like creating and initializing
   * data fields defined in the program and configuring the different graphical settings, such as 
   * loading the background image, setting the dimensions of the display window, etc.
   * <p>
   * @param processingObj a reference to the graphic display window of this application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj;
    // load the image of the background
    backgroundImage = processing.loadImage("images/background.png");

    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores
    // the width [resp. height] of the display window.

    // stores up to 8 Fish objects and starts with them all null
    fishes = new Fish[8];
    // creates a Random object to assign fishes random x and y coordinates
    randGen = new Random();
  }

  /**
   * Draws and updates the application display window This callback method called in an infinite
   * loop until the program is stopped. The screen is updated every time the method is called.
   */
  public static void draw() {
    // redraws the background image every time so that dragging a fish is possible
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);

    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null) {
        fishes[i].draw();
      }
    }
  }

  /**
   * Checks if the mouse is over a specific Fish whose reference is provided as an input parameter
   * <p>
   * @param fish reference to a specific fish
   * @return true if the mouse if over the image of the specific Fish, false otherwise
   */
  public static boolean isMouseOver(Fish fish) {
    // find the X coordinate where the fish image starts by subtracting half of the width and
    // the X coordinate where the fish image ends by adding half the width to the X coordinate of
    // the center
    double imageXStart = fish.getPositionX() - (fish.getImage().width / 2);
    double imageXEnd = fish.getPositionX() + (fish.getImage().width / 2);
    // find the Y coordinate where the fish image starts by subtracting half of the height and
    // the Y coordinate where the fish image ends by adding half the height to the Y coordinate of
    // the center
    double imageYStart = fish.getPositionY() - (fish.getImage().height / 2);
    double imageYEnd = fish.getPositionY() + (fish.getImage().height / 2);

    if ((imageXStart <= processing.mouseX) && (processing.mouseX <= imageXEnd)) {
      if ((imageYStart <= processing.mouseY) && (processing.mouseY <= imageYEnd)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Callback method called each time the user presses the mouse. Code automatically executed when
   * the mouse button is pressed
   */
  public static void mousePressed() {
    // drags the Fish object the mouse is over
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null && isMouseOver(fishes[i])) {
        fishes[i].setDragging(true);
        break;
      }
    }
  }

  /**
   * Callback method called each time the mouse is released. Code automatically executed when the 
   * mouse button is released
   */
  public static void mouseReleased() {
    // stops dragging all Fish objects in the array
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null) {
        fishes[i].setDragging(false);
      }
    }
  }

  /**
   * Callback method called each time the user presses a key. Code automatically executed when a 
   * key is pressed
   */
  public static void keyPressed() {
    // pressing 'f' or 'F' will add a fish at a random position on the screen
    if (processing.key == 'f' || processing.key == 'F') {
      for (int i = 0; i < fishes.length; i++) {
        if (fishes[i] == null) {
          fishes[i] = new Fish(processing, (float) randGen.nextInt(processing.width),
              (float) randGen.nextInt(processing.height));
          break;
        }
      }
    }

    // pressing 'r' or 'R' while the mouse is over a Fish will remove it
    if (processing.key == 'r' || processing.key == 'R') {
      for (int i = 0; i < fishes.length; i++) {
        if (fishes[i] != null && isMouseOver(fishes[i])) {
          fishes[i] = null;
          break;
        }
      }
    }
  }

  /**
   * Main method of the FishTank class. It starts the application.
   * <p>
   * @param args is the command line arguments
   */
  public static void main(String[] args) {
    // creates the display window, sets its dimension, and checks for callback methods 
    Utility.startApplication();
  }

}
