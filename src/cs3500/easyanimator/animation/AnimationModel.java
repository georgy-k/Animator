package cs3500.easyanimator.animation;

import cs3500.easyanimator.shape.ShapesModelAbs;

/**
 * Promise of a behavior of what a single animation is able to do. The main method here is
 * run(shape, t), which applies this animation to the given shape.
 */
public interface AnimationModel extends Comparable {

  /**
   * Applies implementation of this interface to the given shape and produces modified shape.
   *
   * @param shape shape to apply animation to
   * @param i     point of time
   * @return shape with parameters modified according to what animation had to do.
   */
  ShapesModelAbs run(ShapesModelAbs shape, int i);

  /**
   * This should gather the end time from any class in the animation models.
   *
   * @return int representing the endTime
   */
  int getEndTime();

  /**
   * This should gather the start time for any class implementing the animation mode.
   *
   * @return int representing the startTime
   */
  int getStartTime();

  /**
   * This should get the name of the shape this animation is going to be affecting.
   *
   * @return String of the name of the shape being animated
   */
  String getName();


  /**
   * creates description fitting into the format of "moves from (300.0,300.0) to (200.0,200.0) from
   * t=70 to t=100"
   */
  String getDescription();


  float getStartY();

  float getStartYdim();

  float getStartX();

  float getStartXdim();

  float getStartRed();

  float getStartGreen();

  float getStartBlue();

  float getEndY();

  float getEndYdim();

  float getEndX();

  float getEndXdim();

  float getEndRed();

  float getEndGreen();

  float getEndBlue();

  boolean sizeChanged();

  boolean colorChanged();

  boolean positionChanged();


}
