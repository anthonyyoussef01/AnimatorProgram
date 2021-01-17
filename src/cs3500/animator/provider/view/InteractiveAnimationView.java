package cs3500.animator.provider.view;

import cs3500.animator.provider.controller.InteractiveController;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Timer;

/**
 * Represents an AnimationView that has user-interactive controls. Renders an animation and allows
 * for users to pause, restart, loop, and change the speed of the animation.
 */
public class InteractiveAnimationView
    implements AnimationView {

  private static final int X_SIZE = 800;
  private static final int Y_SIZE = 500;

  private ShapeRenderer r;
  private double tickRate;
  private InteractiveController c;
  private Timer t;

  /**
   * Constructs a new InteractiveAnimationView.
   */
  public InteractiveAnimationView() {
    this.tickRate = 0;
    this.c = null;
  }

  @Override
  public void startAnimation(InteractiveController c, int tickRate) {
    this.tickRate = tickRate;
    this.c = c;
    JPanel buttons = new JPanel();
    JButton pause = new JButton("Pause/Play");
    pause.addActionListener(c.pauseAction());
    JButton restart = new JButton("Restart");
    restart.addActionListener(c.restartAction());
    JButton looping = new JButton("Loop");
    looping.addActionListener(c.loopAction());
    JButton speedUp = new JButton("Slow Down");
    speedUp.addActionListener(c.speedDownAction());
    JButton speedDown = new JButton("Speed Up");
    speedDown.addActionListener(c.speedUpAction());
    buttons.add(pause);
    buttons.add(restart);
    buttons.add(looping);
    buttons.add(speedUp);
    buttons.add(speedDown);
    buttons.setMaximumSize(new Dimension(X_SIZE, 100));

    JFrame j = new JFrame();
    JPanel p = new JPanel();
    p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
    p.setPreferredSize(new Dimension(X_SIZE, Y_SIZE + 100));
    ScrollPane scroll = new ScrollPane();
    p.setFocusable(true);
    p.addMouseListener(c.mouseDownAction());
    scroll.addMouseListener(c.mouseDownAction());
    scroll.setPreferredSize(new Dimension(X_SIZE, Y_SIZE));
    this.r = new ShapeRenderer(c, c.getOffset().getY(), c.getOffset().getX(), c.getBounds().getX(),
        c.getBounds().getY());
    scroll.add(r);
    p.add(scroll);
    p.add(buttons);
    j.add(p);
    j.setMinimumSize(new Dimension(X_SIZE, Y_SIZE));
    p.addKeyListener(c.keyDownAction());
    j.setVisible(true);

    t = new Timer(1000 / tickRate, new RepaintListener(r));
    t.start();
  }

  @Override
  public void scaleTickRate(double tickRate) throws RuntimeException {
    this.tickRate *= tickRate;
    t.stop();
    t = new Timer((int) (1000 / this.tickRate), new RepaintListener(r));
    t.start();
  }

  /**
   * A simple ActionListener used to repaint the canvas given a particular canvas to repaint.
   */
  private static class RepaintListener implements ActionListener {

    private ShapeRenderer r;

    private RepaintListener(ShapeRenderer r) {
      this.r = r;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      r.repaint();
    }
  }

  @Override
  public double getTickRate() {
    return this.tickRate;
  }
}
