import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.IAnimatorController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.view.AnimatorInteractiveView;
import cs3500.animator.view.AnimatorSVGView;
import cs3500.animator.view.AnimatorTextualView;
import cs3500.animator.view.AnimatorVisualView;
import java.awt.event.ActionEvent;
import java.io.IOException;
import org.junit.Test;

/**
 * A class with tests for an AnimatorController.
 */
public class AnimatorControllerTest {

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor() {
    IAnimatorController ac = new AnimatorController(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidModelConstructor() {
    AnimatorModel am = new AnimatorModel(300, 300,
        0, 0);
    StringBuilder output = new StringBuilder();
    AnimatorTextualView atv = new AnimatorTextualView(am, output);
    IAnimatorController ac = new AnimatorController(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidViewConstructor() {
    AnimatorModel am = new AnimatorModel(300, 300,
        0, 0);
    IAnimatorController ac = new AnimatorController(am, null);
  }

  @Test
  public void textualGo() {
    AnimatorModel am = new AnimatorModel(300, 300,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am.addFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    StringBuilder output = new StringBuilder();
    AnimatorTextualView atv = new AnimatorTextualView(am, output);
    atv.setSpeed(5);
    IAnimatorController ac = new AnimatorController(am, atv);
    try {
      ac.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("canvas 0 0 300 300\n"
        + "shape r rectangle\n"
        + "motion r 0.2 25 25 100 100 100 100 100 0.4 25 30 100 100 100 100 100\n"
        + "motion r 0.4 25 30 100 100 100 100 100 0.6 25 35 100 100 100 100 100\n"
        + "shape e ellipse\n"
        + "motion e 0.2 50 50 33 42 75 215 115 0.4 50 50 33 45 75 215 115\n"
        + "motion e 0.4 50 50 33 45 75 215 115 0.6 50 50 33 50 75 215 115\n", output.toString());
  }

  @Test
  public void SVGGo() {
    AnimatorModel am = new AnimatorModel(400, 200,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    StringBuilder output = new StringBuilder();
    AnimatorSVGView asvgv = new AnimatorSVGView(am, output);
    asvgv.setSpeed(5);
    IAnimatorController ac = new AnimatorController(am, asvgv);
    try {
      ac.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("<svg width=\"400\" height=\"200\" version=\"1.1\" \n"
        + " xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"r\" x=\"25\" y=\"25\" width=\"100\" height=\"100\" fill=\"rgb(100,100,100)\""
        + "  visibility=\"visible\">\n"
        + "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"200ms\" attributeName=\"y\" "
        + "from=\"25\" to=\"30\" fill=\"freeze\"/>\n"
        + "</rect>\n"
        + "<ellipse id=\"e\" cx=\"50\" cy=\"50\" rx=\"16\" ry=\"21\" fill=\"rgb(75,215,115)\" "
        + "visibility=\"visible\">\n"
        + "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"200ms\" attributeName=\"ry\" "
        + "from=\"21\" to=\"22\" fill=\"freeze\"/>\n"
        + "</ellipse>\n"
        + "</svg>", output.toString());
  }

  @Test
  public void VisualGo() {
    AnimatorModel am = new AnimatorModel(400, 200,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    AnimatorVisualView vv = new AnimatorVisualView(am);
    vv.setSpeed(5);
    IAnimatorController ac = new AnimatorController(am, vv);
    assertFalse(vv.isVisible());
    try {
      ac.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertTrue(vv.isVisible());
  }

  @Test
  public void InteractiveGo() {
    AnimatorModel am = new AnimatorModel(400, 200,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    AnimatorInteractiveView iv = new AnimatorInteractiveView(am);
    iv.setSpeed(5);
    IAnimatorController ac = new AnimatorController(am, iv);
    assertFalse(iv.isVisible());
    try {
      ac.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertTrue(iv.isVisible());
  }

  @Test
  public void actionPerformedInteractiveStart() {
    AnimatorModel am = new AnimatorModel(400, 200,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    AnimatorInteractiveView iv = new AnimatorInteractiveView(am);
    iv.setSpeed(5);
    AnimatorController ac = new AnimatorController(am, iv);
    assertFalse(iv.getClock().isRunning());
    ac.actionPerformed(new ActionEvent(iv, 1, "Start Animation"));
    assertTrue(iv.getClock().isRunning());
    assertTrue(iv.getSpeedField().getText().equals("speed: " + iv.getSpeed() + " fps."));
  }

  @Test
  public void actionPerformedInteractiveResume() {
    AnimatorModel am = new AnimatorModel(400, 200,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    AnimatorInteractiveView iv = new AnimatorInteractiveView(am);
    iv.setSpeed(5);
    AnimatorController ac = new AnimatorController(am, iv);
    ac.actionPerformed(new ActionEvent(iv, 1, "Resume Animation"));
    assertTrue(iv.getClock().isRunning());
    assertEquals(iv.getSpeedField().getText(), "speed: " + iv.getSpeed() + " fps.");
  }

  @Test
  public void actionPerformedInteractivePause() {
    AnimatorModel am = new AnimatorModel(400, 200,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    AnimatorInteractiveView iv = new AnimatorInteractiveView(am);
    iv.setSpeed(5);
    AnimatorController ac = new AnimatorController(am, iv);
    ac.actionPerformed(new ActionEvent(iv, 1, "Pause Animation"));
    assertFalse(iv.getClock().isRunning());
    assertEquals(iv.getSpeedField().getText(), "speed: 0.0 fps.");
  }

  @Test
  public void actionPerformedInteractiveRestart() {
    AnimatorModel am = new AnimatorModel(400, 200,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    AnimatorInteractiveView iv = new AnimatorInteractiveView(am);
    iv.setSpeed(5);
    AnimatorController ac = new AnimatorController(am, iv);
    ac.actionPerformed(new ActionEvent(iv, 1, "Restart Animation"));
    assertTrue(iv.getClock().isRunning());
  }

  @Test
  public void actionPerformedInteractiveIncreaseSpeed() {
    AnimatorModel am = new AnimatorModel(400, 200,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    AnimatorInteractiveView iv = new AnimatorInteractiveView(am);
    iv.setSpeed(5);
    AnimatorController ac = new AnimatorController(am, iv);
    double speed = iv.getSpeed();
    ac.actionPerformed(new ActionEvent(iv, 1, "Increase Animation Speed"));
    assertTrue(iv.getSpeed() == speed + 1);
  }

  @Test
  public void actionPerformedInteractiveDecreaseSpeed() {
    AnimatorModel am = new AnimatorModel(400, 200,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    AnimatorInteractiveView iv = new AnimatorInteractiveView(am);
    iv.setSpeed(5);
    AnimatorController ac = new AnimatorController(am, iv);
    double speed = iv.getSpeed();
    ac.actionPerformed(new ActionEvent(iv, 1, "Decrease Animation Speed"));
    assertTrue(iv.getSpeed() == speed - 1);
  }

}
