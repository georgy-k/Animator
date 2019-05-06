package cs3500.easyanimator.view;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JScrollPane;

import cs3500.easyanimator.controller.EditListener;
import cs3500.easyanimator.model.Frame;

/**
 * Visual implementation of IView. Display() will produce JFrame containing JPanel, which is visual
 * representation of our Frame[] received from the model.
 */
public class OurVisualView extends JFrame implements IOurView {

  private int ticksPerSecond;

  private Timer timer;

  private NextStep panel;


  /**
   * This constructs a visual view of the model.
   *
   * @param ticksPerSecond seconds per tick
   * @param frames         frames to be shown
   * @param xPos           x position of the image
   * @param yPos           y position of the image
   * @param width          width of the canvas
   * @param height         height of the canvas
   */
  public OurVisualView(Frame[] frames, int ticksPerSecond, int xPos, int yPos,
                       int width, int height) {

    setSize(width + 50, height + 50);
    setLocation(xPos, yPos);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.ticksPerSecond = ticksPerSecond;
    this.timer = new Timer(15 / ticksPerSecond, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        repaint();
        panel.increment();
      }
    });

    this.panel = new NextStep(frames);
    panel.setPreferredSize(new Dimension(width, height));
    panel.setLocation(xPos, yPos);
    panel.setVisible(true);
    JScrollPane scrollBar = new JScrollPane(panel);
    this.add(scrollBar);

  }

  public void setTimer(Timer timer) {
    this.timer = timer;
  }

  @Override
  public void setEditListener(EditListener editListener) {
    // this implementation of View interface has no action event triggers
  }

  @Override
  public int getTickerVal() {
    return this.ticksPerSecond;
  }


  @Override
  public void start() {
    timer.start();
    panel.start();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public void update(Frame[] frames) {
    panel.updateFrames(frames);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    panel.actionPerformed(e);

  }


}
