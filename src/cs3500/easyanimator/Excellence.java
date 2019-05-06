package cs3500.easyanimator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import cs3500.easyanimator.controller.Controller;
import cs3500.easyanimator.model.AnimatorModelImpl;
import cs3500.easyanimator.model.IModelAdapter;
import cs3500.easyanimator.model.ModelAdapter;
import cs3500.easyanimator.util.AnimationReader;
import cs3500.easyanimator.view.EditView;
import cs3500.easyanimator.view.EditViewAdapter;
import cs3500.easyanimator.view.IOurView;

import cs3500.easyanimator.view.SVGView;
import cs3500.easyanimator.view.TextualView;
import cs3500.easyanimator.view.OurVisualView;
import cs3500.easyanimator.provider.view.VisualViewEditable;

/**
 * Entry point of our program containing main(String[] args) methods that will run the animation.
 */
public final class Excellence {
  /**
   * Creates a model and view based on passed in parameters (args). Commands can be: -out, -view,
   * -in, -speed and must be followed by desired parameters.
   *
   * @param args commands in a form if String[]
   */
  public static void main(String[] args) {
    String inName = "";
    String viewType = "";
    String outName = "";
    int speed = 1;
    FileWriter output = null;
    Appendable out = System.out;

    //Process commands and assign received parameters to variables
    try {
      for (int i = 0; i < args.length; i++) {
        switch (args[i]) {
          case "-in":
            inName = "./resources/" + args[i + 1];
            i += 1;
            break;
          case "-view":
            viewType = args[i + 1];
            i += 1;
            break;
          case "-out":
            output = new FileWriter("./resources/" + args[i + 1]);
            out = output;
            i += 1;
            break;
          case "-speed":
            speed = Integer.parseInt(args[i + 1]);
            i += 1;
            break;
          default:
            JOptionPane.showMessageDialog(null,
                    "Wrong command supplied", null, JOptionPane.ERROR_MESSAGE);
        }
      }
      //Check wether mandatory parameters were received (in file and view type)
      if (!inName.equals("") && !viewType.equals("")) {

        // Create model from file provided
        ModelAdapter model = new ModelAdapter(AnimationReader.parseFile(new FileReader(inName),
                new AnimatorModelImpl.Builder()));


        // create IView instance through factory
        IOurView view = viewFactory(viewType, model, out, speed);

        if (viewType.equals("edit") || viewType.equals("provider")) {
          Controller controller = new Controller(model, view);
          view.display();
        } else {
          // Make view display the animation
          view.display();
          view.start();
        }


        // Close the file if it was used at all
        if (output != null) {
          output.close();
        }
      }

      //Show pop-up error messages
    } catch (IndexOutOfBoundsException iob) {
      JOptionPane.showMessageDialog(null,
              "Some command wasn't followed by an argument",
              null, JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException nfe) {
      JOptionPane.showMessageDialog(null,
              "Expected an integer for a speed but got a string",
              null, JOptionPane.ERROR_MESSAGE);
    } catch (FileNotFoundException fnf) {
      JOptionPane.showMessageDialog(null,
              "No file found with name: " + inName,
              null, JOptionPane.ERROR_MESSAGE);
    } catch (IOException ioe) {
      JOptionPane.showMessageDialog(null,
              "File: " + outName + " can't be opened" + inName,
              null, JOptionPane.ERROR_MESSAGE);
    }

  }

  /**
   * Factory that construct needed view based on the type provided in commands.
   *
   * @param type  type of the view.
   * @param model Animator model.
   * @param out   Appendable object as a source to append output to.
   * @param speed tick per second.
   * @return Implementation of IView interface.
   */
  public static IOurView viewFactory(String type, IModelAdapter model, Appendable out, int speed) {

    switch (type) {
      case "text":
        return new TextualView(model.getBaseModel().textOutput(), out);
      case "svg":
        return new SVGView(model.getBaseModel().getShapeTable(),
                model.getBaseModel().getFrames().length, speed,
                model.getBaseModel().getWidth(), model.getBaseModel().getHeight(), out);
      case "visual":
        return new OurVisualView(model.getBaseModel().getFrames(), speed,
                model.getBaseModel().getXpos(), model.getBaseModel().getYpos(),
                model.getBaseModel().getWidth(), model.getBaseModel().getHeight());
      case "edit":
        return new EditView(model.getBaseModel().getFrames(), speed,
                model.getBaseModel().getXpos(), model.getBaseModel().getYpos(),
                model.getBaseModel().getWidth(), model.getBaseModel().getHeight());
      case "provider":
        return new EditViewAdapter(new VisualViewEditable(model, speed), speed);
      default:
        JOptionPane.showMessageDialog(null,
                "Unknown view type: " + type,
                null, JOptionPane.ERROR_MESSAGE);
        throw new IllegalArgumentException("Unknown view type: " + type);
    }
  }
}
