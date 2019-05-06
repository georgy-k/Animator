package cs3500.easyanimator.model;

import java.util.ArrayList;

import cs3500.easyanimator.shape.ShapesModelAbs;

/**
 * This class represents a frame containing list of ShapeModelAbs at a specific point of time.
 */
public class FrameImpl implements Frame {
  /**
   * This is the list of ShapeModelAbs.
   */
  protected ArrayList<ShapesModelAbs> loS;

  /**
   * Constructor takes nothing and creates a new list.
   */
  FrameImpl() {
    this.loS = new ArrayList<ShapesModelAbs>();
  }

  /**
   * This method adds ShapesModelAbs to the current frame.
   *
   * @param shape This is the ShapesModelAbs being added
   */
  public void addToFrame(ShapesModelAbs shape) {

    loS.add(shape);
  }

  /**
   * This returns a Copy of the frames.
   *
   * @return a clone of ArrayList
   */
  public ArrayList<ShapesModelAbs> getLos() {
    return (ArrayList<ShapesModelAbs>) loS.clone();
  }

  /**
   * Returns a copy of the shape with the given unique name.
   *
   * @param name unique name of the shape
   * @return a clone of a shape
   * @throws IllegalArgumentException if shape was not found.
   */
  public ShapesModelAbs getShape(String name) throws IllegalArgumentException {
    for (ShapesModelAbs shape : loS) {
      if (shape.getName().equals(name)) {
        return shape.makeCopy();
      }
    }
    throw new IllegalArgumentException("Shape : " + name + "was not found in the given frame");
  }
}
