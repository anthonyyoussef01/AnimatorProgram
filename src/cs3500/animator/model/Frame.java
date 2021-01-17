package cs3500.animator.model;

import java.util.Objects;

/**
 * A class representing the details of a shape during a certain tick.
 */
public class Frame implements IFrame {

  private String shapeName;
  private String shapeType;
  private int x;
  private int y;
  private int width;
  private int height;
  private int r;
  private int g;
  private int b;
  private double tick;

  /**
   * The constructor for a Frame.
   *
   * @param shapeName the name the given shape is referred to as.
   * @param shapeType the type of shape (e.g. "rectangle")
   * @param x         the x coordinate of the position of the shape.
   * @param y         the y coordinate of the position of the shape.
   * @param width     the width of the shape.
   * @param height    the height of the shape.
   * @param r         the r coordinate of the color of the shape.
   * @param g         the g coordinate of the color of the shape.
   * @param b         the b coordinate of the color of the shape.
   * @param tick      the point in time that is being represented.
   */
  public Frame(String shapeName, String shapeType, int x, int y, int width, int height, int r,
      int g, int b, double tick) {
    if (shapeName == null || shapeName.equals("") || shapeType == null || height < 1 || width < 1
        || r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255 || tick < 0) {
      throw new IllegalArgumentException("Frame fields are not valid.");
    }
    if (!isValidShapeType(shapeType)) {
      throw new IllegalArgumentException("ShapeType not supported.");
    }
    if (shapeType.equalsIgnoreCase("plus") && width != height) {
      throw new IllegalArgumentException("Plus dimensions not valid.");
    }
    this.shapeName = shapeName;
    this.shapeType = shapeType;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.r = r;
    this.g = g;
    this.b = b;
    this.tick = tick;
  }

  @Override
  public String toString() {
    return x + " " + y + " " + width + " " + height + " " + r + " " + g + " " + b;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Frame)) {
      return false;
    }

    Frame that = (Frame) obj;
    return this.shapeName == that.shapeName && this.shapeType == that.shapeType && this.x == that.x
        && this.y == that.y && this.width == that.width && this.height == that.height
        && this.r == that.r && this.g == that.g && this.b == that.b && this.tick == that.tick;
  }

  @Override
  public int hashCode() {
    return Objects.hash(shapeName, shapeType, x, y, width, height, r, g, b);
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public String getShapeType() {
    return this.shapeType;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getG() {
    return this.g;
  }

  @Override
  public int getB() {
    return this.b;
  }


  @Override
  public double getTick() {
    return this.tick;
  }

  /**
   * Determines if a shapeType is supported by the animator.
   *
   * @param s the shapeType being checked.
   * @return true if the shapeType is supported, false otherwise.
   */
  private boolean isValidShapeType(String s) {
    return (s.equalsIgnoreCase("rectangle") || s.equalsIgnoreCase("ellipse")
        || s.equalsIgnoreCase("plus"));
  }

}
