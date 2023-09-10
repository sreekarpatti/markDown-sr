package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   * @throws IOException - if file is not found
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 3) {
      Path p = Path.of(args[0]);
      MdFileFinderVisitor md = new MdFileFinderVisitor();
      Files.walkFileTree(p, md);
      md.getList(args[1]);
      File myObj = new File("");
      OutputFile output = new OutputFile(myObj);
      OutputFile questionsAndAnswers = new OutputFile(myObj);

      output.createFile(args[2]);
      output.writeFile(md.compileFiles());
      questionsAndAnswers.createFile(produceQuestionAndAnswer(args[2]));
      questionsAndAnswers.writeFile(md.compileQuestions());
    } else if (args.length == 0) {
      StudySessionController controller =
          new StudySessionController(new InputStreamReader(System.in), System.out);
      controller.run();
    } else {
      throw new IllegalStateException("Please enter three arguments to create a study guide "
          + "or no arguments to run a study session");
    }
  }

  private static String produceQuestionAndAnswer(String s) {
    StringBuilder sb = new StringBuilder();
    sb.append(s);
    int i = sb.length() - 1;
    while (sb.charAt(i) != '.') {
      sb.deleteCharAt(i);
      i--;
    }
    sb.append("sr");
    return sb.toString();
  }
}