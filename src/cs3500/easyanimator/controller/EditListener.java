package cs3500.easyanimator.controller;

import cs3500.easyanimator.provider.AnimationController;

/**
 * Interface representing behaviour that needs to be supported by specific EditView controller.
 */
public interface EditListener extends IController, AnimationController {

  /**
   * Sets the key frame for a given name at a given point of time in the model.
   *
   * @param name unique name of the shape
   * @param time point of time
   */
  void setKeyFrame(String name, String time);

  /**
   * Deletes the key frame for a given name at a given point of time in the model.
   *
   * @param name unique name of the shape
   * @param time point of time
   */
  void deleteKeyFrame(String name, String time);

  /**
   * Edits the key frame in the model.
   *
   * @param name unique name of the shape
   * @param time point of time
   * @param x    desired x position
   * @param y    desired y position
   * @param xdim desired width
   * @param ydim desired height
   * @param r    desired red value
   * @param g    desired green value
   * @param b    desired blue value
   */
  void editKeyFrame(String name, String time, String x, String y, String xdim, String ydim,
                    String r, String g, String b);

  /**
   * Saves the work to a file with a given type and name.
   *
   * @param type     type of the file (txt or svg)
   * @param filename name of the file.
   */
  void saveWork(String type, String filename);

  /**
   * Uploads the animation from the file with a given type and name.
   *
   * @param type     type of the file (txt or svg)
   * @param filename name of the file.
   */
  void openWork(String type, String filename);

  /**
   * Updates all frames in the model that are used to draw the animation.
   */
  void updateFrames();

  /**
   * Asks model to delete a shape with a given name.
   *
   * @param name unique name of the shape.
   */
  void deleteShape(String name);

  /**
   * Asks model to create a new shape.
   *
   * @param type type of the shape, must be one of: rectangle, ellipse
   * @param name unique name of the shape
   */
  void addShape(String type, String name);
}
