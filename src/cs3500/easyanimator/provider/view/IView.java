
package cs3500.easyanimator.provider.view;

import cs3500.easyanimator.provider.AnimationController;

/**
 * Interface for our views which will have all its methods implemented by the different view classes
 * that implement this interface.
 */
public interface IView {

  /**
   * Displays the animation in the form required depending on which type of animation it is.
   */
  void display();

  /**
   * Animate the animations that are happening at the current time.
   *
   * @param currentTime The time at which the animation is being shown.
   */
  void animate(int currentTime);

  /**
   * Sets each button's action listener to the controller.
   *
   * @param controller The controller which handles what to do when a button is pressed.
   */
  void setActionListeners(AnimationController controller);

  /**
   * Checks if the view is in a loop or not.
   *
   * @return Whether the animation is looping.
   */
  boolean getIsLoop();

  /**
   * Paints the shapes.
   */
  void refresh();
}

