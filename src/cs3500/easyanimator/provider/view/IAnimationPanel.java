package cs3500.easyanimator.provider.view;

/**
 * Interface for the Visual view panel which implements the animate method to draw the panel.
 */
public interface IAnimationPanel {

  /**
   * Animate the animations that are happening at the current time.
   */
  void animate(int currentTime);

}
