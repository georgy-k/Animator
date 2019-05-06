package cs3500.easyanimator.provider.view;


import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import cs3500.easyanimator.provider.AnimationController;
import cs3500.easyanimator.provider.model.IAnimation;


/**
 * Represents an animation in the visual format allowing the user to actually view the animation.
 */
public class VisualView extends JFrame implements IView {

  protected VisualViewPanel viewPanel;
  private JButton start;

  /**
   * Construct a canvas for the view panel.
   *
   * @param model The given model of this visual view.
   * @param speed The given speed time second per tick of the view.
   */
  public VisualView(IAnimation model, int speed) {
    super();
    this.setSize(360, 360);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.start = new JButton("Start");

    this.setLayout(new BorderLayout());
    viewPanel = new VisualViewPanel(model, speed);
    viewPanel.setPreferredSize(new Dimension(model.getWidth() + model.getLeftMost(),
            model.getHeight() + model.getTopMost()));
    this.add(viewPanel);

    JScrollPane scrollBar = new JScrollPane(viewPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.add(scrollBar);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());


    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setVisible(true);

    this.pack();

    this.viewPanel.add(start);
  }

  /**
   * Displays the animation in the visual form.
   */
  @Override
  public void display() {
    throw new UnsupportedOperationException("Unsupported Action");
  }

  /**
   * Sets each button's action listener to the controller.
   *
   * @param controller The controller which handles what to do when a button is pressed.
   */
  @Override
  public void setActionListeners(AnimationController controller) {
    start.addActionListener(controller);
  }

  /**
   * Checks if the view is in a loop or not.
   *
   * @return Whether the animation is looping.
   */
  @Override
  public boolean getIsLoop() {
    throw new UnsupportedOperationException("Unsupported Action");
  }

  /**
   * Paints the shapes.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

  /**
   * Animate the animations that are happening at the current time.
   *
   * @param currentTime The time at which the animation is being shown.
   */
  public void animate(int currentTime) {
    this.viewPanel.animate(currentTime);
  }
}
