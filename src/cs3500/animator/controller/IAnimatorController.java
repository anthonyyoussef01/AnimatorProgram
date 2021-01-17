package cs3500.animator.controller;

import java.io.IOException;

/**
 * A controller interface for an Animator, which listens to the user's interactions with the view
 * and instructs the view to respond accordingly.
 */
public interface IAnimatorController {

  /**
   * Renders the view and sets listeners if needed (for an interactive view).
   *
   * @throws IOException if the view cannot be rendered.
   */
  void start() throws IOException;

}
