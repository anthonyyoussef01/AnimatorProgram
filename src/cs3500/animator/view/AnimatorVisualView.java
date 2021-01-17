package cs3500.animator.view;

import cs3500.animator.model.IAnimatorModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * View that represents an AnimatorModel animation as a GUI using Java Swing.
 */
public class AnimatorVisualView extends JFrame implements AnimatorView {

  private int delay;
  private Timer clock;
  private int tick;

  /**
   * Constructor for a visual view that takes a model and creates a GUI representation of the model
   * using Java Swing.
   *
   * @param model the IAnimatorModel to be represented.
   * @throws IllegalArgumentException if the model is null.
   */
  public AnimatorVisualView(IAnimatorModel model)
      throws IllegalArgumentException {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.tick = 0;

    this.setTitle("Excellence");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    AnimatorPanel animatorPanel = new AnimatorPanel(model);
    animatorPanel.setBounds(model.getLeftmostX(), model.getTopmostY(), model.getWidth(),
        model.getHeight());
    animatorPanel.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
    this.add(animatorPanel, BorderLayout.CENTER);

    JScrollPane scroll = new JScrollPane(animatorPanel);
    this.add(scroll);

    ActionListener listener = e -> {
      animatorPanel.setTick(tick);
      animatorPanel.repaint();
      tick++;
    };
    this.clock = new Timer(delay, listener);
  }

  @Override
  public void render() throws IOException {
    setVisible(true);
    this.clock.start();
  }

  @Override
  public void setListeners(ActionListener actionEvent, ItemListener itemEvent) {
    return;
  }

  @Override
  public void setSpeed(double speed) {
    this.delay = (int) (1000.00 / speed);
    this.clock.setDelay(delay);
  }

  @Override
  public double getSpeed() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Timer getClock() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getTick() {
    return this.tick;
  }

  @Override
  public void setTick(int tick) {
    throw new UnsupportedOperationException();
  }

  @Override
  public JPanel getButtonPanel() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setLoop(boolean loop) {
    throw new UnsupportedOperationException();
  }

  @Override
  public JLabel getSpeedField() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean getDiscrete() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setDiscrete(boolean b) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean getFill() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setFill(boolean b) {
    throw new UnsupportedOperationException();
  }

}
