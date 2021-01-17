import static org.junit.Assert.assertEquals;

import cs3500.animator.view.AnimatorSVGView;
import java.io.IOException;
import cs3500.animator.model.AnimatorModel;
import org.junit.Test;
import cs3500.animator.view.AnimatorView;

/**
 * /** A class with tests for an Animator's textual representation.
 */
public class AnimatorSVGViewTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullModelDefaultConstructor() {
    AnimatorView av = new AnimatorSVGView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidCustomConstructor() {
    AnimatorView av = new AnimatorSVGView(null,
        null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullModelCustomConstructor() {
    AnimatorView av = new AnimatorSVGView(null, new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullAppendableCustomConstructor() {
    AnimatorModel am = new AnimatorModel();
    AnimatorView av = new AnimatorSVGView(am, null);
  }

  @Test
  public void renderTest1() throws IOException {
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
    AnimatorSVGView asvgv1 = new AnimatorSVGView(am1, output1);
    asvgv1.setSpeed(5);
    asvgv1.render();
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
        + "</svg>", output1.toString());
  }

  @Test
  public void renderTest2() throws IOException {
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
    am1.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am1.addFrame("rr", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    StringBuilder output1 = new StringBuilder();
    AnimatorSVGView asvgv1 = new AnimatorSVGView(am1, output1);
    asvgv1.setSpeed(5);
    asvgv1.render();
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
        + "<rect id=\"rr\" x=\"25\" y=\"30\" width=\"100\" height=\"100\" fill=\"rgb(100,100,100)\""
        + "  visibility=\"visible\">\n"
        + "</rect>\n"
        + "</svg>", output1.toString());

  }

  @Test
  public void renderTestNoChangeRect() throws IOException {
    AnimatorModel am1 = new AnimatorModel(400,
        200, 0, 0);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 2);

    StringBuilder output1 = new StringBuilder();
    AnimatorSVGView asvgv1 = new AnimatorSVGView(am1, output1);
    asvgv1.setSpeed(5);
    asvgv1.render();
    assertEquals("<svg width=\"400\" height=\"200\" version=\"1.1\" \n"
        + " xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"r\" x=\"25\" y=\"25\" width=\"100\" height=\"100\" fill=\"rgb(100,100,100)\""
        + "  visibility=\"visible\">\n"
        + "</rect>\n"
        + "</svg>", output1.toString());

  }

  @Test
  public void renderTestNoChangeEllipse() throws IOException {
    AnimatorModel am1 = new AnimatorModel(400,
        200, 0, 0);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 2);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 2);
    am1.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    StringBuilder output1 = new StringBuilder();
    AnimatorSVGView asvgv1 = new AnimatorSVGView(am1, output1);
    asvgv1.setSpeed(5);
    asvgv1.render();
    assertEquals("<svg width=\"400\" height=\"200\" version=\"1.1\" \n"
        + " xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"r\" x=\"25\" y=\"25\" width=\"100\" height=\"100\" fill=\"rgb(100,100,100)\""
        + "  visibility=\"visible\">\n"
        + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"0ms\" attributeName=\"y\" "
        + "from=\"25\" to=\"30\" fill=\"freeze\"/>\n"
        + "</rect>\n"
        + "<ellipse id=\"e\" cx=\"50\" cy=\"50\" rx=\"16\" ry=\"21\" fill=\"rgb(75,215,115)\" "
        + "visibility=\"visible\">\n"
        + "</ellipse>\n"
        + "</svg>", output1.toString());

  }

  @Test
  public void renderTestChange2Vars() throws IOException {
    AnimatorModel am1 = new AnimatorModel(400,
        200, 0, 0);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 2);
    am1.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 150, 100, 2);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am1.addFrame("e", "ellipse", 30, 50, 33,
        45, 75, 215, 115, 2);
    StringBuilder output1 = new StringBuilder();
    AnimatorSVGView asvgv1 = new AnimatorSVGView(am1, output1);
    asvgv1.setSpeed(5);
    asvgv1.render();
    assertEquals("<svg width=\"400\" height=\"200\" version=\"1.1\" \n"
        + " xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<rect id=\"r\" x=\"25\" y=\"25\" width=\"100\" height=\"100\" fill=\"rgb(100,100,100)\""
        + "  visibility=\"visible\">\n"
        + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"0ms\" attributeName=\"y\" "
        + "from=\"25\" to=\"30\" fill=\"freeze\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"0ms\" attributeName=\"fill\" "
        + "from=\"rgb(100,100,100)\" to=\"rgb(100,150,100)\" fill=\"freeze\"/>\n"
        + "</rect>\n"
        + "<ellipse id=\"e\" cx=\"50\" cy=\"50\" rx=\"16\" ry=\"21\" fill=\"rgb(75,215,115)\" "
        + "visibility=\"visible\">\n"
        + "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"200ms\" attributeName=\"ry\" "
        + "from=\"21\" to=\"22\" fill=\"freeze\"/>\n"
        + "<animate attributeType=\"xml\" begin=\"400ms\" dur=\"0ms\" attributeName=\"cx\" "
        + "from=\"50\" to=\"30\" fill=\"freeze\"/>\n"
        + "</ellipse>\n"
        + "</svg>", output1.toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void renderTestInvalidShape() throws IOException {
    AnimatorModel am1 = new AnimatorModel(400,
        200, 0, 0);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am1.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 2);
    am1.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 150, 100, 2);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am1.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am1.addFrame("e", "ellipse", 30, 50, 33,
        45, 75, 215, 115, 2);
    am1.addFrame("o", "oval", 50, 50, 33,
        45, 75, 215, 115, 2);
    am1.addFrame("o", "oval", 30, 50, 33,
        0, 76, 215, 115, 2);
    StringBuilder output1 = new StringBuilder();
    AnimatorSVGView asvgv1 = new AnimatorSVGView(am1, output1);
    asvgv1.setSpeed(5);
    asvgv1.render();
  }

}