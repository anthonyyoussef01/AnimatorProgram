package cs3500.animator.model;

/**
 * An interface representing a frame of the animation.
 */
public interface IFrame {

  /**
   * A String representation of a Frame.
   *
   * @return the Frame as a String.
   */
  String toString();

  /**
   * Compares the equality of two Frames.
   *
   * @param obj the object being compared.
   * @return whether or not the passed frame is equal to this.
   */
  boolean equals(Object obj);

  /**
   * A hashing of an Frame.
   *
   * @return a hash code of the Frame.
   */
  int hashCode();

  /**
   * Gets the shapeName of the Frame.
   *
   * @return the shapeName.
   */
  String getShapeName();


  /**
   * Gets the shapeType of the Frame.
   *
   * @return the shapeType.
   */
  String getShapeType();

  /**
   * Gets the horizontal coordinate of the shape displayed in the Frame.
   *
   * @return x.
   */
  int getX();

  /**
   * Gets the vertical coordinate of the shape displayed in the Frame.
   *
   * @return y.
   */
  int getY();

  /**
   * Gets the width of the shape displayed in the Frame.
   *
   * @return the width.
   */
  int getWidth();

  /**
   * Gets the height of the shape displayed in the Frame.
   *
   * @return the height.
   */
  int getHeight();

  /**
   * Gets the red coordinate of the shape displayed in the Frame.
   *
   * @return r.
   */
  int getR();

  /**
   * Gets the green coordinate of the shape displayed in the Frame.
   *
   * @return g.
   */
  int getG();

  /**
   * Gets the blue coordinate of the shape displayed in the Frame.
   *
   * @return b.
   */
  int getB();

  /**
   * Gets the tick of the Frame.
   *
   * @return the tick.
   */
  double getTick();

}
