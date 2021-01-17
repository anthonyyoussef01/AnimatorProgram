package cs3500.animator.view;

import cs3500.animator.model.Frame;
import cs3500.animator.model.IAnimatorModel;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * View that creates an SVG rendering of an animation in SVG code.
 */
public class AnimatorSVGView implements AnimatorView {

  private double speed;
  private final IAnimatorModel model;
  private final Appendable ap;

  /**
   * Constructor for an SVG view that takes an AnimatorModel and makes the appendable an empty
   * StringBuilder.
   *
   * @param model the IAnimatorModel to be represented.
   * @throws IllegalArgumentException if the model is null.
   */
  public AnimatorSVGView(IAnimatorModel model)
      throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
    this.ap = new StringBuilder();
  }

  /**
   * Constructor for an SVG view that takes an AnimatorModel and an appendable.
   *
   * @param model the IAnimatorModel to be represented.
   * @param ap    the Appendable to transmit the view to.
   * @throws IllegalArgumentException if the model or appendable are null.
   */
  public AnimatorSVGView(IAnimatorModel model, Appendable ap) {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("Model and/or Appendable cannot be null.");
    }
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void render() throws IOException {
    this.outputSVG();
  }

  @Override
  public void setListeners(ActionListener actionEvent, ItemListener itemEvent) {
    return;
  }

  @Override
  public void setSpeed(double speed) {
    this.speed = speed;
  }

  @Override
  public double getSpeed() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Timer getClock() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getTick() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTick(int tick) {
    throw new UnsupportedOperationException();
  }

  @Override
  public JPanel getButtonPanel() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setLoop(boolean loop) {
    throw new UnsupportedOperationException();
  }

  @Override
  public JLabel getSpeedField() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean getDiscrete() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setDiscrete(boolean b) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean getFill() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setFill(boolean b) {
    throw new UnsupportedOperationException();
  }

  /**
   * Appends the SVG code to the given appendable.
   *
   * @throws IOException if the code cannot be appended.
   */
  private void outputSVG() throws IOException {
    Map<String, String> map;
    map = model.getShapeNamesAndTypes();

    ap.append("<svg width=\""
        + model.getWidth() + "\" "
        + "height=\""
        + model.getHeight() + "\" version=\"1.1\" \n xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (String s : map.keySet()) {
      List<Frame> l = this.model.getFramesByShapeName(s);
      if (map.get(s).equals("rectangle")) {
        ap.append(String.format("<rect id=\"%s\" x=\"%d\""
                + " y=\"%d\" width=\"%d\" height=\"%d\" fill=\"rgb(%d,%d,%d)\" "
                + " visibility=\"visible\">\n",
            l.get(0).getShapeName(),
            l.get(0).getX(),
            l.get(0).getY(),
            l.get(0).getWidth(),
            l.get(0).getHeight(),
            l.get(0).getR(),
            l.get(0).getG(),
            l.get(0).getB()));
        for (int i = 1; i < l.size(); i++) {
          if (l.get(i).getX() != l.get(i - 1).getX()) {
            ap.append(String.format("<animate attributeType=\"xml\" "
                    + "begin=\"%dms\"" + " dur=\"%dms\" attributeName=\"x\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed), l.get(i - 1).getX(), l.get(i).getX()));
          }

          if (l.get(i).getY() != l.get(i - 1).getY()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\""
                    + " dur=\"%dms\" attributeName=\"y\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed), l.get(i - 1).getY(), l.get(i).getY()));
          }
          if (l.get(i).getWidth() != l.get(i - 1).getWidth()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"width\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getWidth(), l.get(i).getWidth()));
          }
          if (l.get(i).getHeight() != l.get(i - 1).getHeight()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"height\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getHeight(), l.get(i).getHeight()));
          }
          if (l.get(i).getR() != l.get(i - 1).getR() || l.get(i).getG() != l.get(i - 1).getG()
              || l.get(i).getB() != l.get(i - 1).getB()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"fill\" from=\"rgb(%d,%d,%d)\""
                    + " to=\"rgb(%d,%d,%d)\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getR(), l.get(i - 1).getG(), l.get(i - 1).getB(),
                l.get(i).getR(), l.get(i).getG(), l.get(i).getB()));
          }
        }
        ap.append(String.format("</rect>\n"));
      } else if ((map.get(s).equals("plus"))) {
        ap.append(String.format("<rect id=\"%s\" x=\"%d\""
                + " y=\"%d\" width=\"%d\" height=\"%d\" fill=\"rgb(%d,%d,%d)\" "
                + " visibility=\"visible\">\n",
            l.get(0).getShapeName(),
            l.get(0).getX() - (l.get(0).getWidth() / 3),
            l.get(0).getY(),
            l.get(0).getWidth(),
            l.get(0).getHeight() / 3,
            l.get(0).getR(),
            l.get(0).getG(),
            l.get(0).getB()));
        for (int i = 1; i < l.size(); i++) {
          if (l.get(i).getX() != l.get(i - 1).getX()) {
            ap.append(String.format("<animate attributeType=\"xml\" "
                    + "begin=\"%dms\"" + " dur=\"%dms\" attributeName=\"x\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed), l.get(i - 1).getX() - (l.get(i - 1).getWidth() / 3),
                l.get(i).getX() - (l.get(i).getWidth() / 3)));
          }
          if (l.get(i).getY() != l.get(i - 1).getY()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\""
                    + " dur=\"%dms\" attributeName=\"y\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed), l.get(i - 1).getY(), l.get(i).getY()));
          }
          if (l.get(i).getWidth() != l.get(i - 1).getWidth()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"width\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getWidth(), l.get(i).getWidth()));
          }
          if (l.get(i).getHeight() != l.get(i - 1).getHeight()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"height\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getHeight() / 3, l.get(i).getHeight() / 3));
          }
          if (l.get(i).getR() != l.get(i - 1).getR() || l.get(i).getG() != l.get(i - 1).getG()
              || l.get(i).getB() != l.get(i - 1).getB()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"fill\" from=\"rgb(%d,%d,%d)\""
                    + " to=\"rgb(%d,%d,%d)\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getR(), l.get(i - 1).getG(), l.get(i - 1).getB(),
                l.get(i).getR(), l.get(i).getG(), l.get(i).getB()));
          }
        }
        ap.append(String.format("</rect>\n"));
        ap.append(String.format("<rect id=\"%s\" x=\"%d\""
                + " y=\"%d\" width=\"%d\" height=\"%d\" fill=\"rgb(%d,%d,%d)\" "
                + " visibility=\"visible\">\n",
            l.get(0).getShapeName(),
            l.get(0).getX(),
            l.get(0).getY() - (l.get(0).getHeight() / 3),
            l.get(0).getWidth() / 3,
            l.get(0).getHeight(),
            l.get(0).getR(),
            l.get(0).getG(),
            l.get(0).getB()));
        for (int i = 1; i < l.size(); i++) {
          if (l.get(i).getX() != l.get(i - 1).getX()) {
            ap.append(String.format("<animate attributeType=\"xml\" "
                    + "begin=\"%dms\"" + " dur=\"%dms\" attributeName=\"x\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed), l.get(i - 1).getX(), l.get(i).getX()));
          }
          if (l.get(i).getY() != l.get(i - 1).getY()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\""
                    + " dur=\"%dms\" attributeName=\"y\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed), l.get(i - 1).getY()  - (l.get(i - 1).getHeight() / 3),
                l.get(i).getY() - (l.get(i).getHeight() / 3)));
          }
          if (l.get(i).getWidth() != l.get(i - 1).getWidth()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"width\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getWidth() / 3, l.get(i).getWidth() / 3));
          }
          if (l.get(i).getHeight() != l.get(i - 1).getHeight()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"height\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getHeight(), l.get(i).getHeight()));
          }
          if (l.get(i).getR() != l.get(i - 1).getR() || l.get(i).getG() != l.get(i - 1).getG()
              || l.get(i).getB() != l.get(i - 1).getB()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"fill\" from=\"rgb(%d,%d,%d)\""
                    + " to=\"rgb(%d,%d,%d)\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getR(), l.get(i - 1).getG(), l.get(i - 1).getB(),
                l.get(i).getR(), l.get(i).getG(), l.get(i).getB()));
          }
        }
        ap.append(String.format("</rect>\n"));
      } else if (map.get(s).equals("ellipse")) {
        ap.append(String.format("<ellipse id=\"%s\" cx=\"%d\""
                + " cy=\"%d\" rx=\"%d\" ry=\"%d\" fill=\"rgb(%d,%d,%d)\""
                + " visibility=\"visible\">\n",
            l.get(0).getShapeName(),
            (l.get(0).getX()),
            (l.get(0).getY()),
            l.get(0).getWidth() / 2,
            l.get(0).getHeight() / 2,
            l.get(0).getR(),
            l.get(0).getG(),
            l.get(0).getB()));
        for (int i = 1; i < l.size(); i++) {
          if (l.get(i).getX() != l.get(i - 1).getX()) {
            ap.append(String.format("<animate attributeType=\"xml\" "
                    + "begin=\"%dms\"" + " dur=\"%dms\" attributeName=\"cx\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed), l.get(i - 1).getX(), l.get(i).getX()));
          }
          if (l.get(i).getY() != l.get(i - 1).getY()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\""
                    + " dur=\"%dms\" attributeName=\"cy\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed), l.get(i - 1).getY(), l.get(i).getY()));
          }
          if (l.get(i).getWidth() != l.get(i - 1).getWidth()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"rx\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getWidth() / 2, l.get(i).getWidth() / 2));
          }
          if (l.get(i).getHeight() != l.get(i - 1).getHeight()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"ry\" from=\"%d\""
                    + " to=\"%d\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getHeight() / 2, l.get(i).getHeight() / 2));
          }
          if (l.get(i).getR() != l.get(i - 1).getR() || l.get(i).getG() != l.get(i - 1).getG()
              || l.get(i).getB() != l.get(i - 1).getB()) {
            ap.append(String.format("<animate attributeType=\"xml\" " + "begin=\"%dms\" "
                    + "dur=\"%dms\" attributeName=\"fill\" from=\"rgb(%d,%d,%d)\""
                    + " to=\"rgb(%d,%d,%d)\" fill=\"freeze\"/>\n",
                (int) ((l.get(i - 1).getTick() / 1) * 1000 / this.speed),
                (int) ((l.get(i).getTick() - l.get(i - 1).getTick()) / 1 * 1000
                    / this.speed),
                l.get(i - 1).getR(), l.get(i - 1).getG(), l.get(i - 1).getB(),
                l.get(i).getR(), l.get(i).getG(), l.get(i).getB()));
          }
        }
        ap.append(String.format("</ellipse>\n"));
      } else {
        throw new IllegalArgumentException("Shape is not valid");
      }
    }
    ap.append("</svg>");
  }
}
