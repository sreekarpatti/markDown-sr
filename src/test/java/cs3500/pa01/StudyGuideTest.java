package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for the class StudyGuide
 */
class StudyGuideTest {
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
  private String array1;
  private String array2;
  private String array3;
  private String vector1;
  private String vector2;
  private String vector3;

  /**
   * Initializes all required variables to test the class StudyGuide
   */
  @BeforeEach
  public void setUp() {
    Path p1 = Path.of("sampleData/arrays.md");
    Path p2 = Path.of("sampleData/next/vectors.md");

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

    array1 = "# Java Arrays\n- An **array** is a collection of variables of the same type\n\n";
    array2 =
        "## Declaring an Array\n- General Form: type[] arrayName;\n- only creates a reference"
            + "\n- no array has actually been created yet\n\n";
    array3 =
        "## Creating an Array (Instantiation)"
            + "\n- General form:  arrayName = new type[numberOfElements];"
            + "\n- numberOfElements must be a positive Integer."
            + "\n- Gotcha: Array size is not modifiable once instantiated.\n\n";

    vector1 = "# Vectors\n- Vectors act like resizable arrays\n\n";
    vector2 =
        "## Declaring a vector\n- General Form: Vector<type> v = new Vector();"
            + "\n- type needs to be a valid reference type\n\n";
    vector3 = "## Adding an element to a vector\n- v.add(object of type);\n\n";
  }

  /**
   * tests the method studyGuideToString()
   */
  @Test
  public void testStudyGuideToString() {
    String actualA1 = a1.studyGuideToString();
    String actualA2 = a2.studyGuideToString();
    String actualA3 = a3.studyGuideToString();

    assertEquals(array1, actualA1);
    assertEquals(array2, actualA2);
    assertEquals(array3, actualA3);

    String actualV1 = v1.studyGuideToString();
    String actualV2 = v2.studyGuideToString();
    String actualV3 = v3.studyGuideToString();

    assertEquals(vector1, actualV1);
    assertEquals(vector2, actualV2);
    assertEquals(vector3, actualV3);

  }

}