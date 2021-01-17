package cs3500.animator.controller;

import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.view.AnimatorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.JButton;


/**
 * An instance of IAnimatorController that takes an IAnimatorModel and an AnimatorView. It also
 * tracks actions performed by the user and item states changed by the user, and responds to those
 * actions accordingly via the view.
 */
public class AnimatorController implements IAnimatorController, ActionListener, ItemListener {

  private AnimatorView view;

  /**
   * A constructor for a controller that takes a model and a view.
   *
   * @param model the IAnimatorModel being created.
   * @param view  the AnimatorView, which is the visual representation of the IAnimatorModel.
   */
  public AnimatorController(IAnimatorModel model, AnimatorView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Invalid model or view.");
    }
    this.view = view;
  }

  @Override
  public void start() throws IOException {
    this.view.setListeners(this, this);
    try {
      this.view.render();
    } catch (IOException e) {
      throw new IllegalArgumentException("View could not be rendered.");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Start Animation":
        this.view.getClock().start();
        this.view.getButtonPanel().remove(0);
        this.view.getSpeedField().setText("speed: " + this.view.getSpeed()
            + " fps.");
        JButton resumeButton = new JButton("Resume");
        resumeButton.setActionCommand("Resume Animation");
        resumeButton.addActionListener(this);
        this.view.getButtonPanel().add(resumeButton, 0);
        this.view.getButtonPanel().updateUI();
        this.view.getButtonPanel().getComponent(0).setEnabled(false);
        this.view.getButtonPanel().getComponent(1).setEnabled(true);
        this.view.getButtonPanel().getComponent(2).setEnabled(true);
        this.view.getButtonPanel().getComponent(5).setEnabled(true);
        this.view.getButtonPanel().getComponent(6).setEnabled(true);
        this.view.getButtonPanel().getComponent(7).setEnabled(true);
        this.view.getButtonPanel().getComponent(10).setEnabled(true);
        break;
      case "Resume Animation":
        this.view.getClock().restart();
        this.view.getButtonPanel().getComponent(0).setEnabled(false);
        this.view.getButtonPanel().getComponent(1).setEnabled(true);
        this.view.getSpeedField().setText("speed: " + this.view.getSpeed()
            + " fps.");
        this.view.getButtonPanel().getComponent(5).setEnabled(true);
        this.view.getButtonPanel().getComponent(6).setEnabled(true);
        break;
      case "Pause Animation":
        this.view.getClock().stop();
        this.view.getButtonPanel().getComponent(0).setEnabled(true);
        this.view.getButtonPanel().getComponent(1).setEnabled(false);
        this.view.getSpeedField().setText("speed: 0.0 fps.");
        this.view.getButtonPanel().getComponent(5).setEnabled(false);
        this.view.getButtonPanel().getComponent(6).setEnabled(false);
        break;
      case "Restart Animation":
        this.view.getClock().restart();
        this.view.setTick(0);
        this.view.getButtonPanel().getComponent(0).setEnabled(false);
        this.view.getButtonPanel().getComponent(1).setEnabled(true);
        break;
      case "Increase Animation Speed":
        this.view.setSpeed(this.view.getSpeed() + 1);
        break;
      case "Decrease Animation Speed":
        if (this.view.getSpeed() - 1 > 0.00001) {
          this.view.setSpeed(this.view.getSpeed() - 1);
        }
        break;
      case "Make Animation Discrete":
        this.view.setDiscrete(true);
        this.view.getButtonPanel().getComponent(7).setEnabled(false);
        this.view.getButtonPanel().getComponent(8).setEnabled(true);
        break;
      case "Make Animation Continuous":
        this.view.setDiscrete(false);
        this.view.getButtonPanel().getComponent(7).setEnabled(true);
        this.view.getButtonPanel().getComponent(8).setEnabled(false);
        break;
      case "Fill Animation":
        this.view.setFill(true);
        this.view.getButtonPanel().getComponent(9).setEnabled(false);
        this.view.getButtonPanel().getComponent(10).setEnabled(true);
        break;
      case "Outline Animation":
        this.view.setFill(false);
        this.view.getButtonPanel().getComponent(9).setEnabled(true);
        this.view.getButtonPanel().getComponent(10).setEnabled(false);
        break;
      default:
        throw new IllegalArgumentException("Not a valid ActionEvent.");
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      this.view.setLoop(true);
    } else {
      this.view.setLoop(false);
    }
  }
}
