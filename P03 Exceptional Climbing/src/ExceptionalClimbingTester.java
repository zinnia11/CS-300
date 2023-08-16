//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Bouldering Climbing Tracker with Exceptions Tester Class
// Course:   CS 300 Fall 2021
//
// Author:   Zinnia Nie
// Email:    zznie@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
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

import java.util.zip.DataFormatException;

/**
 * Tester class for ExceptionalClimbing class, testing each of the methods for if they throw the 
 * correct exception in different situations
 * <p>
 * @author Zinnia Nie
 */
public class ExceptionalClimbingTester {
  
  /**
   * Tests the sendClimb method in the ExceptionalClimbing class with many cases of arrays and 
   * sent Strings to make sure the correct exception is thrown
   * <p>
   * @return true if all tests pass and false if any test fails
   * @see ExceptionalClimbing#sendClimb(String[], int, String)
   */
  public static boolean testSendClimb() {
    String[] send = {"V0", "V0", "V1", null, null};
    int sendNum = 3;    
    // test scenario 1: testing that exception is not thrown when input is valid
    try {
      ExceptionalClimbing.sendClimb(send, sendNum, "V4");
    } catch (IllegalArgumentException iae) {
      //isn't supposed to catch this
      return false;
    } catch (DataFormatException dfe) {
      // isn't supposed to catch this
      return false;
    } catch (Exception e) {
      // unexpected exception thrown, test fails
      e.printStackTrace();
      return false;
    }
    
    // test scenario 2: trying all the potential invalid inputs throws IllegalArgumentException
    // with invalid input message
    try {
      ExceptionalClimbing.sendClimb(send, sendNum, "V10");  
      // test fails if no exception is thrown
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("V10 is not a valid grade")) {
        // if message isn't correct, then fail the test
        return false;
      }
    } catch (DataFormatException dfe) {
      // isn't supposed to catch this
      return false;
    } catch (Exception e) {
      // unexpected exception thrown, test fails
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalClimbing.sendClimb(send, sendNum, "U1");
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("U1 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalClimbing.sendClimb(send, sendNum, "v1");
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("v1 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalClimbing.sendClimb(send, sendNum, "VA");  
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("VA is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalClimbing.sendClimb(send, sendNum, "V-1");   
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("V-1 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalClimbing.sendClimb(send, sendNum, "V8");   
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("V8 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] send2 = {"V0", "V0", "V1", "V1", "V2"};
    int sendNum2 = 5;     
    // test scenario 3: a full array with no open spaces throws IllegalArgumentException
    // with full array message
    try {
      ExceptionalClimbing.sendClimb(send2, sendNum2, "V2");   
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("cannot add new value to full length 5 array")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] send3 = {"V0", null , "V1", "V1", null};
    int sendNum3 = 4; 
    // test scenario 4: array with null elements throws DataFormatException
    try {
      ExceptionalClimbing.sendClimb(send3, sendNum3, "V2");   
      return false;
    } catch (IllegalArgumentException iae) {
      // isn't supposed to catch this
      return false;
    } catch (DataFormatException dfe) {
      if (!dfe.getMessage().equals("invalid oversize array")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] send4 = {"V0", "V0" , "V1", "V1", null};
    int sendNum4 = -1;     
    // test scenario 5: negative length of oversize array throws DataFormatException
    try {
      ExceptionalClimbing.sendClimb(send4, sendNum4, "V2");   
      return false;
    } catch (IllegalArgumentException iae) {
      // isn't supposed to catch this
      return false;
    } catch (DataFormatException dfe) {
      if (!dfe.getMessage().equals("invalid oversize array")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] send5 = {"V0", "V0", "V1", null, null};
    int sendNum5 = -1; 
    // test scenario 6: multiple different exceptions (invalid input and negative size)
    try {
      ExceptionalClimbing.sendClimb(send5, sendNum5, "v2");   
      return false;
    } catch (IllegalArgumentException iae) {
      // we want to catch this and have is print out the invalid grade message
      if (!iae.getMessage().equals("v2 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      // should not catch this
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] send6 = {"V0", "V0", "V1", "V1", "V2"};
    int sendNum6 = 5; 
    // test scenario 6(cont.): multiple different exceptions (invalid input and full array)
    try {
      ExceptionalClimbing.sendClimb(send6, sendNum6, "v2");   
      return false;
    } catch (IllegalArgumentException iae) {
      // we want to catch this and have is print out the invalid grade message
      if (!iae.getMessage().equals("v2 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      // should not catch this
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] send7 = {"V0", null, "V1", null, "V2"};
    int sendNum7 = 5; 
    // test scenario 6(cont.): multiple different exceptions (full array and null elements)
    try {
      ExceptionalClimbing.sendClimb(send7, sendNum7, "V2");   
      return false;
    } catch (IllegalArgumentException iae) {
      // we want to catch this and have is print out the full array message
      if (!iae.getMessage().equals("cannot add new value to full length 5 array")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      // should not catch this
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }

  /**
   * Tests the failClimb method in the ExceptionalClimbing class with many cases of arrays and 
   * sent Strings to make sure the correct exception is thrown
   * <p>
   * @return true if all tests pass and false if any test fails
   * @see ExceptionalClimbing#failClimb(String[], int, String)
   */
  public static boolean testFailClimb() {
    String[] fail = {"V0", "V0", "V1", null, null};
    int failNum = 3;    
    // test scenario 1: testing that exception is not thrown when input is valid
    try {
      ExceptionalClimbing.failClimb(fail, failNum, "V4");
    } catch (IllegalArgumentException iae) {
      // isn't supposed to catch this
      return false;
    } catch (DataFormatException dfe) {
      // isn't supposed to catch this
      return false;
    } catch (Exception e) {
      // unexpected exception thrown, test fails
      e.printStackTrace();
      return false;
    }
    
    // test scenario 2: trying all the potential invalid inputs throws IllegalArgumentException
    // with invalid grade message
    try {
      ExceptionalClimbing.failClimb(fail, failNum, "V10");  
      // test fails if no exception is thrown
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("V10 is not a valid grade")) {
        // if message isn't correct, then fail the test
        return false;
      }
    } catch (DataFormatException dfe) {
      // isn't supposed to catch this
      return false;
    } catch (Exception e) {
      // unexpected exception thrown, test fails
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalClimbing.failClimb(fail, failNum, "U1");  
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("U1 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalClimbing.failClimb(fail, failNum, "v1");  
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("v1 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalClimbing.failClimb(fail, failNum, "VA");  
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("VA is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalClimbing.failClimb(fail, failNum, "V-1");
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("V-1 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalClimbing.failClimb(fail, failNum, "V8");
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("V8 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] fail2 = {"V0", "V0", "V1", "V1", "V2"};
    int failNum2 = 5;     
    // test scenario 3: a full array with no open spaces throws IllegalArgumentException
    // with full array message
    try {
      ExceptionalClimbing.failClimb(fail2, failNum2, "V2");  
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("cannot add new value to full length 5 array")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] fail3 = {"V0", null , "V1", "V1", null};
    int failNum3 = 4; 
    // test scenario 4: array with null elements throws DataFormatException
    try {
      ExceptionalClimbing.failClimb(fail3, failNum3, "V2");  
      return false;
    } catch (IllegalArgumentException iae) {
      // isn't supposed to catch this
      return false;
    } catch (DataFormatException dfe) {
      if (!dfe.getMessage().equals("invalid oversize array")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] fail4 = {"V0", "V0" , "V1", "V1", null};
    int failNum4 = -1;     
    // test scenario 5: negative length of oversize array throws DataFormatException
    try {
      ExceptionalClimbing.failClimb(fail4, failNum4, "V2");   
      return false;
    } catch (IllegalArgumentException iae) {
      // isn't supposed to catch this
      return false;
    } catch (DataFormatException dfe) {
      if (!dfe.getMessage().equals("invalid oversize array")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] fail5 = {"V0", "V0", "V1", null, null};
    int failNum5 = -1; 
    // test scenario 6: multiple different exceptions (invalid input and negative size)
    try {
      ExceptionalClimbing.failClimb(fail5, failNum5, "v2");   
      return false;
    } catch (IllegalArgumentException iae) {
      // we want to catch this and have is print out the invalid grade message
      if (!iae.getMessage().equals("v2 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      // should not catch this
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] fail6 = {"V0", "V0", "V1", "V1", "V2"};
    int failNum6 = 5; 
    // test scenario 6(cont.): multiple different exceptions (invalid input and full array)
    try {
      ExceptionalClimbing.failClimb(fail6, failNum6, "v2");   
      return false;
    } catch (IllegalArgumentException iae) {
      // we want to catch this and have is print out the invalid grade message
      if (!iae.getMessage().equals("v2 is not a valid grade")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      // should not catch this
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] fail7 = {"V0", null, "V1", null, "V2"};
    int failNum7 = 5; 
    // test scenario 6(cont.): multiple different exceptions (full array and null elements)
    try {
      ExceptionalClimbing.failClimb(fail7, failNum7, "V2");   
      return false;
    } catch (IllegalArgumentException iae) {
      // we want to catch this and have is print out the full array message
      if (!iae.getMessage().equals("cannot add new value to full length 5 array")) {
        return false;
      }
    } catch (DataFormatException dfe) {
      // should not catch this
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the method getStats from the ExceptionalClimbing class with multiple different cases of 
   * arrays and historyLength to make sure the correct exception is thrown
   * <p>
   * @return true if all tests pass and false if any test fails
   * @see ExceptionalClimbing#getStats(String[], int, String[], int, int)
   */
  public static boolean testGetStats() {
    String[] send = {"V0", "V0", "V1", null, null};
    int sendNum = 3;  
    String[] fail = {"V0", "V1", "V1", "V2", null};
    int failNum = 4; 
    // test scenario 1: testing that exception is not thrown when input is valid
    try {
      ExceptionalClimbing.getStats(send, sendNum, fail, failNum, 3);
    } catch (IllegalArgumentException iae) {
      //isn't supposed to catch this
      return false;
    } catch (RuntimeException re) { // IllegalArgumentExcpetion is a child of RuntimeException
                                    // and must be caught before RuntimeException if it occurs
      // isn't supposed to catch this
      return false;
    } catch (Exception e) {
      // unexpected exception, test fails
      e.printStackTrace();
      return false;
    }
     
    // test scenario 2: testing that exception is not thrown when input is valid and historyLength
    // is longer than size of both arrays
    try {
      ExceptionalClimbing.getStats(send, sendNum, fail, failNum, 100);
    } catch (IllegalArgumentException iae) {
      // isn't supposed to catch this
      return false;
    } catch (RuntimeException re) {
      // isn't supposed to catch this
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] send2 = {"V0", "V0", "V1", null, null};
    int sendNum2 = 3;  
    String[] fail2 = new String[5];
    int failNum2 = 0; 
    // test scenario 3: testing that exception is not thrown when input is valid and one array is 
    // empty
    try {
      ExceptionalClimbing.getStats(send2, sendNum2, fail2, failNum2, 3);
    } catch (IllegalArgumentException iae) {
      // isn't supposed to catch this
      return false;
    } catch (RuntimeException re) {
      // isn't supposed to catch this
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    String[] send3 = new String[5];
    int sendNum3 = 0;  
    String[] fail3 = {"V0", "V1", "V1", "V2", null};
    int failNum3 = 4; 
    // test scenario 3(cont.): testing that exception is not thrown when input is valid and one 
    // array is empty
    try {
      ExceptionalClimbing.getStats(send3, sendNum3, fail3, failNum3, 3);
    } catch (IllegalArgumentException iae) {
      // isn't supposed to catch this
      return false;
    } catch (RuntimeException re) {
      // isn't supposed to catch this
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] send4 = new String[5];
    int sendNum4 = 0;
    String[] fail4 = new String[5];
    int failNum4 = 0;
    // test scenario 4: two empty arrays throws RuntimeException
    try {
      ExceptionalClimbing.getStats(send4, sendNum4, fail4, failNum4, 3);
      // if no exception thrown, fail the test
      return false;
    } catch (IllegalArgumentException iae) {
      // not supposed to be caught
      return false;
    } catch (RuntimeException re) {
      if (!re.getMessage().equals("no climbs provided")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] send5 = {"V0", "V0", "V1", null, null};
    int sendNum5 = 3;
    String[] fail5 = {"V0", "V1", "V1", "V2", null};
    int failNum5 = 4;
    // test scenario 5: historyLength equals 0 throws IllegalArgumentException
    try {
      ExceptionalClimbing.getStats(send5, sendNum5, fail5, failNum5, 0);
      // if no exception thrown, fail the test
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("0 is not a valid history length")) {
        return false;
      }
    } catch (RuntimeException re) {
      // not supposed to be caught
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    //test scenario 6: historyLength is negative throws IllegalArgumentException
    try {
      ExceptionalClimbing.getStats(send5, sendNum5, fail5, failNum5, -29);
      // if no exception thrown, fail the test
      return false;
    } catch (IllegalArgumentException iae) {
      if (!iae.getMessage().equals("-29 is not a valid history length")) {
        return false;
      }
    } catch (RuntimeException re) {
      // not supposed to be caught
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] send6 = new String[5];
    int sendNum6 = 0;
    String[] fail6 = new String[5];
    int failNum6 = 0;
    //test scenario 7: multiple different exceptions (empty arrays and negative historyLength)
    try {
      ExceptionalClimbing.getStats(send6, sendNum6, fail6, failNum6, -10);
      // if no exception thrown, fail the test
      return false;
    } catch (IllegalArgumentException iae) {
      // not supposed to be caught
      return false;
    } catch (RuntimeException re) {
      if (!re.getMessage().equals("no climbs provided")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the method getHistogram in the ExceptionalClimbing class with multiple different cases of
   * arrays to make sure the correct exception is thrown
   * <p>
   * @return true if all tests pass and false if any tests fail
   * @see ExceptionalClimbing#getHistogram(String[], int, String[], int)
   */
  public static boolean testGetHistogram() {
    String[] send = {"V0", "V0", "V1", null, null};
    int sendNum = 3;  
    String[] fail = {"V0", "V1", "V1", "V2", null};
    int failNum = 4; 
    // test scenario 1: testing that exception is not thrown when input is valid
    try {
      ExceptionalClimbing.getHistogram(send, sendNum, fail, failNum);
    } catch (RuntimeException re) { 
      // isn't supposed to catch this
      return false;
    } catch (Exception e) {
      // unexpected exception, test fails
      e.printStackTrace();
      return false;
    }
    
    String[] send2 = {"V0", "V0", "V1", null, null};
    int sendNum2 = 3;  
    String[] fail2 = new String[5];
    int failNum2 = 0; 
    // test scenario 2: testing that exception is not thrown when input is valid and one array is 
    // empty
    try {
      ExceptionalClimbing.getHistogram(send2, sendNum2, fail2, failNum2);
    } catch (RuntimeException re) {
      // isn't supposed to catch this
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    String[] send3 = new String[5];
    int sendNum3 = 0;  
    String[] fail3 = {"V0", "V1", "V1", "V2", null};
    int failNum3 = 4; 
    // test scenario 2(cont.): testing that exception is not thrown when input is valid and one 
    // array is empty
    try {
      ExceptionalClimbing.getHistogram(send3, sendNum3, fail3, failNum3);
    } catch (RuntimeException re) {
      // isn't supposed to catch this
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    String[] send4 = new String[5];
    int sendNum4 = 0;
    String[] fail4 = new String[5];
    int failNum4 = 0;
    // test scenario 3: two empty arrays throws RuntimeExceptions
    try {
      ExceptionalClimbing.getHistogram(send4, sendNum4, fail4, failNum4);
      return false;
    } catch (RuntimeException re) {
      if(!re.getMessage().equals("no climbs provided")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Runs all of the tester methods
   * <p>
   * @return true if all tester methods pass and false if any fail
   * @see #testSendClimb()
   * @see #testFailClimb()
   * @see #testGetStats()
   * @see #testGetHistogram()
   */
  public static boolean runAllTests() {
    if (testSendClimb() == false) {
      return false;
    }
    if (testFailClimb() == false) {
      return false;
    }
    if (testGetStats() == false) {
      return false;
    }
    if (testGetHistogram() == false) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    //testSendClimb();
    //testFailClimb();
    //testGetStats();
    //testGetHistogram();
    
    //System.out.println(runAllTests());
    runAllTests();

  }

}
