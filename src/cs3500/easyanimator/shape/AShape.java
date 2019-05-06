package cs3500.easyanimator.shape;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.easyanimator.animation.MotionAnimation;
import cs3500.easyanimator.model.KeyFrame;
import cs3500.easyanimator.provider.model.IShape;

/**
 * Abstract class representing a shape (was required by providers model).
 */
public abstract class AShape implements IShape {
  // Existence of this class was forced by providers code, because they were relying on this class
  // in their signatures instead of their IShape interface.

  // This ability of empty methods comes from the reason above. In our editor view non of them need
  // to be used, but this class still has to exist.

  // Only a couple of methods ended up being overridden by our implementation of Shape in order to
  // get edit view work.

  @Override
  public void changeColor(Color oldColor, Color newColor, int tick, int end, int start) {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
  }


  @Override
  public void stateChangeColor(Color newColor) {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
  }


  @Override
  public String getColor() {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
    return null;
  }

  ;

  @Override
  public void changeWidth(double width, double newWidth, int tick, int end, int start) {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
  }

  @Override
  public void stateChangeWidth(double newWidth) {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
  }

  @Override
  public String getWidth() {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
    return null;
  }


  @Override
  public void changeHeight(double height, double newHeight, int tick, int end, int start) {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
  }


  @Override
  public void stateChangeHeight(double newHeight) {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
  }


  @Override
  public String getHeight() {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
    return null;
  }


  @Override
  public void move(Posn from, Posn to, int tick, int end, int start) {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
  }


  @Override
  public void stateMove(Posn to) {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
  }


  @Override
  public String getPosn() {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
    return null;
  }


  @Override
  public String getName() {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
    return null;
  }


  @Override
  public String state() {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
    return null;
  }


  @Override
  public ArrayList<MotionAnimation> allAnimations() {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
    return null;
  }


  @Override
  public void removeKeyFrame(int time) {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
  }


  @Override
  public void addKeyFrame(KeyFrame kf) {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
  }


  @Override
  public void modifyKeyFrame(int time, Posn p, double w, double h, Color c) {
    //adapter pattern allowed to avoid using this method, but this class still has to exist
    //with all these methods because of the reason explained above.
  }
}
