package cs3500.pa01;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Represents a study session controller
 */
public class StudySessionController {
  StudySessionModel model;
  StudySessionView view;
  OutputFile updatedData;
  Scanner input;

  /**
   * Constructs a study session controller
   *
   * @param read   - readable object
   * @param append - appendable object
   */
  StudySessionController(Readable read, Appendable append) {
    this.view = new StudySessionView(append);
    input = new Scanner(read);
  }

  private int questionCounter;
  private int easyCounter;
  private int hardCounter;

  /**
   * Runs the study session
   */
  public void run() {
    view.start();
    System.out.println(view.getOutput());

    String filePath = input.nextLine();
    Path path = Paths.get(filePath);
    updatedData = new OutputFile(new File(path.toString()));
    model = new StudySessionModel(path);

    int numQuestions = getNumberOfQuestions();
    askQuestions(numQuestions);
    updatedData.writeFile(model.updateLoq());
    endSession(numQuestions);
  }

  private int getNumberOfQuestions() {
    view.questionNumber();
    System.out.println(view.getOutput());
    int numQuestions = input.nextInt();
    input.nextLine(); // Consume the newline character after reading the integer input
    return numQuestions;
  }

  private void askQuestions(int numQuestions) {
    model.readQuestions();
    model.shuffleQuestions();
    model.getUserLoq(numQuestions);

    questionCounter = 0;
    easyCounter = 0;
    hardCounter = 0;
    boolean showNextQuestion = true;
    boolean chooseDifficulty = false;
    boolean quitGame = false;

    while (questionCounter < numQuestions && !quitGame) {
      if (showNextQuestion) {
        displayQuestion();
      }

      if (chooseDifficulty) {
        chooseDifficulty();
        chooseDifficulty = false;
      } else {
        showButtons();
      }

      String userInput = input.nextLine().trim();

      switch (userInput) {
        case "1":
          changeQuestionDifficulty(Question.Difficulty.EASY);
          easyCounter++;
          questionCounter++;
          showNextQuestion = true;
          break;
        case "2":
          changeQuestionDifficulty(Question.Difficulty.HARD);
          hardCounter++;
          questionCounter++;
          showNextQuestion = true;
          break;
        case "3":
          if (easyCounter == questionCounter || hardCounter == questionCounter) {
            displayAnswer();
            chooseDifficulty = true;
            continue;
          }
          break;
        case "4":
          quitGame = true;
          break;
        default:
          // Handle invalid input
          break;
      }

      if (showNextQuestion && !quitGame) {
        if (questionCounter < numQuestions) {
          displayQuestion();
        }
        showNextQuestion = false;
      }
    }

    if (quitGame) {
      endSession(numQuestions);
    }
  }

  private void displayQuestion() {
    view.printQuestion(model.getCurrQuestion(questionCounter).getQuestion());
    System.out.println(view.getOutput());
  }

  private void chooseDifficulty() {
    view.chooseDifficulty();
    System.out.println(view.getOutput());
  }

  private void showButtons() {
    view.showButtons();
    System.out.println(view.getOutput());
  }

  private void changeQuestionDifficulty(Question.Difficulty difficulty) {
    model.changeDifficulty(difficulty, questionCounter);
  }

  private void displayAnswer() {
    view.printAnswer(model.getAnswer(questionCounter));
    System.out.println(view.getOutput());
  }

  private void endSession(int numQuestions) {
    view.end(Integer.toString(questionCounter), Integer.toString(easyCounter),
        Integer.toString(hardCounter), Integer.toString(numQuestions));
    System.out.println(view.getOutput());
  }
}

