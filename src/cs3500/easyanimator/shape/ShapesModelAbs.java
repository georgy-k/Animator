package cs3500.easyanimator.shape;

import java.awt.Shape;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import cs3500.easyanimator.animation.AnimationModelAbs;
import cs3500.easyanimator.model.IKeyFrame;

/**
 * This is the abstract class representing an abstract shape and containing common information.
 */
public abstract class ShapesModelAbs extends AShape {
  /**
   * Variables all Shapes should have.
   */
  protected String name;
  protected int start;
  protected int end;
  protected float red;
  protected float green;
  protected float blue;
  protected float rotation;
  protected ArrayList<AnimationModelAbs> myAnimes;
  protected ArrayList<IKeyFrame> myKeyFrames;

  /**
   * Constructor for an abstract shape.
   *
   * @param name     unique name of the shape
   * @param start    start time of the shape
   * @param end      end time of the shape
   * @param red      Red param of RGB
   * @param green    Green param of RGB
   * @param blue     Blue param of RGB
   * @param rotation rotation angle
   */
  public ShapesModelAbs(String name, int start, int end, float red, float green,
                        float blue, float rotation) {
    this.name = name;
    this.start = start;
    this.end = end;
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.rotation = rotation;
    this.myAnimes = new ArrayList<>();
    this.myKeyFrames = new ArrayList<>();
  }

  /**
   * Declares a shape with default values.
   *
   * @param name unique name of the shape
   */
  public ShapesModelAbs(String name) {
    this.name = name;
    this.start = 0;
    this.end = 0;
    this.red = 0;
    this.green = 0;
    this.blue = 0;
    this.rotation = 0;
    this.myAnimes = new ArrayList<>();
  }

  public float getRed() {
    return red;
  }

  public float getGreen() {
    return green;
  }

  public float getBlue() {
    return blue;
  }

  public float getRotation() {
    return rotation;
  }

  /**
   * Method use to get the end value of a shape.
   *
   * @return The int representing the end value
   */
  public int getEnd() {
    return end;
  }


  @Override
  public String getName() {
    return name;
  }


  /**
   * This method creates an exact copy of a shape.
   *
   * @return A ShapeModelAbs
   */
  public abstract ShapesModelAbs makeCopy();

  /**
   * This function returns the Start value for the Shape.
   *
   * @return int representing the start time of the shape
   */
  public int getStart() {
    return start;
  }

  ;


  /**
   * Get start x position.
   */
  public abstract float getStartX();

  /**
   * Get start y position.
   */
  public abstract float getStartY();

  /**
   * Get start x dimension.
   */
  public abstract float getStartXdim();

  /**
   * Get start y dimension.
   */
  public abstract float getStartYdim();


  public abstract String getInfo();

  /**
   * Sets given animations to shape's animation.
   *
   * @param animations list of animations associated with this shape
   */
  public void setAnimes(ArrayList<AnimationModelAbs> animations) {
    Collections.sort(animations);
    this.myAnimes = animations;
  }

  /**
   * Sets given key frames to shape's key frames.
   *
   * @param keyFrames list of key frames associated with this shape
   */
  public void setMyKeyFrames(ArrayList<IKeyFrame> keyFrames) {
    Collections.sort(keyFrames);
    this.myKeyFrames = keyFrames;
  }


  /**
   * Simple getter for a private field.
   *
   * @return animations on this shape
   */
  public ArrayList<AnimationModelAbs> getMyAnimes() {
    return this.myAnimes;
  }

  /**
   * Simple getter for a private field.
   *
   * @return animations on this shape
   */
  public ArrayList<IKeyFrame> getMyKeyFrames() {
    return this.myKeyFrames;
  }

  /**
   * Check consistency of provided animations.
   *
   * @return boolean representing checking result
   */
  public boolean checkConsistency() {
    boolean testbool = true;

    int lastNum = 0;
    //Tests for some constant amount of animation
    for (AnimationModelAbs anime : myAnimes) {
      if (lastNum >= anime.getStartTime() && lastNum < anime.getEndTime()) {
        lastNum = anime.getEndTime();
      }
    }

    if (lastNum != this.end) {
      testbool = false;
    }
    return (testbool);
  }


  /**
   * Returns a string representation of a shape.
   *
   * @return string representing a state.
   */
  public abstract String getState();

  /**
   * Builds a shape with a given parameters.
   *
   * @param name     Name of the Shape
   * @param start    This is the start time for the shape
   * @param end      This is the end time for the shape
   * @param xPos     This is the x coordinate of a shape
   * @param yPos     This is the Y coordinate of a shape
   * @param xDim     This is the width a shape
   * @param yDim     This is the height a shape
   * @param red      This is the RGB value for red
   * @param green    This is theRGB value for green
   * @param blue     This is the RGB value for Blue
   * @param rotation This is the degrees that this shape is rotated
   * @return ShapesModelAbs with given parameters.
   */
  public abstract ShapesModelAbs build(String name, int start, int end, float xPos, float yPos,
                                       float xDim, float yDim, float red, float green, float blue,
                                       float rotation);

  /**
   * Creates Shape representation of this shape needed for drawing.
   *
   * @return Shape object with parameters of this shape.
   */
  public abstract Shape getShape();

  /**
   * Sets shapes parameters according to the first animation on this shape.
   */
  public abstract void initialize();

  /**
   * Returns type of this shape.
   *
   * @return String representing type of the shape.
   */
  public abstract String getType();

  //
  /////////// Needed for adoptation /////////////////
  //

  @Override
  public Color shapeColor() {
    return new Color((int)this.red, (int)this.green, (int)this.blue);
  }


}
