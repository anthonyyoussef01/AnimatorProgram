package cs3500.animator;

import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.view.AnimatorInteractiveView;
import cs3500.animator.view.AnimatorSVGView;
import cs3500.animator.view.AnimatorTextualView;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.AnimatorVisualView;

/**
 * Factory class for an AnimatorView.
 */
public class AnimatorViewCreator {

  /**
   * Creates an instance of an AnimatorView given a model.
   *
   * @param type  the type of view to be created (currently supports visual views, which represent
   *              the animation in Java Swing, textual views, which represent the animation as text,
   *              and SVG views, which represent the animation in SVG code)
   * @param model the AnimatorModel of of animation to be created.
   * @return the view that corresponds to the passed AnimatorViewType.
   */
  public static AnimatorView create(String type, IAnimatorModel model) {
    if (type.equalsIgnoreCase(AnimatorViewType.TEXT.toString())) {
      return new AnimatorTextualView(model);
    } else if (type.equalsIgnoreCase(AnimatorViewType.SVG.toString())) {
      return new AnimatorSVGView(model);
    } else if (type.equalsIgnoreCase(AnimatorViewType.VISUAL.toString())) {
      return new AnimatorVisualView(model);
    } else if (type.equalsIgnoreCase(AnimatorViewType.INTERACTIVE.toString())) {
      return new AnimatorInteractiveView(model);
    } else {
      throw new IllegalArgumentException("Not a valid view type.");
    }
  }

  /**
   * Creates an instance of an AnimatorView given a model and an Appendable.
   *
   * @param type  the type of view to be created (currently supports visual views, which represent
   *              the animation in Java Swing, textual views, which represent the animation as text,
   *              and SVG views, which represent the animation in SVG code)
   * @param model the AnimatorModel of of animation to be created.
   * @return the view that corresponds to the passed AnimatorViewType.
   */
  public static AnimatorView create(String type, IAnimatorModel model, Appendable ap) {
    if (type.equalsIgnoreCase(AnimatorViewType.TEXT.toString())) {
      return new AnimatorTextualView(model, ap);
    } else if (type.equalsIgnoreCase(AnimatorViewType.SVG.toString())) {
      return new AnimatorSVGView(model, ap);
    } else if (type.equalsIgnoreCase(AnimatorViewType.VISUAL.toString())) {
      return new AnimatorVisualView(model);
    } else if (type.equalsIgnoreCase(AnimatorViewType.INTERACTIVE.toString())) {
      return new AnimatorInteractiveView(model);
    } else {
      throw new IllegalArgumentException("Not a valid view type.");
    }
  }

  /**
   * An enum representing the type of AnimatorView to be created (currently supports visual views,
   * which represent the animation in Java Swing, textual views, which represent the animation as
   * text, and SVG views, which represent the animation in SVG code).
   */
  public enum AnimatorViewType {
    TEXT, SVG, VISUAL, INTERACTIVE;

    @Override
    public String toString() {
      switch (this) {
        case TEXT:
          return "text";
        case SVG:
          return "svg";
        case VISUAL:
          return "visual";
        case INTERACTIVE:
          return "interactive";
        default:
          throw new IllegalArgumentException("Must use an existing view type.");
      }
    }
  }
}
