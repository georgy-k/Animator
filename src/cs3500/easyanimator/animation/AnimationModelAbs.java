package cs3500.easyanimator.animation;

import cs3500.easyanimator.model.IKeyFrame;
import cs3500.easyanimator.model.KeyFrame;

/**
 * This abstract class used to contain common information for all possible animations.
 */
public abstract class AnimationModelAbs extends MotionAnimation {
  /**
   * Time the animation ends.
   */
  private int endTime;
  /**
   * Time the animation starts.
   */
  private int startTime;
  /**
   * The name of the shape being affected.
   */
  private String shapeName;

  IKeyFrame starting;
  IKeyFrame ending;

  /**
   * /** Constructor for an abstract animation.
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
  public AnimationModelAbs(int startTime, int endTime, String shapeName,
                           float startX, float endX, float startY, float endY, float startXdim,
                           float endXdim, float startYdim, float endYdim, float startRed,
                           float endRed, float startGreen, float endGreen, float startBlue,
                           float endBlue, float startRotation, float endRotation) {
    super(new KeyFrame(shapeName, startTime, startX, startY, startXdim,
                    startYdim, startRed, startGreen, startBlue, true),
            new KeyFrame(shapeName, endTime, endX, endY, endXdim, endYdim,
                    endRed, endGreen, endBlue, true));
    if (startTime > endTime) {
      throw new IllegalArgumentException("Start time must occur before end time");
    }

    this.startTime = startTime;
    this.endTime = endTime;

    this.shapeName = shapeName;

    starting = super.startKeyFrame;

    ending = super.endKeyFrame;

  }

  /**
   * Constructs animation based of beginning and ending Key Frames.
   *
   * @param starting KeyFrame representing start of this animation
   * @param ending   KeyFrame representing start of this animation
   */
  public AnimationModelAbs(KeyFrame starting, KeyFrame ending) {
    super(starting, ending);

    this.startTime = starting.getTime();
    this.endTime = ending.getTime();
    this.shapeName = starting.getName();
    this.starting = starting;
    this.ending = ending;
  }

  @Override
  public int getEndTime() {
    return endTime;
  }

  @Override
  public int getStartTime() {
    return startTime;
  }

  public String getName() {
    return shapeName;
  }

  public float getStartY() {
    return starting.getyPos();
  }

  public float getStartYdim() {
    return starting.getyDim();
  }

  public float getStartX() {
    return starting.getxPos();
  }

  public float getStartXdim() {
    return starting.getxDim();
  }

  public float getStartRed() {
    return starting.getRed();
  }

  public float getStartGreen() {
    return starting.getGreen();
  }

  public float getStartBlue() {
    return starting.getBlue();
  }

  public float getEndX() {
    return ending.getxPos();
  }

  public float getEndYdim() {
    return ending.getyDim();
  }

  public float getEndY() {
    return ending.getyPos();
  }

  public float getEndXdim() {
    return ending.getxDim();
  }

  public float getEndRed() {
    return ending.getRed();
  }

  public float getEndGreen() {
    return ending.getGreen();
  }

  public float getEndBlue() {
    return ending.getBlue();
  }


  /**
   * Check whether this animation is changing color of the shape.
   *
   * @return boolean
   */
  public boolean colorChanged() {
    return ((starting.getRed() - ending.getRed() != 0) ||
            (starting.getGreen() - ending.getGreen() != 0) ||
            (starting.getBlue() - ending.getBlue() != 0));
  }

  /**
   * Check whether this animation is changing size of the shape.
   *
   * @return boolean
   */
  public boolean sizeChanged() {
    return ((starting.getxDim() - ending.getxDim() != 0) ||
            (starting.getyDim() - ending.getyDim() != 0));
  }

  /**
   * Check whether this animation is changing size of the shape.
   *
   * @return boolean
   */
  public boolean positionChanged() {
    return ((starting.getxPos() - ending.getxPos()) != 0 ||
            (starting.getyPos() - ending.getyPos()) != 0);
  }

  @Override
  public int compareTo(Object other) {
    if (other instanceof AnimationModel) {
      AnimationModel animation = (AnimationModel) other;
      return this.getStartTime() - animation.getStartTime();
    } else {
      throw new IllegalArgumentException("wrong type");
    }

  }
}



