package cs3500.easyanimator.model;

import java.util.ArrayList;

import cs3500.easyanimator.shape.ShapesModelAbs;

/**
 * Interface represents a a functionality promise by a single frame. Frame is simply a set of shapes
 * to be shown at a specific point of time.
 */
public interface Frame {
  /**
   * This method adds ShapesModelAbs to the current frame.
   *
   * @param shape This is the ShapesModelAbs being added
   */
  void addToFrame(ShapesModelAbs shape);

  /**
   * This returns a Copy of the frames.
   *
   * @return a clone of ArrayList
   */
  ArrayList<ShapesModelAbs> getLos();

  /**
   * Returns a shape with a corresponding name from this frame.
   *
   * @param name unique name of the shape
   * @return ShapesModelAbs if shape is found.
   * @throws IllegalArgumentException if shape was not found.
   */
  ShapesModelAbs getShape(String name);
}
