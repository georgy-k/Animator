package cs3500.easyanimator.view;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.Timer;

import cs3500.easyanimator.controller.EditListener;
import cs3500.easyanimator.model.Frame;


/**
 * A textual implementation of IView. Display() will append textual representation of the animations
 * onto provided Appendable "out" source.
 */
public class TextualView implements IOurView {


  private String text;
  Appendable textOutput;


  /**
   * Constructs the textual view of the object.
   *
   * @param text textual representation of animation received from the model.
   * @param out  Appendable object to append output to.
   */
  public TextualView(String text, Appendable out) {

    this.text = text;
    this.textOutput = out;


  }

  @Override
  public void start() {
    try {
      textOutput.append(text);
    } catch (IOException io) {
      throw new IllegalArgumentException("Unable to append output");
    }
  }

  @Override
  public void display() {
    // this view has nothing to display
  }

  @Override
  public void setEditListener(EditListener editListener) {
    // this view has no action event triggers
  }


  @Override
  public void setTimer(Timer timer) {
    // this view does not need timer
  }


  @Override
  public int getTickerVal() {
    //this view has no ticker val since it is not dynamic
    return 0;
  }


  @Override
  public void update(Frame[] frames) {
    // this view is not dynamic, therefore it can not update frames on the way
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    // this class has no action event triggers
  }


}


