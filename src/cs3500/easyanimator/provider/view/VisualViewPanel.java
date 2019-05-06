package cs3500.easyanimator.provider.view;

import java.awt.Graphics;

import java.awt.Color;

import javax.swing.JPanel;

import cs3500.easyanimator.provider.model.IAnimation;
import cs3500.easyanimator.provider.model.IShape;
import cs3500.easyanimator.animation.MotionAnimation;

/**
 * Represents the panel for the view on which the animation will be shown.
 */
public class VisualViewPanel extends JPanel implements IAnimationPanel {

  private IAnimation model;

  /**
   * Constructs the panel for the visual view.
   *
   * @param model The animation model whose shapes are going to be animated.
   * @param speed The speed of the animation.
   */
  public VisualViewPanel(IAnimation model, int speed) {
    super();
    this.model = model;
    this.setBackground(Color.WHITE);
    this.setVisible(true);
  }

  /**
   * Animate the animations that are happening at the current time.
   */
  @Override
  public void animate(int currentTime) {
    for (int i = 0; i < this.model.getAnimations().size(); i++) {
      MotionAnimation a = this.model.getAnimations().get(i);
      if (currentTime >= a.startKeyFrame.time && currentTime <= a.endKeyFrame.time) {
        this.model.motionAnimate(a, currentTime);
      }
    }
  }

  /**
   * Draws each shape on the canvas.
   *
   * @param g The graphics used to set the color of each shape.
   */
  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    g.setColor(Color.WHITE);
    for (IShape shape : model.getShapes()) {
      if (shape.getType().equals("C")) {
        g.setColor(shape.shapeColor());
        g.fillOval(shape.shapePosn().getX(), shape.shapePosn().getY(),
                ((int) shape.shapeWidth()), ((int) shape.shapeHeight()));
      }
      if (shape.getType().equals("R")) {
        g.setColor(shape.shapeColor());

        g.fillRect(shape.shapePosn().getX(), shape.shapePosn().getY(),
                ((int) shape.shapeWidth()), ((int) shape.shapeHeight()));
      }
    }
  }
}
