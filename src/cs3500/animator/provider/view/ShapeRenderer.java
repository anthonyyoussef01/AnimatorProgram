package cs3500.animator.provider.view;

import cs3500.animator.provider.controller.InteractiveController;
import cs3500.animator.provider.model.shapes.Shape;
import cs3500.animator.provider.model.shapes.ShapeType;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Represents a class that renders the shapes in a model.
 */
public class ShapeRenderer extends JPanel {

  private static final int X_SIZE = 800;
  private static final int Y_SIZE = 500;
  // We stretch the canvas to at least these dimensions so that the window is covered by the canvas
  // This won't affect the way animations render.

  private InteractiveController c;
  private int top;
  private int left;
  private int width;
  private int height;

  /**
   * Creates a shape renderer with a cs3500.model, a vertical and horizontal offset.
   *
   * @param c    is the controller
   * @param top  is the vertical offset for the canvas.
   * @param left is the horizontal offset for the canvas.
   */
  public ShapeRenderer(InteractiveController c, int top, int left, int w, int h) {
    this.c = c;
    this.top = top;
    this.left = left;
    this.width = w;
    this.height = h;
    this.setMinimumSize(new Dimension(X_SIZE, Y_SIZE));
    this.setPreferredSize(new Dimension(w, h));
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.clearRect(0, 0, Math.max(X_SIZE, width), Math.max(Y_SIZE, height));
    Shape s;
    for (String id : c.getShapeOrder()) {
      s = c.getShapes().get(id);
      g2.setColor(new Color(s.getColor().getR(),
          s.getColor().getG(),
          s.getColor().getB()));
      if (s.getType().equals(ShapeType.Ellipse)) {
        g2.fillOval(s.getPosition().getX() - left, s.getPosition().getY() - top,
            s.getSize().getX(), s.getSize().getY());
      } else if (s.getType().equals(ShapeType.Rectangle)) {
        g2.fillRect(s.getPosition().getX() - left, s.getPosition().getY() - top,
            s.getSize().getX(), s.getSize().getY());
      }
    }
    c.stepTime();
  }
}

