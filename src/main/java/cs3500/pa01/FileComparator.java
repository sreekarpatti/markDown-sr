package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;

/**
 * Represents a comparator class to compare Paths
 */
public class FileComparator implements Comparator<Path> {
  private String sortingMethod;

  /**
   * Instantiates a file comparator
   *
   * @param sortingMethod the given sortingMethod by the user
   */
  FileComparator(String sortingMethod) {
    this.sortingMethod = sortingMethod;
  }

  /**
   * compares two Paths based on the sorting method provided in the constructor
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return an int based on the compareTo function.
   * @throws IllegalArgumentException if provided an incorrect sorting method
   */
  @Override
  public int compare(Path o1, Path o2) {
    BasicFileAttributes attr1 = null;
    try {
      attr1 = Files.readAttributes(o1, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException("Failed to read attributes for file " + o1.toString(), e);
    }
    BasicFileAttributes attr2 = null;
    try {
      attr2 = Files.readAttributes(o2, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException("Failed to read attributes for file " + o2.toString(), e);
    }
    if (sortingMethod.equals("filename")) {
      String fileName1 = o1.getFileName().toString();
      String fileName2 = o2.getFileName().toString();
      return fileName1.compareTo(fileName2);
    } else if (sortingMethod.equals("created")) {
      FileTime creationTime1 = attr1.creationTime();
      FileTime creationTime2 = attr2.creationTime();
      return creationTime1.compareTo(creationTime2);
    } else if (sortingMethod.equals("modified")) {
      FileTime modifiedTime1 = attr1.lastModifiedTime();
      FileTime modifiedTime2 = attr2.lastModifiedTime();
      return modifiedTime1.compareTo(modifiedTime2);
    }
    throw new IllegalArgumentException("Incorrect sorting method");
  }
}