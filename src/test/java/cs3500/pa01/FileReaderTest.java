package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a tester class for file reader
 */
class FileReaderTest {
  private FileReader fr1;
  private FileReader fr2;
  private ArrayList<String> arrayInfo1;
  private ArrayList<String> arrayInfo2;
  private ArrayList<String> arrayInfo3;
  private ArrayList<String> vectorInfo1;
  private ArrayList<String> vectorInfo2;
  private ArrayList<String> vectorInfo3;
  private StudyGuide a1;
  private StudyGuide a2;
  private StudyGuide a3;
  private StudyGuide v1;
  private StudyGuide v2;
  private StudyGuide v3;
  private ArrayList<StudyGuide> losA;
  private ArrayList<StudyGuide> losV;

  /**
   * Initializes all required variables for testing the file reader class
   */
  @BeforeEach
  public void setUp() {
    Path p1 = Path.of("sampleData/arrays.md");
    Path p2 = Path.of("sampleData/next/vectors.md");

    fr1 = new FileReader(p1);
    fr2 = new FileReader(p2);

    arrayInfo1 = new ArrayList<>();
    arrayInfo2 = new ArrayList<>();
    arrayInfo3 = new ArrayList<>();

    vectorInfo1 = new ArrayList<>();
    vectorInfo2 = new ArrayList<>();
    vectorInfo3 = new ArrayList<>();

    arrayInfo1.add("An **array** is a collection of variables of the same type");

    arrayInfo2.add("General Form: type[] arrayName;");
    arrayInfo2.add("only creates a reference");
    arrayInfo2.add("no array has actually been created yet");

    arrayInfo3.add("General form:  arrayName = new type[numberOfElements];");
    arrayInfo3.add("numberOfElements must be a positive Integer.");
    arrayInfo3.add("Gotcha: Array size is not modifiable once instantiated.");

    vectorInfo1.add("Vectors act like resizable arrays");

    vectorInfo2.add("General Form: Vector<type> v = new Vector();");
    vectorInfo2.add("type needs to be a valid reference type");

    vectorInfo3.add("v.add(object of type);");


    a1 = new StudyGuide("# Java Arrays", arrayInfo1);
    a2 = new StudyGuide("## Declaring an Array", arrayInfo2);
    a3 = new StudyGuide("## Creating an Array (Instantiation)", arrayInfo3);

    v1 = new StudyGuide("# Vectors", vectorInfo1);
    v2 = new StudyGuide("## Declaring a vector", vectorInfo2);
    v3 = new StudyGuide("## Adding an element to a vector", vectorInfo3);

    losA = new ArrayList<>(Arrays.asList(a1, a2, a3));
    losV = new ArrayList<>(Arrays.asList(v1, v2, v3));
  }

  /**
   * tests the method compileGuide()
   */
  @Test
  public void testCompileGuide() {
    ArrayList<StudyGuide> resultA = fr1.compileGuide();
    ArrayList<StudyGuide> resultV = fr2.compileGuide();

    assertEquals(losA.size(), resultA.size());
    assertEquals(losV.size(), resultV.size());
  }
}