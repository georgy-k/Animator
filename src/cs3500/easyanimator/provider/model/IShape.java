package cs3500.easyanimator.provider.model;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.easyanimator.model.KeyFrame;
import cs3500.easyanimator.shape.Posn;
import cs3500.easyanimator.animation.MotionAnimation;

/**
 * Interface representing the methods that we have defined for animating shapes.
 */
public interface IShape {

  /**
   * Changes the current color of the shape to the new color at the given tick.
   *
   * @param oldColor Old color of the shape.
   * @param newColor Color being changed to.
   * @param tick     The tick which allows the gradual change.
   * @param end      The end time of the change in color.
   * @param start    The start time of the change in color.
   */
  void changeColor(Color oldColor, Color newColor, int tick, int end, int start);

  /**
   * Changes the color of the shape.
   *
   * @param newColor Color being changed to.
   */
  void stateChangeColor(Color newColor);

  /**
   * Returns a description of the color of the shape in the r g b scale in the form of a String.
   *
   * @return String representation of the color of the shape.
   */
  String getColor();

  /**
   * Changes the width of the shape to the new width at the given tick.
   *
   * @param width    The width of the shape.
   * @param newWidth Width to be changed to.
   * @param tick     The tick at which the gradual change occurs
   * @param end      The end time for the change in width.
   * @param start    The start time for the change in width.
   */
  void changeWidth(double width, double newWidth, int tick, int end, int start);

  /**
   * Changes the width of the shape.
   *
   * @param newWidth Width being changed to.
   */
  void stateChangeWidth(double newWidth);

  /**
   * Returns the width of the shape in the form of a String.
   *
   * @return String representation of the width of the shape.
   */
  String getWidth();

  /**
   * Changes the height of the shape to the new height ath the given tick.
   *
   * @param height    Height of the shape.
   * @param newHeight Height to be changed to.
   * @param tick      The tick at which the gradual changes happens.
   * @param end       The end time for the change in height.
   * @param start     The start time for the change in height.
   */
  void changeHeight(double height, double newHeight, int tick, int end, int start);

  /**
   * Changes the height of the shape.
   *
   * @param newHeight Height being changed to.
   */
  void stateChangeHeight(double newHeight);

  /**
   * Returns the height of the shape in the form of a String.
   *
   * @return String representation of the height of the shape.
   */
  String getHeight();

  /**
   * Changes the position of the shape to the new position at the given tick.
   *
   * @param from  Position of the shape.
   * @param to    Position to be changed to.
   * @param tick  The tick at which the gradual change occurs.
   * @param end   The end time for the change in position.
   * @param start The start time for the change in position.
   */
  void move(Posn from, Posn to, int tick, int end, int start);

  /**
   * Changes the position of the shape.
   *
   * @param to Position being changed to.
   */
  void stateMove(Posn to);

  /**
   * Returns the position of the shape in the form of a String.
   *
   * @return String representation of the position of the shape.
   */
  String getPosn();

  /**
   * Returns the type of the shape.
   *
   * @return Type of the shape.
   */
  String getType();

  /**
   * Returns the name of the shape.
   *
   * @return Name of the shape.
   */
  String getName();

  /**
   * Compares two shapes and checks if they are equal if they have the same parameters.
   *
   * @param other The shape to be compared to.
   * @return True is the shapes are equal, false otherwise.
   */
  boolean equals(Object other);

  /**
   * Returns the state of the shape of this animation in the form of a string.
   *
   * @return Text form of the state of the shape of this animation.
   */
  String state();

  /**
   * Returns the position of this shape.
   *
   * @return Position of this shape.
   */
  Posn shapePosn();

  /**
   * Returns the width of the shape.
   *
   * @return The width of this shape.
   */
  double shapeWidth();

  /**
   * Height of the shape.
   *
   * @return The height of this shape.
   */
  double shapeHeight();

  /**
   * Returns the color of this shape.
   *
   * @return The color of this shape.
   */
  Color shapeColor();

  /**
   * Returns an array list tracking all the animations on this shape.
   *
   * @return All Animations on this shape.
   */
  ArrayList<MotionAnimation> allAnimations();

  /**
   * Removes a key frame from a shapes already existing list of animations.
   *
   * @param time Time of key frame to be deleted.
   */
  void removeKeyFrame(int time);

  /**
   * Adds a key frame to a shapes already existing list of animations.
   *
   * @param kf The key frame being added.
   */
  void addKeyFrame(KeyFrame kf);

  /**
   * Modifies a key frame of this shape.
   *
   * @param time Time of the key frame being modified.
   * @param p    Modified position.
   * @param w    Modified width.
   * @param h    Modified height.
   * @param c    Modified color.
   */
  void modifyKeyFrame(int time, Posn p, double w, double h, Color c);
}
