package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the class FileComparator.
 */
class FileComparatorTest {
  private FileComparator filename;
  private FileComparator creation;
  private FileComparator modified;
  private FileComparator invalid;
  private Path p1;
  private Path p2;
  private BasicFileAttributes attr1;
  private BasicFileAttributes attr2;
  private LocalDateTime old;
  private LocalDateTime recent;
  private Instant instantOld;
  private Instant instantRecent;

  /**
   * Initializes all required variables to test the class FileComparator.
   */
  @BeforeEach
  public void setUp() {
    filename = new FileComparator("filename");
    creation = new FileComparator("created");
    modified = new FileComparator("modified");
    invalid = new FileComparator("abcd");

    p1 = Path.of("sampleData/arrays.md");
    p2 = Path.of("sampleData/next/vectors.md");

    old = LocalDateTime.of(2023, 4, 1, 12, 30, 0);
    recent = LocalDateTime.of(2023, 5, 2, 4, 59, 59);
    instantOld = old.toInstant(ZoneOffset.UTC);
    instantRecent = recent.toInstant(ZoneOffset.UTC);

    try {
      attr1 = Files.readAttributes(p1, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      attr2 = Files.readAttributes(p2, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Tests the method compare() for comparing filenames.
   */
  @Test
  public void testCompareFileName() {
    assertTrue(filename.compare(p1, p2) < 0);
    assertFalse(filename.compare(p1, p2) > 0);
    assertFalse(filename.compare(p1, p2) == 0);
  }

  /**
   * Tests the method compare() for comparing creation timestamps.
   */
  @Test
  public void testCompareCreation() {
    BasicFileAttributeView attribute1 = Files.getFileAttributeView(p1,
        BasicFileAttributeView.class);
    FileTime time1 = FileTime.fromMillis(1684187318);
    try {
      attribute1.setTimes(time1, time1, time1);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    BasicFileAttributeView attribute2 = Files.getFileAttributeView(p2,
        BasicFileAttributeView.class);
    FileTime time2 = FileTime.fromMillis(0);
    try {
      attribute2.setTimes(time2, time2, time2);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertFalse(creation.compare(p1, p2) < 0);
    assertTrue(creation.compare(p1, p2) > 0);
    assertFalse(creation.compare(p1, p2) == 0);
  }

  /**
   * Tests the method compare() for comparing modified timestamps.
   */
  @Test
  public void testCompareModified() {
    try {
      Files.setLastModifiedTime(p1, FileTime.from(instantOld));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      Files.setLastModifiedTime(p2, FileTime.from(instantRecent));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertTrue(modified.compare(p1, p2) < 0);
    assertFalse(modified.compare(p1, p2) > 0);
    assertFalse(modified.compare(p1, p2) == 0);
  }

  /**
   * Tests the method compare() for an invalid comparator.
   */
  @Test
  public void testInvalidCompare() {
    assertThrows(IllegalArgumentException.class, () -> invalid.compare(p1, p2));
  }
}
