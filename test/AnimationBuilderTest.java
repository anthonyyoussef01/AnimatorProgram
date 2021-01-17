import static org.junit.Assert.assertEquals;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.List;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Frame;
import org.junit.Test;

/**
 * A class with tests for an AnimatorModel.
 */
public class AnimationBuilderTest {

  @Test
  public void addFrameAnimatorModel() {
    Frame f1 = new Frame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    Frame f2 = new Frame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    Frame f3 = new Frame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    Frame f4 = new Frame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    Frame f5 = new Frame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    Frame f6 = new Frame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    List<Frame> listOfFrames = new ArrayList<Frame>();
    listOfFrames.add(f1);
    listOfFrames.add(f2);
    listOfFrames.add(f3);
    listOfFrames.add(f4);
    listOfFrames.add(f5);
    listOfFrames.add(f6);
    AnimatorModel am = new AnimatorModel();
    AnimationBuilder<AnimatorModel> builder = am.builder();
    builder.setBounds(0, 0, 300, 300);
    builder.declareShape("r", "rectangle");
    builder.declareShape("e", "ellipse");
    builder.addMotion("r", 1, 25, 25, 100,
        100, 100, 100, 100, 2, 25, 30, 100,
        100, 100, 100, 100);
    builder.addMotion("r", 2, 25, 30, 100,
        100, 100, 100, 100, 3, 25, 35, 100,
        100, 100, 100, 100);
    builder.addMotion("e", 1, 50, 50, 33,
        42, 75, 215, 115, 2, 50, 50, 33,
        45, 75, 215, 115);
    builder.addMotion("e", 2, 50, 50, 33,
        45, 75, 215, 115, 3, 50, 50, 33,
        50, 75, 215, 115);
    am = builder.build();
    assertEquals(listOfFrames, am.getFrames());
  }

  @Test(expected = IllegalArgumentException.class)
  public void plusXEqualsY() {
    Frame f = new Frame("p", "plus", 50, 50, 60,
        50, 75, 215, 115, 3);
  }

}