package cs3500.easyanimator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cs3500.easyanimator.animation.AnimationModelAbs;
import cs3500.easyanimator.animation.MotionAnimation;
import cs3500.easyanimator.shape.AShape;
import cs3500.easyanimator.shape.Posn;
import cs3500.easyanimator.shape.ShapesModelAbs;

/**
 * Implementation of a ModelAdapter which will adopt our AnimatorModel (adaptee) to providers
 * model.
 */
public class ModelAdapter implements IModelAdapter {

  AnimatorModel adaptee;
  ArrayList<MotionAnimation> motions;
  ArrayList<AShape> shapesToDraw;


  /**
   * Constructs adapter with given instance of our model interface (AnimatorModel).
   *
   * @param adaptee instance of our model Interface
   */
  public ModelAdapter(AnimatorModel adaptee) {
    this.adaptee = adaptee;
    this.shapesToDraw = new ArrayList<>();


    this.motions = createMotions();
  }

  @Override
  public void animate(MotionAnimation animation) {
    // this method was intentionally left because it has nothing to do with providers edit view.
  }

  @Override
  public String animationState() {
    return null;
  }

  @Override
  public int getWidth() {
    return adaptee.getWidth();
  }

  @Override
  public void updateAllFrames() {
    this.motions = createMotions();

    adaptee.updateAllFrames();
  }

  /**
   * Will create motions that are rewuired by provider's edit view to render shapes.
   *
   * @return ArrayList of MotionAnimation
   */
  private ArrayList<MotionAnimation> createMotions() {
    ArrayList<MotionAnimation> animotions = new ArrayList<>();

    for (ShapesModelAbs shape : adaptee.getShapeTable().values()) {
      for (AnimationModelAbs motion : shape.getMyAnimes()) {
        animotions.add(motion);
      }
    }
    Collections.sort(animotions);
    return animotions;
  }

  @Override
  public Frame[] getFrames() {
    return adaptee.getFrames();
  }

  @Override
  public ArrayList<ShapesModelAbs> getStarterShapes() {
    return adaptee.getStarterShapes();
  }

  @Override
  public HashMap<String, ShapesModelAbs> getShapeTable() {
    return adaptee.getShapeTable();
  }

  @Override
  public String textOutput() {
    return adaptee.textOutput();
  }

  @Override
  public String shapeState(int t, String name) {
    return adaptee.shapeState(t, name);
  }

  @Override
  public void addAshape(String type, String name) {
    adaptee.addAshape(type, name);
  }

  @Override
  public void editKeyframe(String name, int time, int xPos, int yPos, int width,
                           int height, int r, int g, int b) {
    adaptee.editKeyframe(name, time, xPos, yPos, width,
            height, r, g, b);
  }

  @Override
  public void setKeyFrame(String name, int time) {
    adaptee.setKeyFrame(name, time);
  }

  @Override
  public void deleteKeyFrame(String name, int time) {
    adaptee.deleteKeyFrame(name, time);

  }

  @Override
  public int getXpos() {
    return adaptee.getXpos();
  }

  @Override
  public int getYpos() {
    return adaptee.getYpos();
  }

  @Override
  public int getHeight() {
    return adaptee.getHeight();
  }

  @Override
  public int getLeftMost() {
    return getXpos();
  }

  @Override
  public int getTopMost() {
    return getYpos();
  }

  @Override
  public ArrayList<AShape> getShapes() {
    return this.shapesToDraw;
  }

  @Override
  public ArrayList<MotionAnimation> getAnimations() {
    return this.motions;
  }

  @Override
  public void motionAnimate(MotionAnimation animation, int tick) {
    for (AShape shape : adaptee.getFrames()[tick - 1].getLos()) {
      if (shape.getName().equals(animation.getName())) {
        shapesToDraw.add(shape);
      }
    }
  }

  @Override
  public void addShapes(AShape s) {
    // this method doesn't need to be implemented beacuse it was substituted by what we already had.
  }

  @Override
  public void removeKey(String name, int time) {
    adaptee.deleteKeyFrame(name, time);

  }

  @Override
  public void addKey(String name, int time, Posn p, double w, double h, Color color) {
    adaptee.setKeyFrame(name, time);

  }

  @Override
  public void deleteShape(String name) {
    adaptee.deleteShape(name);
  }

  @Override
  public void addShape(Color color, Posn posn, String name, String type, double width,
                       double height, int appear, int disappear) {
    adaptee.addAshape(type, name);
  }

  @Override
  public void modifyKF(String name, int time, Posn p, double w, double h, Color c) {
    editKeyframe(name, time, p.getX(), p.getY(), (int) w, (int) h,
            c.getRed(), c.getGreen(), c.getBlue());
  }

  @Override
  public void clearPreviousShapes() {
    this.shapesToDraw = new ArrayList<>();
  }

  @Override
  public AnimatorModel getBaseModel() {
    return this.adaptee;
  }
}
