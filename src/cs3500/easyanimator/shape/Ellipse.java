package cs3500.easyanimator.shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import cs3500.easyanimator.animation.AnimationModel;

/**
 * This class represents an Ellipse.
 */
public class Ellipse extends ShapesModelAbs {
  /**
   * These are the extra variables needed to create an oval.
   */

  private float centerX;
  private float centerY;
  private float xRadius;
  private float yRadius;

  /**
   * Constructor for an oval. The start time must occur before the end time.
   *
   * @param name     Name of the shape
   * @param start    Time the Shape Start to appear on the screen
   * @param end      Time the Shape disappears from the screen
   * @param centerX  The Center coordinate for the X position
   * @param centerY  The Center Coordinate for the Y position
   * @param xRadius  The Radius of the Oval in the X direction
   * @param yRadius  The Radius of the oval in the Y direction
   * @param red      The RGB value for Red
   * @param green    The RGB value for green
   * @param blue     The RGB value for Blue
   * @param rotation The degrees of rotation on the oval
   */
  public Ellipse(String name, int start, int end, float centerX, float centerY, float xRadius,
                 float yRadius, float red, float green, float blue, float rotation) {
    super(name, start, end, red, green, blue, rotation);
    if (start > end) {
      throw new IllegalArgumentException("Start time must occur before end time");
    }

    this.centerX = centerX;
    this.centerY = centerY;
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * Constructs the default shape, with only name in it.
   *
   * @param name unique name of the shape
   */
  public Ellipse(String name) {
    super(name);
    this.centerX = 0;
    this.centerY = 0;
    this.xRadius = 0;
    this.yRadius = 0;
  }


  @Override
  public ShapesModelAbs build(String name, int start, int end, float xPos, float yPos,
                              float xDim, float yDim, float red, float green, float blue,
                              float rotation) {
    return new Ellipse(name, start, end, xPos, yPos, xDim, yDim, red, green, blue, rotation);
  }

  @Override
  public Shape getShape() {
    return new Ellipse2D.Float(
            this.centerX,
            this.centerY,
            this.xRadius * 2,
            this.yRadius * 2);
  }


  /**
   * This function makes a copy of this exact oval.
   *
   * @return Returns an Oval
   */
  @Override
  public ShapesModelAbs makeCopy() {
    return new Ellipse(this.getName(), this.getStart(), this.getEnd(), this.centerX, this.centerY,
            this.xRadius, this.yRadius, this.getRed(), this.getGreen(),
            this.getBlue(), this.getRotation());
  }

  @Override
  public float getStartX() {
    return this.centerX;
  }

  @Override
  public float getStartY() {
    return this.centerY;
  }

  @Override
  public float getStartXdim() {
    return xRadius;
  }

  @Override
  public float getStartYdim() {
    return yRadius;
  }

  //Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
  @Override
  public String getInfo() {

    String shape = "shape " + this.getName() + " ellipse\n";
    for (AnimationModel anime : this.getMyAnimes()) {
      shape = shape + anime.getDescription();
    }
    return shape;
  }

  @Override
  public String getState() {
    return "Ellipse: " + getName() + "\n" +
            "Position: " + (int) centerX + ", " + (int) centerY + ";\n"
            + "Size: " + (int) xRadius + ", " + (int) yRadius + "\n"
            + "Color: " + "R: " + (int) getRed() + ", " + "G: " + (int) getGreen() + ", "
            + "B: " + (int) getBlue() + ";";
  }

  @Override
  public void initialize() {
    if (this.myAnimes.size() > 0) {
      this.start = myAnimes.get(0).getStartTime();
      this.end = myAnimes.get(myAnimes.size() - 1).getEndTime();
      this.red = myAnimes.get(0).getStartRed();
      this.green = myAnimes.get(0).getStartGreen();
      this.blue = myAnimes.get(0).getStartBlue();
      this.centerX = myAnimes.get(0).getStartX();
      this.centerY = myAnimes.get(0).getStartY();
      this.xRadius = myAnimes.get(0).getStartXdim() / 2;
      this.yRadius = myAnimes.get(0).getStartYdim() / 2;
    }
  }

  @Override
  public Posn shapePosn() {
    return new Posn((int) this.centerX, (int) this.centerY);
  }

  @Override
  public String getType() {
    return "C";
  }

  @Override
  public double shapeWidth() {
    return this.xRadius;
  }

  @Override
  public double shapeHeight() {
    return this.yRadius;
  }
}
