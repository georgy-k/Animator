package cs3500.easyanimator.shape;

/**
 * Class representing a simple position.
 */
public class Posn {
  int xPos;
  int yPos;

  /**
   * Creates a position.
   *
   * @param xPos x position
   * @param yPos y position
   */
  public Posn(int xPos, int yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
  }

  public int getX() {
    return this.xPos;
  }


  public int getY() {
    return this.yPos;
  }

}
