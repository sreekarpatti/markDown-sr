package cs3500.pa01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the final study guide file with all the correct contents
 */
public class OutputFile {
  private File create;

  /**
   * Instantiates an Output File
   *
   * @param create create represents the new file to be made
   */
  OutputFile(File create) {
    this.create = create;
  }

  /**
   * creates a new file, if needed, based on the given path name
   *
   * @param output the output is the path in which the new file will be created
   */
  public void createFile(String output) {
    try {
      create = new File(output);
      if (create.createNewFile()) {
        System.out.println("File created: " + create.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  /**
   * writes to the file all the important information needed for the study guide
   *
   * @param m m is the file visitor
   */
  public void writeFile(String m) {
    try {
      FileWriter myWriter = new FileWriter(create);
      myWriter.write(m);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
