package cs3500.easyanimator.provider.model;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.easyanimator.shape.AShape;
import cs3500.easyanimator.shape.Posn;
import cs3500.easyanimator.animation.MotionAnimation;

/**
 * Permits the implementation and construction of the methods the model will have. Makes
 * modification easy if there is a change in the design.
 */
public interface IAnimation {

  /**
   * Performs a single animation.
   *
   * @param animation The animation that will be performed.
   */
  void animate(MotionAnimation animation);

  /**
   * Returns all the animations that have occurred in the form of a String describing each
   * animation.
   *
   * @return The text form of the animations.
   */
  String animationState();

  /**
   * Return the width of the canvas for the model.
   *
   * @return the width of the canvas for the model.
   */
  int getWidth();

  /**
   * Return the height for the canvas of the model.
   *
   * @return the height for the canvas of the model.
   */
  int getHeight();

  /**
   * Return the left most value of the model.
   *
   * @return the left most value of the model.
   */
  int getLeftMost();

  /**
   * Return the top most value of the model.
   *
   * @return the top most value of the model.
   */
  int getTopMost();

  /**
   * Return the list of shapes of this model.
   *
   * @return the list of shapes of this model.
   */
  ArrayList<AShape> getShapes();

  /**
   * Return the list of animations of this model.
   *
   * @return the list of animations of this model.
   */
  ArrayList<MotionAnimation> getAnimations();

  /**
   * Animate the given animation on this model at the given tick.
   *
   * @param animation the given animation.
   * @param tick      the given tick.
   */
  void motionAnimate(MotionAnimation animation, int tick);

  /**
   * Adds shapes to the model.
   *
   * @param s Shape to be added.
   */
  void addShapes(AShape s);

  /**
   * Removes key frames from the model.
   *
   * @param name Name of the shape the key frame is being removed from.
   * @param time The time the key frame that is being removed.
   */
  void removeKey(String name, int time);

  /**
   * Adds key frames to the model.
   *
   * @param name  Name of the shape the key frame is being added to.
   * @param time  Time at which the key frame is being added.
   * @param p     Posn of the shape at the key frame being added.
   * @param w     Width of the shape at the key frame being added.
   * @param h     Height of the shape at the key frame being added.
   * @param color Color of the shape at the key frame being added.
   */
  void addKey(String name, int time, Posn p, double w, double h, Color color);

  /**
   * Removes shapes from the model.
   *
   * @param name The name of the shape being removed.
   */
  void deleteShape(String name);

  /**
   * Adds shapes to the model.
   *
   * @param name Name of the shape being added.
   * @param type Type of shape being added.
   */
  /**
   * Adds shapes to the model.
   *
   * @param color     Color of shape being added.
   * @param posn      Position of shape being added.
   * @param name      Name of the shape being added.
   * @param type      Type of shape being added.
   * @param width     Width of shape being added.
   * @param height    Height of shape being added.
   * @param appear    Appearance time of shape being added.
   * @param disappear Disappearance time of shape being added.
   */
  void addShape(Color color, Posn posn, String name, String type, double width,
                double height, int appear, int disappear);

  /**
   * Modifies key frames of currently existing shapes.
   *
   * @param name Name of the shape whose key frame is going to be modified.
   * @param time Time of the key frame that is going to modified.
   * @param p    The modified position.
   * @param w    The modified width.
   * @param h    The modified height.
   * @param c    The modified color.
   */
  void modifyKF(String name, int time, Posn p, double w, double h, Color c);
}
