package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Question class.
 */
class QuestionTest {

  Question q1;
  Question q2;
  Question q3;

  /**
   * Sets up the questions for testing.
   */
  @BeforeEach
  public void setUp() {
    q1 = new Question(1, "What is the capital of Massachusetts?", "Boston",
        Question.Difficulty.EASY);
    q2 =
        new Question(2, "What is the capital of New York?", "Albany", Question.Difficulty.HARD);
    q3 = new Question(3, "What is the capital of California?", "Sacramento",
        Question.Difficulty.EASY);
  }

  /**
   * Tests that the question is converted to a String correctly.
   */
  @Test
  public void testQuestionToString() {
    assertEquals("[[EASY]] What is the capital of Massachusetts? ::: Boston\n",
        q1.questionToString());
    assertEquals("[[HARD]] What is the capital of New York? ::: Albany\n", q2.questionToString());
    assertEquals("[[EASY]] What is the capital of California? ::: Sacramento\n",
        q3.questionToString());
  }

  /**
   * Tests that we extract the question.
   */
  @Test
  public void testGetQuestion() {
    assertEquals("What is the capital of Massachusetts?", q1.getQuestion());
    assertEquals("What is the capital of New York?", q2.getQuestion());
    assertEquals("What is the capital of California?", q3.getQuestion());
  }

  /**
   * Tests that we extract the answer.
   */
  @Test
  public void testGetAnswer() {
    assertEquals("Boston", q1.getAnswer());
    assertEquals("Albany", q2.getAnswer());
    assertEquals("Sacramento", q3.getAnswer());
  }

  /**
   * Tests that we extract the difficulty.
   */
  @Test
  public void testGetDifficulty() {
    assertEquals(Question.Difficulty.EASY, q1.getDifficulty());
    assertEquals(Question.Difficulty.HARD, q2.getDifficulty());
    assertEquals(Question.Difficulty.EASY, q3.getDifficulty());
  }

  /**
   * Tests that we extract the number.
   */
  @Test
  public void testGetNum() {
    assertEquals(1, q1.getNum());
    assertEquals(2, q2.getNum());
    assertEquals(3, q3.getNum());
  }

  /**
   * Tests that we can change the difficulty.
   */
  @Test
  public void testChangeDifficulty() {
    q1.changeDifficulty(Question.Difficulty.HARD);
    assertEquals(Question.Difficulty.HARD, q1.getDifficulty());
    q2.changeDifficulty(Question.Difficulty.EASY);
    assertEquals(Question.Difficulty.EASY, q2.getDifficulty());
    q3.changeDifficulty(Question.Difficulty.HARD);
    assertEquals(Question.Difficulty.HARD, q3.getDifficulty());
  }
}