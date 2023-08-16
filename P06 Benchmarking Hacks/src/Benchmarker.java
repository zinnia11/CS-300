//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Benchmarking Hacks: Timing and Main Methods
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

public class Benchmarker {
  
  /**
   * Records the time in milliseconds of the bruteForce() method in PasswordHacker
   * <p>
   * @param ph is the PasswordHacker the bruteForce() method is used on
   * @return a long that is the time in milliseconds the bruteForce() method took to run
   * @see PasswordHacker#bruteForce()
   */
  public static long timeBruteForce(PasswordHacker ph) {
    long begin;
    long end;
    
    begin = System.currentTimeMillis();
    ph.bruteForce();
    end = System.currentTimeMillis();
    
    return end-begin;
  }
  
  /**
   * Records the time in milliseconds of the hack() method in PasswordHacker
   * <p>
   * @param ph is the PasswordHacker in hack() method is used on
   * @return a long that is the time in millisecond the hack() method took to run
   * @see PasswordHacker#hack()
   */
  public static long timeHack(PasswordHacker ph) {
    long begin;
    long end;
    
    begin = System.currentTimeMillis();
    ph.hack();
    end = System.currentTimeMillis();
    
    return end-begin;
  }
  
  /**
   * Finds the average time it took for the hack() method to run for a certain password length over 
   * multiple runs and the average time it took for the bruteForce() method to run. Returns a 
   * formatted String with both the average times.
   * <p>
   * @param passwordLength int length of the password
   * @param numRuns int indicating how many runs the time will be averaged over
   * @return a formatted String with both the average time for the hack() and bruteForce() methods
   * @see #timeBruteForce(PasswordHacker)
   * @see #timeHack(PasswordHacker)
   */
  public static String race(int passwordLength, int numRuns) {
    long timeHack=0;
    long timeForce=0;
    // generates a new PasswordHacker for each run and adds the time of the hack method to a 
    // variable and the time for bruteForce() to a variable
    for(int i=0; i<numRuns; i++) {
      PasswordHacker pHack = new PasswordHacker(passwordLength);
      timeHack += timeHack(pHack);
      timeForce += timeBruteForce(pHack);
      //System.out.println(timeForce);
      //System.out.println(timeHack);
    }
    
    timeHack /= numRuns;
    timeForce /= numRuns;
    
    return "Brute force " + passwordLength + ": " + timeForce + "\nHack " + passwordLength
        + ": " + timeHack;
  }

  /**
   * Main method of the Benchmarker class. It is used for testing.
   * <p>
   * @param args is the command line arguments
   */
  public static void main(String[] args) {
    LockBox test = new LockBox(4);
    
    PasswordHacker hack = new PasswordHacker(4);
    
    
    System.out.println(timeBruteForce(hack));
    System.out.println(timeHack(hack));
    
    System.out.println(race(6,10));
    
    System.out.println(test.hackMe());
  }

}
