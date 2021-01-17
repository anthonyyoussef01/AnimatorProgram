package cs3500.animator.provider.view;


import cs3500.animator.provider.controller.InteractiveController;

/**
 * Represents a generic animation view that operates on models.
 */
public interface AnimationView {

  /**
   * Starts the animation with the given controller and tick rate.
   *
   * @param c        is the controller
   * @param tickRate is the rate at which the motions occur in ticks per second.
   */
  public void startAnimation(InteractiveController c, int tickRate);

  /**
   * Changes the tick rate of the animation.
   *
   * @param tickRate the scale to change the current tick rate by
   */
  public void scaleTickRate(double tickRate) throws RuntimeException;

  /**
   * Gets the tick rate of the view.
   *
   * @return the tick rate of the view.
   */
  public double getTickRate();
}
