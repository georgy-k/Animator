package cs3500.easyanimator.model;

/**
 * Interface representing a key frame (either start or endpoint of an animation).
 */
public interface IKeyFrame extends Comparable {

  String getName();

  void setName(String name);

  int getTime();

  void setTime(int time);

  float getxPos();

  void setxPos(float xPos);

  float getyPos();

  void setyPos(float yPos);

  float getxDim();

  void setxDim(float xDim);

  float getyDim();

  void setyDim(float yDim);

  float getRed();

  void setRed(float red);

  float getGreen();

  void setGreen(float green);

  float getBlue();

  void setBlue(float blue);

  boolean getVisible();

  void setVisible();
}
