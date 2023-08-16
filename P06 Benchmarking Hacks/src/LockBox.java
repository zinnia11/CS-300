//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Benchmarking Hacks: Object hacking into
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

/**
 * This class defines an object, a LockBox, with a random numerical password of a given length that
 * will be used to hack into.
 */
public class LockBox {
  
  /****** DATA FIELDS ******/
  
  protected static Random randGen;
  
  private String password = "";
  
  private boolean isOpen;
  
  /****** CONSTRUCTORS ******/
  
  /**
   * Creates a LockBox Object. Initializes Random randGen if is hasn't already been initialized and
   * generates a random password consisting of only numbers that has a length of the inputed 
   * passwordLength
   * <p>
   * @param passwordLength int length of the generated password
   * @throws IllegalArgumentException if the user given passwordLength is less than or equal to 0
   */
  public LockBox(int passwordLength) throws IllegalArgumentException {
    // throws exception if the password length is less than or equal to 0
    if (passwordLength <= 0) {
      throw new IllegalArgumentException("Invalid password length :( ");
    }
    
    // creates a new Random if randGen doesn't have one yet
    if (randGen==null) {
      randGen = new Random();
    }
    
    // generate passwordLength number of random numbers and add them to the String password field
    for(int i=0; i<passwordLength; i++) {
      this.password += String.valueOf(randGen.nextInt(10));
    }
  }
  
  /**
   * Checks a given String guess with the stored String password and assigns the isOpen field with
   * the resulting boolean
   * <p>
   * @param guess is a String that is the guess for the password
   */
  public void authenticate(String guess) {
    isOpen = (guess.equals(password));
  }
  
  /**
   * Getter for the password field.
   * <p>
   * @return String that is stored in the password field
   */
  public String hackMe() {
    return password;
  }
  
  /**
   * Getter for the isOpen field
   * <p>
   * @return boolean that is stored in the isOpen field
   */
  public boolean isOpen() {
    return isOpen;
  }
  
  /**
   * Set the isOpen field to false
   */
  public void reset() {
    isOpen = false;
  }
}
