package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a file reader
 */
public class FileReader {
  private Path path = null;
  private Scanner scanner = null;
  private ArrayList<StudyGuide> los = new ArrayList<StudyGuide>();
  private ArrayList<String> loq = new ArrayList<String>();

  /**
   * Instantiates a file reader
   *
   * @param path path of the file provided
   */
  FileReader(Path path) {
    this.path = path;
    try {
      scanner = new Scanner(this.path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Scans through a file and adds the headings and their corresponding important bullet points to
   * a custom data type called StudyGuide.
   * Then we add each StudyGuide to a list of StudyGuide to compile the contents of
   * all the heading and important information
   * from one file.
   *
   * @return a list of study guide sections from a single file
   */
  public ArrayList<StudyGuide> compileGuide() {
    ArrayList<String> bulletPoints = new ArrayList<>();
    StringBuilder multilineBulletPoint = new StringBuilder();
    String header = "";

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();

      if (line.startsWith("#")) {
        getHeader(header, bulletPoints);
        header = line;
        bulletPoints = new ArrayList<>(); // Create a new ArrayList for each header
      } else {
        getBulletPoint(line, bulletPoints, multilineBulletPoint);
      }
    }

    getHeader(header, bulletPoints);

    return los;
  }

  private void getHeader(String header, ArrayList<String> bulletPoints) {
    if (!header.isEmpty() && !bulletPoints.isEmpty()) {
      los.add(new StudyGuide(header, bulletPoints));
    }
  }

  private void getBulletPoint(String line, ArrayList<String> bulletPoints,
                              StringBuilder multilineBulletPoint) {
    Pattern pattern = Pattern.compile("\\[\\[(.*?)\\]\\]");
    Matcher matcher = pattern.matcher(line);

    while (matcher.find()) {
      String bulletPoint = matcher.group(1);
      if (bulletPoint.contains(":::")) {
        loq.add(bulletPoint);
      } else {
        bulletPoints.add(bulletPoint);
      }
    }

    if (line.contains("[[") && !line.contains("]]")) {
      getMultilineBulletPoint(line, multilineBulletPoint, bulletPoints);
    }
  }

  private void getMultilineBulletPoint(String line, StringBuilder multilineBulletPoint,
                                       ArrayList<String> bulletPoints) {
    multilineBulletPoint.append(line.substring(line.indexOf("[[") + 2));

    while (scanner.hasNextLine()) {
      line = scanner.nextLine().trim();
      if (line.contains("]]")) {
        multilineBulletPoint.append(" ").append(line.substring(0, line.indexOf("]]")));
        String multilineBulletPointText = multilineBulletPoint.toString();
        if (multilineBulletPointText.contains(":::")) {
          loq.add(multilineBulletPointText);
        } else {
          bulletPoints.add(multilineBulletPointText);
        }
        multilineBulletPoint.setLength(0); // Reset the StringBuilder
        break;
      } else {
        multilineBulletPoint.append(" ").append(line);
      }
    }
  }

  /**
   * gets the compiled list of questions from all the files
   *
   * @return a list of questions with all files included
   */
  public ArrayList<String> getListOfQuestions() {

    return loq;
  }
}
