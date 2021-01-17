package cs3500.animator.model;

import java.util.List;
import java.util.Map;

/**
 * An interface representing an animation.
 */
public interface IAnimatorModel {

  /**
   * A String representation of an AnimatorModel.
   *
   * @return the AnimatorModel as a String.
   */
  String toString();

  /**
   * Adds a frame to the animation.
   *
   * @param shapeName the name of the shape being created.
   * @param shapeType the type of shape being created.
   * @param x         the horizontal coordinate.
   * @param y         the vertical coordinate.
   * @param height    the height.
   * @param width     the width.
   * @param r         the rgb color coordinate.
   * @param g         the rgb color coordinate.
   * @param b         the rgb color coordinate.
   * @param tick      the time in ticks.
   */
  void addFrame(String shapeName, String shapeType, int x, int y, int height,
      int width, int r, int g, int b, double tick);

  /**
   * Removes a frame from the animation.
   *
   * @param shapeName the name of the shape being created.
   * @param shapeType the type of shape being created.
   * @param x         the horizontal coordinate.
   * @param y         the vertical coordinate.
   * @param height    the height.
   * @param width     the width.
   * @param r         the rgb color coordinate.
   * @param g         the rgb color coordinate.
   * @param b         the rgb color coordinate.
   * @param tick      the time in ticks.
   */
  void removeFrame(String shapeName, String shapeType, int x, int y, int width, int height, int r,
      int g, int b, double tick);

  /**
   * Adds a shape to the map of shapeNames and shapeTypes in the model.
   *
   * @param shapeName the name of the shape being added.
   * @param shapeType the type of shape being added.
   */
  void addShape(String shapeName, String shapeType);

  /**
   * Removes any frames with the given shape from the frames and shapes in the animation.
   *
   * @param shapeName the name of the shape to be removed.
   */
  void removeShape(String shapeName);

  /**
   * Adds a slomo interval to the list of slomo intervals in the animation.
   * @param ints an int[2] representing the start and end ticks of the interval.
   */
  void addSlomo(int[] ints);

  /**
   * Gets the list of Frames in the model.
   *
   * @return the model's frames.
   */
  List<Frame> getFrames();

  /**
   * A list of Frames for a given shape.
   *
   * @param shapeName the name of the shape being analyzed.
   * @return a list of Frames that describe the given shape.
   */
  List<Frame> getFramesByShapeName(String shapeName);

  /**
   * A list of Frames for a given tick.
   *
   * @param tick the tick being analyzed.
   * @return a list of Frames at that given tick.
   */
  List<Frame> getFramesByTick(double tick);

  /**
   * Gets a list of the all the individual shapes in the animation.
   *
   * @return a list of the unique shapeNames in the animator's frames.
   */
  List<String> getShapeNames();

  /**
   * Gets the width of the model.
   *
   * @return the width.
   */
  int getWidth();

  /**
   * Gets the height of the model.
   *
   * @return the height.
   */
  int getHeight();

  /**
   * Gets the leftmostX of the model.
   *
   * @return the leftmostX.
   */
  int getLeftmostX();

  /**
   * Gets the topmostY of the model.
   *
   * @return the topmostY.
   */
  int getTopmostY();

  /**
   * Gets the a Map of shapes in the model to their respective types.
   *
   * @return the model's shapeNamesAndTypes.
   */
  Map<String, String> getShapeNamesAndTypes();

  /**
   * Gets the list of slomo intervals for the animation.
   *
   * @return a set of int[2] arrays containing the start and end time for slomo intervals.
   */
  List<int[]> getSlomo();

  /**
   * Sets the width of the model.
   *
   * @param width the width of the canvas for the AnimatorModel.
   */
  void setWidth(int width);

  /**
   * Sets the height of the model.
   *
   * @param height the height of the canvas for the AnimatorModel.
   */
  void setHeight(int height);

  /**
   * Sets the leftmost X coordinate of the model.
   *
   * @param leftmostX the leftmost X coordinate of the canvas for the AnimatorModel.
   */
  void setLeftmostX(int leftmostX);

  /**
   * Sets the topmost Y coordinate of the model.
   *
   * @param topmostY the topmost Y coordinate of the canvas for the AnimatorModel.
   */
  void setTopmostY(int topmostY);

}



