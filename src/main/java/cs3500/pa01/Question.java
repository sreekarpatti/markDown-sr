package cs3500.pa01;

/**
 * Represents a question in a study session.
 */
public class Question {
  int num;
  String question;
  String answer;
  Difficulty difficulty;

  /**
   * Constructs a question.
   *
   * @param num        the number of the question
   * @param question   the question
   * @param answer     the answer to the question
   * @param difficulty the difficulty of the question
   */
  Question(int num, String question, String answer, Difficulty difficulty) {
    this.num = num;
    this.question = question;
    this.answer = answer;
    this.difficulty = difficulty;
  }

  /**
   * Represents the difficulty of a question.
   */
  public enum Difficulty {
    /**
     * Easy difficulty.
     */
    EASY,
    /**
     * Hard difficulty.
     */
    HARD
  }

  /**
   * Returns a string representation of a question.
   *
   * @return String the string representation of a question
   */
  public String questionToString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[[" + difficulty.toString() + "]] ")
        .append(question + " ::: ")
        .append(answer)
        .append("\n");
    return sb.toString();
  }

  /**
   * Returns the question.
   *
   * @return String the question
   */
  public String getQuestion() {
    return question;
  }

  /**
   * Returns the answer to the question.
   *
   * @return String the answer to the question
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Returns the difficulty of the question.
   *
   * @return Difficulty the difficulty of the question
   */
  public Difficulty getDifficulty() {
    return difficulty;
  }

  /**
   * Returns the number of the question.
   *
   * @return int the number of the question
   */
  public int getNum() {
    return num;
  }

  /**
   * Changes the difficulty of the question.
   *
   * @param difficulty the new difficulty of the question
   */
  public void changeDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }
}
