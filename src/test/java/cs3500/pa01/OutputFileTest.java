package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

/**
 * test the output file class
 */
class OutputFileTest {
  private Path path;
  private Path output;
  private Path array;
  private Path vector;
  private MdFileFinderVisitor md;

  private StringBuilder array1;
  private StringBuilder array2;
  private StringBuilder array3;
  private StringBuilder vector1;
  private StringBuilder vector2;
  private StringBuilder vector3;
  private String expected;
  private File test;
  private OutputFile end;

  /**
   * Initializes all required variables to test the output file class
   */
  @BeforeEach
  public void setUp() {
    path = Path.of("sampleData/");
    output = Path.of("sampleData/output.md");
    array = Path.of("sampleData/arrays.md");
    vector = Path.of("sampleData/next/vectors.md");
    md = new MdFileFinderVisitor();
    try {
      Files.walkFileTree(path, md);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    md.getList("filename");
    test = new File("sampleData/output.md");
    end = new OutputFile(test);
    array1 = new StringBuilder();
    array2 = new StringBuilder();
    array3 = new StringBuilder();
    vector1 = new StringBuilder();
    vector2 = new StringBuilder();
    vector3 = new StringBuilder();


    array1.append("# Java Arrays"
        + "\n- An **array** is a collection of variables of the same type\n\n");
    array2.append("## Declaring an Array\n- General Form: type[] arrayName;"
        + "\n- only creates a reference\n- no array has actually been created yet\n\n");
    array3.append("## Creating an Array (Instantiation)"
        + "\n- General form:  arrayName = new type[numberOfElements];"
        + "\n- numberOfElements must be a positive Integer."
        + "\n- Gotcha: Array size is not modifiable once instantiated.\n\n");

    vector1.append("# Vectors\n- Vectors act like resizable arrays\n\n");
    vector2.append("## Declaring a vector\n- General Form: Vector<type> v = new Vector();"
        + "\n- type needs to be a valid reference type\n\n");
    vector3.append("## Adding an element to a vector\n- v.add(object of type);\n\n");

    expected = array1.append(
        array2.append(
            array3.append(
                vector1.append(
                    vector2.append(
                        vector3))))).toString();
  }

  /**
   * tests the method createFile()
   */
  @Test
  public void testCreateFile() {
    end.createFile("sampleData/output.md");
    assertTrue(test.exists());
  }

  /**
   * tests the method writeFile()
   */
  @Test
  public void testWriteFile() {
    end.writeFile(md.compileFiles());
    String written = null;
    try {
      written = Files.readString(output);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertEquals(expected, written);

  }
}