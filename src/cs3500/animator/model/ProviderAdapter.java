package cs3500.animator.model;

import cs3500.animator.provider.model.AnimatorModel;
import cs3500.animator.provider.model.Color;
import cs3500.animator.provider.model.Vector2;
import cs3500.animator.provider.model.keyframes.AnimationType;
import cs3500.animator.provider.model.keyframes.Keyframe;
import cs3500.animator.provider.model.shapes.ConcreteShape;
import cs3500.animator.provider.model.shapes.Shape;
import cs3500.animator.provider.model.shapes.ShapeType;
import cs3500.animator.view.AnimatorView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * An adapter class to adapt our code to the provider's model.
 */
public class ProviderAdapter implements AnimatorModel<Shape, ProviderKeyframeAdapter> {

  private IAnimatorModel model;
  private AnimatorView view;
  private Map<String, Shape> shapes;
  private List<Keyframe> keyframes;

  /**
   * A constructor for the adapter. Because our provider's model had functionality that we have in
   * both our model and our view, the constructor takes a model and view, and initializes the
   * private fields that are used to align our additional objects to our additional objects.
   *
   * @param model an IAnimatorModel to adapt the user's code to.
   * @param view  an AnimatorView to adapt the user's code to.
   */
  public ProviderAdapter(IAnimatorModel model, AnimatorView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("IAnimatorModel cannot be null");
    }
    this.model = model;
    this.view = view;
    this.shapes = new LinkedHashMap<>();
    for (String sID : model.getShapeNames()) {
      ShapeType type = null;
      if (model.getShapeNamesAndTypes().get(sID).equalsIgnoreCase("rectangle")) {
        type = ShapeType.Rectangle;
      }
      if (model.getShapeNamesAndTypes().get(sID).equalsIgnoreCase("ellipse")) {
        type = ShapeType.Ellipse;
      }
      Shape s = new ConcreteShape(new Vector2(model.getFramesByShapeName(sID).get(0).getWidth(),
          model.getFramesByShapeName(sID).get(0).getHeight()),
          new Vector2(model.getFramesByShapeName(sID).get(0).getX(),
              model.getFramesByShapeName(sID).get(0).getY()),
          new Color(model.getFramesByShapeName(sID).get(0).getR(),
              model.getFramesByShapeName(sID).get(0).getG(),
              model.getFramesByShapeName(sID).get(0).getB()), type);
      shapes.put(sID, s);
    }
    this.keyframes = new ArrayList<>();
    for (String s : model.getShapeNames()) {
      for (int i = 0; i < model.getFramesByShapeName(s).size() - 1; i += 2) {
        AnimationType type = null;
        if (model.getFramesByShapeName(s).get(i).getX()
            != model.getFramesByShapeName(s).get(i + 1).getX()
            || model.getFramesByShapeName(s).get(i).getY()
            != model.getFramesByShapeName(s).get(i + 1).getY()) {
          type = AnimationType.Move;
        }
        if (model.getFramesByShapeName(s).get(i).getWidth()
            != model.getFramesByShapeName(s).get(i + 1).getWidth()
            || model.getFramesByShapeName(s).get(i).getHeight()
            != model.getFramesByShapeName(s).get(i + 1).getHeight()) {
          type = AnimationType.Scale;
        }
        if (model.getFramesByShapeName(s).get(i).getR()
            != model.getFramesByShapeName(s).get(i + 1).getR()
            || model.getFramesByShapeName(s).get(i).getG()
            != model.getFramesByShapeName(s).get(i + 1).getG()
            || model.getFramesByShapeName(s).get(i).getB()
            != model.getFramesByShapeName(s).get(i + 1).getB()) {
          type = AnimationType.Color;
        }
        Keyframe k = new ProviderKeyframeAdapter(
            (int) model.getFramesByShapeName(s).get(i).getTick(),
            (int) model.getFramesByShapeName(s).get(i + 1).getTick(), type,
            model.getFramesByShapeName(s).get(i).getShapeName());
        this.keyframes.add(k);
      }
    }
  }

  @Override
  public boolean hasStarted() {
    try {
      return this.view.getTick() > 0;
    } catch (UnsupportedOperationException e) {
      return false;
    }
  }

  @Override
  public void start() {
    return;
  }

  @Override
  public void stop() throws IllegalStateException {
    return;
  }

  @Override
  public Map getShapes() throws IllegalArgumentException {
    return this.shapes;
  }

  @Override
  public List getKeyframes() {
    return this.keyframes;
  }

  @Override
  public void incrementTime() throws IllegalStateException {
    try {
      this.view.setTick(this.view.getTick() + 1);
    } catch (UnsupportedOperationException e) {
      return;
    }
  }

  @Override
  public void addKeyframe(ProviderKeyframeAdapter o)
      throws IllegalArgumentException, IllegalStateException {
    int t1 = o.getStartTime();
    int t2 = o.getEndTime();
    String shapeType = o.getType().toString();
    String shapeName = o.getShapeId();
    Shape s = this.shapes.get(shapeName);
    int width = s.getSize().getX();
    int height = s.getSize().getY();
    int x = s.getPosition().getX();
    int y = s.getPosition().getY();
    int r = s.getColor().getR();
    int g = s.getColor().getG();
    int b = s.getColor().getB();
    this.model.addFrame(shapeName, shapeType, x, y, height, width, r, g, b, t1);
    this.model.addFrame(shapeName, shapeType, x, y, height, width, r, g, b, t2);
    this.keyframes.add(o);
  }

  @Override
  public void addShape(String name, Shape shape) throws IllegalArgumentException {
    this.model.addShape(name, shape.getType().toString());
    this.shapes.put(name, shape);
  }

  @Override
  public int getCurrentTime() throws IllegalStateException {
    try {
      return this.view.getTick();
    } catch (UnsupportedOperationException e) {
      return 0;
    }
  }

  @Override
  public int lastTick() {
    return (int) this.model.getFrames().get(this.model.getFrames().size() - 1).getTick();
  }

  @Override
  public int getWidth() {
    return this.model.getWidth();
  }

  @Override
  public int getHeight() {
    return this.model.getHeight();
  }

  @Override
  public int getX() {
    return this.model.getLeftmostX();
  }

  @Override
  public int getY() {
    return this.model.getTopmostY();
  }

  @Override
  public List<String> getShapeOrder() {
    return this.model.getShapeNames();
  }
}
