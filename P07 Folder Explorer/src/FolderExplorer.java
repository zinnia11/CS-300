//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Folder Explorer through Recursion
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

/**
 * This class uses recursion to search through a file hierarchy, returning the contents and 
 * searching for files that have certain characteristics, like a certain name, key, or size.
 */
public class FolderExplorer {
  /**
   * Gets the shallow contents of the given directory which are the Files that are in it and the
   * other directories
   * <p>
   * @param currentDirectory representing the folder to get the contents from
   * @return an ArrayList of Strings that represent the contents of the directory
   * @throws NotDirectoryException when the given directory is not a directory or doesn't exist
   */
  public static ArrayList<String> getContents(File currentDirectory) throws NotDirectoryException {
    // Throws NotDirectoryException with a description error message if
    // the provided currentDirectory does not exist or if it is not a directory
    if (!currentDirectory.exists()) {
      throw new NotDirectoryException("A directory with this name does not exist.");
    }
    if (!currentDirectory.isDirectory()) {
      throw new NotDirectoryException(currentDirectory + " is not a directory");
    }
    
    // Returns a list of the names of all files and directories in
    // the the given folder currentDirectory.
    ArrayList<String> all = new ArrayList<String>();
    for(int i=0; i<currentDirectory.list().length; i++) {
      all.add(currentDirectory.list()[i]);
    }
    return all;
  }

  /**
   * Gets the deep contents of the given directory which are all of the Files in the directory
   * <p>
   * @param currentDirectory representing the folder to get the contents from
   * @return an ArrayList of Strings that represent the File contents of the directory
   * @throws NotDirectoryException when the given directory is not a directory or doesn't exist
   */
  public static ArrayList<String> getDeepContents(File currentDirectory)
      throws NotDirectoryException {
    // Throws NotDirectoryException with a description error message if
    // the provided currentDirectory does not exist or if it is not a directory
    if (!currentDirectory.exists()) {
      throw new NotDirectoryException("A directory with this name does not exist.");
    }
    if (!currentDirectory.isDirectory()) {
      throw new NotDirectoryException(currentDirectory + " is not a directory");
    }
    
    // Recursive method that lists the names of all the files (not directories)
    // in the given directory and its sub-directories.
    ArrayList<String> all = new ArrayList<String>();
    for(int i=0; i<currentDirectory.listFiles().length; i++) {
      if (currentDirectory.listFiles()[i].isFile()) {
        all.add(currentDirectory.listFiles()[i].getName());
      }
      else {
        all.addAll(getDeepContents(currentDirectory.listFiles()[i]));
      }
    }
    
    return all;
  }

  /**
   * Recursive method that searches the given directory and all of its sub-directories for
   * an exact match to the provided fileName.
   * <p>
   * @param currentDirectory the directory in which to look for the given file
   * @param fileName the file that the method is looking for
   * @return a String that is the path of the given file
   * @throws NoSuchElementException when the given directory isn't a directory or doesn't exist, or 
   * when the given file name is null, or when the search returns with no results
   */
  public static String lookupByName(File currentDirectory, String fileName) 
      throws NoSuchElementException{
    // Throws NoSuchElementException if fileName is null, or currentDirectory does not exist
    // or was not a directory
    if (!currentDirectory.exists()) {
      throw new NoSuchElementException("A directory with this name does not exist.");
    }
    if (!currentDirectory.isDirectory()) {
      throw new NoSuchElementException(currentDirectory + " is not a directory");
    }
    if (fileName == null) {
      throw new NoSuchElementException("Please provide a file name to search");
    }
    
    // Recursive method that lists the names of all the files (not directories)
    // in the given directory and its sub-directories.
    String filePath = "";
    filePath = lookupByNameRE(currentDirectory, fileName, filePath);
    
    // search operation returns with no results found
    if (filePath.equals("")) {
      throw new NoSuchElementException("The given name does not exist in the given directory");
    }

    return filePath;
  }
  
  /**
   * Helper method that is meant to do the recursive portion of the lookupByName method
   * <p>
   * @param currentDirectory the directory in which to look for the given file
   * @param fileName the file that the method is looking for
   * @param filePath the String that the file path that is found will be assigned to
   * @return a String that is the path of the given file
   */
  private static String lookupByNameRE(File currentDirectory, String fileName, String filePath) {
    // Recursion
    for(int i=0; i<currentDirectory.listFiles().length; i++) {
      if (currentDirectory.listFiles()[i].isFile()) {
        if (currentDirectory.list()[i].equals(fileName)) {
          filePath = currentDirectory.listFiles()[i].toString();
          return filePath;
        }
      }
      else {
        filePath = lookupByNameRE(currentDirectory.listFiles()[i], fileName, filePath);
      }
    }

    return filePath;
  }

  /**
   * Recursive method that searches the given folder and its sub-directories for ALL files 
   * that contain the given key in part of their name.
   * <p>
   * @param currentDirectory the directory in which to look for the given file
   * @param key the specific key the method is looking for
   * @return an ArrayList of Strings of all the names of the files that have the wanted key
   */
  public static ArrayList<String> lookupByKey(File currentDirectory, String key) {
    ArrayList<String> all = new ArrayList<String>();
    
    // returns empty Arraylist if the key is null, or the current directory doesn't exist or is 
    // not a directory
    if (!currentDirectory.exists()) {
      return all;
    }
    if (!currentDirectory.isDirectory()) {
      return all;
    }
    if (key == null) {
      return all;
    }
    
    // Recursive method that lists the names of all the files (not directories)
    // in the given directory and its sub-directories.
    for(int i=0; i<currentDirectory.listFiles().length; i++) {
      if (currentDirectory.listFiles()[i].isFile()) {
        String name = currentDirectory.listFiles()[i].getName();
        if (name.substring(name.length()-key.length()).equals(key)) {
          all.add(currentDirectory.listFiles()[i].getName());
        }
      }
      else {
        all.addAll(lookupByKey(currentDirectory.listFiles()[i], key));
      }
    }
    
    return all;
  }

  /**
   * Recursive method that searches the given folder and its sub-directories for ALL files whose 
   * size is within the given max and min values, inclusive.
   * <p>
   * @param currentDirectory the directory in which to look for the given file
   * @param sizeMin long that is the minimum number of bytes the file size should be
   * @param sizeMax long that is the maximum number of bytes the file size should be
   * @return an ArrayList of Strings of all the names of the files that are between the min and max
   * byte size
   */
  public static ArrayList<String> lookupBySize(File currentDirectory, long sizeMin, long sizeMax) {
    ArrayList<String> all = new ArrayList<String>();
    
    // returns empty Arraylist if the current directory doesn't exist or is not a directory
    if (!currentDirectory.exists()) {
      return all;
    }
    if (!currentDirectory.isDirectory()) {
      return all;
    }
    
    // Recursive method that lists the names of all the files (not directories)
    // in the given directory and its sub-directories.
    for(int i=0; i<currentDirectory.listFiles().length; i++) {
      if (currentDirectory.listFiles()[i].isFile()) {
        if (currentDirectory.listFiles()[i].length()<=sizeMax && 
            currentDirectory.listFiles()[i].length()>=sizeMin) {
          all.add(currentDirectory.listFiles()[i].getName());
        }
      }
      else {
        all.addAll(lookupBySize(currentDirectory.listFiles()[i], sizeMin, sizeMax));
      }
    }
    
    return all;
  }

}
