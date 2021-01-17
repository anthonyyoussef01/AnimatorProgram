package cs3500.animator.provider.model;

import java.util.List;
import java.util.Map;

/**
 * Represents a generic animation model that operates on some hypothetical idea of a shape using an
 * unspecified type of keyframe to accomplish that.
 *
 * @param <T> the Shape type
 * @param <U> the Keyframe type
 */
public interface AnimatorModel<T, U> {

  /**
   * Determines if this model is currently in an "animating" state.
   *
   * @return the state of this cs3500.model
   */
  public abstract boolean hasStarted();

  /**
   * Starts this cs3500.model animating. If the cs3500.model is currently animating, resets all
   * animation parameters to the beginning.
   */
  public void start();

  /**
   * Stops this model animating.
   *
   * @throws IllegalStateException if the animation is not currently running
   */
  public void stop() throws IllegalStateException;

  /**
   * Gets the current state of the shapes in the animation.
   *
   * @return the map of shapes this animation controls
   * @throws IllegalStateException if the animation isn't started
   */
  public Map<String, T> getShapes() throws IllegalArgumentException;

  /**
   * Gets a list of this object's keyframes.
   *
   * @return the list of keyframes
   */
  public List<U> getKeyframes();

  /**
   * Steps this cs3500.model's time by 1 and applies each keyframe's operation to the shapes they
   * apply to. In effect, "animates" a frame.
   *
   * @throws IllegalStateException if the animation hasn't started
   */
  public void incrementTime() throws IllegalStateException;

  /**
   * Adds a new keyframe to this cs3500.model.
   *
   * @param u the keyframe to add
   * @throws IllegalArgumentException if the keyframe conflicts with another, or does not apply to
   *                                  any shape in this cs3500.model or is null
   * @throws IllegalStateException    if the animation has started
   */
  public void addKeyframe(U u) throws IllegalArgumentException, IllegalStateException;

  /**
   * Adds a shape to the cs3500.model.
   *
   * @param name  the name of the shape
   * @param shape the shape to be added
   * @throws IllegalArgumentException if the shape's name is taken or the shape/name is null
   * @throws IllegalStateException    if the animation has started
   */
  public void addShape(String name, T shape) throws IllegalArgumentException;

  /**
   * Gets the current frame tick that this cs3500.model is on.
   *
   * @return the current tick
   * @throws IllegalStateException if the animation has not started
   */
  public int getCurrentTime() throws IllegalStateException;

  /**
   * Gets the last tick of the last motion in this cs3500.model.
   *
   * @return the last tick of the last motion in this cs3500.model.
   */
  public int lastTick();

  /**
   * Gets the width of the canvas.
   *
   * @return the width of the canvas
   */
  public int getWidth();

  /**
   * Gets the height of the canvas.
   *
   * @return the height of the canvas
   */
  public int getHeight();

  /**
   * Gets the leftmost x value of the canvas.
   *
   * @return the leftmost x value of the canvas.
   */
  public int getX();

  /**
   * Gets the uppermost y value of the canvas.
   *
   * @return the uppermost y value of the canvas.
   */
  public int getY();

  /**
   * Gets a mapping of shape ID's to their relative order on the canvas.
   *
   * @return the shape ordering
   */
  public List<String> getShapeOrder();

}
