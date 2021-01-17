import static org.junit.Assert.assertEquals;

import cs3500.animator.model.Frame;
import org.junit.Test;

/**
 * A class with tests for Frames.
 */
public class FrameTest {

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructorTestFrame() {
    Frame f = new Frame("", "", 0, 0, 0,
        0, -1, -1, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidShapeNameTestFrame() {
    Frame f = new Frame("", "rectangle", 0, 0, 0,
        0, -1, -1, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidShapeTypeTestFrame() {
    Frame f = new Frame("r", "", 0, 0, 0,
        0, -1, -1, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullShapeNameTestFrame() {
    Frame f = new Frame(null, "rectangle", 0, 0, 0,
        0, -1, -1, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullShapeTypeTestFrame() {
    Frame f = new Frame("r", null, 0, 0, 0,
        0, -1, -1, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidDimensionsTestFrame() {
    Frame f = new Frame("r", "rectangle", 25, 25, 0,
        0, 100, 100, 100, 23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void NegativeRGBTestFrame() {
    Frame f = new Frame("r", "rectangle", 25, 25, 100,
        100, -1, -1, -1, 23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidRGBTestFrame() {
    Frame f = new Frame("r", "rectangle", 25, 25, 100,
        100, 256, 256, 256, 23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidTickTestFrame() {
    Frame f = new Frame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, -1);
  }

  @Test
  public void equalsFalseFrame() {
    Frame f1 = new Frame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    Frame f2 = new Frame("r", "rectangle", 50, 50, 100,
        100, 100, 100, 100, 1);
    assertEquals(false, f1.equals(f2));
    assertEquals(false, f2.equals(f1));
    assertEquals(false, f1.equals(5));
  }

  @Test
  public void equalsTrueFrame() {
    Frame f1 = new Frame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    Frame f2 = new Frame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    assertEquals(true, f1.equals(f2));
    assertEquals(true, f2.equals(f1));
    assertEquals(true, f1.equals(f1));
  }

  @Test
  public void toStringFrame() {
    Frame f1 = new Frame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    Frame f2 = new Frame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 3);
    assertEquals("25 25 100 100 100 100 100", f1.toString());
    assertEquals("50 50 33 42 75 215 115", f2.toString());
  }

  @Test
  public void gettersFrame() {
    Frame f1 = new Frame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    assertEquals("r", f1.getShapeName());
    assertEquals("rectangle", f1.getShapeType());
    assertEquals(25, f1.getX());
    assertEquals(25, f1.getY());
    assertEquals(100, f1.getWidth());
    assertEquals(100, f1.getHeight());
    assertEquals(100, f1.getR());
    assertEquals(100, f1.getG());
    assertEquals(100, f1.getB());
    assertEquals(1, f1.getTick(), 0);
    Frame f2 = new Frame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 3);
    assertEquals("e", f2.getShapeName());
    assertEquals("ellipse", f2.getShapeType());
    assertEquals(50, f2.getX());
    assertEquals(50, f2.getY());
    assertEquals(33, f2.getWidth());
    assertEquals(42, f2.getHeight());
    assertEquals(75, f2.getR());
    assertEquals(215, f2.getG());
    assertEquals(115, f2.getB());
    assertEquals(3, f2.getTick(), 0);
  }
}

