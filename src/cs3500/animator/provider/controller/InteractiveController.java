package cs3500.animator.provider.controller;

import cs3500.animator.provider.model.AnimatorModel;
import cs3500.animator.provider.model.Vector2;
import cs3500.animator.provider.model.keyframes.Keyframe;
import cs3500.animator.provider.model.shapes.Shape;
import cs3500.animator.provider.view.AnimationView;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Represents a controller class that allows interaction between the view and model.
 */
public class InteractiveController {

  private static final double INC_SPEED = 1.25;
  private static final double DEC_SPEED = 0.8;

  private AnimatorModel<Shape, Keyframe> model;
  private AnimationView view;
  private boolean paused;
  private boolean looping;

  /**
   * Constructs a new InteractiveController with given model and view.
   *
   * @param model the model
   * @param view  the view
   */
  public InteractiveController(AnimatorModel<Shape, Keyframe> model, AnimationView view) {
    this.model = model;
    this.view = view;
    model.start();
  }

  private static class TickScale implements BiConsumer<AnimatorModel<Shape, Keyframe>,
      AnimationView> {

    private double scaleBy;

    private TickScale(double scaleBy) {
      this.scaleBy = scaleBy;
    }

    @Override
    public void accept(AnimatorModel<Shape, Keyframe> m, AnimationView v) {
      v.scaleTickRate(scaleBy);
    }
  }

  /**
   * Steps time by one increment in the model unless the controller is paused, or it is looped and
   * on the last tick, where it returns to the original time.
   */
  public void stepTime() {
    if (!paused) {
      model.incrementTime();
      if (model.getCurrentTime() > model.lastTick() && looping) {
        model.start();
      }
    }
  }

  /**
   * Gets the bounds of the model.
   *
   * @return the a vector2 of the bounds of the model.
   */
  public Vector2 getBounds() {
    return new Vector2(model.getWidth(), model.getHeight());
  }

  /**
   * Gets the offset of the model.
   *
   * @return a vector2 of the offset of the model.
   */
  public Vector2 getOffset() {
    return new Vector2(model.getX(), model.getY());
  }

  /**
   * Gets the last tick where there are still active keyframes in the model.
   *
   * @return the last tick with active keyframes in the model.
   */
  public int getLastTick() {
    return model.lastTick();
  }

  /**
   * Gets the list of keyframes in the model.
   *
   * @return the list of keyframes in the model.
   */
  public List<Keyframe> getKeyframes() {
    return model.getKeyframes();
  }

  /**
   * Gets the list of shapes in the model.
   *
   * @return the list of shapes in the model.
   */
  public Map<String, Shape> getShapes() {
    return model.getShapes();
  }

  /**
   * Gets the shapes in the model, sorted by their order.
   *
   * @return the list of shapes, sorted
   */
  public List<String> getShapeOrder() {
    return model.getShapeOrder();
  }

  /**
   * Returns the current time in the model.
   *
   * @return the current time.
   */
  public int getCurrentTime() {
    return model.getCurrentTime();
  }

  /**
   * Gets the tick rate of the view.
   *
   * @return the tick rate of the view.
   */
  public double getTickRate() {
    return view.getTickRate();
  }

  /**
   * An ActionListener that pauses/unpauses the animation.
   *
   * @return the pause ActionListener
   */
  public ActionListener pauseAction() {
    return e -> paused = !paused;
  }

  /**
   * An ActionListener that toggles program looping.
   *
   * @return the loop ActionListener
   */
  public ActionListener loopAction() {
    return e -> looping = !looping;
  }

  /**
   * An ActionListener that restarts the animation.
   *
   * @return the restart ActionListener
   */
  public ActionListener restartAction() {
    return e -> model.start();
  }

  /**
   * An ActionListener that speeds up the animation.
   *
   * @return the speed up ActionListener
   */
  public ActionListener speedUpAction() {
    return e -> view.scaleTickRate(INC_SPEED);
  }

  /**
   * An ActionListener that slows down the animation.
   *
   * @return the slow down ActionListener
   */
  public ActionListener speedDownAction() {
    return e -> view.scaleTickRate(DEC_SPEED);
  }

  /**
   * Provides a MouseAdapter that pauses on mouse press.
   *
   * @return the pause MouseAdapter
   */
  public MouseAdapter mouseDownAction() {
    return new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        paused = !paused;
      }
    };
  }

  /**
   * Provides a KeyAdapter that maps some keyboard keys to pause and speed up.
   *
   * @return the KeyAdapter object
   */
  public KeyAdapter keyDownAction() {
    return new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
          case KeyEvent.VK_SPACE:
            paused = !paused;
            break;
          case KeyEvent.VK_RIGHT:
            view.scaleTickRate(INC_SPEED);
            break;
          case KeyEvent.VK_LEFT:
            view.scaleTickRate(DEC_SPEED);
            break;
          case KeyEvent.VK_R:
            model.start();
            break;
          default:
            break;
        }
      }
    };
  }
}
