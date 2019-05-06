package cs3500.easyanimator.view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import cs3500.easyanimator.model.Frame;
import cs3500.easyanimator.shape.ShapesModelAbs;

/**
 * Class represents a JPanel containing our animation.
 */
public class NextStep extends JPanel implements ActionListener {

  private int time;
  private Frame[] frames;
  private ArrayList<ShapesModelAbs> currentList;
  private int tickerVal;
  private boolean rewind;
  private boolean looping;


  /**
   * Constructs an initial animation of provided frames that will continuously refresh.
   *
   * @param frames set of frames to be displayed
   */
  public NextStep(Frame[] frames) {
    this.time = 0;
    this.frames = frames;
    this.currentList = frames[time % frames.length].getLos();
    this.tickerVal = 0;
    rewind = false;
    looping = false;
  }


  /**
   * Will draw the shape from frame corresponding to this point of time.
   *
   * @param g component that will represent shape.
   */
  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    currentList = frames[time % frames.length].getLos();

    for (ShapesModelAbs shape : currentList) {
      g2d.setColor(new Color((int) shape.getRed(), (int) shape.getGreen(), (int) shape.getBlue()));
      g2d.fill(shape.getShape());
    }

  }

  /**
   * Will handle action events that it is responsible for.
   *
   * @param ae action event.
   */
  @Override
  public void actionPerformed(ActionEvent ae) {
    switch (ae.getActionCommand()) {
      case "Repaint":
        if (rewind && time - tickerVal >= 0) {
          time = time - tickerVal;
          repaint();
        } else if (!rewind && time + tickerVal <= frames.length) {
          time = time + tickerVal;
          repaint();
        } else if (looping && rewind) {
          time = frames.length;
        } else if (looping && !rewind) {
          time = 0;
        }
        break;
      case "Rewind painting":
        rewind = true;
        break;
      case "Restart painting":
        time = 0;
        tickerVal = 0;
        break;
      case "Loop painting":
        looping = !looping;
        break;
      default:
        throw new IllegalArgumentException("This could not happen");
    }


  }


  /**
   * Starts the animation by making incrementation coefficient (tickerVal) to 1. It will start
   * counting frame index that needs to be shown
   */
  public void start() {
    tickerVal = 1;
    rewind = false;
  }

  /**
   * Updates the animation according to the received frames.
   *
   * @param frames Array of frames, each representing List of shapes at specific time.
   */
  public void updateFrames(Frame[] frames) {
    this.frames = frames;
  }

  public void increment() {
    this.time += tickerVal;
  }
}


