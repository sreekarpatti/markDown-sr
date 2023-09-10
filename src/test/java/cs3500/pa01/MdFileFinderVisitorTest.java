package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the markdown file visitor class
 */
class MdFileFinderVisitorTest {
  private Path path;
  private Path array;
  private Path vector;
  private MdFileFinderVisitor md;
  private StringBuilder array1;
  private StringBuilder array2;
  private StringBuilder array3;
  private StringBuilder vector1;
  private StringBuilder vector2;
  private StringBuilder vector3;
  private BasicFileAttributes attr;
  private String expected;

  /**
   * Initializes all required variables to test the file visitor class
   */
  @BeforeEach
  public void setUp() {
    path = Path.of("sampleData/");
    array = Path.of("sampleData/arrays.md");
    vector = Path.of("sampleData/next/vectors.md");
    md = new MdFileFinderVisitor();
    try {
      Files.walkFileTree(path, md);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    md.getList("filename");
    array1 = new StringBuilder();
    array2 = new StringBuilder();
    array3 = new StringBuilder();
    vector1 = new StringBuilder();
    vector2 = new StringBuilder();
    vector3 = new StringBuilder();

    try {
      attr = Files.readAttributes(path, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


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
   * tests the method getList()
   */
  @Test
  public void testGetList() {
    ArrayList<Path> expected = new ArrayList<Path>();
    expected.add(array);
    expected.add(vector);
    ArrayList<Path> actual = md.getList("filename");
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  /**
   * tests the method compileFiles()
   */
  @Test
  public void testCompileFiles() {
    String actual = md.compileFiles();
    assertEquals(expected, actual);
  }

  /**
   * tests the method preVisitDirectory()
   */
  @Test
  public void testPreVisitDirectory() {
    try {
      assertEquals(FileVisitResult.CONTINUE, md.preVisitDirectory(path, attr));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * tests the method visitFile()
   */
  @Test
  public void testVisitFile() {
    try {
      assertEquals(FileVisitResult.CONTINUE, md.visitFile(path, attr));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * tests the method visitFileFailed()
   */
  @Test
  public void testVisitFileFailed() {
    try {
      assertEquals(FileVisitResult.CONTINUE, md.visitFileFailed(path, new IOException("Failed")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * tests the method postVisitDirectory
   */
  @Test
  public void testPostVisitDirectory() {
    try {
      assertEquals(FileVisitResult.CONTINUE, md.visitFileFailed(path, new IOException("Failed")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}