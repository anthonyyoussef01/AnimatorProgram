package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Renders an AnimatorView in some manner.
 */
public interface AnimatorView {

  /**
   * Renders an IAnimatorModel in some manner (currently supports visual views, which represent the
   * animation in Java Swing, textual views, which represent the animation as text, and SVG views,
   * which represent the animation in SVG code).
   *
   * @throws IOException if the rendering fails for some reason, such as if the rendering of the
   *                     model cannot be transmitted to an animation or Appendable.
   */
  void render() throws IOException;

  /**
   * Sets listeners for action events and item state changes in the view. Used in the controller.
   *
   * @param actionEvent     action events (such as buttons clicked).
   * @param itemStateChange item state changes (such as boxes checked or unchecked).
   */
  void setListeners(ActionListener actionEvent, ItemListener itemStateChange);

  /**
   * Sets the speed for the animator.
   *
   * @param speed the speed of the animation in frames per second.
   */
  void setSpeed(double speed);

  /**
   * Gets the speed of the view.
   *
   * @return the view's speed in frames per second.
   */
  double getSpeed();

  /**
   * Gets the clock of the view, which is a Java Swing Timer.
   *
   * @return the view's clock.
   */
  Timer getClock();

  /**
   * Gets the current tick of the animator.
   */
  int getTick();

  /**
   * Sets the current tick of the animator.
   *
   * @param tick the tick being represented.
   */
  void setTick(int tick);

  /**
   * Gets the button panel of the view, which is a JPanel of the buttons the user can interact
   * with.
   *
   * @return the buttonPanel.
   */
  JPanel getButtonPanel();

  /**
   * Sets the loop boolean for the animator, which determines whether or not the animation will
   * restart when finished.
   *
   * @param loop whether or not the animation loops.
   */
  void setLoop(boolean loop);

  /**
   * Gets the speed field of the view, which is a JTextbox which displays the current speed of the
   * view.
   *
   * @return the speedField.
   */
  JLabel getSpeedField();

  /**
   * Gets the discrete boolean field of the interactive view.
   */
  boolean getDiscrete();

  /**
   * Sets the boolean for the animation to be discrete or continuous..
   *
   * @param b true if the animation should be discrete, false if the animation should be
   *          continuous.
   */
  void setDiscrete(boolean b);

  /**
   * Gets the fill boolean field of the interactive view.
   */
  boolean getFill();

  /**
   * Sets the boolean for the animation to be fill or outline.
   *
   * @param b true if the shapes in the animation should be filled, false if the animation should
   *          be outlined.
   */
  void setFill(boolean b);


}

