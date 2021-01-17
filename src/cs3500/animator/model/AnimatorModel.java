package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing an AnimatorModel, which contains the Frames, shapes and canvas details of an
 * animation.
 */
public class AnimatorModel implements IAnimatorModel {

  private List<Frame> frames;
  private int width;
  private int height;
  private int leftmostX;
  private int topmostY;
  private Map<String, String> shapeNamesAndTypes;
  private List<int[]> slomo;

  /**
   * A default constructor for an AnimatorModel.
   */
  public AnimatorModel() {
    this.frames = new ArrayList<Frame>();
    this.width = 2000;
    this.height = 1500;
    this.leftmostX = 0;
    this.topmostY = 0;
    this.shapeNamesAndTypes = new LinkedHashMap<String, String>();
    this.slomo = new ArrayList<>();
  }

  /**
   * A constructor for an AnimatorModel that takes all the fields as arguments.
   */
  public AnimatorModel(int width, int height, int leftmostX,
      int topmostY) {
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("AnimatorModel fields are not valid.");
    }
    this.frames = new ArrayList<Frame>();
    this.width = width;
    this.height = height;
    this.leftmostX = leftmostX;
    this.topmostY = topmostY;
    this.shapeNamesAndTypes = new LinkedHashMap<String, String>();
    this.slomo = new ArrayList<>();
  }


  @Override
  public String toString() {
    String result = "";
    for (String s : this.getShapeNames()) {
      result += "shape " + s + " " + this.shapeNamesAndTypes.get(s) + "\n";
      for (int x = 0; x < (getFramesByShapeName(s).size() - 1); x++) {
        result +=
            "motion " + s + " " + getFramesByShapeName(s).get(x).getTick() + " "
                + getFramesByShapeName(s).get(x).toString() + "   " + getFramesByShapeName(s)
                .get(x + 1).getTick() + " " + getFramesByShapeName(s).get(x + 1).toString() + "\n";
      }
      result += "\n";
    }
    return result;
  }

  @Override
  public void addFrame(String shapeName, String shapeType, int x, int y, int width, int height,
      int r, int g, int b, double tick) throws IllegalArgumentException {
    Frame newFrame = (new Frame(shapeName, shapeType, x, y, width, height, r, g, b, tick));
    if (this.frames.contains(newFrame)) {
      return;
    }
    if (!this.shapeNamesAndTypes.containsKey(shapeName)) {
      this.shapeNamesAndTypes.put(shapeName, shapeType);
    }
    this.frames.add(newFrame);
  }

  @Override
  public void removeFrame(String shapeName, String shapeType, int x, int y, int width, int height,
      int r, int g, int b, double tick) throws IllegalArgumentException {
    if (!this.frames
        .contains(new Frame(shapeName, shapeType, x, y, width, height, r, g, b, tick))) {
      throw new IllegalArgumentException("Frame not found.");
    }
    if (getFramesByShapeName(shapeName).size() == 1) {
      this.shapeNamesAndTypes.remove(shapeName);
    }
    for (int j = 0; j < this.frames.size(); j++) {
      if (this.frames.get(j)
          .equals(new Frame(shapeName, shapeType, x, y, width, height, r, g, b, tick))) {
        frames.remove(j);
        break;
      }
    }
  }

  @Override
  public void addShape(String shapeName, String shapeType) {
    this.shapeNamesAndTypes.put(shapeName, shapeType);
  }

  @Override
  public void removeShape(String shapeName) throws IllegalArgumentException {
    if (!this.getShapeNames().contains(shapeName) || shapeName == null || shapeName.equals("")) {
      throw new IllegalArgumentException("Shape not found.");
    }
    List<Frame> toBeRemoved = new ArrayList<Frame>();
    for (Frame f : this.frames) {
      if (f.getShapeName().equals(shapeName)) {
        toBeRemoved.add(f);
      }
    }
    for (Frame f : toBeRemoved) {
      this.frames.remove(f);
    }
    this.shapeNamesAndTypes.remove(shapeName);
  }

  @Override
  public void addSlomo(int[] ints) {
    this.slomo.add(ints);
  }

  @Override
  public List<Frame> getFrames() {
    return this.frames;
  }

  @Override
  public List<Frame> getFramesByShapeName(String shapeName) throws IllegalArgumentException {
    if (!this.getShapeNames().contains(shapeName) || shapeName == null || shapeName.equals("")) {
      throw new IllegalArgumentException("Shape not found.");
    }
    List<Frame> result = new ArrayList<Frame>();
    for (Frame f : this.frames) {
      if (f.getShapeName().equals(shapeName)) {
        result.add(f);
      }
    }
    return result;
  }

  @Override
  public List<Frame> getFramesByTick(double tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick not found.");
    }
    List<Frame> result = new ArrayList<Frame>();
    for (Frame f : this.frames) {
      if (f.getTick() == tick) {
        result.add(f);
      }
    }
    return result;
  }

  @Override
  public List<String> getShapeNames() {
    List<String> result = new ArrayList<String>();
    for (String key : this.shapeNamesAndTypes.keySet()) {
      result.add(key);
    }
    return result;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getLeftmostX() {
    return this.leftmostX;
  }

  @Override
  public int getTopmostY() {
    return this.topmostY;
  }

  @Override
  public Map<String, String> getShapeNamesAndTypes() {
    return this.shapeNamesAndTypes;
  }

  @Override
  public List<int[]> getSlomo() {
    return this.slomo;
  }

  @Override
  public void setWidth(int width) {
    if (width < 1) {
      throw new IllegalArgumentException("Width cannot be set.");
    }
    this.width = width;
  }

  @Override
  public void setHeight(int height) {
    if (height < 1) {
      throw new IllegalArgumentException("Height cannot be set.");
    }
    this.height = height;
  }

  @Override
  public void setLeftmostX(int leftmostX) {
    this.leftmostX = leftmostX;
  }

  @Override
  public void setTopmostY(int topmostY) {
    this.topmostY = topmostY;
  }

  public static AnimationBuilder builder() {
    return new AnimationBuilderImpl();
  }

  /**
   * A builder implementation for the AnimatorModel, which implements the builder pattern on the
   * model so that it can be more easily created in the main.
   */
  public static final class AnimationBuilderImpl implements AnimationBuilder<AnimatorModel> {

    private final AnimatorModel model;

    public AnimationBuilderImpl() {
      this.model = new AnimatorModel();
    }

    @Override
    public AnimatorModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder setBounds(int x, int y, int width, int height) {
      this.model.setLeftmostX(x);
      this.model.setTopmostY(y);
      this.model.setWidth(width);
      this.model.setHeight(height);
      return this;
    }

    @Override
    public AnimationBuilder declareShape(String name, String type) {
      this.model.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1,
        int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      if (!this.model.getShapeNamesAndTypes().containsKey(name)) {
        throw new IllegalArgumentException("Shape type not found.");
      }
      this.model.addFrame(name, this.model.getShapeNamesAndTypes().get(name), x1, y1, w1, h1,
          r1, g1, b1, t1);
      this.model.addFrame(name, this.model.getShapeNamesAndTypes().get(name), x2, y2, w2, h2,
          r2, g2, b2, t2);
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addSlomoInterval(int[] ints) {
      if (ints[1] < ints[0]) {
        throw new IllegalArgumentException("End time cannot be less than start time.");
      }
      this.model.addSlomo(ints);
      return this;
    }
  }

}