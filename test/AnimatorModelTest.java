import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Frame;
import org.junit.Test;

/**
 * A class with tests for an AnimatorModel.
 */
public class AnimatorModelTest {

  @Test(expected = IllegalArgumentException.class)
  public void invalidWidthCustomConstructor() {
    AnimatorModel am = new AnimatorModel(0,
        100, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidHeightCustomConstructor() {
    AnimatorModel am = new AnimatorModel(300,
        0, 0, 0);
  }

  @Test
  public void toStringAnimatorModelNoMotion() {
    List<Frame> listOfFrames = new ArrayList<Frame>();
    AnimatorModel am = new AnimatorModel(300, 300,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 3);
    assertEquals(
        "shape r rectangle\n\nshape e ellipse\n\n", am.toString());
  }

  @Test
  public void toStringAnimatorModel() {
    AnimatorModel am1 = new AnimatorModel(300, 300,
        0, 0);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am1.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    assertEquals(
        "shape r rectangle\nmotion r 1.0 25 25 100 100 100 100 100   2.0 25 30 100 100 "
            + "100 100 "
            + "100\n\nshape e ellipse\nmotion e 1.0 50 50 33 42 75 215 115   2.0 50 50 33 45 75 "
            + "215 115\n\n", am1.toString());
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
    assertEquals(
        "shape r rectangle\nmotion r 1.0 25 25 100 100 100 100 100   2.0 25 30 100 100 "
            + "100 100 "
            + "100\nmotion r 2.0 25 30 100 100 100 100 100   3.0 25 35 100 100 100 100 100\n\n"
            + "shape e ellipse\nmotion e 1.0 50 50 33 42 75 215 115   2.0 50 50 33 45 75 215 115\n"
            + "motion e 2.0 50 50 "
            + "33 45 75 215 115   3.0 50 50 33 50 75 215 115\n\n", am2.toString());
  }

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
    AnimatorModel am = new AnimatorModel(300, 300,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    am.addFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    assertEquals(listOfFrames, am.getFrames());
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeInvalidFrameAnimatorModel() {
    AnimatorModel am = new AnimatorModel(300, 300,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    am.removeFrame("e", "ellipse", 50, 50, 33,
        50, 75, 255, 115, 3);
  }

  @Test
  public void removeFrameAnimatorModel() {
    AnimatorModel am1 = new AnimatorModel(300, 300,
        0, 0);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am1.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am1.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    AnimatorModel am2 = new AnimatorModel(300, 300,
        0, 0);
    am2.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am2.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am2.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am2.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am2.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am2.addFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    am2.removeFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    assertEquals(am2.toString(), am1.toString());
  }


  @Test
  public void getFramesByShapeNameAnimatorModel() {
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
    AnimatorModel am = new AnimatorModel(300, 300,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    List<Frame> listOfR = new ArrayList<Frame>();
    listOfR.add(f1);
    listOfR.add(f2);
    listOfR.add(f3);
    List<Frame> listOfE = new ArrayList<Frame>();
    listOfE.add(f4);
    listOfE.add(f5);
    listOfE.add(f6);
    assertEquals(listOfR, am.getFramesByShapeName("r"));
    assertEquals(listOfE, am.getFramesByShapeName("e"));
  }

  @Test
  public void removeShapeAnimatorModel() {
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
    AnimatorModel am1 = new AnimatorModel(300, 300,
        0, 0);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am1.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am1.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    AnimatorModel am2 = new AnimatorModel(300, 300,
        0, 0);
    am2.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am2.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am2.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am1.removeShape("e");
    assertEquals(am2.getShapeNames(), am1.getShapeNames());
  }

  @Test
  public void getShapeNamesAnimatorModel() {
    AnimatorModel am = new AnimatorModel(300, 300,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    List<String> listString = new ArrayList<>();
    listString.add("r");
    listString.add("e");
    assertEquals(listString, am.getShapeNames());
  }

  @Test
  public void getShapeNamesAndTypesAnimatorModel() {
    AnimatorModel am = new AnimatorModel(300, 300,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    am.addFrame("r1", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("r2", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    Map<String, String> shapesAndTypes = new HashMap<>();
    shapesAndTypes.put("r", "rectangle");
    shapesAndTypes.put("r1", "rectangle");
    shapesAndTypes.put("r2", "rectangle");
    shapesAndTypes.put("e", "ellipse");
    assertEquals(shapesAndTypes, am.getShapeNamesAndTypes());
    am.removeFrame("r1", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.removeFrame("r2", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    shapesAndTypes.remove("r1");
    shapesAndTypes.remove("r2");
    assertEquals(shapesAndTypes, am.getShapeNamesAndTypes());
  }

}
