package cs3500.animator.provider.model;

import java.util.Objects;

/**
 * Represents an RGB color object.
 */
public class Color {

  private int r;
  private int g;
  private int b;

  /**
   * Constructs a new Color object with given RGB colors.
   *
   * @param r the red intensity
   * @param g the green intensity
   * @param b the blue intensity
   * @throws IllegalArgumentException if any of r,g,b are not between 0 and 255
   */
  public Color(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Invalid color");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public int getR() {
    return r;
  }

  public void setR(int r) {
    this.r = r;
  }

  public int getG() {
    return g;
  }

  public void setG(int g) {
    this.g = g;
  }

  public int getB() {
    return b;
  }

  public void setB(int b) {
    this.b = b;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Color) {
      Color temp = (Color) other;
      return temp.getR() == this.r && temp.getG() == this.g && temp.getB() == this.b;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.r, this.g, this.b);
  }
}
