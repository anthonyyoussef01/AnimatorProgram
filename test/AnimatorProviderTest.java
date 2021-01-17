import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ProviderAdapter;
import cs3500.animator.provider.controller.InteractiveController;
import cs3500.animator.provider.view.AnimationView;
import cs3500.animator.provider.view.TextualAnimationView;
import cs3500.animator.view.AnimatorTextualView;
import org.junit.Test;

/**
 * A class with tests for an AnimatorController.
 */
public class AnimatorProviderTest {

  @Test
  public void textualGoProvider() {
    AnimatorModel am = new AnimatorModel(300, 300,
        0, 0);
    am.addFrame("r", "rectangle", 25, 25, 100,
        100, 100, 100, 100, 1);
    am.addFrame("r", "rectangle", 25, 30, 100,
        100, 100, 100, 100, 2);
    am.addFrame("e", "ellipse", 50, 50, 33,
        42, 75, 215, 115, 1);
    am.addFrame("e", "ellipse", 50, 50, 33,
        45, 75, 215, 115, 2);
    am.addFrame("r", "rectangle", 25, 35, 100,
        100, 100, 100, 100, 3);
    am.addFrame("e", "ellipse", 50, 50, 33,
        50, 75, 215, 115, 3);
    StringBuilder output1 = new StringBuilder();
    AnimatorTextualView atv = new AnimatorTextualView(am, output1);
    cs3500.animator.provider.model.AnimatorModel providerModel = new ProviderAdapter(am, atv);
    StringBuilder output2 = new StringBuilder();
    AnimationView providerView = new TextualAnimationView(output2);
    InteractiveController providerController = new InteractiveController(providerModel,
        providerView);
    providerView.startAnimation(providerController, 5);
    assertEquals("shape r Rectangle\n"
        + "motion r 0.00 25 25 100 100 100 100 100   0.00 25 25 100 100 100 100 100\n"
        + "\n"
        + "shape e Ellipse\n"
        + "motion e 0.00 50 50 33 42 75 215 115   0.00 50 50 33 42 75 215 115\n\n",
        output2.toString());
  }

}

