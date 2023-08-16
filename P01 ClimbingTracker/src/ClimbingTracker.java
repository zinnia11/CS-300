//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Bouldering Route Grade Tracker
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
//                  shorturl.at/hilCU - how to format JavaDoc comments in general
//                  shorturl.at/bjGH3 - Java 11 API for how to use some of the methods
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class contains methods for a bouldering route grade tracker. It includes methods that will
 * add a new grade onto the end of a fails or successes oversize array. There is a method that will
 * find the average of a certain number of past days. It also has a method that will print out a 
 * formatted string that contains the number of success and fails in each grade.
 */
public class ClimbingTracker {
  /**
   * A helper method that converts the String grade into an integer
   * <p>
   * @param getList is the array the grade is coming from 
   * @param index is the index the grade is at in the array
   * @return the integer of the climb grade
   */
  private static int numGrade(String[] getList, int index) {
    //return Character.getNumericValue(getList[index].charAt(1));
    return Integer.parseInt(getList[index].substring(1));
  }
  
  /**
   * Adds the grade of a successful route to the end of an array if there is enough room and the 
   * grade is valid
   * <p>
   * @param send is an oversized array containing the successful grades
   * @param numSend is the size of the array
   * @param grade is the grade of the climb that the user wants to add
   * @return an integer that is the new length of the array 
   * @see #checkIfValid(String)
   */
  public static int sendClimb(String[] send, int numSend, String grade) {
    if ((checkIfValid(grade)) && (numSend < send.length) && (send[numSend] == null)) {
      send[numSend] = grade;
      numSend++;
    }
    
    return numSend;
  }
  
  /**
   * Adds the grade of a failed route to the end of an array if there is enough room and the 
   * grade is valid
   * <p>
   * @param fail is an oversized array containing the failed grades
   * @param numFail is the size of the array
   * @param grade is the grade of the climb the user wants to add
   * @return an integer that is the new length of the array
   * @see #checkIfValid(String)
   */
  public static int failClimb(String[] fail, int numFail, String grade) {
    if ((checkIfValid(grade)) && (numFail < fail.length) && (fail[numFail] == null)) {
      fail[numFail] = grade;
      numFail++;
    }
    
    return numFail;
  }
  
  /**
   * Checks the conditions to make sure that the grade from the user is a valid grade of a climb
   * <p>
   * @param grade is the String that is the grade the user wants to use
   * @return a boolean true if the grade is valid and false if it is not
   */
  private static boolean checkIfValid(String grade) {
    if (grade.length() == 2) {
      if (grade.charAt(0) == 'V') {
        if (0 <= Character.getNumericValue(grade.charAt(1)) && 
            Character.getNumericValue(grade.charAt(1)) < 8 ) {
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * Calculates the average grade of the climbs from the previous days in both the successful and 
   * failed arrays and outputs the results in a formatted String.
   * <p>
   * @param send is an oversized array containing the successful runs
   * @param numSend is an integer that is the size of the successful array
   * @param fail is an oversized array containing the failed runs
   * @param numFail is an integer that is the size of the failed array
   * @param historyLength is the number of previous days the average will be found of
   * @return a formatted String with the average of the two arrays
   * @see #calculateAverage(String[], int, int)
   */
  public static String getStats(String[] send, int numSend, String[] fail, int numFail, 
      int historyLength) {
    double sendAverage = 0.0;
    double failAverage = 0.0;
    
    if (historyLength <= 0) {
      return "send: -- \nfail: --";
    }
    if ((numSend == 0) && (numFail == 0)) {
      return "send: -- \nfail: --";
    }
    
    sendAverage = calculateAverage(send, numSend, historyLength);
    failAverage = calculateAverage(fail, numFail, historyLength);
    
    if (numSend == 0) {
      return "send: -- \nfail: " + failAverage;
    }
    if (numFail == 0) {
      return "send: " + sendAverage + "\nfail: --";
    }
    return "send: " + sendAverage + "\nfail: " + failAverage;
  }
  
  /**
   * Calculates the average of an oversized array's most recent entries
   * <p>
   * @param useList is the oversized array that the average is being calculated from
   * @param numSize is the size of the array
   * @param historyLength is the number of previous entries that will be used to calculate the 
   *      average
   * @return a double that is the calculated average
   * @see #numGrade(String[], int)
   */
  private static double calculateAverage(String[] useList, int numSize, int historyLength) {
    double listAverage = 0.0;
    int calculateStart = 0;
    
    //determine which index of the array to start on
    if ((numSize >= 0) && (numSize > historyLength)) {
      calculateStart = numSize - historyLength;
    }
    
    //determine how many times to loop the code
    int numLoop = historyLength;
    if(historyLength >= useList.length) {
      numLoop = useList.length;
    }
    //calculate sum of grades in arrays
    for(int i=0; i<numLoop; i++) {
      if (useList[calculateStart] != null) {
        listAverage += numGrade(useList, calculateStart);
        calculateStart++;
      }
    }
    
    //determine what to divide the sum of the grades by
    if (numSize != 0) {
      if (historyLength <= numSize) {
        listAverage /= historyLength;
      }
      else {
      listAverage /= numSize;
      }
    }
    
    return listAverage;
  }
  
  /**
   * Creates a formatted String that records how many success and failures of each grade are 
   * in the oversized arrays
   * <p>
   * @param send is the array of successful climbs
   * @param numSend is the length of the successes array
   * @param fail is the array of failed climbs
   * @param numFail is the length of the fails array
   * @return a formatted string with the success and failures
   */
  public static String getHistogram(String[] send, int numSend, String[] fail, int numFail) {
    String display = "";
    int topGrade = 0;
    
    if((numSend == 0) && (numFail == 0)) {
      return "Error: no data to display";
    }
    
    //find the single highest grade in both the send and fail arrays
    for(int i=0; i<numFail; i++) {
      if ((fail[i] != null) && (numGrade(fail, i) > topGrade)) {
        topGrade = numGrade(fail, i);
      }
    }
    for(int i=0; i<numSend; i++) {
      if ((send[i] != null) && (numGrade(send, i) > topGrade)) {
        topGrade = numGrade(send, i);
      }
    }
    
    //print the grades starting from V0 until the highest grade and the number of instances of each
    //in the arrays
    for(int i=0; i<=topGrade; i++) {
      display = display + "V" + i + ": ";
      //Starting with fail array, print the correct symbol if the grades match
      for(int j=0; j<numFail; j++) {
        if ((fail[j] != null) && (numGrade(fail, j) == i)) {
          display = display + " - ";
        }
      }
      for(int k=0; k<numSend; k++) {
        if ((send[k] != null) && (numGrade(send, k) == i)) {
          display = display + " + ";
        }
      }
      display = display + "\n";
    }
    
    return display; 
  }
  
}
