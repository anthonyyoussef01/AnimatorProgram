package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import cs3500.animator.model.IAnimatorModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * View that creates a textual rendering of an animation, which describes its canvas, shapes and
 * motions.
 */
public class AnimatorTextualView implements AnimatorView {

  private double speed;
  private final IAnimatorModel model;
  private final Appendable ap;

  /**
   * Constructor for a textual view that takes an AnimatorModel and makes the appendable System.out,
   * and creates a textual rendering of an animation.
   *
   * @param model the IAnimatorModel to be represented.
   * @throws IllegalArgumentException if the model is null.
   */
  public AnimatorTextualView(IAnimatorModel model)
      throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
    this.ap = System.out;
  }

  /**
   * Constructor for a textual view that takes an AnimatorModel and an appendable and creates a
   * textual rendering of an animation.
   *
   * @param model the IAnimatorModel to be represented.
   * @param ap    the Appendable to transmit the view to.
   * @throws IllegalArgumentException if the model or appendable are null.
   */
  public AnimatorTextualView(IAnimatorModel model, Appendable ap) {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("Model and/or Appendable cannot be null.");
    }
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void render() throws IOException {
    try {
      this.ap.append(this.modelAsString());
    } catch (IOException e) {
      throw new IOException("Render failed.");
    }
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
   * Represents the animation as a string, which describes its canvas, shapes, and motions.
   *
   * @return a string representing the AnimatorModel.
   */
  private String modelAsString() {
    String result = "";
    result += "canvas" + " " + this.model.getLeftmostX() + " " + this.model.getTopmostY() + " "
        + this.model.getWidth() + " " + this.model.getHeight() + "\n";
    for (String s : this.model.getShapeNames()) {
      result += "shape " + s + " " + this.model.getShapeNamesAndTypes().get(s) + "\n";
      for (int x = 0; x < (this.model.getFramesByShapeName(s).size() - 1); x++) {
        result +=
            "motion " + s + " " + this.model.getFramesByShapeName(s).get(x).getTick() / this.speed
                + " " + this.model.getFramesByShapeName(s).get(x).toString() + " "
                + this.model.getFramesByShapeName(s).get(x + 1).getTick() / this.speed
                + " " + this.model.getFramesByShapeName(s).get(x + 1).toString() + "\n";
      }
    }
    return result;
  }

}
