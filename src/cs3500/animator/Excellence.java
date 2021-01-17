package cs3500.animator;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.IAnimatorController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorModel;
import cs3500.animator.model.ProviderAdapter;
import cs3500.animator.provider.controller.InteractiveController;
import cs3500.animator.provider.view.AnimationView;
import cs3500.animator.provider.view.InteractiveAnimationView;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimatorInteractiveView;
import cs3500.animator.view.AnimatorView;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A main class for an animation, which creates an animation with information provided from the
 * user.
 */
public final class Excellence {

  /**
   * A main class that takes in the arguments from the user and creates the corresponding model and
   * view.
   *
   * @param args the arguments provided by the user.
   */
  public static void main(String[] args) {
    JFrame errorFrame = new JFrame();
    if (args.length < 1) {
      JOptionPane.showMessageDialog(errorFrame,
          "Args are not valid.",
          "error",
          JOptionPane.ERROR_MESSAGE);
    }
    String input = "";
    String viewType = "";
    String outputLocation = "";
    String speed = "";
    for (int i = 0; i < args.length - 1; i += 2) {
      switch (args[i]) {
        case "-in":
          input = args[i + 1];
          break;
        case "-view":
          viewType = args[i + 1];
          break;
        case "-out":
          outputLocation = args[i + 1];
          break;
        case "-speed":
          speed = args[i + 1];
          break;
        default:
          JOptionPane.showMessageDialog(errorFrame,
              "Args are not valid.",
              "error",
              JOptionPane.ERROR_MESSAGE);
          break;
      }
    }
    if (input.equals("")) {
      JOptionPane.showMessageDialog(errorFrame,
          "Args are not valid.",
          "error",
          JOptionPane.ERROR_MESSAGE);
    }
    if (input.equals("")) {
      int result = JOptionPane.showConfirmDialog(null,
          "Do you want to re-enter the input file?", "INPUT FILE IS MISSING",
          JOptionPane.YES_NO_OPTION);
      if (result == JOptionPane.YES_OPTION) {
        input = JOptionPane.showInputDialog(null, "Enter file path");
        if (input.equals("")) {
          JOptionPane.showMessageDialog(errorFrame,
              "Args are not valid.",
              "error",
              JOptionPane.ERROR_MESSAGE);
          System.exit(0);
        }
      }
      if (result == JOptionPane.NO_OPTION) {
        JOptionPane.showMessageDialog(errorFrame,
            "Args are not valid.",
            "error",
            JOptionPane.ERROR_MESSAGE);
        System.exit(0);
      }
    }
    AnimationBuilder<AnimatorModel> builder = AnimatorModel.builder();
    IAnimatorModel model = null;
    AnimationReader reader = new AnimationReader();
    try {
      model = reader.parseFile(new FileReader(input), builder);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(errorFrame,
          "File could not be parsed.",
          "error",
          JOptionPane.ERROR_MESSAGE);
    }
    AnimatorView view;
    if (viewType.equalsIgnoreCase("provider")) {
      view = new AnimatorInteractiveView(model);
      cs3500.animator.provider.model.AnimatorModel providerModel = new ProviderAdapter(model, view);
      AnimationView providerView = new InteractiveAnimationView();
      InteractiveController providerController = new InteractiveController(providerModel,
          providerView);
      int providerSpeed;
      if (speed.equals("")) {
        providerSpeed = 1;
      } else {
        providerSpeed = (int) Double.parseDouble(speed);
      }
      providerView.startAnimation(providerController, providerSpeed);
    } else {
      FileWriter output = null;
      if (outputLocation.equals("")) {
        view = new AnimatorViewCreator().create(viewType, model);
      } else {
        try {
          output = new FileWriter(outputLocation);
        } catch (IOException e) {
          JOptionPane.showMessageDialog(errorFrame,
              "Output could not be initialized.",
              "error",
              JOptionPane.ERROR_MESSAGE);
        }
        view = new AnimatorViewCreator().create(viewType, model, output);
      }
      if (speed.equals("")) {
        view.setSpeed(1);
      } else {
        view.setSpeed(Double.parseDouble(speed));
      }
      IAnimatorController controller = new AnimatorController(model, view);

      try {
        controller.start();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(errorFrame,
            "View could not be rendered.",
            "error",
            JOptionPane.ERROR_MESSAGE);
      }
      if (output != null) {
        try {
          output.close();
        } catch (IOException e) {
          JOptionPane.showMessageDialog(errorFrame,
              "Output could not be closed.",
              "error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }
}
