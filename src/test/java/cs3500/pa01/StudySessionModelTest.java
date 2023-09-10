package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the StudySessionModel class.
 */
class StudySessionModelTest {
  private StudySessionModel model;

  /**
   * Sets up the model for testing.
   */
  @BeforeEach
  public void setUp() {
    // Provide the path to the sample data file
    Path path = Paths.get("sampleData/output.sr");
    model = new StudySessionModel(path);
    model.readQuestions();
    model.shuffleQuestions();
  }

  /**
   * Tests that the model reads the questions from the file.
   */
  @Test
  public void testReadQuestions() {
    ArrayList<Question> loq = model.getLoq();
    assertEquals(29, loq.size());
  }

  /**
   * Tests that the model shuffles the questions and gives it to the user.
   */
  @Test
  public void testGetUserLoq() {
    ArrayList<Question> loq = model.getUserLoq(10);
    assertEquals(10, loq.size());
  }

  /**
   * Tests that the model gets the current question.
   */
  @Test
  public void testGetCurrQuestion() {
    model.getUserLoq(10);
    Question question = model.getCurrQuestion(0);
    assertNotNull(question);
  }

  /**
   * Tests that the model gets the answer to the current answer.
   */
  @Test
  public void testGetAnswer() {
    model.getUserLoq(10);
    String answer = model.getAnswer(0);
    assertNotNull(answer);
  }

  /**
   * Tests that the model changes the difficulty of teh current question.
   */
  @Test
  public void testChangeDifficulty() {
    model.getUserLoq(10);
    int questionIndex = 0; // Select a random question index
    Question originalQuestion = model.getCurrQuestion(questionIndex);
    int questionNumber = originalQuestion.getNum();
    Question.Difficulty originalDifficulty = originalQuestion.getDifficulty();

    // Change the difficulty of the selected question to EASY
    model.changeDifficulty(Question.Difficulty.EASY, questionIndex);

    // Verify that the difficulty of the selected question has changed
    Question updatedQuestion = model.getCurrQuestion(questionIndex);
    int updatedQuestionNumber = updatedQuestion.getNum();
    Question.Difficulty newDifficulty = model.getLoq().get(updatedQuestionNumber).getDifficulty();

    assertEquals(Question.Difficulty.EASY, newDifficulty);
    assertNotEquals(originalDifficulty, newDifficulty);
  }

  /**
   * Tests that the model gets the shuffled list of questions.
   */
  @Test
  public void testShuffleQuestions() {
    ArrayList<Question> originalQuestions = model.getLoq();
    model.shuffleQuestions();
    ArrayList<Question> shuffledQuestions = model.getShuffled();
    assertNotEquals(originalQuestions, shuffledQuestions);
  }

  /**
   * Tests that the model updates the loq.
   */
  @Test
  public void testUpdateLoq() {
    String updatedLoq = model.updateLoq();
    assertNotNull(updatedLoq);
    assertFalse(updatedLoq.isEmpty());
  }
}