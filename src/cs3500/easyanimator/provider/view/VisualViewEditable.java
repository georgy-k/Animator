package cs3500.easyanimator.provider.view;

import javax.swing.JButton;

import cs3500.easyanimator.provider.AnimationController;
import cs3500.easyanimator.provider.model.IAnimation;

/**
 * Represents an animation in the visual format allowing the user to actually view the animation.
 * Also provides additional functionality allowing the user to edit the animation, add and remove
 * shapes, change the speed, pause, start, restart, and loop the animation.
 */
public class VisualViewEditable extends VisualView {

  private boolean isLoop;
  private JButton restart;
  private JButton start;
  private JButton pause;
  private JButton loop;
  private JButton speedUp;
  private JButton speedDown;
  private JButton removeKeyFrame;
  private JButton addKeyFrame;
  private JButton modifyKF;
  private JButton deleteShape;
  private JButton addShape;


  /**
   * Constructs the buttons which allow the user to edit the animation.
   *
   * @param model The animation model whose shapes are going to be animated.
   * @param speed The speed of the animation.
   */
  public VisualViewEditable(IAnimation model, int speed) {
    super(model, speed);
    this.isLoop = false;

    this.restart = new JButton("Restart");

    this.start = new JButton("Start/Resume");

    this.pause = new JButton("Pause");

    this.loop = new JButton("Loop");

    this.speedUp = new JButton("Increase Speed");

    this.speedDown = new JButton("Decrease Speed");

    this.removeKeyFrame = new JButton("Remove Key Frame");

    this.addKeyFrame = new JButton("Add Key Frame");

    this.modifyKF = new JButton("Modify Key Frame");

    this.deleteShape = new JButton("Delete Shape");

    this.addShape = new JButton("Add Shape");

    super.viewPanel.add(start);
    super.viewPanel.add(pause);
    super.viewPanel.add(restart);
    super.viewPanel.add(loop);
    super.viewPanel.add(speedUp);
    super.viewPanel.add(speedDown);
    super.viewPanel.add(removeKeyFrame);
    super.viewPanel.add(addKeyFrame);
    super.viewPanel.add(modifyKF);
    super.viewPanel.add(deleteShape);
    super.viewPanel.add(addShape);


    setVisible(true);
  }

  /**
   * Sets each button's action listener to the controller.
   *
   * @param controller The controller which handles what to do when a button is pressed.
   */
  @Override
  public void setActionListeners(AnimationController controller) {
    restart.addActionListener(controller);
    start.addActionListener(controller);
    pause.addActionListener(controller);
    loop.addActionListener(controller);
    speedUp.addActionListener(controller);
    speedDown.addActionListener(controller);
    removeKeyFrame.addActionListener(controller);
    addKeyFrame.addActionListener(controller);
    modifyKF.addActionListener(controller);
    deleteShape.addActionListener(controller);
    addShape.addActionListener(controller);
  }

  /**
   * Checks if the view is in a loop or not.
   *
   * @return Whether the animation is looping.
   */
  @Override
  public boolean getIsLoop() {
    return this.isLoop;
  }
}
