package cs3500.easyanimator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import cs3500.easyanimator.model.AnimatorModel;
import cs3500.easyanimator.view.IOurView;

/**
 * Class representing a controller needed to handle set/edit/delete key frame operations.
 */
public class Controller implements EditListener {
  AnimatorModel model;
  IOurView view;
  Timer timer;
  int currentTickVal;


  /**
   * Constructs a controller.
   *
   * <p>Creates a tier to be in control of animation. Sets its timer to be visual views timer.
   *
   * @param model instance of Animator Model
   * @param view  Instance of IView
   */
  public Controller(AnimatorModel model, IOurView view) {
    this.model = model;
    this.currentTickVal = view.getTickerVal();

    this.timer = new Timer(1000 / currentTickVal, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.clearPreviousShapes();
        view.actionPerformed(new ActionEvent(new JButton("Repaint"),
                ActionEvent.ACTION_PERFORMED, "Repaint"));
      }
    });
    this.view = view;
    view.display();
    view.setTimer(this.timer);
    view.setEditListener(this);


  }

  @Override
  public void setKeyFrame(String name, String time) {
    try {
      model.setKeyFrame(name, Integer.parseInt(time));
    } catch (NumberFormatException nfe) {
      JOptionPane.showMessageDialog(
              null, "Provide Key Frame parameters", "Error",
              JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException iae) {
      JOptionPane.showMessageDialog(
              null, iae.getMessage(), "Error",
              JOptionPane.ERROR_MESSAGE);
    }
  }


  @Override
  public void deleteKeyFrame(String name, String time) {
    try {

      model.deleteKeyFrame(name, Integer.parseInt(time));
    } catch (NumberFormatException nfe) {
      JOptionPane.showMessageDialog(
              null, null, "Provide Key Frame parameters",
              JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException iae) {
      JOptionPane.showMessageDialog(
              null, iae.getMessage(), "Error",
              JOptionPane.ERROR_MESSAGE);
    }
  }


  @Override
  public void editKeyFrame(String name, String time, String x, String y, String xdim, String ydim,
                           String r, String g, String b) {
    try {
      model.editKeyframe(name, Integer.parseInt(time), Integer.parseInt(x), Integer.parseInt(y),
              Integer.parseInt(xdim), Integer.parseInt(ydim), Integer.parseInt(r),
              Integer.parseInt(g), Integer.parseInt(b));
    } catch (NumberFormatException nfe) {
      JOptionPane.showMessageDialog(
              null, null, "Provide Key Frame parameters",
              JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException iae) {
      JOptionPane.showMessageDialog(
              null, iae.getMessage(), "Error",
              JOptionPane.ERROR_MESSAGE);
    }

    model.updateAllFrames();
    view.update(model.getFrames());
    restart();

  }


  @Override
  public void saveWork(String type, String filename) {
    // Was planning to add this extra functionality but run out of time.
  }

  @Override
  public void openWork(String type, String filename) {
    // Was planning to add this extra functionality but run out of time.
  }

  @Override
  public void updateFrames() {
    model.updateAllFrames();
  }

  @Override
  public void deleteShape(String name) {
    this.model.deleteShape(name);
  }

  @Override
  public void addShape(String type, String name) {
    model.addAshape(type, name);
  }

  @Override
  public void refreshAnimations() {
    model.updateAllFrames();
    view.update(model.getFrames());
    restart();
  }

  @Override
  public void pause() {
    this.timer.stop();

  }

  @Override
  public void rewind() {
    view.actionPerformed(new ActionEvent(new JButton("Rewind painting"),
            ActionEvent.ACTION_PERFORMED, "Rewind painting"));

  }

  @Override
  public void resume() {
    this.timer.start();

  }

  @Override
  public void restart() {
    view.actionPerformed(new ActionEvent(new JButton("Restart painting"),
            ActionEvent.ACTION_PERFORMED, "Restart painting"));

  }

  @Override
  public void start() {
    System.out.println("was here");
    this.timer.start();
    view.start();

  }

  @Override
  public void looping() {
    view.actionPerformed(new ActionEvent(new JButton("Loop painting"),
            ActionEvent.ACTION_PERFORMED, "Loop painting"));

  }

  @Override
  public void changeSpeed(int val, boolean slower) {
    if (slower && currentTickVal > val) {
      currentTickVal -= val;
      timer.setDelay(1000 / (currentTickVal));
    } else {
      currentTickVal += val;
      timer.setDelay(1000 / (currentTickVal + val));
    }

  }

  @Override
  public int getTickerVal() {
    return this.currentTickVal;
  }

  @Override
  public int getAnimationEnd() {
    int end = 0;
    for (int i = 0; i < model.getFrames().length; i++) {
      end = i;
    }
    return end;
  }

  //////
  ///////////////  This part was created to operate with providers view ////////////////
  /////
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case ("Restart"):
        this.restart();
        break;
      case ("Start"):
        this.start();
        break;

      case ("Start/Resume"):
        this.start();
        break;

      case ("Pause"):
        this.pause();
        break;

      case ("Loop"):
        this.looping();
        break;

      case ("Increase Speed"):
        this.changeSpeed(5, false);
        break;

      case ("Decrease Speed"):
        this.changeSpeed(5, false);
        break;

      case ("Remove Key Frame"):
        view.actionPerformed(new ActionEvent(new JButton("Parse delete params"),
                ActionEvent.ACTION_PERFORMED, "Parse delete params"));
        break;

      case ("Add Key Frame"):
        view.actionPerformed(new ActionEvent(new JButton("Parse set params"),
                ActionEvent.ACTION_PERFORMED, "Parse set params"));
        break;

      case ("Modify Key Frame"):
        view.actionPerformed(new ActionEvent(new JButton("Parse edit params"),
                ActionEvent.ACTION_PERFORMED, "Parse edit params"));
        break;
      case ("Delete Shape"):
        view.actionPerformed(new ActionEvent(new JButton("Parse delete shape params"),
                ActionEvent.ACTION_PERFORMED, "Parse delete shape params"));
        break;
      case ("Add Shape"):
        view.actionPerformed(new ActionEvent(new JButton("Parse add shape params"),
                ActionEvent.ACTION_PERFORMED, "Parse add shape params"));
        break;
      default:
        throw new IllegalArgumentException("This could not happen. Invalid button pressed");
    }


  }
}
