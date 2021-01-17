package cs3500.animator.view;

import cs3500.animator.model.Frame;
import cs3500.animator.model.IAnimatorModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * View that represents an AnimatorModel animation as a GUI using Java Swing with interactive
 * features, allowing the user to control the speed and timing of the animation.
 */
public class AnimatorInteractiveView extends JFrame implements AnimatorView {

  private double speed;
  private int delay;
  private IAnimatorModel model;
  private Timer clock;
  private int tick;
  private AnimatorPanel animatorPanel;
  private JPanel buttonPanel;
  private boolean loop;
  private boolean discrete;
  private JLabel speedField;
  private JButton startButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JCheckBox loopBox;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;
  private JButton discreteButton;
  private JButton continuousButton;
  private JButton fillButton;
  private JButton outlineButton;

  /**
   * Constructor for a visual view that takes a model and creates a GUI representation of the model
   * using Java Swing, with buttons to start, stop, restart, loop and change the speed of the
   * animation.
   *
   * @param model the IAnimatorModel to be represented.
   * @throws IllegalArgumentException if the model is null.
   */
  public AnimatorInteractiveView(IAnimatorModel model)
      throws IllegalArgumentException {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
    this.tick = 0;
    this.loop = false;
    this.discrete = false;

    this.setTitle("Excellence");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    this.animatorPanel = new AnimatorPanel(model);
    animatorPanel.setBounds(model.getLeftmostX(), model.getTopmostY(), model.getWidth(),
        model.getHeight());
    animatorPanel.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
    animatorPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    this.buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(model.getWidth(), 150));
    buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    this.startButton = new JButton("Start");
    startButton.setActionCommand("Start Animation");
    buttonPanel.add(startButton);

    this.pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause Animation");
    pauseButton.setEnabled(false);
    buttonPanel.add(pauseButton);

    this.restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart Animation");
    restartButton.setEnabled(false);
    buttonPanel.add(restartButton);

    this.loopBox = new JCheckBox("Loop");
    buttonPanel.add(loopBox);

    this.speedField = new JLabel("speed: " + speed + " fps.");
    speedField.setForeground(Color.black);
    speedField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    speedField.setBorder(BorderFactory.createCompoundBorder(speedField.getBorder(),
        BorderFactory.createEmptyBorder(1, 5, 1, 5)));
    buttonPanel.add(speedField);

    this.increaseSpeedButton = new JButton("Increase Speed");
    increaseSpeedButton.setActionCommand("Increase Animation Speed");
    increaseSpeedButton.setEnabled(false);
    buttonPanel.add(increaseSpeedButton);

    this.decreaseSpeedButton = new JButton("Decrease Speed");
    decreaseSpeedButton.setActionCommand("Decrease Animation Speed");
    decreaseSpeedButton.setEnabled(false);
    buttonPanel.add(decreaseSpeedButton);

    this.discreteButton = new JButton("Discrete");
    discreteButton.setActionCommand("Make Animation Discrete");
    discreteButton.setEnabled(false);
    buttonPanel.add(discreteButton);

    this.continuousButton = new JButton("Continuous");
    continuousButton.setActionCommand("Make Animation Continuous");
    continuousButton.setEnabled(false);
    buttonPanel.add(continuousButton);

    this.fillButton = new JButton("Fill");
    fillButton.setActionCommand("Fill Animation");
    fillButton.setEnabled(false);
    buttonPanel.add(fillButton);

    this.outlineButton = new JButton("Outline");
    outlineButton.setActionCommand("Outline Animation");
    outlineButton.setEnabled(false);
    buttonPanel.add(outlineButton);

    JScrollPane animatorScroll = new JScrollPane(animatorPanel);
    this.add(animatorScroll, BorderLayout.CENTER);

    JScrollPane buttonScroll = new JScrollPane(buttonPanel);
    this.add(buttonScroll, BorderLayout.SOUTH);

    this.pack();

    ActionListener listener = e -> {
      animatorPanel.setTick(tick);
      if (!discrete || tickHasBeginningOrEndOfMotion(tick)) {
        animatorPanel.repaint();
      }
      tick++;
      List<Frame> modelFrames = this.model.getFrames();
      Collections.sort(modelFrames, Comparator.comparingDouble(o -> o.getTick()));
      if (loop && tick == modelFrames.get(modelFrames.size() - 1).getTick()) {
        clock.restart();
        tick = 0;
      }
      if (slomoStartTick(tick)) {
        setSpeed(getSpeed() / 4);
      }
      if (slomoEndTick(tick)) {
        setSpeed(getSpeed() * 4);
      }
    };
    this.clock = new Timer(delay, listener);
  }

  @Override
  public void render() throws IOException {
    setVisible(true);
  }

  @Override
  public void setListeners(ActionListener actionEvent, ItemListener itemStateChange) {
    this.startButton.addActionListener(actionEvent);
    this.pauseButton.addActionListener(actionEvent);
    this.restartButton.addActionListener(actionEvent);
    this.loopBox.addItemListener(itemStateChange);
    this.increaseSpeedButton.addActionListener(actionEvent);
    this.decreaseSpeedButton.addActionListener(actionEvent);
    this.discreteButton.addActionListener(actionEvent);
    this.continuousButton.addActionListener(actionEvent);
    this.fillButton.addActionListener(actionEvent);
    this.outlineButton.addActionListener(actionEvent);
  }

  @Override
  public void setSpeed(double speed) {
    this.speed = speed;
    this.delay = (int) (1000.00 / this.speed);
    this.clock.setDelay(this.delay);
    this.speedField.setText("speed: " + this.speed + " frames/second.");
  }

  @Override
  public double getSpeed() {
    return this.speed;
  }

  @Override
  public Timer getClock() {
    return this.clock;
  }

  @Override
  public int getTick() {
    return this.tick;
  }

  @Override
  public void setTick(int tick) {
    this.tick = tick;
  }

  @Override
  public JPanel getButtonPanel() {
    return this.buttonPanel;
  }

  @Override
  public void setLoop(boolean loop) {
    this.loop = loop;
  }

  @Override
  public JLabel getSpeedField() {
    return this.speedField;
  }

  @Override
  public boolean getDiscrete() {
    return this.discrete;
  }

  @Override
  public void setDiscrete(boolean b) {
    this.discrete = b;
  }

  @Override
  public boolean getFill() {
    return this.animatorPanel.getFill();
  }

  @Override
  public void setFill(boolean b) {
    this.animatorPanel.setFill(b);
  }

  /**
   * Determines if the tick has the beginning or end of any motion (used for discrete animation).
   * @param t the tick being checked.
   * @return true if the tick contains the beginning or end of a motion, false otherwise.
   */
  private boolean tickHasBeginningOrEndOfMotion(double t) {
    for (String s : this.model.getShapeNames()) {
      for (Frame f : this.model.getFramesByShapeName(s)) {
        if (f.getTick() == tick) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Determines if the tick is the beginning of a slomo interval.
   * @param t the tick being checked.
   * @return true if the first int in any int[] in the model's slomo intervals are equal to the
   *         current tick, false otherwise.
   */
  private boolean slomoStartTick(double t) {
    for (int[] i : this.model.getSlomo()) {
      if (t == i[0]) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determines if the tick is the end of a slomo interval.
   * @param t the tick being checked.
   * @return true if the second int in any int[] in the model's slomo intervals are equal to the
   *         current tick, false otherwise.
   */
  private boolean slomoEndTick(double t) {
    for (int[] i : this.model.getSlomo()) {
      if (t == i[1]) {
        return true;
      }
    }
    return false;
  }

}



