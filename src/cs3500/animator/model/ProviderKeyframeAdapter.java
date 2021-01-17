package cs3500.animator.model;

import cs3500.animator.provider.model.keyframes.AnimationType;
import cs3500.animator.provider.model.keyframes.Keyframe;
import cs3500.animator.provider.model.shapes.ConcreteShape;
import cs3500.animator.provider.model.shapes.Shape;
import java.util.Map;

/**
 * A class that extends Keyframe so that it can be instantiated in our model adaptation. Keyframe
 * implements comparable, so it cannot be instantiated directly, so the ProviderKeyframeAdapter
 * constructor is used to add keyframes to the model in the model adapter.
 */
public class ProviderKeyframeAdapter extends Keyframe {

  /**
   * Constructs a new Keyframe.
   *
   * @param startTime the starting frame of the keyframe
   * @param endTime   the ending frame of the keyframe
   * @param type      the type of keyframe
   * @param shapeId   the name of the shape that this keyframe modifies
   * @throws IllegalArgumentException if the start or end times are negative.
   */
  public ProviderKeyframeAdapter(int startTime, int endTime,
      AnimationType type, String shapeId)
      throws IllegalArgumentException {
    super(startTime, endTime, type, shapeId);
  }

  @Override
  public void modifyShape(int time, Map<String, Shape> shapes) throws IllegalArgumentException {
    Shape s = shapes.get(this.getShapeId());
    if (s == null) {
      throw new IllegalArgumentException("Shape does not exist");
    }
    shapes.put(getShapeId(), modifyShape(time, s));
  }

  @Override
  public Shape modifyShape(int time, Shape shape) {
    return new ConcreteShape(shape.getSize(), shape.getPosition(), shape.getColor(),
        shape.getType());
  }
}
