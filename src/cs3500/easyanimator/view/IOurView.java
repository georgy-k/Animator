package cs3500.easyanimator.view;


import java.awt.event.ActionListener;


import javax.swing.Timer;

import cs3500.easyanimator.controller.EditListener;
import cs3500.easyanimator.model.Frame;

/**
 * Promise of behaviour of a view implementation.
 */
public interface IOurView extends ActionListener {


  /**
   * Sets editListener to the given editListener implementation. This editLister is implemented by
   * controller and will handle all edit key Frame fun-ty.
   *
   * @param editListener edit listener
   */
  void setEditListener(EditListener editListener);

  /**
   * Sets timer of this IView to the given Timer object.
   *
   * @param timer timer object
   */
  void setTimer(Timer timer);

  /**
   * Simple getter for current speed.
   *
   * @return Timer object
   */
  int getTickerVal();

  /**
   * Updates itself based on received frames.
   *
   * @param frames Array of frames, each representing List of shapes at specific time.
   */
  void update(Frame[] frames);

  /**
   * Starts the animation process.
   */
  void start();


  /**
   * Makes animation visible.
   */
  void display();


}
