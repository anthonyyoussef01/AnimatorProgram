package cs3500.animator.provider.model;

import java.util.Objects;

/**
 * Represents a 2D vector with x and y components.
 */
public class Vector2 {

  private int x;
  private int y;

  /**
   * Constructs a new 2D vector with x and y components.
   *
   * @param x the x component
   * @param y the y component
   */
  public Vector2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Vector2) {
      Vector2 vector2 = (Vector2) o;
      return x == vector2.getX() &&
          y == vector2.getY();
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

}
