package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * tests the class Driver
 */
class DriverTest {

  /**
   * test the main method
   */
  @Test
  public void fakeTest() {
    try {
      Driver.main(new String[] {"sampleData/", "filename", "sampleData/output.txt"});
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}