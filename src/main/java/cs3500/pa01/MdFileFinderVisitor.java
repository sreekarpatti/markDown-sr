package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the file visitor
 */
public class MdFileFinderVisitor implements FileVisitor<Path> {
  private ArrayList<Path> list = new ArrayList<>();
  private ArrayList<String> questions = new ArrayList<>();
  private boolean isDone = false;

  /**
   * gets all the study guide sections for all the files in the list of paths
   * and converts them all to a properly formatted string
   *
   * @return a list of study guides with all files included
   */
  public String compileFiles() {
    ArrayList<StudyGuide> s = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      FileReader fr = new FileReader(list.get(i));
      s.addAll(fr.compileGuide());
      questions.addAll(fr.getListOfQuestions());
    }
    StringBuilder total = new StringBuilder();
    for (StudyGuide sg : s) {
      total.append(sg.studyGuideToString());
    }
    return total.toString();
  }

  /**
   * gets all the questions for all the files in the list of paths
   * and converts them all to a properly formatted string
   *
   * @return a list of questions with all files included
   */
  public String compileQuestions() {
    StringBuilder first = new StringBuilder();
    for (String s : questions) {
      first.append("[[HARD]] ").append(s).append("\n");
    }
    return first.toString();
  }

  /**
   * sorts the list of paths collected from the vistFile() method by the given sorting method.
   *
   * @param sort the sorting method chosen to sort the list of paths
   * @return a sorted list of paths
   * @throws IllegalStateException if we try to sort the list of paths before visiting all the files
   */
  public ArrayList<Path> getList(String sort) {
    if (isDone == true) {
      Collections.sort(list, new FileComparator(sort));
      return list;
    } else {
      throw new IllegalStateException("Did not finish visiting all the files");
    }
  }

  /**
   * implemented method from FileVisitor class
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return an Enum
   * @throws IOException if path and attributes aren't retrievable
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws
      IOException {
    isDone = true;
    return CONTINUE;
  }

  /**
   * adds a markdown file when found will walking down the file tree
   *
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return an Enum
   * @throws IOException if path and attributes aren't retrievable
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    String fileName = file.getFileName().toString();
    if (fileName.endsWith(".md")) {
      list.add(file);
    }
    isDone = true;
    return CONTINUE;
  }

  /**
   * implemented method from FileVisitor class
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return an Enum
   * @throws IOException if path isn't retrievable
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    System.err.println(exc);
    isDone = true;
    return CONTINUE;
  }

  /**
   * implemented method from FileVisitor class
   *
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *            an error; otherwise the I/O exception that caused the iteration
   *            of the directory to complete prematurely
   * @return an Enum
   * @throws IOException if path isn't retrievable
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    isDone = true;
    return CONTINUE;
  }
}
