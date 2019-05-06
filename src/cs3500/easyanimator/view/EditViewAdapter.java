package cs3500.easyanimator.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import cs3500.easyanimator.controller.EditListener;
import cs3500.easyanimator.model.Frame;
import cs3500.easyanimator.provider.view.IView;

/**
 * Adapter class for provider's edit view.
 *
 * <p>It wall add functionality of parsing arguments which is required by our controller.</p>
 */
public class EditViewAdapter implements IOurView, ActionListener {

  IView adaptee;
  EditListener editListener;
  int tickVal;
  int currentTime;
  boolean looping;

  /**
   * Creates adapter class containing providers edit view.
   *
   * @param adaptee providers edit view
   * @param tickVal initial speed
   */
  public EditViewAdapter(IView adaptee, int tickVal) {
    this.adaptee = adaptee;
    this.tickVal = tickVal;
    this.looping = false;


  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case ("Parse edit params"):
        askForEditParams();
        break;
      case ("Parse set params"):
        askForSetParams();
        break;
      case ("Parse delete params"):
        askForDeleteParams();
        break;
      case ("Parse delete shape params"):
        deleteShapeParams();
        break;
      case ("Parse add shape params"):
        addShapeParams();
        break;
      case ("Repaint"):
        if (currentTime == editListener.getAnimationEnd()) {
          if (this.looping) {
            this.currentTime = 0;
            adaptee.animate(currentTime);
            currentTime += 1;
            adaptee.refresh();
          } else {
            editListener.restart();
            editListener.pause();
          }
        } else {
          adaptee.animate(currentTime);
          currentTime += 1;
          adaptee.refresh();
        }
        break;
      case ("Restart painting"):
        this.currentTime = 0;
        adaptee.animate(currentTime);
        currentTime += 1;
        adaptee.refresh();
        editListener.start();
        break;
      case ("Loop painting"):
        this.looping = !looping;
        break;
      default:
        throw new IllegalArgumentException("This could not happen, wrong button was pressed");

    }

  }

  /**
   * Parser to request parameters needed to Set Key Frame.
   *
   * <p>It will extract needed parameters and call editListener.setFrame method with received
   * params.</p>
   */
  private void askForSetParams() {
    JPanel p = new JPanel(new BorderLayout(5, 5));

    JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
    labels.add(new JLabel("Shape Name", SwingConstants.RIGHT));
    labels.add(new JLabel("Time", SwingConstants.RIGHT));
    p.add(labels, BorderLayout.WEST);

    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
    JTextField shapeName = new JTextField();
    //shapeName.addActionListener(setNameLis);
    controls.add(shapeName);
    JTextField time = new JTextField();
    //time.addActionListener(setTimeLis);
    controls.add(time);
    p.add(controls, BorderLayout.CENTER);

    //LayoutManager l = new GroupLayout(p);
    //p.setLayout(l);
    JFrame popUp = new JFrame();
    popUp.setSize(200, 200);


    JButton submit = new JButton("Submit changes");
    JPanel buttonPanel = new JPanel();

    buttonPanel.add(submit);
    submit.setSize(30, 15);
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        editListener.setKeyFrame(shapeName.getText(), time.getText());
        popUp.dispose();
      }
    });
    p.add(submit, BorderLayout.PAGE_END);
    popUp.add(p);
    popUp.setVisible(true);
  }

  /**
   * Parser to request parameters needed to Add a shape.
   *
   * <p>It will extract needed parameters and call editListener.addShape method with received
   * params.</p>
   */
  private void addShapeParams() {
    JPanel p = new JPanel(new BorderLayout(5, 5));

    JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
    labels.add(new JLabel("Rectangle or Ellipse?", SwingConstants.RIGHT));
    labels.add(new JLabel("Shape Name", SwingConstants.RIGHT));
    p.add(labels, BorderLayout.WEST);

    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
    JTextField shapeName = new JTextField();
    //shapeName.addActionListener(setNameLis);
    controls.add(shapeName);
    JTextField time = new JTextField();
    //time.addActionListener(setTimeLis);
    controls.add(time);
    p.add(controls, BorderLayout.CENTER);

    //LayoutManager l = new GroupLayout(p);
    //p.setLayout(l);
    JFrame popUp = new JFrame();
    popUp.setSize(200, 200);


    JButton submit = new JButton("Submit changes");
    JPanel buttonPanel = new JPanel();

    buttonPanel.add(submit);
    submit.setSize(30, 15);
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        editListener.addShape(shapeName.getText(), time.getText());
        editListener.updateFrames();
        popUp.dispose();
      }
    });
    p.add(submit, BorderLayout.PAGE_END);
    popUp.add(p);
    popUp.setVisible(true);
  }


  /**
   * Parser to request parameters needed to delete a shape.
   *
   * <p>It will extract needed params and call editListener.deleteShape method with extracted
   * params.</p>
   */
  private void deleteShapeParams() {
    JPanel p = new JPanel(new BorderLayout(5, 5));

    JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
    labels.add(new JLabel("Shape Name", SwingConstants.RIGHT));
    p.add(labels, BorderLayout.WEST);

    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
    JTextField shapeName = new JTextField();
    //shapeName.addActionListener(setNameLis);
    controls.add(shapeName);
    p.add(controls, BorderLayout.CENTER);

    //LayoutManager l = new GroupLayout(p);
    //p.setLayout(l);
    JFrame popUp = new JFrame();
    popUp.setSize(200, 200);


    JButton submit = new JButton("Submit changes");
    JPanel buttonPanel = new JPanel();

    buttonPanel.add(submit);
    submit.setSize(30, 15);
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        editListener.deleteShape(shapeName.getText());
        editListener.updateFrames();
        popUp.dispose();
      }
    });
    p.add(submit, BorderLayout.PAGE_END);
    popUp.add(p);
    popUp.setVisible(true);
  }


  /**
   * Parser to request parameters needed to Edit Key Frame.
   *
   * <p>It will extract needed parameters and call editListener.editKeyFrame method with received
   * params.</p>
   */
  private void askForEditParams() {
    JPanel p = new JPanel(new BorderLayout(5, 5));

    JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
    labels.add(new JLabel("Shape Name", SwingConstants.RIGHT));
    labels.add(new JLabel("Time", SwingConstants.RIGHT));
    labels.add(new JLabel("X", SwingConstants.RIGHT));
    labels.add(new JLabel("Y", SwingConstants.RIGHT));
    labels.add(new JLabel("Width", SwingConstants.RIGHT));
    labels.add(new JLabel("Height", SwingConstants.RIGHT));
    labels.add(new JLabel("Red", SwingConstants.RIGHT));
    labels.add(new JLabel("Green", SwingConstants.RIGHT));
    labels.add(new JLabel("Blue", SwingConstants.RIGHT));
    p.add(labels, BorderLayout.WEST);

    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
    JTextField shapeName = new JTextField();
    //shapeName.addActionListener(setNameLis);
    controls.add(shapeName);
    JTextField time = new JTextField();
    //time.addActionListener(setTimeLis);
    controls.add(time);
    JTextField x = new JTextField();
    controls.add(x);
    JTextField y = new JTextField();
    controls.add(y);
    JTextField xdim = new JTextField();
    controls.add(xdim);
    JTextField ydim = new JTextField();
    controls.add(ydim);
    JTextField r = new JTextField();
    controls.add(r);
    JTextField g = new JTextField();
    controls.add(g);
    JTextField b = new JTextField();
    controls.add(b);
    p.add(controls, BorderLayout.CENTER);

    //LayoutManager l = new GroupLayout(p);
    //p.setLayout(l);
    JFrame popUp = new JFrame();
    popUp.setSize(200, 500);


    JButton submit = new JButton("Submit changes");
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(submit);
    submit.setSize(30, 15);
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Was here");
        editListener.editKeyFrame(shapeName.getText(), time.getText(), x.getText(), y.getText(),
                xdim.getText(), ydim.getText(), r.getText(), g.getText(), b.getText());
        editListener.updateFrames();
        popUp.dispose();
      }
    });

    p.add(submit, BorderLayout.PAGE_END);
    popUp.add(p);
    popUp.setVisible(true);

  }

  /**
   * Parser for required arguments for deleting key frame functionality. It will extract needed
   * parameters and call editListener.deleteKeyFrame method with received params.
   */
  public void askForDeleteParams() {
    JPanel p = new JPanel(new BorderLayout(5, 5));

    JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
    labels.add(new JLabel("Shape Name", SwingConstants.RIGHT));
    labels.add(new JLabel("Time", SwingConstants.RIGHT));
    p.add(labels, BorderLayout.WEST);

    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
    JTextField shapeName = new JTextField();
    //shapeName.addActionListener(setNameLis);
    controls.add(shapeName);
    JTextField time = new JTextField();
    //time.addActionListener(setTimeLis);
    controls.add(time);
    p.add(controls, BorderLayout.CENTER);

    //LayoutManager l = new GroupLayout(p);
    //p.setLayout(l);
    JFrame popUp = new JFrame();
    popUp.setSize(200, 200);


    JButton submit = new JButton("Submit changes");
    JPanel buttonPanel = new JPanel();

    buttonPanel.add(submit);
    submit.setSize(30, 15);
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        editListener.deleteKeyFrame(shapeName.getText(), time.getText());
        editListener.updateFrames();
        popUp.dispose();
      }
    });

    p.add(submit, BorderLayout.PAGE_END);
    popUp.add(p);
    popUp.setVisible(true);

  }

  @Override
  public void setEditListener(EditListener editListener) {
    this.editListener = editListener;
    this.adaptee.setActionListeners(editListener);
  }

  @Override
  public void setTimer(Timer timer) {
    // Has to be here because adapter needs to implement our View interface, but not necessarily
    // needs to implement all methods
    // This method doesn't need to be implemented since that's an adapter class and timer doesn't
    // belong here.
  }

  @Override
  public int getTickerVal() {
    return this.tickVal;
  }

  @Override
  public void update(Frame[] frames) {
    // Has to be here because adapter needs to implement our View interface, but not necessarily
    // needs to implement all methods.
    // Given edit view gets updates from model directly, so this method has nothing to do.
  }

  @Override
  public void start() {
    // Has to be here because adapter needs to implement our View interface, but not necessarily
    // needs to implement all methods.
  }

  @Override
  public void display() {
    // Has to be here because adapter needs to implement our View interface, but not necessarily
    // needs to implement all methods.
  }
}