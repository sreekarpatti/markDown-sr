package cs3500.pa01;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the StudySessionController class.
 */
class StudySessionControllerTest {
  private StudySessionController controller;
  private StringBuilder outputBuilder;

  /**
   * Sets up the controller and outputBuilder for testing.
   */
  @BeforeEach
  public void setup() {
    outputBuilder = new StringBuilder();
    controller = new StudySessionController(getMockReadable(), outputBuilder);
  }

  /**
   * Tests that the controller runs without error.
   */
  @Test
  public void testRun() {
    controller.run();

    String output = outputBuilder.toString();
    Assertions.assertTrue(output.contains("Thank you for using the Study Session!"));
  }

  private InputStreamReader getMockReadable() {
    String mockInput = "sampleData/output.sr\n5\n1\n2\n4\n";
    return new InputStreamReader(
        new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8)));
  }
}