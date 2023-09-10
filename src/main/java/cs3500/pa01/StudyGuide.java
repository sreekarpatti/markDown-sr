package cs3500.pa01;

import java.util.ArrayList;

/**
 * Represents a topic, and it's corresponding information in a study guide
 */
public class StudyGuide {
  private String header;
  private ArrayList<String> information;

  /**
   * Instantiates a section of the study guide
   *
   * @param header      the name of the topic in the given files
   * @param information the bullet points under that specific topic in the given file
   */
  public StudyGuide(String header, ArrayList<String> information) {
    this.header = header;
    this.information = information;
  }

  /**
   * Converts the data type StudyGuide to a string
   *
   * @return the final formatted string representing all the data in a StudyGuide as a String
   */
  public String studyGuideToString() {
    StringBuilder start = new StringBuilder();
    start.append(header).append("\n");

    for (int i = 0; i < information.size(); i++) {
      String s = information.get(i);
      if (!s.isEmpty()) {
        start.append("- ").append(s).append("\n");
      }
      if (i == information.size() - 1 && !s.isEmpty()) {
        start.append("\n");
      }
    }

    return start.toString();
  }
}
