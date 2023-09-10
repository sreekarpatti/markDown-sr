package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Represents a study session model
 */
public class StudySessionModel {
  private Path path = null;
  private Scanner scanner = null;
  private ArrayList<Question> loq = new ArrayList<>();
  private ArrayList<Question> shuffled = new ArrayList<>();
  private ArrayList<Question> userQuestions = new ArrayList<>();

  /**
   * Instantiates a study session model
   *
   * @param path path of the file provided
   */
  StudySessionModel(Path path) {
    this.path = path;
    try {
      scanner = new Scanner(this.path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Scans through a file and adds the question and their corresponding difficulty to
   * a custom data type called Question.
   * Then we add each Question to a list of Question to compile the contents of
   * all the questions and their difficulty
   * from one file.
   */
  public void readQuestions() {
    int questionNum = 0;

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      Question question = parseLineToQuestion(line, questionNum);
      loq.add(question);
      questionNum++;
    }
  }

  private Question parseLineToQuestion(String line, int questionNum) {
    line = line.trim();

    int start = line.indexOf("[[");
    int end = line.indexOf("]]");
    String difficultyStr = line.substring(start + 2, end);
    Question.Difficulty difficulty = Question.Difficulty.valueOf(difficultyStr);

    int separatorIndex = line.indexOf(":::");
    String question = line.substring(end + 2, separatorIndex).trim();
    String answer = line.substring(separatorIndex + 3).trim();

    return new Question(questionNum, question, answer, difficulty);
  }

  /**
   * returns the list of questions
   *
   * @return loq the list of questions
   */
  public ArrayList<Question> getLoq() {
    return loq;
  }

  /**
   * returns the list of questions
   *
   * @param num the number of questions to be returned
   * @return loq the list of questions
   */
  public ArrayList<Question> getUserLoq(int num) {
    if (num < 1) {
      throw new IllegalArgumentException("Choose a number greater than 0");
    }

    if (num >= shuffled.size()) {
      userQuestions.addAll(shuffled); // Add all shuffled questions
    } else {
      for (int i = 0; i < num; i++) {
        userQuestions.add(shuffled.get(i)); // Add num questions from shuffled list
      }
    }
    return userQuestions;

  }

  /**
   * returns the current Question
   *
   * @param num the index of the question to be returned
   * @return Question the current question
   */
  public Question getCurrQuestion(int num) {
    return userQuestions.get(num);
  }

  /**
   * returns the current Answer
   *
   * @param num the index of the question's answer to be returned
   * @return String the current answer
   */
  public String getAnswer(int num) {
    return getCurrQuestion(num).getAnswer();
  }

  /**
   * changes the difficulty of a given question at the index of the original list
   *
   * @param num the index of the question to be returned
   * @param difficulty the difficulty to be changed to
   */
  public void changeDifficulty(Question.Difficulty difficulty, int num) {
    loq.get(getCurrQuestion(num).getNum()).changeDifficulty(difficulty);
  }

  /**
   * returns the shuffled list of questions
   *
   * @return shuffled the shuffled list of questions
   */
  public ArrayList<Question> getShuffled() {
    return shuffled;
  }

  /**
   * shuffles the list of questions
   */
  public void shuffleQuestions() {
    ArrayList<Question> easyQuestions = new ArrayList<>();
    ArrayList<Question> hardQuestions = new ArrayList<>();

    for (Question question : loq) {
      if (question.getDifficulty() == Question.Difficulty.EASY) {
        easyQuestions.add(question);
      } else if (question.getDifficulty() == Question.Difficulty.HARD) {
        hardQuestions.add(question);
      }
    }

    Collections.shuffle(easyQuestions);
    Collections.shuffle(hardQuestions);

    // Add all the hard questions at the front of the shuffled list
    shuffled.addAll(hardQuestions);

    // Add the remaining easy questions after the hard questions
    shuffled.addAll(easyQuestions);
  }

  /**
   * rewrites all the questions so that they are updated with their difficulties in the file
   *
   * @return String the updated list of questions
   */
  public String updateLoq() {
    StringBuilder start = new StringBuilder();
    for (Question q : loq) {
      start.append(q.questionToString());
    }
    return start.toString();
  }
}