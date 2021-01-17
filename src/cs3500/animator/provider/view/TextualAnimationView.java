package cs3500.animator.provider.view;

import cs3500.animator.provider.controller.InteractiveController;

import java.io.IOException;

import cs3500.animator.provider.model.keyframes.Keyframe;
import cs3500.animator.provider.model.shapes.Shape;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Class represents a textual animation cs3500.view that is able to display the animation
 * textually.
 */
public class TextualAnimationView implements AnimationView {

  private Appendable out;

  /**
   * Constructs a textual animation cs3500.view with the given appendable.
   *
   * @param out is the appendable given to the textual animation.
   */
  public TextualAnimationView(Appendable out) {
    this.out = out;
  }

  @Override
  public void startAnimation(InteractiveController c, int tickRate) {
    renderAnim(c, tickRate);
  }

  /**
   * Renders the animation to the appendable in the textual animation cs3500.view as a list of
   * motions for each shape.
   *
   * @param c        is the controller
   * @param tickRate is the rate of motion in ticks over seconds.
   */
  private void renderAnim(InteractiveController c, int tickRate) {

    HashMap<String, HashSet<Integer>> keyframePoints = new HashMap<>();
    HashMap<String, StringBuilder> shapeStrings = new HashMap<>();

    int endTime = 0;
    for (String s : c.getShapes().keySet()) {
      HashSet<Integer> shapePoints = new HashSet<>();
      for (Keyframe k : c.getKeyframes()) {
        if (k.getShapeId().equals(s)) {
          if (k.getEndTime() > endTime) {
            endTime = k.getEndTime();
          }
          shapePoints.add(k.getStartTime());
          shapePoints.add(k.getEndTime());
        }
      }

      keyframePoints.put(s, shapePoints);
      shapeStrings.put(s, new StringBuilder());
      shapeStrings.get(s).append("shape ").append(s).append(" ")
          .append(c.getShapes().get(s).getType().toString()).append("\n");
    }

    for (int i = 1; i <= endTime; i++) {
      for (String s : c.getShapes().keySet()) {
        int start = keyframePoints.get(s).stream().min(Integer::compareTo).get();
        int end = keyframePoints.get(s).stream().max(Integer::compareTo).get();
        if (keyframePoints.get(s).contains(i)) {
          if (i == start) {
            shapeStrings.get(s).append("motion ").append(s).append(" ")
                .append(getShapeNumbers(c, tickRate, s))
                .append("   ");
          } else if (i == end) {
            shapeStrings.get(s).append(getShapeNumbers(c, tickRate, s))
                .append("\n").append("\n");
          } else {
            shapeStrings.get(s).append(getShapeNumbers(c, tickRate, s))
                .append("\n").append("motion ")
                .append(s).append(" ").append(getShapeNumbers(c, tickRate, s)).append("   ");
          }
        }
      }
      c.stepTime();
    }
    for (StringBuilder s : shapeStrings.values()) {
      try {
        out.append(s.toString());
      } catch (IOException ioe) {
        throw new IllegalArgumentException("Cannot append");
      }
    }
    if (out.toString().length() > 2) {
      out.toString().substring(0, out.toString().length() - 1);
    }
  }

  /**
   * Gets the row with all of the values in it for one motion for the given shape.
   *
   * @param c         is the controller
   * @param ticks     is the current number of ticks in the animation.
   * @param shapeName is the shape that the motion is being performed on.
   * @return a string with a full row for one motion for the given shape.
   */
  private String getShapeNumbers(InteractiveController c, double ticks, String shapeName) {
    Shape s = c.getShapes().get(shapeName);
    return String.format("%.2f %2d %2d %2d %2d %2d %2d %2d",
        (c.getCurrentTime() / ticks), s.getPosition().getX(), s.getPosition().getY(),
        s.getSize().getX(), s.getSize().getY(), s.getColor().getR(), s.getColor().getG(),
        s.getColor().getB());
  }


  @Override
  public void scaleTickRate(double tickRate) throws RuntimeException {
    throw new RuntimeException("Cannot scale tick rate");
  }

  @Override
  public double getTickRate() {
    throw new UnsupportedOperationException("Cannot get tick rate for textual view");
  }
}
