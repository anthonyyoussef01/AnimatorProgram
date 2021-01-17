package cs3500.animator.provider.model.shapes;

import cs3500.animator.provider.model.Color;
import cs3500.animator.provider.model.Vector2;

/**
 * Represents a generic shape and all of the operations that one might perform on its information.
 * Encodes a rotation, a size, a position, and a color.
 */
public interface Shape {


  /**
   * Gets the angle of this shape's rotation, in degrees.
   *
   * @return the angle of this shape
   */
  double getAngle();

  /**
   * Makes a new Shape of the same type with the given angle and all other parameters equal to the
   * parameters of this one.
   *
   * @param angle the angle for the new shape
   * @return the new shape with the given angle
   */
  Shape setAngle(double angle);

  /**
   * Gets the dimensions of this Shape's bounding box.
   *
   * @return the bounding box for this Shape
   */
  Vector2 getSize();

  /**
   * Makes a new Shape of the same type with the given bounding box and all other parameters equal
   * to the parameters of this one.
   *
   * @param v the bounding box for the new shape
   * @return the new shape with the given bounding box
   */
  Shape setSize(Vector2 v);

  /**
   * Gets the 2D position of this Shape.
   *
   * @return the position of this shape
   */
  Vector2 getPosition();

  /**
   * Makes a new Shape of the same type with the given position and all other parameters equal to
   * the parameters of this one.
   *
   * @param v the position of the new shape
   * @return the new shape with given position
   */
  Shape setPosition(Vector2 v);

  /**
   * Gets the color of this Shape.
   *
   * @return the color of this shape
   */
  Color getColor();

  /**
   * Makes a new Shape of the same type with the given position and all other parameters equal to
   * the parameters of this one.
   *
   * @param c the color of the new shape
   * @return the new shape with given color
   */
  Shape setColor(Color c);

  Shape copy();

  ShapeType getType();

}
