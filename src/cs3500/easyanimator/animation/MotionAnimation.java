package cs3500.easyanimator.animation;

import cs3500.easyanimator.model.KeyFrame;

/**
 * Class representing an animation (required by provider's editor view).
 */
public abstract class MotionAnimation implements AnimationModel {

  public KeyFrame startKeyFrame;
  public KeyFrame endKeyFrame;

  /**
   * Constructs animation based of beginning and ending Key Frames.
   *
   * @param start KeyFrame representing start of this animation
   * @param end   KeyFrame representing start of this animation
   */
  public MotionAnimation(KeyFrame start, KeyFrame end) {
    if (!start.getName().equals(end.getName())) {
      throw new IllegalArgumentException("KeyFrames must refer to the same Shape");
    } else if (start.getTime() > end.getTime()) {
      throw new IllegalArgumentException("The starting time may not be greater than " +
              "the ending time");
    }
    this.startKeyFrame = start;
    this.endKeyFrame = end;
  }

}
