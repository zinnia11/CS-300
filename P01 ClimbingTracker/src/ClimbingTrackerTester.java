//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Climbing Tracker Class Tester
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
// Persons:         None
// Online Sources:  zyBooks - formatting and using oversize arrays
//                  shorturl.at/fpCKS - how to format the JavaDoc comments for the main method
//                  shorturl.at/hilCU - how to format JavaDoc comments in general
//                  shorturl.at/bjGH3 - Java 11 API for how to use some of the methods
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class contains tester methods for the class ClimbingTracker that are meant to test both 
 * typical and edge cases for each of the public methods in ClimbingTracker
 */
public class ClimbingTrackerTester {
  /**
   * Tests the sendClimb method in the ClimbingTracker class with multiple different cases of arrays
   * and Strings to add
   * <p>
   * @return true if all tests pass and false if any test fails
   * @see ClimbingTracker#sendClimb(String[], int, String)
   */
  public static boolean testSendClimb() {
    //typical cases
    String[] testClimbs = {"V1", "V2", "V0", null, null};
    int sendNum = 3;
    if (!(ClimbingTracker.sendClimb(testClimbs, sendNum, "V7") == 4)) {
      return false;
    }
    
    String[] testClimbs2 = {null, null, "V3", "V2", null};
    sendNum = 4;
    if (!(ClimbingTracker.sendClimb(testClimbs2, sendNum, "V2") == 5)) {
      return false;
    }
    
    //edge cases
    String[] testClimbs3 = new String[5];
    sendNum = 0;
    if (!(ClimbingTracker.sendClimb(testClimbs3, sendNum, "V2") == 1)) {
      return false;
    }
    
    String[] testClimbs4 = {"V1", "V1", "V1", "V1", "V1"};
    sendNum = 5;
    if (!(ClimbingTracker.sendClimb(testClimbs4, sendNum, "V2") == 5)) {
      return false;
    }
    
    //invalid Strings
    String[] testClimbs5 = {"V1", "V2", "V0", null, null};
    sendNum = 3;
    if (!(ClimbingTracker.sendClimb(testClimbs5, sendNum, "v2") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.sendClimb(testClimbs5, sendNum, "V100") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.sendClimb(testClimbs5, sendNum, "V8") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.sendClimb(testClimbs5, sendNum, "") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.sendClimb(testClimbs5, sendNum, "2") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.sendClimb(testClimbs5, sendNum, "V") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.sendClimb(testClimbs5, sendNum, "this is wrong") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.sendClimb(testClimbs5, sendNum, "1000000") == 3)) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the failClimb method in the ClimbingTracker class with multiple different cases of arrays
   * and Strings to add
   * <p>
   * @return true if all tests pass and false if any fail
   * @see ClimbingTracker#failClimb(String[], int, String)
   */
  public static boolean testFailClimb() {
    //typical cases
    String[] testClimbs = {"V1", "V2", "V0", null, null};
    int failNum = 3;
    if (!(ClimbingTracker.failClimb(testClimbs, failNum, "V7") == 4)) {
      return false;
    }
    
    String[] testClimbs2 = {null, null, "V3", "V2", null};
    failNum = 4;
    if (!(ClimbingTracker.failClimb(testClimbs2, failNum, "V2") == 5)) {
      return false;
    }
    
    //edge cases
    String[] testClimbs3 = new String[5];
    failNum = 0;
    if (!(ClimbingTracker.failClimb(testClimbs3, failNum, "V2") == 1)) {
      return false;
    }
    
    String[] testClimbs4 = {"V1", "V1", "V1", "V1", "V1"};
    failNum = 5;
    if (!(ClimbingTracker.failClimb(testClimbs4, failNum, "V2") == 5)) {
      return false;
    }
    
    //invalid Strings
    String[] testClimbs5 = {"V1", "V2", "V0", null, null};
    failNum = 3;
    if (!(ClimbingTracker.failClimb(testClimbs5, failNum, "v2") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.failClimb(testClimbs5, failNum, "V100") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.failClimb(testClimbs5, failNum, "V8") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.failClimb(testClimbs5, failNum, "") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.failClimb(testClimbs5, failNum, "2") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.failClimb(testClimbs5, failNum, "V") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.failClimb(testClimbs5, failNum, "this is wrong") == 3)) {
      return false;
    }
    if (!(ClimbingTracker.failClimb(testClimbs5, failNum, "1000000") == 3)) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the method getStats from the ClimbingTracker class with multiple different cases of 
   * arrays and historyLength
   * <p>
   * @return true if all tests pass and false if any test fails
   * @see ClimbingTracker#getStats(String[], int, String[], int, int)
   */
  public static boolean testGetStats() {
    //typical cases
    String[] testSend = {"V0", "V0", "V3", "V2", null};
    int sendNum = 4;
    String[] testFail = {"V3", "V2", "V4", null, null};
    int failNum = 3;
    if (!ClimbingTracker.getStats(testSend, sendNum, testFail, failNum, 2).equals(
        "send: " + (5.0/2.0) + "\nfail: " + (6.0/2.0))) {
      return false;
    }
    if (!ClimbingTracker.getStats(testSend, sendNum, testFail, failNum, 3).equals(
        "send: " + (5.0/3.0) + "\nfail: " + (9.0/3.0))) {
      return false;
    }
    
    String[] testSend3 = {"V0", "V0", "V3", "V2", "V1"};
    int sendNum3 = 5;
    String[] testFail3 = {"V3", "V2", "V4", null, null};
    int failNum3 = 3;
    if (!ClimbingTracker.getStats(testSend3, sendNum3, testFail3, failNum3, 5).equals(
        "send: " + (6.0/5.0) + "\nfail: " + (9.0/3.0))) {
      return false;
    }
    
    //test with nulls in the arrays
    String[] testSend4 = {null, null, "V3", "V2", null};
    int sendNum4 = 4;
    String[] testFail4 = {null, "V2", "V4", null, null};
    int failNum4 = 3;
    if (!ClimbingTracker.getStats(testSend4, sendNum4, testFail4, failNum4, 2).equals(
        "send: " + (5.0/2.0) + "\nfail: " + (6.0/2.0))) {
      return false;
    }
    
    //test correct String output with 0 as inputs
    String[] testSend5 = {"V0", "V0", "V3", "V2", "V1"};
    int sendNum5 = 5;
    String[] testFail5 = {"V3", "V2", "V4", null, null};
    int failNum5 = 3;
    if (!ClimbingTracker.getStats(testSend5, sendNum5, testFail5, failNum5, 9).equals(
        "send: " + (6.0/5.0) + "\nfail: " + (9.0/3.0))) {
      return false;
    }
    if (!ClimbingTracker.getStats(testSend5, sendNum5, testFail5, failNum5, 0).equals(
        "send: -- \nfail: --")) {
      return false;
    }
    if (!ClimbingTracker.getStats(testSend5, 0, testFail5, 0, 5).equals(
        "send: -- \nfail: --")) {
      return false;
    }
    if (!ClimbingTracker.getStats(testSend5, 0, testFail5, failNum5, 5).equals(
        "send: -- \nfail: " + (9.0/3.0))) {
      return false;
    }
    if (!ClimbingTracker.getStats(testSend5, sendNum5, testFail5, 0, 5).equals(
        "send: " + (6.0/5.0) + "\nfail: --")) {
      return false;
    }
    
    //test 2 different lengths of arrays
    String[] testSend6 = {"V0", "V0", "V1", "V1", "V2", null, null};
    int sendNum6 = 5;
    String[] testFail6 = {"V3", "V2", "V4", null, null};
    int failNum6 = 3;
    if (!ClimbingTracker.getStats(testSend6, sendNum6, testFail6, failNum6, 4).equals(
        "send: " + (4.0/4.0) + "\nfail: " + (9.0/3.0))) {
      return false;
    }
    
    //test large numbers and all zeros
    String[] testSend7 = {"V4", "V4", "V4", "V8", "V7"};
    int sendNum7 = 5;
    String[] testFail7 = {"V0", "V0", "V0", null, null};
    int failNum7 = 3;
    if (!ClimbingTracker.getStats(testSend7, sendNum7, testFail7, failNum7, 5).equals(
        "send: " + (27.0/5.0) + "\nfail: " + (0.0/3.0))) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the method getHistogram in the ClimbingTracker class with multiple different cases of 
   * arrays
   * @return true if all tests pass and false if any test fail
   * @see ClimbingTracker#getHistogram(String[], int, String[], int)
   */
  public static boolean testGetHistogram() {
    //typical cases
    String[] testSend = {"V1", "V0", "V3", "V0", null};
    int sendNum = 4;
    String[] testFail = {"V3", "V2", "V0", null, null};
    int failNum = 3;
    if (!(ClimbingTracker.getHistogram(testSend, sendNum, testFail, failNum).equals(
        "V0:  -  +  + \n"
        + "V1:  + \n"
        + "V2:  - \n"
        + "V3:  -  + \n"))) {
      return false;
    }
    
    //edge cases
    String[] testSend2 = {"V2", "V2", "V2", "V2", "V2"};
    int sendNum2 = 5;
    String[] testFail2 = {"V0", "V2", "V4", "V2", "V0"};
    int failNum2 = 5;
    if (!(ClimbingTracker.getHistogram(testSend2, sendNum2, testFail2, failNum2).equals(
        "V0:  -  - \n"
        + "V1: \n"
        + "V2:  -  -  +  +  +  +  + \n"
        + "V3: \n"
        + "V4:  - \n"))) {
      return false;
    }
    
    String[] testSend3 = new String[5];
    int sendNum3 = 0;
    String[] testFail3 = new String[5];
    int failNum3 = 0;
    if (!(ClimbingTracker.getHistogram(testSend3, sendNum3, testFail3, failNum3).equals(
        "Error: no data to display"))) {
      return false;
    }
    
    //large grades and empty send array
    String[] testSend4 = new String[5];
    int sendNum4 = 0;
    String[] testFail4 = {"V7", "V7", "V0", null, null};
    int failNum4 = 3;
    if (!(ClimbingTracker.getHistogram(testSend4, sendNum4, testFail4, failNum4).equals(
        "V0:  - \n"
        + "V1: \n"
        + "V2: \n"
        + "V3: \n"
        + "V4: \n"
        + "V5: \n"
        + "V6: \n"
        + "V7:  -  - \n"))) {
      return false;
    }
    
    //empty fail array
    String[] testSend5 = {"V7", "V7", "V0", null, null};
    int sendNum5 = 3;
    String[] testFail5 = new String[5];
    int failNum5 = 0;
    if (!(ClimbingTracker.getHistogram(testSend5, sendNum5, testFail5, failNum5).equals(
        "V0:  + \n"
        + "V1: \n"
        + "V2: \n"
        + "V3: \n"
        + "V4: \n"
        + "V5: \n"
        + "V6: \n"
        + "V7:  +  + \n"))) {
      return false;
    }
    
    //nulls in array
    String[] testSend6 = {null, null, "V3", "V0", null};
    int sendNum6 = 4;
    String[] testFail6 = {null, "V2", "V0", null, null};
    int failNum6 = 3;
    if (!(ClimbingTracker.getHistogram(testSend6, sendNum6, testFail6, failNum6).equals(
        "V0:  -  + \n"
        + "V1: \n"
        + "V2:  - \n"
        + "V3:  + \n"))) {
      return false;
    }
    
    //arrays of different lengths
    String[] testSend7 = {"V0", "V0", "V1", "V1", "V2", "V0", null};
    int sendNum7 = 6;
    String[] testFail7 = {"V3", "V2", "V4", null, null};
    int failNum7 = 3;
    if (!(ClimbingTracker.getHistogram(testSend7, sendNum7, testFail7, failNum7).equals(
        "V0:  +  +  + \n"
        + "V1:  +  + \n"
        + "V2:  -  + \n"
        + "V3:  - \n"
        + "V4:  - \n"))) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Runs all of the tester methods
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
  
  /**
   * Main method of the ClimbingTrackerTester class. It calls runAllTests().
   * <p>
   * @param args is the command line arguments
   * @see #runAllTests()
   */
  public static void main(String[] args) {
    //System.out.println(testFailClimb());
    //System.out.println(testSendClimb());
    //System.out.println(testGetStats());
    //System.out.println(testGetHistogram());
    //System.out.print(runAllTests());
    
    runAllTests();
  }
}


