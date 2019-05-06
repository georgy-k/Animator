package cs3500.easyanimator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import cs3500.easyanimator.animation.AnimationModel;
import cs3500.easyanimator.animation.AnimationModelAbs;
import cs3500.easyanimator.animation.EasyAnimation;
import cs3500.easyanimator.shape.Ellipse;
import cs3500.easyanimator.shape.Rectangle;
import cs3500.easyanimator.shape.ShapesModelAbs;
import cs3500.easyanimator.util.AnimationBuilder;


/**
 * This is an implementation of AnimatorModel that implements behaviour of constructing frames. It
 * will take shapes and animations, check for consistency of both and construct sequence of frames
 * needed to produce a visual representation (animation).
 */
public class AnimatorModelImpl implements AnimatorModel {

  private Frame[] arrays;
  private HashMap<String, ShapesModelAbs> shapeTable;
  private ArrayList<ShapesModelAbs> allShapes;

  private HashMap<String, ArrayList<IKeyFrame>> allKeyFrames;
  private final int xPos;
  private final int yPos;
  private final int width;
  private final int height;


  /**
   * Constructs Animator Model. This needs to sanitize the inputs of repeated names of shapes,
   * animations that don't point to any real shapes, and animations occuring at the same time as
   * animations of the same type on the same shape.
   *
   * @param shapes     list of shapes used for animation
   * @param animations list of animations to be performed
   * @param xPos       x position of the canvas
   * @param yPos       y position of the canvas
   * @param width      width of the canvas
   * @param height     height of the canvas
   */
  public AnimatorModelImpl(ArrayList<ShapesModelAbs> shapes,
                           HashMap<String, ArrayList<AnimationModelAbs>> animations,
                           HashMap<String, ArrayList<IKeyFrame>> allKeyFrames, int xPos, int yPos,
                           int width, int height) {
    if (shapes == null || animations == null) {
      throw new IllegalArgumentException("Shapes and animations can't be null");
    }
    this.xPos = xPos;
    this.yPos = yPos;
    this.width = width;
    this.height = height;
    this.allShapes = shapes;

    this.allKeyFrames = allKeyFrames;


    // Make each shape initialize itself according to the first animation on it.
    for (ShapesModelAbs shape : shapes) {
      shape.setAnimes(animations.get(shape.getName()));
      shape.initialize();
    }

    this.shapeTable = new HashMap<String, ShapesModelAbs>();
    for (ShapesModelAbs shape : shapes) {
      //Check that name of each shape is unique
      if (shapeTable.containsKey(shape.getName())) {
        throw new IllegalArgumentException("Some inputted ShapeModels have the same names "
                + "including two shapes with the name of" + shape.getName());
      }
      // Create HashMap of (Name: Shape)
      shapeTable.put(shape.getName(), shape);
    }

    //Sanitize inputs
    this.checksShapesAndAnimations(animations);

    for (ShapesModelAbs shape : shapes) {
      shape.checkConsistency();
    }
    /**
     * Create needed frames of animation. Each frame is a list of shapes that have been modified by
     * the animations. The size of the array is set by the longest lasting shape.
     */
    this.arrays = createFrames(shapes);

  }

  /**
   * Filters out animations for a specific shape.
   *
   * @param name       unique name of the shape
   * @param animations all animations
   * @return animation that are related to given shape name
   */
  protected ArrayList<AnimationModel> animes(String name, ArrayList<AnimationModel> animations) {
    ArrayList<cs3500.easyanimator.animation.AnimationModel> animes = new ArrayList<>();
    for (cs3500.easyanimator.animation.AnimationModel animation : animations) {
      if (animation.getName().equals(name)) {
        animes.add(animation);
      }
    }
    return animes;
  }


  /**
   * Private method used to sanitize the given inputs.
   *
   * @param animations These are the animations that will be used to create frames for the
   *                   controller. These animations must point to a shape, and these animations
   *                   cannot occur at the same time as another animation of the same type on the
   *                   same shape.
   */
  private void checksShapesAndAnimations(HashMap<String, ArrayList<AnimationModelAbs>> animations) {

    //This checks to see if all animations point to a shape.
    for (String name : animations.keySet()) {
      boolean testBool = false;
      if (!shapeTable.containsKey(name)) {
        throw new IllegalArgumentException("Some inputted AnimationModel does not provide a name"
                + " that corresponds to a given shape");
      }
    }
    //This part checks to see if any of the animations of the same type are occuring at the same
    //time on the same shape.
    Set<String> names = animations.keySet();

    for (String name : names) {
      for (int i = 0; i < animations.get(name).size(); i++) {
        String testVar1 = animations.get(name).get(i).getName();
        int start1 = animations.get(name).get(i).getStartTime();
        int end1 = animations.get(name).get(i).getEndTime();

        for (int j = i + 1; j < animations.get(name).size(); j++) {
          String testVar2 = animations.get(name).get(j).getName();
          int start2 = animations.get(name).get(j).getStartTime();
          int end2 = animations.get(name).get(j).getEndTime();

          if (testVar1 == testVar2) {
            if (!(end1 <= start2 || start1 >= end2)) {
              {
                throw new IllegalArgumentException("Animations of the same type cannot occur at" +
                        " the same time on the same shape");
              }
            }
          }
        }
      }
    }
  }


  /**
   * This function creates each frame needed for animation.
   *
   * @param shapes These are the unique shapes used for the animation process
   * @return array of frames
   */
  private Frame[] createFrames(ArrayList<ShapesModelAbs> shapes) {
    /**
     * This initializes the array of frames to be the size of the shape that last for the longest
     * time
     */
    Frame[] setUp;
    int length = 0;
    for (int i = 0; i < shapes.size(); i++) {
      int next = shapes.get(i).getEnd();
      if (next > length) {
        length = next;
      }
    }
    setUp = new Frame[length];
    //Iterates Through each shape


    for (ShapesModelAbs shape : shapeTable.values()) {
      ShapesModelAbs current = null;

      for (int i = 0; i < length; i++) {
        //Makes a copy to work with
        if (setUp[i] == null) {
          setUp[i] = new FrameImpl();
        }
        ShapesModelAbs shapeToFrame;

        if (current == null) {
          shapeToFrame = shape.makeCopy();
        } else {
          shapeToFrame = current;
        }
        for (AnimationModel animation : shape.getMyAnimes()) {
          shapeToFrame = animation.run(shapeToFrame, i);
          if (animation.getStartTime() < i && animation.getEndTime() >= i) {
            setUp[i].addToFrame(shapeToFrame);

          }

          if (animation.getEndTime() == i) {
            current = shapeToFrame;
          }
        }
      }
    }
    return setUp;
  }

  /**
   * This gets a clone of the array of frames created for animation.
   *
   * @return clone of a Frame[]
   */
  @Override
  public Frame[] getFrames() {
    return arrays.clone();
  }

  /**
   * Creates a clone copy if all shapes.
   *
   * @return clone of a ArrrayList
   */
  @Override
  public ArrayList<ShapesModelAbs> getStarterShapes() {
    return (ArrayList<ShapesModelAbs>) allShapes.clone();
  }

  @Override
  public HashMap<String, ShapesModelAbs> getShapeTable() {
    return this.shapeTable;
  }

  /**
   * This method creates a text output of the animations.
   *
   * @return String as the text output of the animations
   */
  @Override
  public String textOutput() {
    String start = "canvas " + this.xPos + " " + this.yPos + " " + this.width + " " + this.height
            + "\n";
    for (ShapesModelAbs shape : allShapes) {
      start = start + shape.getInfo();
    }
    return start;
  }


  @Override
  public String shapeState(int t, String name) {
    return arrays[t - 1].getShape(name).getState();
  }

  @Override
  public int getXpos() {
    return this.xPos;
  }

  @Override
  public int getYpos() {
    return this.xPos;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Adds individual shape to our model.
   *
   * @param type type of a shape to be added
   * @param name unique name of a shape to be added
   */
  public void addAshape(String type, String name) {
    String shapeType = type.toLowerCase();
    if (!shapeTable.containsKey(name)) {
      switch (shapeType) {
        case "rectangle":
          shapeTable.put(name, new Rectangle(name));
          allKeyFrames.put(name, new ArrayList<>());
          break;
        case "ellipse":
          shapeTable.put(name, new Ellipse(name));
          allKeyFrames.put(name, new ArrayList<>());
          break;
        default:
          throw new IllegalArgumentException("Unsupported shape type, please use either ellipse " +
                  "or rectangle");
      }
    } else {
      throw new IllegalArgumentException("Shape: " + name + " can't be added because" +
              "such name already exists");
    }
  }

  @Override
  public void deleteShape(String name) {
    if (shapeTable.containsKey(name)) {
      this.shapeTable.remove(name);
      this.allKeyFrames.remove(name);
    } else {
      throw new IllegalArgumentException("Shape with name: " + name + " does not exist");
    }
  }

  /**
   * Creates a Key Frame that does not have any affect on animation yet.
   *
   * <p>If key frame frame already exists, does nothing. Otherwise creates key frame with field
   * visible set to false.
   *
   * @param name name of the shape
   * @param time time value
   * @throws IllegalArgumentException if shape with given name does not exist.
   */
  public void setKeyFrame(String name, int time) throws IllegalArgumentException {

    if (allKeyFrames.containsKey(name)) {
      for (IKeyFrame frame : allKeyFrames.get(name)) {
        if (frame.getTime() == time) {
          return;
        }
      }
      allKeyFrames.get(name).add(new KeyFrame(name, time, 0, 0, 0, 0, 0,
              0, 0, false));
      Collections.sort(allKeyFrames.get(name));

    } else {
      throw new IllegalArgumentException("Can't add keyframe to shape: " + name + ",because it " +
              "does not exist");
    }
  }

  /**
   * Edits existing key frame and sets its values to provided values and reconstructs animations.
   *
   * @param name   unique name of the shape
   * @param time   point of time
   * @param xPos   new x position
   * @param yPos   new y position
   * @param width  new width of the shape
   * @param height new height of the shape
   * @param r      new red value of RGB
   * @param g      new green value of RGB
   * @param b      new blue vaalue of RGB
   */
  public void editKeyframe(String name, int time, int xPos, int yPos, int width, int height,
                           int r, int g, int b) {

    IKeyFrame frameToEdit = findKeyFrame(name, time);

    frameToEdit.setTime(time);
    frameToEdit.setxPos(xPos);
    frameToEdit.setyPos(yPos);
    frameToEdit.setxDim(width);
    frameToEdit.setyDim(height);
    frameToEdit.setRed(r);
    frameToEdit.setGreen(g);
    frameToEdit.setBlue(b);
    frameToEdit.setVisible();
    updateAnimations(name);

  }

  /**
   * Finds a key frame based on the name and time provided.
   *
   * @param name name of the shape
   * @param time point of time
   * @return IKeyFrame
   * @throws IllegalArgumentException if either shape name or time does not match.
   */
  private IKeyFrame findKeyFrame(String name, int time) throws IllegalArgumentException {
    if (!this.shapeTable.containsKey(name)) {
      throw new IllegalArgumentException("No shape with name " + name + " exists");
    } else {
      ShapesModelAbs shape = this.shapeTable.get(name);
      ArrayList<IKeyFrame> framesFromShape = this.allKeyFrames.get(name);
      IKeyFrame frameToEdit = null;

      // Find frame that user wants to edit
      for (IKeyFrame frame : framesFromShape) {

        if (frame.getTime() == time) {
          frameToEdit = frame;
        }
      }
      //if frame to edit is still null, throw exception since no key frame exists at given time
      if (frameToEdit == null) {
        throw new IllegalArgumentException("No keyframe for shape " + name + " exists " +
                "at time: " + time);
      }
      return frameToEdit;
    }
  }


  /**
   * Deletes key frame and updates animations on affected shape.
   *
   * @param name name of the shape
   * @param time point of time
   */
  public void deleteKeyFrame(String name, int time) {


    IKeyFrame frameToDelete = findKeyFrame(name, time);

    allKeyFrames.get(name).remove(frameToDelete);
    ArrayList<IKeyFrame> updated = allKeyFrames.get(name);
    shapeTable.get(name).setMyKeyFrames(updated);
    updateAnimations(name);

  }

  @Override
  public void updateAllFrames() {
    this.arrays = createFrames(allShapes);

  }

  @Override
  public void clearPreviousShapes() {
    // This method needs to only be implemented by Adapter implementation.
  }

  /**
   * Updates animations on the given shape.
   *
   * @param name name of the shape
   */
  private void updateAnimations(String name) {
    ArrayList<IKeyFrame> visKeys = new ArrayList<IKeyFrame>();
    for (IKeyFrame key : allKeyFrames.get(name)) {
      if (key.getVisible()) {
        visKeys.add(key);
      }
    }
    Collections.sort(visKeys);

    // Case when shape has no key frame.
    if (visKeys.size() == 0) {
      shapeTable.get(name).setAnimes(new ArrayList<AnimationModelAbs>());
      shapeTable.get(name).initialize();

      //Case when shape has exactly one key frame (No animation should happen, but shape should get
      // its values for position, size and color)
    } else if (visKeys.size() == 1) {
      ArrayList<AnimationModelAbs> newList = new ArrayList<AnimationModelAbs>();
      for (IKeyFrame keys : visKeys) {
        newList.add(new EasyAnimation(keys.getTime(), keys.getTime(), name,
                keys.getxPos(), keys.getyPos(), keys.getxDim(), keys.getyDim(),
                keys.getRed(), keys.getGreen(),
                keys.getBlue(), 0, keys.getxPos(), keys.getyPos(),
                keys.getxDim(), keys.getyDim(), keys.getRed(), keys.getGreen(),
                keys.getBlue(), 0));

      }
      shapeTable.get(name).setAnimes(newList);
      shapeTable.get(name).initialize();

      // Case when shape has more than one key frame
      // We will go one by one and construct animations corresponding to current key frames.

    } else if (visKeys.size() > 1) {

      ArrayList<AnimationModelAbs> newList = new ArrayList<AnimationModelAbs>();

      for (int i = 0; i < visKeys.size(); i++) {
        if (i != visKeys.size() - 1) {
          IKeyFrame current = visKeys.get(i);
          IKeyFrame next = visKeys.get(i + 1);


          newList.add(new EasyAnimation(current.getTime(), next.getTime(), name,
                  current.getxPos(), current.getyPos(), current.getxDim(), current.getyDim(),
                  current.getRed(), current.getGreen(),
                  current.getBlue(), 0, next.getxPos(), next.getyPos(),
                  next.getxDim(), next.getyDim(), next.getRed(), next.getGreen(),
                  next.getBlue(), 0));
        }
      }
      shapeTable.get(name).setAnimes(newList);
      shapeTable.get(name).initialize();

    }
  }

  /**
   * Builder class used by AnimationReader to construct AnimatiorModel from a text file.
   */
  public static final class Builder implements AnimationBuilder<AnimatorModel> {

    private Frame[] arrays;
    private ArrayList<ShapesModelAbs> allShapes;
    private HashMap<String, ArrayList<AnimationModelAbs>> allAnimations;
    private HashMap<String, ArrayList<IKeyFrame>> keyFrames;
    int xPos;
    int yPos;
    int width;
    int height;

    /**
     * Constructs the instance of the builder with most recent parameters.
     *
     * @param shapes     list of shapes
     * @param animations list of animations
     * @param xPos       x position of canvas
     * @param yPos       y position of canvas
     * @param width      width of canvas
     * @param height     height of canvas
     */
    public Builder(ArrayList<ShapesModelAbs> shapes,
                   HashMap<String, ArrayList<AnimationModelAbs>> animations,
                   HashMap<String, ArrayList<IKeyFrame>> keyFrames,
                   int xPos, int yPos, int width, int height) {
      this.allShapes = shapes;
      this.allAnimations = animations;
      this.keyFrames = keyFrames;
      this.xPos = xPos;
      this.yPos = yPos;
      this.width = width;
      this.height = height;
    }

    /**
     * Constructor for an yet empty builder.
     */
    public Builder() {
      this.allShapes = new ArrayList<>();
      this.allAnimations = new HashMap<>();
      this.keyFrames = new HashMap<>();
    }

    @Override
    public cs3500.easyanimator.model.AnimatorModel build() {
      for (String shapeName : allAnimations.keySet()) {
        Collections.sort(allAnimations.get(shapeName));
        ArrayList<AnimationModelAbs> animes = allAnimations.get(shapeName);
        for (int i = 0; i < animes.size(); i++) {
          AnimationModel current = animes.get(i);
          if (i == 0) {
            addKeyframe(shapeName, current.getStartTime(), (int) current.getStartX(),
                    (int) current.getStartY(), (int) current.getStartXdim(),
                    (int) current.getStartYdim(), (int) current.getStartRed(),
                    (int) current.getStartGreen(),
                    (int) current.getStartBlue());
            addKeyframe(shapeName, current.getEndTime(), (int) current.getEndX(),
                    (int) current.getEndY(), (int) current.getEndXdim(),
                    (int) current.getEndYdim(), (int) current.getEndRed(),
                    (int) current.getEndGreen(), (int) current.getEndBlue());
          } else {
            addKeyframe(shapeName, current.getEndTime(), (int) current.getEndX(),
                    (int) current.getEndY(), (int) current.getEndXdim(),
                    (int) current.getEndYdim(), (int) current.getEndRed(),
                    (int) current.getEndGreen(), (int) current.getEndBlue());
          }
        }
      }
      return new AnimatorModelImpl(this.allShapes, this.allAnimations, this.keyFrames, this.xPos,
              this.yPos, this.width, this.height);
    }

    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      this.xPos = x;
      this.yPos = y;
      this.width = width;
      this.height = height;
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
      switch (type) {
        case "ellipse":
          this.allShapes.add(new Ellipse(name));
          this.allAnimations.put(name, new ArrayList<>());
          this.keyFrames.put(name, new ArrayList<>());
          return this;
        case "rectangle":
          this.allShapes.add(new Rectangle(name));
          this.allAnimations.put(name, new ArrayList<>());
          this.keyFrames.put(name, new ArrayList<>());
          return this;
        default:
          throw new IllegalArgumentException("Unsupported shape type");
      }
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                     int h1, int r1, int g1, int b1, int t2, int x2,
                                                     int y2, int w2, int h2, int r2,
                                                     int g2, int b2) {
      AnimationModelAbs motion1 = new EasyAnimation(t1, t2, name,
              x1, y1, w1, h1, r1,
              g1, b1, 0, x2, y2, w2,
              h2, r2, g2, b2, 0);

      AnimationModelAbs motion = new EasyAnimation(new KeyFrame(name,
              t1, x1, y1, w1, h1, r1,
              g1, b1, true), new KeyFrame(name, t2, x2, y2, w2,
              h2, r2, g2, b2, true));

      if (this.allAnimations.containsKey(name)) {
        this.allAnimations.get(name).add(motion);
      } else {
        throw new IllegalArgumentException("Shape " + name + "doesn't exist");
      }
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addKeyframe(String name, int t, int x, int y, int w,
                                                       int h, int r, int g, int b) {
      this.keyFrames.get(name).add(new KeyFrame(name, t, x, y, w, h, r, g, b, true));

      return this;
    }
  }
}
