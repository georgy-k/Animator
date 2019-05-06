package cs3500.easyanimator.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JMenuItem;
import javax.swing.AbstractButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import cs3500.easyanimator.controller.EditListener;
import cs3500.easyanimator.model.Frame;

/**
 * Edit view implementation of IView with various edit menu options.
 *
 * <p>It contains animation (visual view) as a component.
 */
public class EditView implements IOurView, ActionListener {

  HashMap<String, AbstractButton> menuComponents = new HashMap<>();

  boolean looping;

  EditListener editListener;

  OurVisualView frame;

  int ticks;

  boolean startedOnce;


  /**
   * Construct an EditView.
   *
   * @param frames         frames to construct visual view component.
   * @param ticksPerSecond tick per second.
   * @param xPos           x position of the frame
   * @param yPos           y position of the frame
   * @param width          width of the window
   * @param height         height of the window
   */
  public EditView(Frame[] frames, int ticksPerSecond, int xPos, int yPos,
                  int width, int height) {
    frame = new OurVisualView(frames, ticksPerSecond, xPos, yPos,
            width, height);
    ticks = ticksPerSecond;


    JMenuBar menuBar = new JMenuBar();

    //Build the first menu.
    JMenu menu = new JMenu("Edit Options");
    menuBar.add(menu);

    //a group of JMenuItems
    JMenuItem menuItemSetKeyFrame = new JMenuItem("Set KeyFrame");
    menu.add(menuItemSetKeyFrame);
    menuComponents.put("Set KeyFrame", menuItemSetKeyFrame);

    JMenuItem menuItemEditKeyFrame = new JMenuItem("Edit KeyFrame");
    menu.add(menuItemEditKeyFrame);
    menuComponents.put("Edit KeyFrame", menuItemEditKeyFrame);

    JMenuItem menuItemDeleteKeyFrame = new JMenuItem("Delete KeyFrame");
    menu.add(menuItemDeleteKeyFrame);
    menuComponents.put("Delete KeyFrame", menuItemDeleteKeyFrame);

    JMenuItem menuItemUpdateFrames = new JMenuItem("Refresh Animation");
    menu.add(menuItemUpdateFrames);
    menuComponents.put("Refresh Animation", menuItemUpdateFrames);


    //Build second menu in the menu bar.
    menu = new JMenu("Animation Options");
    menu.getAccessibleContext().setAccessibleDescription(
            "This menu does nothing");
    JMenuItem menuItemResume = new JMenuItem("Resume");
    menu.add(menuItemResume);
    menuComponents.put("Resume", menuItemResume);

    JMenuItem menuItemPause = new JMenuItem("Pause");
    menuComponents.put("Pause", menuItemPause);
    menu.add(menuItemPause);

    JMenuItem menuItemRewind = new JMenuItem("Rewind");
    menuComponents.put("Rewind", menuItemRewind);
    menu.add(menuItemRewind);

    JMenuItem menuItemRestart = new JMenuItem("Restart");
    menuComponents.put("Restart", menuItemRestart);
    menu.add(menuItemRestart);

    JMenuItem menuItemLooping = new JRadioButtonMenuItem("Looping?");
    menu.add(menuItemLooping);
    menuComponents.put("Looping?", menuItemLooping);
    menuItemLooping.setSelected(false);


    menuBar.add(menu);

    JMenuItem menuItemSlower = new JMenuItem("Slower");
    menuBar.add(menuItemSlower, BorderLayout.EAST);
    menuComponents.put("Slower", menuItemSlower);

    JMenuItem menuItemFaster = new JMenuItem("Faster");
    menuBar.add(menuItemFaster, BorderLayout.EAST);
    menuComponents.put("Faster", menuItemFaster);


    frame.setJMenuBar(menuBar);

    looping = false;

    JButton startButton = new JButton("Start");
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(startButton);
    startButton.setSize(30, 15);
    menuComponents.put("Start", startButton);

    frame.add(startButton, BorderLayout.PAGE_END);

    setListeners();

  }


  /**
   * Sets timer of this view to the given timer.
   *
   * @param timer Timer object
   */
  public void setTimer(Timer timer) {
    frame.setTimer(timer);
  }

  @Override
  public void setEditListener(EditListener editListener) {
    this.editListener = editListener;

  }

  @Override
  public int getTickerVal() {
    return this.ticks;
  }


  @Override
  public void update(Frame[] frames) {
    frame.update(frames);
  }


  /**
   * Adds EditView as a listener to all its menu components.
   */
  private void setListeners() {
    for (String componentName : menuComponents.keySet()) {
      menuComponents.get(componentName).addActionListener(this);
    }
  }


  /**
   * Parser to request parameters needed to Set Key Frame. It will extract needed parameters and
   * call editListener.setFrame method with received params.
   */
  private void askForFrameParams() {
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
   * Parser to request parameters needed to Edit Key Frame.
   * <p>It will extract needed parameters and call editListener.editKeyFrame method with received
   * params</p>.
   */
  private void editParams() {
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
  public void deleteParams() {
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
        popUp.dispose();
      }
    });

    p.add(submit, BorderLayout.PAGE_END);
    popUp.add(p);
    popUp.setVisible(true);

  }

  @Override
  public void start() {
    frame.start();
  }

  @Override
  public void display() {
    frame.display();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Looping?":
        editListener.looping();
        break;
      case "Slower":
        editListener.changeSpeed(5, true);
        break;
      case "Faster":
        editListener.changeSpeed(5, false);
        break;
      case "Start":
        editListener.start();
        startedOnce = true;
        menuComponents.get("Start").setVisible(false);
        break;
      case "Resume":
        break;
      case "Pause":
        editListener.pause();
        break;
      case "Rewind":
        editListener.rewind();
        break;
      case "Restart":
        editListener.restart();
        menuComponents.get("Start").setVisible(true);
        break;
      case "Edit KeyFrame":
        editParams();
        break;
      case "Delete KeyFrame":
        deleteParams();
        break;
      case "Set KeyFrame":
        askForFrameParams();
        break;
      case "Refresh Animation":
        editListener.refreshAnimations();
        menuComponents.get("Start").setVisible(true);
        break;
      default:
        frame.actionPerformed(e);
    }
  }
}

