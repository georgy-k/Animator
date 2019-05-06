package cs3500.easyanimator.model;

/**
 * Implementation that contains specific information needed for a key frame.
 */
public class KeyFrame implements IKeyFrame {

  String name;
  public int time;
  public float xPos;
  public float yPos;
  public float xDim;
  public float yDim;
  public float red;
  public float green;
  public float blue;
  public boolean visible;


  /**
   * Constructs a key frame with given parameters.
   *
   * @param name    unique name of the shape
   * @param t       point of time
   * @param x       x position
   * @param y       y position
   * @param w       width of the shape
   * @param h       height of the shape
   * @param r       red value of RGB
   * @param g       green value of RGB
   * @param b       blue vaalue of RGB
   * @param visible was keyframe edited (does it have affect on animation)
   */
  public KeyFrame(String name, int t, float x, float y, float w,
                  float h, float r, float g, float b, boolean visible) {
    this.name = name;
    this.time = t;
    this.xPos = x;
    this.yPos = y;
    this.xDim = w;
    this.yDim = h;
    this.red = r;
    this.green = g;
    this.blue = b;
    this.visible = visible;

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public float getxPos() {
    return xPos;
  }

  public void setxPos(float xPos) {
    this.xPos = xPos;
  }

  public float getyPos() {
    return yPos;
  }

  public void setyPos(float yPos) {
    this.yPos = yPos;
  }

  public float getxDim() {
    return xDim;
  }

  public void setxDim(float xDim) {
    this.xDim = xDim;
  }

  public float getyDim() {
    return yDim;
  }

  public void setyDim(float yDim) {
    this.yDim = yDim;
  }

  public float getRed() {
    return red;
  }

  public void setRed(float red) {
    this.red = red;
  }

  public float getGreen() {
    return green;
  }

  public void setGreen(float green) {
    this.green = green;
  }

  public float getBlue() {
    return blue;
  }

  public void setBlue(float blue) {
    this.blue = blue;
  }

  public boolean getVisible() {
    return visible;
  }

  /**
   * Sets this key frame to be visible and affect current animation.
   */
  public void setVisible() {
    this.visible = true;
  }

  @Override
  public int compareTo(Object other) {
    if (other instanceof IKeyFrame) {
      IKeyFrame otherFrame = (IKeyFrame) other;
      return this.getTime() - otherFrame.getTime();
    } else {
      throw new IllegalArgumentException("wrong type");
    }

  }
}
