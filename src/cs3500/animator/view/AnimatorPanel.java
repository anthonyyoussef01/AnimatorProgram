package cs3500.animator.view;

import cs3500.animator.model.Frame;
import cs3500.animator.model.IAnimatorModel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Represents the JPanel used by the AnimatorVisualView and contains the animation being rendered.
 */
public class AnimatorPanel extends JPanel {

  private final IAnimatorModel model;
  private double tick;
  private boolean fill;

  /**
   * A constructor for an AnimatorPanel, which takes an AnimatorModel and renders its contents.
   */
  public AnimatorPanel(IAnimatorModel model) {
    super();
    this.model = model;
    this.tick = 0;
    this.fill = true;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (String s : this.model.getShapeNames()) {
      if (this.model.getFramesByShapeName(s).get(0).getTick() == tick) {
        setFrameOnTick(g2d, this.model.getFramesByShapeName(s).get(0));
      } else {
        for (int i = 1; i < this.model.getFramesByShapeName(s).size(); i++) {
          if (this.model.getFramesByShapeName(s).get(i).getTick() == tick) {
            setFrameOnTick(g2d, this.model.getFramesByShapeName(s).get(i));
            break;
          }
          if (this.model.getFramesByShapeName(s).get(i).getTick() > tick) {
            setFrameBetweenTick(g2d, this.model.getFramesByShapeName(s).get(i - 1),
                this.model.getFramesByShapeName(s).get(i));
            break;
          }
        }
      }
    }
    //}
  }

  /**
   * Sets the tick (so that the panel can be repainted for each tick).
   *
   * @param tick the double tick is being set to.
   */
  public void setTick(double tick) {
    this.tick = tick;
  }

  /**
   * Gets the boolean for fill (for use by the view and controller).
   *
   * @return true if the shapes in the animation are filled, false if they are outlined.
   */
  public boolean getFill() {
    return this.fill;
  }

  /**
   * Sets the animation as filled or outlined.
   *
   * @param b true if the shapes in the animation are filled, false if they are outlined.
   */
  public void setFill(boolean b) {
    this.fill = b;
  }

  /**
   * Finds the interpolating values for shapes that don't have a frame at that current tick.
   *
   * @param tickA  the most recent tick where the shape has a frame.
   * @param tickB  the next tick where the shape has a frame.
   * @param valueA the most recent value of the measure being found.
   * @param valueB the next value of the measure being found.
   * @return the current value of the measure being found based on the given input.
   */
  private int findCurrentValue(double tickA, double tickB, double valueA, double valueB) {
    return (int) Math.floor((valueA * (tickB - tick) + valueB * (tick - tickA)) / (tickB - tickA));
  }

  /**
   * Draws a frame for a shape if the shape has a frame on the current tick.
   *
   * @param g2d the Graphics2D being drawn in the panel.
   * @param f   the frame to be drawn.
   */
  private void setFrameOnTick(Graphics2D g2d, Frame f) {
    g2d.setColor(new Color(f.getR(), f.getG(), f.getB()));
    switch (f.getShapeType().toUpperCase()) {
      case "RECTANGLE":
        if (this.fill) {
          g2d.fillRect(f.getX(), f.getY(), f.getWidth(), f.getHeight());
        } else {
          g2d.drawRect(f.getX(), f.getY(), f.getWidth(), f.getHeight());
        }
        break;
      case "ELLIPSE":
        if (this.fill) {
          g2d.fillOval(f.getX(), f.getY(), f.getWidth(), f.getHeight());
        } else {
          g2d.drawOval(f.getX(), f.getY(), f.getWidth(), f.getHeight());
        }
        break;
      case "PLUS":
        int firstQuarter = f.getWidth() / 4;
        int thirdQuarter = 3 * firstQuarter;
        int[] xpoints = new int[]{f.getX() + firstQuarter, f.getX() + thirdQuarter, f.getX()
            + thirdQuarter, f.getX() + f.getWidth(), f.getX() + f.getWidth(), f.getX()
            + thirdQuarter, f.getX() + thirdQuarter, f.getX() + firstQuarter, f.getX()
            + firstQuarter, f.getX(), f.getX(), f.getX() + firstQuarter};
        int[] ypoints = new int[]{f.getY(), f.getY(), f.getY() + firstQuarter, f.getY()
            + firstQuarter, f.getY() + thirdQuarter, f.getY() + thirdQuarter,
            f.getY() + f.getHeight(), f.getY() + f.getHeight(), f.getY() + thirdQuarter,
            f.getY() + thirdQuarter, f.getY() + firstQuarter, f.getY() + firstQuarter
        };
        if (this.fill) {
          g2d.fillPolygon(xpoints, ypoints, 12);
        } else {
          g2d.drawPolygon(xpoints, ypoints, 12);
        }
        break;
      default:
        break;
    }
  }

  /**
   * Draws a frame for a shape if the shape if it's values need to be interpolated.
   *
   * @param g2d the Graphics2D being drawn in the panel.
   * @param f1  the most recent frame for the shape currently being drawn.
   * @param f2  the next frame for the shape currently being drawn.
   */
  private void setFrameBetweenTick(Graphics2D g2d, Frame f1, Frame f2) {
    g2d.setColor(new Color(
        findCurrentValue(f1.getTick(), f2.getTick(), f1.getR(), f2.getR()),
        findCurrentValue(f1.getTick(), f2.getTick(), f1.getG(), f2.getG()),
        findCurrentValue(f1.getTick(), f2.getTick(), f1.getB(), f2.getB())));
    switch (f1.getShapeType().toUpperCase()) {
      case "RECTANGLE":
        if (this.fill) {
          g2d.fillRect(findCurrentValue(f1.getTick(), f2.getTick(), f1.getX(), f2.getX()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getY(), f2.getY()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getWidth(), f2.getWidth()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getHeight(), f2.getHeight()));
        } else {
          g2d.drawRect(findCurrentValue(f1.getTick(), f2.getTick(), f1.getX(), f2.getX()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getY(), f2.getY()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getWidth(), f2.getWidth()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getHeight(), f2.getHeight()));
        }
        break;
      case "ELLIPSE":
        if (this.fill) {
          g2d.fillOval(findCurrentValue(f1.getTick(), f2.getTick(), f1.getX(), f2.getX()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getY(), f2.getY()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getWidth(), f2.getWidth()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getHeight(), f2.getHeight()));
        } else {
          g2d.drawOval(findCurrentValue(f1.getTick(), f2.getTick(), f1.getX(), f2.getX()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getY(), f2.getY()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getWidth(), f2.getWidth()),
              findCurrentValue(f1.getTick(), f2.getTick(), f1.getHeight(), f2.getHeight()));
        }
        break;
      case "PLUS":
        int currentX = findCurrentValue(f1.getTick(), f2.getTick(), f1.getX(), f2.getX());
        int currentY = findCurrentValue(f1.getTick(), f2.getTick(), f1.getY(), f2.getY());
        int currentWidth = findCurrentValue(f1.getTick(), f2.getTick(), f1.getWidth(),
            f2.getWidth());
        int currentHeight = findCurrentValue(f1.getTick(), f2.getTick(), f1.getHeight(),
            f2.getHeight());
        int firstQuarter = currentWidth / 4;
        int thirdQuarter = 3 * firstQuarter;
        int[] xpoints = new int[]{currentX + firstQuarter, currentX + thirdQuarter, currentX
            + thirdQuarter, currentX + currentWidth, currentX + currentWidth, currentX
            + thirdQuarter, currentX + thirdQuarter, currentX + firstQuarter, currentX
            + firstQuarter, currentX, currentX, currentX + firstQuarter};
        int[] ypoints = new int[]{currentY, currentY, currentY + firstQuarter, currentY
            + firstQuarter, currentY + thirdQuarter, currentY + thirdQuarter,
            currentY + currentHeight, currentY + currentHeight, currentY + thirdQuarter,
            currentY + thirdQuarter, currentY + firstQuarter, currentY + firstQuarter,
        };
        if (this.fill) {
          g2d.fillPolygon(xpoints, ypoints, 12);
        } else {
          g2d.drawPolygon(xpoints, ypoints, 12);
        }
        break;
      default:
        break;
    }
  }

}





