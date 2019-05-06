package cs3500.easyanimator.animation;

import cs3500.easyanimator.model.KeyFrame;
import cs3500.easyanimator.shape.ShapesModelAbs;

/**
 * Respresentation of animation model that can move, change color and size.
 */
public class EasyAnimation extends AnimationModelAbs {


  /**
   * Constructor for an easy animation.
   *
   * @param startTime     start time of this animation
   * @param endTime       end time of this animation
   * @param shapeName     unique shape name to operate with
   * @param startX        start X position of a shape
   * @param startY        start Y position of a shape
   * @param startXdim     start X dimension of a shape
   * @param startYdim     start Y dimension of a shape
   * @param startRed      start Red value of RGB
   * @param startGreen    start Green value of RGB
   * @param startBlue     start Green value of RGB
   * @param startRotation start rotation ngle
   * @param endX          end X position of a shape
   * @param endY          end y position of a shape
   * @param endXdim       end X dimension of a shape
   * @param endYdim       end Y dimension of a shape
   * @param endRed        end Red value of RGB
   * @param endGreen      end Green value of RGB
   * @param endBlue       send Blue value of RGB
   * @param endRotation   end rotation Angle
   */
  public EasyAnimation(int startTime, int endTime, String shapeName, float startX, float startY,
                       float startXdim, float startYdim, float startRed, float startGreen,
                       float startBlue, float startRotation, float endX, float endY,
                       float endXdim, float endYdim, float endRed, float endGreen, float endBlue,
                       float endRotation) {
    super(startTime, endTime, shapeName, startX, endX, startY, endY, startXdim, endXdim,
            startYdim, endYdim, startRed, endRed, startGreen, endGreen, startBlue, endBlue,
            startRotation, endRotation);
  }

  public EasyAnimation(KeyFrame starting, KeyFrame ending) {
    super(starting, ending);
  }

  @Override
  public ShapesModelAbs run(ShapesModelAbs shape, int i) {
    String name = shape.getName();
    int start = shape.getStart();
    int end = shape.getEnd();
    float xPos = shape.getStartX();
    float yPos = shape.getStartY();
    float xDim = shape.getStartXdim();
    float yDim = shape.getStartYdim();
    float red = shape.getRed();
    float green = shape.getGreen();
    float blue = shape.getBlue();
    float rotation = shape.getRotation();

    if (positionChanged()) {
      ShapesModelAbs clone = move(shape, i);
      xPos = clone.getStartX();
      yPos = clone.getStartY();
    }
    if (sizeChanged()) {
      ShapesModelAbs clone = resize(shape, i);
      xDim = clone.getStartXdim();
      yDim = clone.getStartYdim();
    }
    if (colorChanged()) {
      ShapesModelAbs clone = recolor(shape, i);
      red = clone.getRed();
      green = clone.getGreen();
      blue = clone.getBlue();
    }

    return shape.build(name, start, end, xPos, yPos, xDim, yDim, red, green, blue, rotation);

  }

  @Override
  public String getDescription() {
    return "motion " + this.getName() + " " + this.getStartTime() + " " + (int) getStartX() + " "
            + (int) getStartY() + " " + (int) getStartXdim() + " " + (int) getStartYdim() + " " +
            (int) getStartRed() + " " + (int) getStartGreen() + " " + (int) getStartBlue() +
            "    " +
            getEndTime() + " " + (int) getEndX() + " " + (int) getEndY()
            + " " + (int) getEndXdim() + " " + (int) getEndYdim() + " " + (int) getEndRed() +
            " " +
            (int) getEndGreen() + " " + (int) getEndBlue() + "\n";
  }


  /**
   * Constructs a clone of a shape that has modified coordinates.
   *
   * @param s a shape to be moved
   * @param t current point of time
   * @return a shape whose coordinates are correct to this specific point of time (t)
   */
  private ShapesModelAbs move(ShapesModelAbs s, int t) {
    if (t >= this.getStartTime() && t <= this.getEndTime()) {
      if ((s.getStartX() - this.getStartX()) > 0.0001 ||
              (s.getStartY() - this.getStartY()) > 0.0001) {
        throw new IllegalArgumentException("Shape must have same start position as the animation");
      } else {

        float newX = newXY(t)[0];
        float newY = newXY(t)[1];

        return s.build(s.getName(), s.getStart(), s.getEnd(), newX,
                newY, s.getStartXdim(), s.getStartYdim(), s.getRed(),
                s.getGreen(), s.getBlue(), s.getRotation());
      }
    } else {
      return s;
    }
  }

  /**
   * Creates new X and Y pos that are correct for this specific animation at time t.
   *
   * @param t point of time
   * @return float[2], where first element is X pos and second element is Y pos.
   */
  private float[] newXY(int t) {
    float[] cords = new float[2];
    float centerX = (this.getStartX() + (getEndX() - this.getStartX()) /
            (this.getEndTime() - this.getStartTime()) *
            (t - this.getStartTime()));
    float centerY = (this.getStartY() + (getEndY() - this.getStartY()) /
            (this.getEndTime() - this.getStartTime()) *
            (t - this.getStartTime()));

    cords[0] = centerX;
    cords[1] = centerY;
    return cords;
  }

  /**
   * Constructs a clone of a shape that has modified color params.
   *
   * @param s a shape to be recolored
   * @param t current point of time
   * @return a shape whose color parameters are correct to this specific point of time (t)
   */
  private ShapesModelAbs recolor(ShapesModelAbs s, int t) {
    if (t >= this.getStartTime() && t <= this.getEndTime()) {
      if ((s.getRed() - this.getStartRed()) > 0.0001 ||
              (s.getGreen() - this.getStartGreen()) > 0.0001 ||
              (s.getBlue() - this.getStartBlue()) > 0.0001) {
        throw new IllegalArgumentException("Shape must have same starting RGB as the animation");
      } else {
        float newR = newColors(t)[0];
        float newG = newColors(t)[1];
        float newB = newColors(t)[2];

        return s.build(s.getName(), s.getStart(), s.getEnd(), s.getStartX(),
                s.getStartY(), s.getStartXdim(), s.getStartYdim(), newR,
                newG, newB, s.getRotation());

      }
    } else {
      return s;
    }
  }

  /**
   * Creates new R, G and B values that are correct for this specific animation at time t.
   *
   * @param t point of time
   * @return float[3], where 1st element is R value, 2nd element is G value and 3d element is B val.
   */
  private float[] newColors(int t) {
    float[] colors = new float[3];
    float r = (this.getStartRed() + (getEndRed() - getStartRed()) /
            (this.getEndTime() - this.getStartTime()) * (t - getStartTime()));
    float g = (this.getStartGreen() + (getEndGreen() - getStartGreen()) /
            (this.getEndTime() - this.getStartTime()) * (t - getStartTime()));
    float b = (this.getStartBlue() + ((int) Math.floor((getEndBlue() - getStartBlue()) /
            (this.getEndTime() - this.getStartTime()) * (t - getStartTime()))));
    colors[0] = r;
    colors[1] = g;
    colors[2] = b;

    return colors;
  }

  /**
   * Constructs a clone of a shape that has modified size params.
   *
   * @param s a shape to be recolored
   * @param t current point of time
   * @return a shape whose size parameters are correct to this specific point of time (t)
   */
  private ShapesModelAbs resize(ShapesModelAbs s, int t) {
    if (t >= this.getStartTime() && t <= this.getEndTime()) {
      if ((s.getStartXdim() - this.getStartXdim()) > 0.0001 ||
              (s.getStartYdim() - this.getStartYdim()) > 0.0001) {
        throw new IllegalArgumentException("Shape must have same starting size as the animation");
      } else {
        float newXdim = newSize(t)[0];
        float newYdim = newSize(t)[1];

        return s.build(s.getName(), s.getStart(), s.getEnd(), s.getStartX(),
                s.getStartY(), newXdim, newYdim, s.getRed(),
                s.getGreen(), s.getBlue(), s.getRotation());
      }
    } else {
      return s;
    }
  }

  /**
   * Creates new Xdim and Ydim that are correct for this specific animation at time t.
   *
   * @param t point of time
   * @return float[2], where 1st element is Xdim value, 2nd element is Ydim.
   */
  private float[] newSize(int t) {
    float[] dims = new float[2];
    float xDim = (this.getStartXdim() + (getEndXdim() - getStartXdim()) /
            (this.getEndTime() - this.getStartTime()) *
            (t - this.getStartTime()));
    float yDim = (this.getStartYdim() + (getEndYdim() - getStartYdim()) /
            (this.getEndTime() - this.getStartTime()) *
            (t - this.getStartTime()));
    dims[0] = xDim;
    dims[1] = yDim;
    return dims;
  }


}
