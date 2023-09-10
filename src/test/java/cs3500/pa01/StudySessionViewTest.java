package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the StudySessionView class.
 */
class StudySessionViewTest {

  private StudySessionView view;
  private StringBuilder output;

  /**
   * Sets up the view and output for testing.
   */
  @BeforeEach
  public void setUp() {
    output = new StringBuilder();
    view = new StudySessionView(output);
  }

  /**
   * Tests that the view appends the output correctly.
   */
  @Test
  public void testAppendOutput() throws IOException {
    view.appendOutput("Hello");
    assertEquals("Hello", output.toString());
  }

  /**
   * Tests that the view gets the output as a String.
   */
  @Test
  public void testGetOutput() {
    String output = view.getOutput();
    assertEquals("", output);
  }

  /**
   * Tests that the view resets the output.
   */
  @Test
  public void testResetOutputStringBuilder() {
    view.appendOutput("Hello");
    view.resetOutput();
    String output = view.getOutput();
    assertEquals("", output);
  }

  /**
   * Tests that the view resets the output.
   */
  @Test
  public void testResetOutputStringBuffer() {
    view = new StudySessionView(new StringBuffer());
    view.appendOutput("Hello");
    view.resetOutput();
    String output = view.getOutput();
    assertEquals("", output);
  }

  /**
   * Tests that the view resets the output.
   */
  @Test
  public void testResetOutputEmptyOutput() {
    view.resetOutput();
    String output = view.getOutput();
    assertEquals("", output);
  }

  /**
   * Tests that the view resets the output.
   */
  @Test
  public void testResetOutputMultipleAppend() {
    view.appendOutput("Hello");
    view.appendOutput(" World");
    view.resetOutput();
    String output = view.getOutput();
    assertEquals("", output);
  }

  /**
   * Tests that the view starts the study session.
   */
  @Test
  public void testStart() {
    view.start();
    String output = view.getOutput();
    assertEquals("Welcome to the Study Session!\nPlease provide the file path to the .sr file\n",
        output);
  }

  /**
   * Tests that the view asks for a certain number of questions.
   */
  @Test
  public void testQuestionNumber() {
    view.questionNumber();
    String output = view.getOutput();
    assertEquals("How many questions would you like to study?\n", output);
  }

  /**
   * Tests that the view shows the user their options.
   */
  @Test
  public void testShowButtons() {
    view.showButtons();
    String output = view.getOutput();
    String expectedOutput = "Please select one of the following options:\n"
        + "1) Easy\n"
        + "2) Hard\n"
        + "3) Show Answer\n"
        + "4) Quit\n";
    assertEquals(expectedOutput, output);
  }

  /**
   * Tests that the view shows the user their question.
   */
  @Test
  public void testPrintQuestion() {
    view.printQuestion("What is the capital of France?");
    String output = view.getOutput();
    assertEquals("What is the capital of France?\n", output);
  }

  /**
   * Tests that the view shows the user their answer.
   */
  @Test
  public void testPrintAnswer() {
    view.printAnswer("Paris");
    String output = view.getOutput();
    assertEquals("Paris\n", output);
  }

  /**
   * Tests that the view shows the user their score.
   */
  @Test
  public void testEnd() {
    view.end("5", "2", "3", "10");
    String output = view.getOutput();
    String expectedOutput = "Thank you for using the Study Session!\n"
        + "You answered 5 out of 10 questions.\n"
        + "You thought 2 questions were easy and 3 questions were hard.\n";
    assertEquals(expectedOutput, output);
  }

  /**
   * Tests that the view shows the user their options after showing an answer.
   */
  @Test
  public void testChooseDifficulty() {
    view.chooseDifficulty();
    String output = view.getOutput();
    String expectedOutput = "Please choose a difficulty for the current question:\n"
        + "1) Easy\n"
        + "2) Hard\n";
    assertEquals(expectedOutput, output);
  }
}