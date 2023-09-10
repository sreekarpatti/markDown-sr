package cs3500.pa01;

import java.io.IOException;

/**
 * Represents a view for a study session.
 */
public class StudySessionView {

  private Appendable output;

  /**
   * Constructs a study session view.
   *
   * @param output the output
   */
  public StudySessionView(Appendable output) {
    this.output = output;
  }

  /**
   * Appends the given text to the output.
   *
   * @param text the text to append
   */
  public void appendOutput(String text) {
    try {
      output.append(text);
    } catch (IOException e) {
      // Handle the exception appropriately
      e.printStackTrace();
    }
  }

  /**
   * Returns the output.
   *
   * @return String the output
   */
  public String getOutput() {
    return output.toString();
  }

  /**
   * Resets the output.
   */
  public void resetOutput() {
    if (output instanceof StringBuilder) {
      ((StringBuilder) output).setLength(0);
    } else if (output instanceof StringBuffer) {
      ((StringBuffer) output).setLength(0);
    }
  }

  /**
   * Starts the study session.
   */
  public void start() {
    try {
      resetOutput();
      output.append("Welcome to the Study Session!").append(System.lineSeparator());
      output.append("Please provide the file path to the .sr file").append(System.lineSeparator());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Asks the user for the number of questions to study.
   */
  public void questionNumber() {
    try {
      resetOutput();
      output.append("How many questions would you like to study?").append(System.lineSeparator());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Shows the user the options for the study session.
   */
  public void showButtons() {
    try {
      resetOutput();
      output.append("Please select one of the following options:").append(System.lineSeparator());
      output.append("1) Easy").append(System.lineSeparator());
      output.append("2) Hard").append(System.lineSeparator());
      output.append("3) Show Answer").append(System.lineSeparator());
      output.append("4) Quit").append(System.lineSeparator());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Shows the user the current question.
   *
   * @param question the current question
   */
  public void printQuestion(String question) {
    try {
      resetOutput();
      output.append(question).append(System.lineSeparator());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Shows the user the current answer.
   *
   * @param answer the current answer
   */
  public void printAnswer(String answer) {
    try {
      resetOutput();
      output.append(answer).append(System.lineSeparator());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Shows the user the end screen.
   *
   * @param questionsAnswered the number of questions answered
   * @param easyToHard        the number of questions that were easy
   * @param hardToEasy        the number of questions that were hard
   * @param totalQuestions    the total number of questions
   */
  public void end(String questionsAnswered, String easyToHard, String hardToEasy,
                  String totalQuestions) {
    try {
      resetOutput();
      output.append("Thank you for using the Study Session!").append(System.lineSeparator());
      output.append("You answered ").append(questionsAnswered).append(" out of ")
          .append(totalQuestions).append(" questions.").append(System.lineSeparator());
      output.append("You thought ").append(easyToHard).append(" questions were easy and ")
          .append(hardToEasy).append(" questions were hard.").append(System.lineSeparator());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Shows the user the difficulty options after showing the answer.
   */
  public void chooseDifficulty() {
    try {
      resetOutput();
      output.append("Please choose a difficulty for the current question:")
          .append(System.lineSeparator());
      output.append("1) Easy").append(System.lineSeparator());
      output.append("2) Hard").append(System.lineSeparator());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}