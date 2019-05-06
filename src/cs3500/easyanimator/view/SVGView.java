package cs3500.easyanimator.view;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Timer;

import cs3500.easyanimator.animation.AnimationModel;
import cs3500.easyanimator.animation.AnimationModelAbs;
import cs3500.easyanimator.controller.EditListener;
import cs3500.easyanimator.model.Frame;
import cs3500.easyanimator.shape.ShapesModelAbs;
import cs3500.easyanimator.shape.Ellipse;
import cs3500.easyanimator.shape.Rectangle;


/**
 * SVG representation of the IView interface. Display() will append string representation of SVG
 * appended onto provided Appendable source.
 */
public class SVGView implements IOurView {


  HashMap<String, ShapesModelAbs> shapesTables;
  private int ticksPerSecond;
  private int width;
  private int height;
  private int length;
  private Appendable app;

  /**
   * This constructs an SVG view of the model.
   *
   * @param ticksPerSecond This is the given ticks per second.
   * @param width          This is the given width of the SVG.
   * @param height         This is the given height of the SVG.
   */
  public SVGView(HashMap<String, ShapesModelAbs> shapesTables, int length, int ticksPerSecond,
                 int width, int height, Appendable app) {
    this.shapesTables = shapesTables;
    this.ticksPerSecond = ticksPerSecond;
    this.width = width;
    this.height = height;
    this.length = length;
    this.app = app;
  }

  @Override
  public void start() {
    try {
      app.append(this.getSVG());
    } catch (IOException e) {
      e.printStackTrace();
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
    return this.ticksPerSecond;
  }


  @Override
  public void update(Frame[] frames) {
    // this view is not dynamic, therefore it can not update frames on the way
  }


  /**
   * Returns string representation of SVG view.
   *
   * @return HTML code in form of String
   */
  private String getSVG() {
    String answer = "";
    answer += "<svg width=\"" + this.width + "\" height=\"" + this.height + "\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n";
    for (String name : this.shapesTables.keySet()) {
      ShapesModelAbs shape = shapesTables.get(name);
      if (shape.getType().equals("Rectangle")) {
        if (shape.getStart() == 1 && shape.getEnd() == length
        ) {
          Rectangle rectShape = (Rectangle) shape;
          answer += "<rect id=\"" + shape.getName() + "\" x=\"" + rectShape.getStartX() + "\" y=\""
                  + rectShape.getStartY()
                  + "\" width=\"" + rectShape.getStartXdim() + "\" height=\""
                  + rectShape.getStartYdim()
                  + "\" fill=\"rgb("
                  + rectShape.getRed() + "," + rectShape.getGreen() + ","
                  + rectShape.getBlue() + ")\" visibility=\"visible\">\n";
          answer += this.helper(shape.getMyAnimes());
          answer += "</rect>\n";
        } else if (shape.getStart() == 1) {
          Rectangle rectShape = (Rectangle) shape;
          answer += "<rect id=\"" + shape.getName() + "\" x=\"" + rectShape.getStartX() + "\" y=\""
                  + rectShape.getStartY()
                  + "\" width=\"" + rectShape.getStartXdim() + "\" height=\""
                  + rectShape.getStartYdim()
                  + "\" fill=\"rgb("
                  + rectShape.getRed() + "," + rectShape.getGreen() + ","
                  + rectShape.getBlue() + ")\" visibility=\"visible\">";
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (shape.getEnd() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + ((((length) * 1000 / this.ticksPerSecond)) - (
                  (shape.getEnd() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"visibility\" from=\"" + "visible" + "\" to=\""
                  + "hidden" + "\" fill=\"freeze\" />\n";
          answer += this.helper(shape.getMyAnimes());
          answer += "</rect>\n";

        } else {
          Rectangle rectShape = (Rectangle) shape;
          answer += "<rect id=\"" + shape.getName() + "\" x=\"" + rectShape.getStartX() + "\" y=\""
                  + rectShape.getStartY()
                  + "\" width=\"" + rectShape.getStartXdim() + "\" height=\""
                  + rectShape.getStartYdim()
                  + "\" fill=\"rgb("
                  + rectShape.getRed() + "," + rectShape.getGreen() + ","
                  + rectShape.getBlue() + ")\" visibility=\"hidden\">\n";
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (shape.getStart() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + ((((shape.getEnd()) * 1000 / this.ticksPerSecond)) - (
                  (shape.getStart() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"visibility\" from=\"" + "hidden" + "\" to=\""
                  + "visible" + "\" fill=\"freeze\" />\n";
          answer += this.helper(shape.getMyAnimes());
          answer += "</rect>\n";
        }
      } else if (shape.getType().equals("Ellipse")) {
        if (shape.getStart() == 1 && shape.getEnd() == length) {

          Ellipse ovalShape = (Ellipse) shape;
          answer +=
                  "<ellipse id=\"" + ovalShape.getName() + "\" cx=\"" + ovalShape.getStartX()
                          + "\" " + "cy=\"" + ovalShape.getStartY()
                          + "\" rx=\"" + ovalShape.getStartXdim() + "\" ry=\"" +
                          ovalShape.getStartYdim()
                          + "\" fill=\"rgb("
                          + ovalShape.getRed() + "," + ovalShape.getGreen() + ","
                          + ovalShape.getBlue() + ")\" visibility=\"visible\" >\n";
          answer += this.helper(shape.getMyAnimes());
          answer += "</ellipse>\n";
        } else if (shape.getStart() == 1) {
          Ellipse ovalShape = (Ellipse) shape;
          answer +=
                  "<ellipse id=\"" + ovalShape.getName() + "\" cx=\"" + ovalShape.getStartX()
                          + "\" cy=\"" + ovalShape.getStartY()
                          + "\" rx=\"" + ovalShape.getStartXdim() + "\" ry=\""
                          + ovalShape.getStartYdim() + "\" fill=\"rgb("
                          + ovalShape.getRed() + "," + ovalShape.getGreen() + ","
                          + ovalShape.getBlue() + ")\" visibility=\"visible\" >\n";
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (shape.getEnd() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + ((((length) * 1000 / this.ticksPerSecond)) - (
                  (shape.getEnd() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"visibility\" from=\"" + "visible" + "\" to=\""
                  + "hidden" + "\" fill=\"freeze\" />\n";
          answer += this.helper(shape.getMyAnimes());
          answer += "</ellipse>\n";
        } else {
          Ellipse ovalShape = (Ellipse) shape;
          answer +=
                  "<ellipse id=\"" + ovalShape.getName() + "\" cx=\"" + ovalShape.getStartX()
                          + "\" cy=\"" + ovalShape.getStartY()
                          + "\" rx=\"" + ovalShape.getStartXdim() + "\" ry=\""
                          + ovalShape.getStartYdim() + "\" fill=\"rgb("
                          + ovalShape.getRed() + "," + ovalShape.getGreen() + ","
                          + ovalShape.getBlue() + ")\" visibility=\"hidden\" >\n";
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (shape.getStart() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + ((((shape.getEnd()) * 1000 / this.ticksPerSecond)) - (
                  (shape.getStart() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"visibility\" from=\"" + "hidden" + "\" to=\""
                  + "visible" + "\" fill=\"freeze\" />\n";

          answer += this.helper(shape.getMyAnimes());
          answer += "</ellipse>\n";
        }
      }
    }

    answer += "</svg>";
    return answer;
  }

  private String helper(ArrayList<AnimationModelAbs> more) {
    String answer = "";
    for (AnimationModel a : more) {
      if (a.positionChanged()) {
        if (shapesTables.get(a.getName()).getType().equals("Rectangle")) {
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (a.getStartTime() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + (((a.getEndTime() * 1000 / this.ticksPerSecond)) - (
                  (a.getStartTime() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"x\" from=\"" + a.getStartX() + "\" to=\""
                  + a.getEndX() + "\" fill=\"freeze\" />\n";
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (a.getStartTime() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + (((a.getEndTime() * 1000 / this.ticksPerSecond)) - (
                  (a.getStartTime() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"y\" from=\"" + a.getStartY() + "\" to=\""
                  + a.getEndY() + "\" fill=\"freeze\" />\n";
        } else if (shapesTables.get(a.getName()).getType().equals("Ellipse")) {
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (a.getStartTime() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + (((a.getEndTime() * 1000 / this.ticksPerSecond)) - (
                  (a.getStartTime() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"cx\" from=\"" + a.getStartX() + "\" to=\""
                  + a.getEndX() + "\" fill=\"freeze\" />\n";
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (a.getStartTime() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + (((a.getEndTime() * 1000 / this.ticksPerSecond)) - (
                  (a.getStartTime() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"cy\" from=\"" + a.getStartY() + "\" to=\""
                  + a.getEndY() + "\" fill=\"freeze\" />\n";
        }
      }
      if (a.colorChanged()) {
        answer += "<animate attributeType=\"CSS\" begin=\""
                + (a.getStartTime() * 1000 / this.ticksPerSecond)
                + "ms\" dur=\""
                + (((a.getEndTime() * 1000 / this.ticksPerSecond)) - (
                (a.getStartTime() * 1000 / this.ticksPerSecond)))
                + "ms\" "
                + "attributeName=\"fill\" from=\"rgb("
                + (int) a.getStartRed()
                + "," + (int) a.getStartGreen() + ","
                + (int) a.getStartBlue() + ")\" to=\"rgb("
                + (int) a.getEndRed() + ","
                + (int) a.getEndGreen() + ","
                + (int) a.getEndBlue() + ")\" fill=\"freeze\" />\n";
      }

      if (a.sizeChanged()) {
        if (shapesTables.get(a.getName()).getType().equals("Rectangle")) {
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (a.getStartTime() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + (((a.getEndTime() * 1000 / this.ticksPerSecond)) - (
                  (a.getStartTime() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"width\" from=\"" + a.getStartXdim() + "\" to=\""
                  + a.getEndXdim() + "\" fill=\"freeze\" />\n";
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (a.getStartTime() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + (((a.getEndTime() * 1000 / this.ticksPerSecond)) - (
                  (a.getStartTime() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"height\" from=\"" + a.getStartYdim()
                  + "\" to=\""
                  + a.getEndYdim() + "\" fill=\"freeze\" />\n";
        } else if (shapesTables.get(a.getName()).getType().equals("Ellipse")) {
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (a.getStartTime() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + (((a.getEndTime() * 1000 / this.ticksPerSecond)) - (
                  (a.getStartTime() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"rx\" from=\"" + a.getStartXdim() + "\" to=\""
                  + a.getEndXdim() + "\" fill=\"freeze\" />\n";
          answer += "<animate attributeType=\"xml\" begin=\""
                  + (a.getStartTime() * 1000 / this.ticksPerSecond)
                  + "ms\" dur=\""
                  + (((a.getEndTime() * 1000 / this.ticksPerSecond)) - (
                  (a.getStartTime() * 1000 / this.ticksPerSecond))) + "ms\" "
                  + "attributeName=\"ry\" from=\"" + (a.getStartYdim() + "\" to=\""
                  + a.getEndYdim() + "\" fill=\"freeze\" />\n");
        }
      }
    }
    return answer;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // this class has no action event triggers
  }
}
