package cs3500.easyanimator.controller;

/**
 * Interface representing general functionality that needs to be supported by controller.
 */
public interface IController {

  /**
   * Refreshes animations.
   */
  void refreshAnimations();

  /**
   * Sets animation on pause.
   */
  void pause();

  /**
   * Plays animation from end to start.
   */
  void rewind();

  /**
   * Resumes the animation.
   */
  void resume();

  /**
   * Restarts the animation.
   */
  void restart();

  /**
   * Starts the animation.
   */
  void start();

  /**
   * Makes animation loop.
   */
  void looping();

  /**
   * Deacreases/Increases speed of animation.
   *
   * @param val    by how much needs to be changed.
   * @param slower is it slowing the animation.
   */
  void changeSpeed(int val, boolean slower);

  /**
   * Returns current speed of animation.
   *
   * @return int - ticks per second.
   */
  int getTickerVal();

  /**
   * Returns current end time of the animation.
   *
   * @return index of last frame to be shown
   */
  int getAnimationEnd();
}
