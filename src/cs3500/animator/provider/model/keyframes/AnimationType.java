package cs3500.animator.provider.model.keyframes;

/**
 * Represents one of the types of (smooth) animation available - moving, scaling, changing color,
 * and rotation. We use this to verify that two different animations are not overlapping with each
 * other, primarily - because it's totally fine to have a color and rotation happening to the same
 * shape at the same time, but two conflicting movement animations would really mess things up. The
 * reason we use this instead of double dispatch is to allow for different types of keyframes that
 * do unusual things (e.g. a keyframe which moves the object to the desired spot in "steps" instead
 * of smoothly, but is still a type of "Move" animation). This means that we don't have to add extra
 * methods to the various keyframe classes to check for conflicting animations.
 */
public enum AnimationType {
  Move,
  Scale,
  Color,
  Rotate,
}
