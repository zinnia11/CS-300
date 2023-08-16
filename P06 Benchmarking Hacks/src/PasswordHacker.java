//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Benchmarking Hacks: Object to hack with
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
 * This class defines an object that will be used to do the simple hacking with. It defines a
 * LockBox object within the constructor and performs operations on it to hack into it.
 */
public class PasswordHacker {
  
  /****** DATA FIELDS ******/
  
  private LockBox toPick;
  
  private int passwordLength;
  
  /****** CONSTRUCTORS ******/
  
  /**
   * Creates a PasswordHacker object that defines a LockBox object with the given passwordLength
   * <p>
   * @param passwordLength is the length of the randomly generated password
   */
  public PasswordHacker(int passwordLength) {
    toPick = new LockBox(passwordLength);
    this.passwordLength = passwordLength;
  }
  
  /**
   * Asks the LockBox object for its password and uses it to authenticate
   * <p>
   * Complexity: O( 1 )
   */
  public void hack() {
    toPick.reset();
    // ask for the password
    String password = toPick.hackMe();
    toPick.authenticate(password);
  }
  
  /**
   * Uses the generateGuess() method to systematically create guesses for the passwordLength 
   * password of the LockBox item until it opens the LockBox
   * <p>
   * Complexity: O( N^2 ) 
   * <p>
   * @see #generateGuess(count)
   */
  public void bruteForce() {
    toPick.reset();
    // generate guesses and authenticate
    int count = 0;
    while(!toPick.isOpen()) {
      //String s = generateGuess(count);
      toPick.authenticate(generateGuess(count));
      //System.out.println(s);
      count++;
    }
  }
  
  /**
   * Systematically creates guesses of the length passwordLength starting from 0 given the number 
   * of times a guess has been made
   * <p>
   * @param count int the number of times a guess was made
   * @return a String that is the guess for this number of count
   */
  public String generateGuess(int count) {
    String guess = "";
    // truncate the guess
    for(int i=0; i<passwordLength; i++) {
      guess = (count % 10) + guess;
      count = count / 10;
    }
    
    return guess;
  }
  
}
