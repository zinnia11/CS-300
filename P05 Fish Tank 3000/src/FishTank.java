//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish Tank 3000 Graphical Application Main Method
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
import java.util.ArrayList;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The FishTank class defines the basic settings of the PApplet for the FishTank. It initializes 
 * the window of the application, sets up an ArrayList containing the interactive objects in the 
 * tank and defines the basic callback methods. 
 */
public class FishTank extends PApplet {
  
  /******** DATA FIELDS ********/

  private PImage backgroundImage; // PImage object which represents the background image

  protected ArrayList<TankListener> objects; // list storing interactive objects

  protected Random randGen; // Generator of random numbers
  
  private TankObject flower;
  
  private TankObject log;
  
  private TankObject shell;
  
  private TankObject ship;
  
  /**
   * Sets the size of this PApplet screen to 800 width x 600 height.
   * Overrides the settings() method in PApplet.
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /**
   * Defines the initial environment properties of this application, like creating and initializing
   * data fields defined in the program and configuring the different graphical settings, such as 
   * loading the background image, setting the dimensions of the display window, etc.
   * Initializes all data fields. Overrides the setup() method in PApplet.
   */
  @Override
  public void setup() {
    // Set and display the title of the display window
    this.getSurface().setTitle("Fish Tank 3000");
    // Set the location from which images are drawn to CENTER
    this.imageMode(PApplet.CENTER);
    // Set the location from which rectangles are drawn.
    this.rectMode(PApplet.CORNERS);
    // rectMode(CORNERS) interprets the first two parameters of rect() method
    // as the location of one corner, and the third and fourth parameters as
    // the location of the opposite corner.
    // rect() method draws a rectangle to the display window

    this.focused = true; // Confirms that our Processing program is focused,
                         // meaning that it is active and will accept mouse or keyboard input.

    // sets the text alignment to center
    this.textAlign(PApplet.CENTER, PApplet.CENTER);

    // load the background image and store the loaded image to backgroundImage
    backgroundImage = this.loadImage("images/background.png");

    // create an empty array list of objects
    objects = new ArrayList<TankListener>();

    // set randGen to the reference of a new Random objects
    randGen = new Random();

    // set the PApplet the TankObject and Button classes will be drawing on to this FishTank
    TankObject.setProcessing(this);
    Button.setProcessing(this);
    
    // initialize and add buttons to the objects list
    Button blueButton = new AddBlueFishButton(43, 16);
    Button yellowButton = new AddYellowFishButton(129, 16);
    Button orangeButton = new AddOrangeFishButton(215, 16);
    Button clearButton = new ClearTankButton(301, 16);
    this.addObject(blueButton);
    this.addObject(yellowButton);
    this.addObject(orangeButton);
    this.addObject(clearButton);
    
    // initialize and add the decorations to the objects list
    flower = new TankObject(430, 60, "images" + File.separator + "flower.png");
    log = new TankObject(580, 470, "images" + File.separator + "log.png");
    shell = new TankObject(65, 520, "images" + File.separator + "shell.png");
    ship = new TankObject(280, 535, "images" + File.separator + "ship.png");
    this.addObject(flower);
    this.addObject(log);
    this.addObject(shell);
    this.addObject(ship);
    
    // initialize and add the BlackFish to the objects list
    BlackFish black1 = new BlackFish(log, flower);
    BlackFish black2 = new BlackFish(shell, flower);
    this.addObject(black1);
    this.addObject(black2);
  }
  
  /**
   * Adds an instance of TankListener passed as input to the objects ArrayList
   * @param object that will be added to the ArrayList
   */
  public void addObject(TankListener object) {
    objects.add(object);
  }
  
  /**
   * Removes all instances of the class Fish from this tank. Overrides the clear() method in PApplet
   */
  public void clear() {
    for(int i=0; i<objects.size(); i++) {
      if (objects.get(i) instanceof Fish) {
        objects.remove(i);
        i--;
      }
    }
  }

  /******* CALLBACK METHODS *******/
  
  /**
   * Draws and updates this application display window. This callback method called in an infinite
   * loop until the program is stopped. The screen is updated every time the method is called.
   * Overrides the draw() method in PApplet.
   */
  @Override
  public void draw() {
    // clear the display window by drawing the background image
    this.image(backgroundImage, this.width / 2, this.height / 2);
    
    // traverse the objects list and draw each of the objects to this display window
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    }
  }

  /**
   * Callback method called each time the user presses the mouse. Code automatically executed when
   * the mouse button is pressed. Overrides the mousePressed() method in PApplet.
   */
  @Override
  public void mousePressed() {
    // traverse the objects list and call mousePressed method of the first object being clicked
    for(int i=0; i<objects.size(); i++) {
      if (objects.get(i).isMouseOver()) {
        objects.get(i).mousePressed();
      }
    }
  }

  /**
   * Callback method called each time the mouse is released. Code automatically executed when the 
   * mouse button is released. Overrides the mouseReleased() method in PApplet.
   */
  @Override
  public void mouseReleased() {
    // traverse the objects list and call each object's mouseReleased() method
    for(int i=0; i<objects.size(); i++) {
      objects.get(i).mouseReleased();
    }
  }

  /**
   * Callback method called each time the user presses a key. Code automatically executed when a 
   * key is pressed. Overrides the keyPressed() method in PApplet.
   */
  @Override
  public void keyPressed() {
    // pressing 'o' or 'O' adds an orange Fish
    if (this.key == 'o' || this.key == 'O') {
      objects.add(new Fish());
    }
    
    // pressing 'y' or 'Y' adds a yellow Fish
    if (this.key == 'y' || this.key == 'Y') {
      objects.add(new Fish(2, "images" + File.separator + "yellow.png"));
    }
    
    // pressing 'b' or 'B' adds a BlueFish
    if (this.key == 'b' || this.key == 'B') {
      objects.add(new BlueFish());
    }
    
    // pressing 'r' or 'R' while the mouse is over a Fish will remove it
    if (this.key == 'r' || this.key == 'R') {
      for (int i = 0; i < objects.size(); i++) {
        if (objects.get(i).isMouseOver() && objects.get(i) instanceof Fish) {
          objects.remove(i);
          break;
        }
      }
    }
    
    // pressing 's' or 'S' will make all the fish start swimming
    if (this.key == 's' || this.key == 'S') {
      for (int i = 0; i < objects.size(); i++) {
        if (objects.get(i) instanceof Fish) {
          ((Fish) objects.get(i)).startSwimming();
        }
      }
    }
    
    // pressing 'x' or 'X' will make all the fish stop swimming
    if (this.key == 'x' || this.key == 'X') {
      for (int i = 0; i < objects.size(); i++) {
        if (objects.get(i) instanceof Fish) {
          ((Fish) objects.get(i)).stopSwimming();
        }
      }
    }
    
    // pressing 'c' or 'C' will remove all fish from the tank
    if (this.key == 'c' || this.key == 'C') {
      this.clear();
    }
  }

  /*********** MAIN METHOD ***********/

  /**
   * Main method of the FishTank class. It starts the application.
   * <p>
   * @param args is the command line arguments
   */
  public static void main(String[] args) {
    PApplet.main("FishTank");
    // The PApplet.main() method takes a String input parameter which represents
    // the name of the PApplet class.

  }

}
