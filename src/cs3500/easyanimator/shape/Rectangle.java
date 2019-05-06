package cs3500.easyanimator.shape;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import cs3500.easyanimator.animation.AnimationModel;

/**
 * This class represents a rectangle.
 */
public class Rectangle extends ShapesModelAbs {
  /**
   * These are the values used to help represent a rectangle.
   */
  private float xCorner;
  private float yCorner;
  private float width;
  private float height;

  /**
   * This is the constructor for a rectangle.
   *
   * @param name     Name of the Shape
   * @param start    This is the start time for the shape
   * @param end      This is the end time for the shape
   * @param xCorner  This is the x coordinate of the corner of the rectangle
   * @param yCorner  This is the Y coordinate of the corner of the rectangle
   * @param width    This is the width of the rectangle
   * @param height   This is the height of the rectangle
   * @param red      This is the RGB value for red
   * @param green    This is theRGB value for green
   * @param blue     This is the RGB value for Blue
   * @param rotation This is the degrees that this shape is rotated
   */
  public Rectangle(String name, int start, int end, float xCorner, float yCorner, float width,
                   float height, float red, float green, float blue, float rotation) {
    super(name, start, end, red, green, blue, rotation);
    if (start > end) {
      throw new IllegalArgumentException("Start time must occur before end time");
    }
    this.xCorner = xCorner;
    this.yCorner = yCorner;
    this.width = width;
    this.height = height;
  }

  /**
   * Constructs the default rectangle, with only name in it.
   *
   * @param name unique name of the shape
   */
  public Rectangle(String name) {
    super(name);
    this.xCorner = 0;
    this.yCorner = 0;
    this.width = 0;
    this.height = 0;


  }


  @Override
  public ShapesModelAbs build(String name, int start, int end, float xPos, float yPos,
                              float xDim, float yDim, float red, float green, float blue,
                              float rotation) {
    return new Rectangle(name, start, end, xPos, yPos, xDim, yDim, red, green, blue, rotation);
  }


  /**
   * Returns an exact copy of this ShapesModelAbs.
   *
   * @return a copy of this rectangle
   */
  @Override
  public ShapesModelAbs makeCopy() {
    return new Rectangle(this.getName(), this.getStart(), this.getEnd(), this.xCorner,
            this.yCorner, this.width, this.height, this.getRed(), this.getGreen(), this.getBlue(),
            this.getRotation());
  }


  @Override
  public float getStartX() {
    return xCorner;
  }

  @Override
  public float getStartY() {
    return yCorner;
  }

  @Override
  public float getStartXdim() {
    return width;
  }

  @Override
  public float getStartYdim() {
    return height;
  }

  @Override
  public String getInfo() {

    String shape = "shape " + this.getName() + " rectangle\n";
    for (AnimationModel anime : this.getMyAnimes()) {
      shape = shape + anime.getDescription();
    }
    return shape;
  }

  @Override
  public String getState() {
    return "Rectangle: " + getName() + "\n" +
            "Position: " + (int) xCorner + ", " + (int) yCorner + ";\n"
            + "Size: " + (int) width + ", " + (int) height + "\n"
            + "Color: " + "R: " + (int) getRed() + ", " + "G: " + (int) getGreen() + ", "
            + "B: " + (int) getBlue() + ";";
  }

  @Override
  public Shape getShape() {
    return new Rectangle2D.Float(this.xCorner, this.yCorner, this.width, this.height);
  }

  @Override
  public void initialize() {
    if (this.myAnimes.size() > 0) {
      this.start = myAnimes.get(0).getStartTime();
      this.end = myAnimes.get(myAnimes.size() - 1).getEndTime();
      this.red = myAnimes.get(0).getStartRed();
      this.green = myAnimes.get(0).getStartGreen();
      this.blue = myAnimes.get(0).getStartBlue();
      this.xCorner = myAnimes.get(0).getStartX();
      this.yCorner = myAnimes.get(0).getStartY();
      this.width = myAnimes.get(0).getStartXdim();
      this.height = myAnimes.get(0).getStartYdim();
    }
  }

  //
  /////////// Needed for adoptation /////////////////
  //

  @Override
  public Posn shapePosn() {
    return new Posn((int) this.xCorner, (int)this.yCorner);
  }

  @Override
  public String getType() {
    return "R";
  }

  @Override
  public double shapeWidth() {
    return this.width;
  }

  @Override
  public double shapeHeight() {
    return this.height;
  }
}
