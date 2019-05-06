package cs3500.easyanimator.model;

import java.util.ArrayList;
import java.util.HashMap;

import cs3500.easyanimator.shape.ShapesModelAbs;

/**
 * This is the entry point for the user. Implementation of this interface will take shapes and
 * animations on these shapes and produces set of frames needed for animation.
 */
public interface AnimatorModel {
  /**
   * This method retrieves the cope array of Frames for the user of this program.
   *
   * @return array of frames used for an animation
   */
  Frame[] getFrames();

  /**
   * This is the initial Shapes used to create the frames. This was kept in order to create text
   * descriptions of the animations.
   *
   * @return arraylist of ShapeModelAbs
   */
  ArrayList<ShapesModelAbs> getStarterShapes();

  /**
   * This is the initial Animations used to create the frames. This was kept in order to create
   * descriptions of the animations
   *
   * @return arraylist of AnimationsModelAbs
   */

  HashMap<String, ShapesModelAbs> getShapeTable();

  /**
   * String representation of starting and ending conditions of this animation.
   *
   * @return string representation of animation
   */
  String textOutput();

  /**
   * Returns state of the shape at a specific point of time.
   *
   * @param t    point of time to retrieve state from.
   * @param name unique name of the shape.
   * @return string representation of the shape's state
   */
  String shapeState(int t, String name);

  /**
   * Adds a single shape.
   *
   * @param type type of the shape
   * @param name unique name of the shape
   */
  void addAshape(String type, String name) throws IllegalArgumentException;

  /**
   * Deletes a single shape.
   *
   * @param name unique name of the shape to be deleted.
   */
  void deleteShape(String name) throws IllegalArgumentException;

  /**
   * Edits one of the existing key frames.
   *
   * @param name   unique name of the shape
   * @param time   time of the frame
   * @param xPos   x position of the shape
   * @param yPos   y position of the shape
   * @param width  width of the shape
   * @param height height of the shape
   * @param r      red value of RGB
   * @param g      green value of RGB
   * @param b      blue value of RGB
   */
  void editKeyframe(String name, int time, int xPos, int yPos, int width, int height,
                    int r, int g, int b) throws IllegalArgumentException;

  /**
   * Set a key from for a shape with a given name at a given point of time.
   *
   * @param name unique name of the shape
   * @param time time when key frame should exist
   * @throws IllegalArgumentException if shape name doesn't exist
   */
  void setKeyFrame(String name, int time) throws IllegalArgumentException;

  /**
   * Delete a key from for a shape with a given name at a given point of time.
   *
   * @param name unique name of the shape
   * @param time time when key frame should exist
   * @throws IllegalArgumentException if no frame exists for this shape at that time
   */
  void deleteKeyFrame(String name, int time) throws IllegalArgumentException;

  /**
   * Returns an x position of the window.
   *
   * @return x position of the window
   */
  int getXpos();

  /**
   * Returns a y position of the window.
   *
   * @return y position of the window
   */
  int getYpos();

  /**
   * Returns height of window.
   *
   * @return height of the window
   */
  int getHeight();

  /**
   * Returns width of window.
   *
   * @return width of the window
   */
  int getWidth();

  /**
   * Updates all frames to be displayed by the view (pool of all shapes to be displayed).
   */
  void updateAllFrames();

  /**
   * Clears current list of shapes to be displayed.
   */
  void clearPreviousShapes();


}
