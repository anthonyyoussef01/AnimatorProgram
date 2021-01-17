import static org.junit.Assert.assertEquals;

import java.io.IOException;
import cs3500.animator.model.AnimatorModel;
import org.junit.Test;
import cs3500.animator.view.AnimatorTextualView;
import cs3500.animator.view.AnimatorView;

/**
 * A class with tests for an Animator's textual representation.
 */
public class AnimatorTextualViewTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullModelDefaultConstructor() {
    AnimatorView av = new AnimatorTextualView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidCustomConstructor() {
    AnimatorView av = new AnimatorTextualView(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullModelCustomConstructor() {
    AnimatorView av = new AnimatorTextualView(null, new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullAppendableCustomConstructor() {
    AnimatorModel am = new AnimatorModel();
    AnimatorView av = new AnimatorTextualView(am, null);
  }

  @Test
  public void renderTest1() {
    AnimatorModel am1 = new AnimatorModel(400,
        200, 0, 0);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am1.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    StringBuilder output1 = new StringBuilder();
    AnimatorTextualView atv1 = new AnimatorTextualView(am1, output1);
    atv1.setSpeed(5);
    try {
      atv1.render();
    } catch (IOException e) {
      System.out.println("error");
    }
    assertEquals("canvas 0 0 400 200\n"
        + "shape r rectangle\n"
        + "motion r 0.2 25 25 100 100 100 100 100 0.4 25 30 100 100 100 100 100\n"
        + "shape e ellipse\n"
        + "motion e 0.2 50 50 33 42 75 215 115 0.4 50 50 33 45 75 215 115\n", output1.toString());
    AnimatorModel am2 = new AnimatorModel(300, 300,
        0, 0);
    am2.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am2.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am2.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am2.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am2.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am2.addFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    StringBuilder output2 = new StringBuilder();
    AnimatorTextualView atv2 = new AnimatorTextualView(am2, output2);
    atv2.setSpeed(5);
    try {
      atv2.render();
    } catch (IOException e) {
      System.out.println("error");
    }
    assertEquals("canvas 0 0 300 300\n"
        + "shape r rectangle\n"
        + "motion r 0.2 25 25 100 100 100 100 100 0.4 25 30 100 100 100 100 100\n"
        + "motion r 0.4 25 30 100 100 100 100 100 0.6 25 35 100 100 100 100 100\n"
        + "shape e ellipse\n"
        + "motion e 0.2 50 50 33 42 75 215 115 0.4 50 50 33 45 75 215 115\n"
        + "motion e 0.4 50 50 33 45 75 215 115 0.6 50 50 33 50 75 215 115\n", output2.toString());
  }

}
