package cs3500.animator.provider.model.keyframes;

import java.util.Objects;

import cs3500.animator.provider.model.shapes.Shape;

import java.util.Map;

/**
 * Represents a "keyframe" - a smooth transition in object state from one state to another over a
 * time interval. Each keyframe is attached to a particular shape it modifies through its name as a
 * String.
 */
public abstract class Keyframe implements Comparable<Keyframe> {

  private final int startTime;
  private final int endTime;
  private final AnimationType type;
  private final String shapeId;

  /**
   * Constructs a new Keyframe.
   *
   * @param startTime the starting frame of the keyframe
   * @param endTime   the ending frame of the keyframe
   * @param type      the type of keyframe
   * @param shapeId   the name of the shape that this keyframe modifies
   * @throws IllegalArgumentException if the start or end times are negative.
   */
  public Keyframe(int startTime, int endTime, AnimationType type, String shapeId)
      throws IllegalArgumentException {
    if (startTime < 0 || endTime < 0 || startTime > endTime) {
      throw new IllegalArgumentException("Invalid time: " + startTime + " " + endTime);
    }
    this.startTime = startTime;
    this.endTime = endTime;
    this.type = type;
    this.shapeId = shapeId;
  }

  /**
   * Determines if the keyframe's animation is finished on the given frame.
   *
   * @param time the current time in the animation
   * @return whether or not the keyframe has finished
   */
  public boolean isFinished(int time) {
    return time > this.endTime;
  }

  /**
   * Determines if the keyframe's animation has started or not on the given frame.
   *
   * @param time the current time in the animation.
   * @return whether or not the keyframe has started
   */
  public boolean hasStarted(int time) {
    return time >= this.startTime;
  }

  /**
   * Determines if the given keyframe conflicts with this keyframe. Two Keyframe objects are said to
   * overlap if they: <br> - Modify the same Shape<br> - Are the same type of animation<br> - Occur
   * during the same timeframe
   *
   * @param other is the given keyframe being checked for conflicts with this keyframe.
   * @return whether or not the given keyframe conflicts with this keyframe.
   */
  public boolean conflicts(Keyframe other) {
    if (this.startTime == this.endTime && other.startTime == other.endTime) {
      return this.type == other.type && this.startTime == other.endTime &&
          this.shapeId.equals(other.shapeId);
    } else if (other.startTime == other.endTime) {
      return this.type == other.type && other.startTime > this.startTime &&
          other.startTime < this.endTime && this.shapeId.equals(other.shapeId);
    } else if (this.startTime == this.endTime) {
      return this.type == other.type && this.startTime > other.startTime &&
          this.startTime < other.endTime && this.shapeId.equals(other.shapeId);
    }
    return this.type == other.type && this.shapeId.equals(other.shapeId) &&
        ((this.startTime <= other.startTime && this.endTime > other.startTime) ||
            (this.startTime < other.endTime && this.endTime >= other.endTime) ||
            (this.startTime >= other.startTime && this.endTime <= other.endTime));
  }

  /**
   * Gets the start frame of this keyframe.
   *
   * @return the start frame of this keyframe
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Gets the end frame of this keyframe.
   *
   * @return the end frame of this keyframe
   */
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Performs this Keyframe's specific operation on the given Shape, on the given frame.
   *
   * @param time   the current time in the animation.
   * @param shapes the list of shapes in the animation.
   * @throws IllegalArgumentException if the shape trying to be modified does not exist in the list
   *                                  of shapes in the animation.
   */
  public abstract void modifyShape(int time, Map<String, Shape> shapes)
      throws IllegalArgumentException;

  /**
   * Modifies the given shape according to the Keyframe's specific operation on the given time.
   *
   * @param time  the current time in the animation.
   * @param shape the shape being modified.
   */
  public abstract Shape modifyShape(int time, Shape shape);

  /**
   * Compares two Keyframes by their intervals (whichever one starts first is "smaller").
   *
   * @param other the keyframe being compared with this keyframe
   * @return      zero if the given keyframe and this keyframe start at the same time, and a
   *              non-zero value if they have different start times
   */
  public int compareTo(Keyframe other) {
    return Integer.compare(this.startTime, other.startTime);
  }

  /**
   * Gets the name of the shape in this keyframe.
   *
   * @return the name of the shape in this keyframe.
   */
  public String getShapeId() {
    return this.shapeId;
  }

  /**
   * Gets the type of the keyframe.
   *
   * @return the type of the keyframe.
   */
  public AnimationType getType() {
    return this.type;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Keyframe) {
      Keyframe keyframe2 = (Keyframe) o;
      return startTime == keyframe2.getStartTime() &&
          endTime == keyframe2.getEndTime()
          && type.equals(keyframe2.getType())
          && shapeId.equals(keyframe2.getShapeId());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(startTime, endTime, shapeId);
  }
}


