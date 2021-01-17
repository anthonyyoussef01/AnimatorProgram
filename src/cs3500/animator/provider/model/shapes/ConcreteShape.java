package cs3500.animator.provider.model.shapes;

import java.util.Objects;

import cs3500.animator.provider.model.Color;
import cs3500.animator.provider.model.Vector2;

/**
 * Represents a generic implementation of all of the common 2D shape things. Stores all of the
 * position, size, angle, and color fields that shapes all use.
 */
public class ConcreteShape implements Shape {

  private Vector2 size;
  private Vector2 position;
  private Color color;
  private double angle;
  private ShapeType type;

  /**
   * Constructs a new Shape with given size, position, and color.
   *
   * @param size     the size of this shape
   * @param position the position of this shape
   * @param color    the color of this shape
   * @throws IllegalArgumentException if the size is below zero in either dimension
   */
  public ConcreteShape(Vector2 size, Vector2 position, Color color, ShapeType type)
      throws IllegalArgumentException {
    this(size, position, color, 0, type);
  }

  /**
   * Constructs a new Shape with the given size, position, color, and angle.
   *
   * @param size     the size of this shape
   * @param position the position of this shape
   * @param color    the color of this shape
   * @param angle    the angle of this shape
   * @throws IllegalArgumentException if the size is below zero in either dimension
   */
  public ConcreteShape(Vector2 size, Vector2 position, Color color, double angle, ShapeType type)
      throws IllegalArgumentException {
    if (size.getX() < 0 || size.getY() < 0) {
      throw new IllegalArgumentException("Invalid size");
    }
    this.size = size;
    this.position = position;
    this.color = color;
    this.angle = angle % 360;
    this.type = type;
  }

  /**
   * Creates a concrete a base concrete shape given only a type by setting all other values to 0.
   *
   * @param type is the type of shape.
   */
  public ConcreteShape(ShapeType type) {
    this(new Vector2(0, 0), new Vector2(0, 0), new Color(0, 0, 0),
        0, type);
  }

  /**
   * Makes a copy of this object with the same type and fields.
   *
   * @return a copy of this object
   */
  public Shape copy() {
    return new ConcreteShape(this.size, this.position, this.color, this.angle, this.type);
  }


  @Override
  public Vector2 getSize() {
    return this.size;
  }

  @Override
  public Shape setSize(Vector2 v) {
    ConcreteShape newShape = (ConcreteShape) this.copy();
    newShape.size = v;
    return newShape;

  }

  @Override
  public Vector2 getPosition() {
    return this.position;
  }

  @Override
  public Shape setPosition(Vector2 v) {
    ConcreteShape newShape = (ConcreteShape) this.copy();
    newShape.position = v;
    return newShape;

  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public Shape setColor(Color c) {
    ConcreteShape newShape = (ConcreteShape) this.copy();
    newShape.color = c;
    return newShape;
  }

  @Override
  public double getAngle() {
    return this.angle;
  }

  @Override
  public Shape setAngle(double angle) {
    ConcreteShape newShape = (ConcreteShape) this.copy();
    newShape.angle = angle % 360;
    return newShape;
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass() == this.getClass()) {
      Shape shape = (Shape) o;
      return size.equals(shape.getSize()) &&
          position.equals(shape.getPosition()) &&
          color.equals(shape.getColor()) &&
          angle - shape.getAngle() < 0.001 &&
          type.equals(shape.getType());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.size, this.color, this.position, this.angle);
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

}
