//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Folder Explorer Tester method
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

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.List;

/**
 * This class has methods that contain tests for the FolderExplorer class. It tests various base 
 * and recursive cases of the getContents(File), getDeepContents(File), lookupByName(File, String),
 * lookupByKey(File, String), and lookupBySize(File, long, long) methods from the 
 * FolderExplorer class
 */
public class FolderExplorerTester {

  /**
   * Tests the getContents() method in the FolderExplorer class with many different cases
   * <p>
   * @param folder the directory that is passed to the method to search in
   * @return true if all tests pass and false if any test fails
   * @see FolderExplorer#getContents(File)
   */
  public static boolean testGetContents(File folder) {
    try {
      // Scenario 1: list the basic contents of the cs300 folder
      ArrayList<String> listContent = FolderExplorer.getContents(folder);
      // expected output must contain "exams preparation", "grades", "lecture notes", "programs", 
      // "reading notes", "syllabus.txt", and "todo.txt" only.
      String[] contents = new String[] {"exams preparation", "grades", "lecture notes", "programs",
          "reading notes", "syllabus.txt", "todo.txt"};
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 7) {
        System.out.println("Problem detected: cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }

      // Scenario 2: list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }

      // Scenario 3: list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
        System.out.println(
            "Problem detected: p02 folder must contain only " + "one file named FishTank.java.");
        return false;
      }
      
      // Scenario 4: List the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not"
            + "a directory.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // no problem detected
      }
      
      // Scenario 5: List the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }
      
    } catch (Exception e) {
      System.out.println("Problem detected: Your FolderExplorer.getContents() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests only the base case of the getDeepContents(File) recursive method in the 
   * FolderExplorer class with many different cases
   * <p>
   * @param folder the directory that is passed to the method to search in
   * @return true if all tests pass and false if any test fails
   * @see FolderExplorer#getDeepContents(File)
   */
  public static boolean testDeepGetContentsBaseCase(File folder) {
    try {
      // set up some local variables that holds different values for testing purposes
      File f = new File(folder.getPath() + File.separator + "reading notes");
      ArrayList<String> listContent = FolderExplorer.getDeepContents(f);
      String[] contents = new String[] {"zyBooksCh1.txt", "zyBooksCh2.txt", "zyBooksCh2.txt",
          "zyBooksCh2.txt"};
      List<String> expectedList = Arrays.asList(contents);
      // Scenario 1: base case looking at contents of the reading notes folder
      if (listContent.size() != 4) {
        System.out.println(
            "Problem detected: reading notes folder must contain only 4 files");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of the reading notes folder.");
          return false;
        }
      }
      
      // Scenario 2: base case looking at the contents of the p03 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p03");
      listContent = FolderExplorer.getDeepContents(f);
      contents = new String[] {"ExceptionalClimbing.java", "ExceptionalClimbingTester.java"};
      expectedList = Arrays.asList(contents);
      if (listContent.size() != 2) {
        System.out.println(
            "Problem detected: p01 folder must contain only 2 files");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of the p01 folder.");
          return false;
        }
      }
      
      // Scenario 3: base case looking at the contents of the grades folder
      f = new File(folder.getPath() + File.separator + "grades");
      listContent = FolderExplorer.getDeepContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
      
      // Scenario 4: use method on a directory that doesn't exist
      f = new File(folder.getPath() + File.separator + "doesn't exist");
      try {
        listContent = FolderExplorer.getDeepContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getDeepContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // behavior expected
      }
      
      // Scenario 5: use method on a File that isn't a directory
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FolderExplorer.getDeepContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getDeepContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not a directory.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }
      
    } catch (Exception e) {
      System.out.println("Problem detected: Your FolderExplorer.getDeepContents() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests only the recursive case of the getDeepContents(File) recursive method in the 
   * FolderExplorer class with many different cases
   * <p>
   * @param folder the directory that is passed to the method to search in
   * @return true if all tests pass and false if any test fails
   * @see FolderExplorer#getDeepContents(File)
   */
  public static boolean testDeepListRecursiveCase(File folder) {
    try {
      // set up some local variables that holds different values for testing purposes
      File f = new File(folder.getPath() + File.separator + "lecture notes");
      ArrayList<String> listContent = FolderExplorer.getDeepContents(f);
      String[] contents = new String[] {"Inheritance.txt", "Generics.txt", "CreatingClasses.txt", 
          "Sorting.txt", "AlgorithmAnalysis.txt", "Recursion.txt", "proceduralProgramming.txt", 
          "UsingObjects.txt", "ExceptionHandling.txt"};
      List<String> expectedList = Arrays.asList(contents);
      // Scenario 1: recursive case looking at contents of the lecture notes folder
      if (listContent.size() != 9) {
        System.out.println(
            "Problem detected: lecture notes folder must contain only 9 files");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of the lecture notes folder.");
          return false;
        }
      }
      
      // Scenario 2: recursive case looking at the contents of the exams preparation folder
      f = new File(folder.getPath() + File.separator + "exams preparation");
      listContent = FolderExplorer.getDeepContents(f);
      contents = new String[] {"codeSamples.java", "outline.txt"};
      expectedList = Arrays.asList(contents);
      if (listContent.size() != 2) {
        System.out.println(
            "Problem detected: exams preparation folder must contain only 2 files");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i) + " is missing from the" 
              + " output of the list contents of the exams preparation folder.");
          return false;
        }
      }
      
      // Scenario 3: recursive case looking at the contents of the grades folder
      f = new File(folder.getPath() + File.separator + "grades");
      listContent = FolderExplorer.getDeepContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
      
      // Scenario 4: use method on a directory that doesn't exist
      f = new File(folder.getPath() + File.separator + "doesn't exist");
      try {
        listContent = FolderExplorer.getDeepContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getDeepContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // behavior expected
      }
      
      // Scenario 5: use method on a File that isn't a directory
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FolderExplorer.getDeepContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getDeepContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not a directory.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }
      
    } catch (Exception e) {
      System.out.println("Problem detected: Your FolderExplorer.getDeepContents() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the lookupByName(File, String) recursive method in the FolderExplorer class with many 
   * different cases
   * <p>
   * @param folder the directory that is passed to the method to search in
   * @return true if all tests pass and false if any test fails
   * @see FolderExplorer#lookupByName(File, String)
   */
  public static boolean testLookupByFileName(File folder) {
    try {
      // set up some local variables that holds different values for testing purposes
      File f = new File(folder.getPath() + File.separator + "lecture notes");
      String path = FolderExplorer.lookupByName(f, "UsingObjects.txt");
      String content = "cs300/lecture notes/unit1/UsingObjects.txt";
      // Scenario 1: looking for "UsingObjects.txt"
      if (!path.equals(content)) {
        System.out.println(
            "Problem detected: did not return the correct path to UsingObjects.txt");
        return false;
      }
      
      // Scenario 2: looking for "Recursion.txt"
      f = new File(folder.getPath() + File.separator + "lecture notes");
      path = FolderExplorer.lookupByName(f, "Recursion.txt");
      content = "cs300/lecture notes/unit3/Recursion.txt";
      if (!path.equals(content)) {
        System.out.println(
            "Problem detected: did not return the correct path to Recursion.txt");
        return false;
      }
      
      // Scenario 3: looking for "todo.txt"
      f = new File(folder.getPath());
      path = FolderExplorer.lookupByName(f, "todo.txt");
      content = "cs300/todo.txt";
      if (!path.equals(content)) {
        System.out.println(
            "Problem detected: did not return the correct path to todo.txt");
        return false;
      }
      
      // Scenario 4: searching through an empty folder
      f = new File(folder.getPath() + File.separator + "grades");
      try {
        path = FolderExplorer.lookupByName(f, "something");
        System.out.println("Problem detected: Your FolderExplorer.lookupByName() must "
            + "throw a NoSuchElementException if the file was not found.");
        return false;
      } catch (NoSuchElementException e) { // catch only the expected exception
        // behavior expected
      }
      
      // Scenario 5: use method on a directory that doesn't exist
      f = new File(folder.getPath() + File.separator + "doesn't exist");
      try {
        path = FolderExplorer.lookupByName(f, "Sorting.txt");
        System.out.println("Problem detected: Your FolderExplorer.lookupByName() must "
            + "throw a NoSuchElementException if the provided directory does not exist.");
        return false;
      } catch (NoSuchElementException e) { // catch only the expected exception
        // behavior expected
      }
      
      // Scenario 6: use method on a File that isn't a directory
      f = new File(folder.getPath() + File.separator + "syllabus.txt");
      try {
        path = FolderExplorer.lookupByName(f, "Sorting.txt");
        System.out.println("Problem detected: Your FolderExplorer.lookupByName() must throw a " + 
            "NoSuchElementExcpetion if it is provided an input which is not a directory.");
        return false;
      } catch (NoSuchElementException e) {
        // behavior expected
      }
      
      // Scenario 7: no file name
      f = new File(folder.getPath() + File.separator + "lecture notes");
      try {
        path = FolderExplorer.lookupByName(f, null);
        System.out.println("Problem detected: Your FolderExplorer.lookupByName() must throw a " + 
            "NoSuchElementExcpetion if it is provided an null String input");
        return false;
      } catch (NoSuchElementException e) {
        // behavior expected
      }
      
    } catch (Exception e) {
      System.out.println("Problem detected: Your FolderExplorer.lookupByName() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the base case of the lookupByKey(File, String) recursive method in the FolderExplorer 
   * class with many different cases
   * <p>
   * @param folder the directory that is passed to the method to search in
   * @return true if all tests pass and false if any test fails
   * @see FolderExplorer#lookupByKey(File, String)
   */
  public static boolean testLookupByKeyBaseCase(File folder) {
    // set up some local variables that holds different values for testing purposes
    File f = new File(folder.getPath() + File.separator + "reading notes");
    ArrayList<String> listContent = FolderExplorer.lookupByKey(f, ".txt");
    String[] contents = new String[] {"zyBooksCh1.txt", "zyBooksCh2.txt", "zyBooksCh2.txt",
        "zyBooksCh2.txt"};
    List<String> expectedList = Arrays.asList(contents);
    // Scenario 1: base case looking at contents of the reading notes folder
    if (listContent.size() != 4) {
      System.out.println(
          "Problem detected: reading notes folder has 4 files with .txt key");
      return false;
    }
    for (int i = 0; i < expectedList.size(); i++) {
      if (!listContent.contains(expectedList.get(i))) {
        System.out.println("Problem detected: " + expectedList.get(i)
            + " is missing from the output of the list contents of the reading notes folder.");
        return false;
      }
    }
    
    // Scenario 2: base case looking at contents of the exam1 folder
    f = new File(folder.getPath() + File.separator + "exams preparation" + File.separator 
        + "exam1");
    listContent = FolderExplorer.lookupByKey(f, ".java");
    contents = new String[] {"codeSamples.java"};
    expectedList = Arrays.asList(contents);
    if (listContent.size() != 1) {
      System.out.println(
          "Problem detected: exam1 folder has 1 file with .java key");
      return false;
    }
    for (int i = 0; i < expectedList.size(); i++) {
      if (!listContent.contains(expectedList.get(i))) {
        System.out.println("Problem detected: " + expectedList.get(i)
            + " is missing from the output of the list contents of the exam1 folder.");
        return false;
      }
    }
    
    // Scenario 3: base case looking at the contents of the grades folder
    f = new File(folder.getPath() + File.separator + "grades");
    listContent = FolderExplorer.lookupByKey(f, ".txt");
    if (listContent.size() != 0) {
      System.out.println("Problem detected: grades folder must be empty.");
      return false;
    }
    
    // Scenario 4: use method on a directory that doesn't exist
    f = new File(folder.getPath() + File.separator + "doesn't exist");
    listContent = FolderExplorer.lookupByKey(f, ".txt");
    if (listContent.size() != 0) {
      System.out.println("Problem detected: empty ArrayList must be returned for " 
          + "a directory that deosn't exist");
      return false;
    }
    
    // Scenario 5: use method on a File that isn't a directory
    f = new File(folder.getPath() + File.separator + "todo.txt");
    listContent = FolderExplorer.lookupByKey(f, ".txt");
    if (listContent.size() != 0) {
      System.out.println("Problem detected: empty ArrayList must be returned for " 
          + "File that isn't a File");
      return false;
    }
    
    // Scenario 6: no key provided
    f = new File(folder.getPath() + File.separator + "reading notes");
    listContent = FolderExplorer.lookupByKey(f, null);
    if (listContent.size() != 0) {
      System.out.println("Problem detected: empty ArrayList must be returned for " 
          + "no provided key");
      return false;
    }
    
    // Scenario 7: no matches
    f = new File(folder.getPath() + File.separator + "reading notes");
    listContent = FolderExplorer.lookupByKey(f, ".pdf");
    if (listContent.size() != 0) {
      System.out.println("Problem detected: empty ArrayList must be returned for " 
          + "no matches for the given key");
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the lookupBySize(File, long, long) recursive method in the FolderExplorer 
   * class with many different cases
   * <p>
   * @param folder the directory that is passed to the method to search in
   * @return true if all tests pass and false if any test fails
   * @see FolderExplorer#lookupBySize(File, long, long)
   */
  public static boolean testLookupBySize(File folder) {
    // set up some local variables that holds different values for testing purposes
    File f = new File(folder.getPath() + File.separator + "reading notes");
    ArrayList<String> listContent = FolderExplorer.lookupBySize(f, 0, 100);
    String[] contents = new String[] {"zyBooksCh1.txt", "zyBooksCh2.txt", "zyBooksCh2.txt",
        "zyBooksCh2.txt"};
    List<String> expectedList = Arrays.asList(contents);
    // Scenario 1: base case looking at contents of the reading notes folder
    if (listContent.size() != 4) {
      System.out.println(
          "Problem detected: reading notes folder has 4 files with size between 0 and 100 bytes");
      return false;
    }
    for (int i = 0; i < expectedList.size(); i++) {
      if (!listContent.contains(expectedList.get(i))) {
        System.out.println("Problem detected: " + expectedList.get(i)
            + " is missing from the output of the list contents of the reading notes folder.");
        return false;
      }
    }
    
    // Scenario 2: looking at contents of the programs folder
    f = new File(folder.getPath() + File.separator + "programs");
    listContent = FolderExplorer.lookupBySize(f, 1000000, 10000000);
    contents = new String[] {"Program01.pdf", "Program02.pdf"};
    expectedList = Arrays.asList(contents);
    if (listContent.size() != 2) {
      System.out.println(
          "Problem detected: exam1 folder has 2 file with size between 1000000 and 10000000 bytes");
      return false;
    }
    for (int i = 0; i < expectedList.size(); i++) {
      if (!listContent.contains(expectedList.get(i))) {
        System.out.println("Problem detected: " + expectedList.get(i)
            + " is missing from the output of the list contents of the programs folder.");
        return false;
      }
    }
    
    // Scenario 3: base case looking at the contents of the grades folder
    f = new File(folder.getPath() + File.separator + "grades");
    listContent = FolderExplorer.lookupBySize(f, 100, 10000000);
    if (listContent.size() != 0) {
      System.out.println("Problem detected: grades folder must be empty.");
      return false;
    }
    
    // Scenario 4: use method on a directory that doesn't exist
    f = new File(folder.getPath() + File.separator + "doesn't exist");
    listContent = FolderExplorer.lookupBySize(f, 100, 10000000);
    if (listContent.size() != 0) {
      System.out.println("Problem detected: empty ArrayList must be returned for " 
          + "a directory that deosn't exist");
      return false;
    }
    
    // Scenario 5: use method on a File that isn't a directory
    f = new File(folder.getPath() + File.separator + "todo.txt");
    listContent = FolderExplorer.lookupBySize(f, 100, 10000000);
    if (listContent.size() != 0) {
      System.out.println("Problem detected: empty ArrayList must be returned for " 
          + "File that isn't a File");
      return false;
    }
    
    // Scenario 6: no matches
    f = new File(folder.getPath() + File.separator + "reading notes");
    listContent = FolderExplorer.lookupBySize(f, 1000000000, 2000000000);
    if (listContent.size() != 0) {
      System.out.println("Problem detected: empty ArrayList must be returned for " 
          + "no matches for the given key");
      return false;
    }
    
    return true;
  }

  /**
   * Main method of the FolderExplorerTester class. It is used for testing.
   * <p>
   * @param args is the command line arguments
   */
  public static void main(String[] args) {
    System.out.println("testGetContents: " + testGetContents(new File("cs300")));
    System.out.println("testDeepGetContentsBaseCase: " 
        + testDeepGetContentsBaseCase(new File("cs300")));
    System.out.println("testDeepListRecursiveCase: " 
        + testDeepListRecursiveCase(new File("cs300")));
    System.out.println("testLookupByFileName: " + testLookupByFileName(new File("cs300")));
    System.out.println("testLookupByKeyBaseCase: " + testLookupByKeyBaseCase(new File("cs300")));
    System.out.println("testLookupBySize: " + testLookupBySize(new File("cs300")));
    
    //File f = new File("cs300"+File.separator+"lecture notes");
    //File f2 = new File("cs300");
    //try {
      //for(int i=0; i<f2.listFiles().length;i++) {
       // System.out.println(f2.listFiles()[i]);
      //}
      //System.out.println(FolderExplorer.getDeepContents(f));
      //System.out.println(FolderExplorer.lookupByName(f, "UsingObjects.txt"));
      //System.out.println(FolderExplorer.lookupByName(f2, "todo.txt"));
      //System.out.println(FolderExplorer.lookupByName(f2, "Recursion.txt"));
      //System.out.println(FolderExplorer.lookupByName(f2, "CreatingClasses.txt"));
      //System.out.println(FolderExplorer.lookupByName(f2, "ClimbingTracker.java"));
      //System.out.println(FolderExplorer.lookupByName(f2, "zyBooksCh2.txt"));
      //System.out.println(FolderExplorer.lookupByName(f2, "Program01.pdf"));
      //System.out.println(FolderExplorer.lookupByName(f2, "grades"));
      //System.out.println(FolderExplorer.lookupByKey(f, ".txt"));
      //System.out.println(FolderExplorer.lookupByKey(f2, ".pdf"));
      //System.out.println(FolderExplorer.lookupBySize(f, 0, 100));
      //System.out.println(FolderExplorer.lookupBySize(f2, 100, 10000));
    //} catch (Exception e) {
      //e.printStackTrace();
    //}
  }

}
