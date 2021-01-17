package cs3500.animator.provider.model.shapes;

/**
 * ShapeType is an enumeration that contains the types of shapes. This is created to differentiate
 * between the different shapes so that they can be properly displayed in the various types of
 * views.
 */
public enum ShapeType {
  Ellipse, Rectangle;

  @Override
  public String toString() {
    if (this == Ellipse) {
      return "Ellipse";
    } else if (this == Rectangle) {
      return "Rectangle";
    } else {
      throw new IllegalStateException("Not a shape");
    }
  }
}
