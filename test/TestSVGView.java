//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import cs3500.easyanimator.animation.AnimationModel;
//import cs3500.easyanimator.model.AnimatorModel;
//import cs3500.easyanimator.model.AnimatorModelImpl;
//import cs3500.easyanimator.animation.EasyAnimation;
//import cs3500.easyanimator.model.KeyFrame;
//import cs3500.easyanimator.shape.Ellipse;
//import cs3500.easyanimator.shape.Rectangle;
//import cs3500.easyanimator.shape.ShapesModelAbs;
//import cs3500.easyanimator.view.SVGView;
//
//import static junit.framework.TestCase.assertEquals;
//
//public class TestSVGView {
//  @Test
//  public void testGetSVG() {
//    ArrayList<ShapesModelAbs> shapes1 = new ArrayList<ShapesModelAbs>();
//    ArrayList<AnimationModel> animes1 = new ArrayList<AnimationModel>();
//    ShapesModelAbs c = new Ellipse("C", 6, 100, 440,
//            70, 120, 60, 0, 0, 255, 0);
//    ShapesModelAbs r = new Rectangle("R", 1, 100, 200, 200,
//            50, 100, 255, 0, 0, 0);
//    shapes1.add(r);
//    shapes1.add(c);
//
//    //All animations for a rectangle R
//    AnimationModel still = new EasyAnimation(1, 10, "R", 200,
//            200, 50, 100, 255, 0, 0,
//            0, 200, 200, 50, 100, 255,
//            0, 0, 0);
//    AnimationModel moveR1 = new EasyAnimation(10, 50, "R", 200,
//            200, 50, 100, 255, 0, 0,
//            0, 300, 300, 50, 100, 255,
//            0, 0, 0);
//    AnimationModel moveR2 = new EasyAnimation(50, 51, "R", 300,
//            300, 50, 100, 255, 0, 0,
//            0, 300, 300, 50, 100, 255,
//            0, 0, 0);
//    AnimationModel resizeR1 = new EasyAnimation(51, 70, "R",
//            300, 300, 50, 100, 255, 0,
//            0, 0, 300, 300, 25, 100,
//            255, 0, 0, 0);
//    AnimationModel moveR3 = new EasyAnimation(70, 100, "R",
//            300, 300, 25, 100, 255, 0,
//            0, 0, 200, 200, 25, 100,
//            255, 0, 0, 0);
//
//    //All animations for ellipse C
//    AnimationModel still1 = new EasyAnimation(6, 20, "C",
//            440, 70, 120, 60, 0, 0,
//            255, 0, 440, 70, 120, 60, 0,
//            0, 255, 0);
//    AnimationModel moveC1 = new EasyAnimation(20, 50, "C",
//            440, 70, 120, 60, 0, 0,
//            255, 0, 440, 250, 120, 60,
//            0, 0, 255, 0);
//    AnimationModel moveC2 = new EasyAnimation(50, 70, "C",
//            440, 250, 120, 60, 0, 0,
//            255, 0, 440, 370, 120, 60,
//            0, 170, 85, 0);
//    AnimationModel recolorC2 = new EasyAnimation(70, 80, "C",
//            440, 370, 120, 60, 0, 170,
//            85, 0, 440, 370, 120, 60, 0,
//            255, 0, 0);
//
//    AnimationModel moveC3 = new EasyAnimation(80, 100, "C",
//            440, 370, 120, 60, 0, 255,
//            0, 0, 440, 370, 120, 60, 0,
//            255, 0, 0);
//
//    animes1.add(still);
//    animes1.add(moveR1);
//    animes1.add(moveR2);
//    animes1.add(resizeR1);
//    animes1.add(moveR3);
//    animes1.add(still1);
//    animes1.add(moveC1);
//    animes1.add(moveC2);
//    animes1.add(recolorC2);
//    animes1.add(moveC3);
//
//    HashMap<String, ArrayList<KeyFrame>> empty = new HashMap<>();
//
//    AnimatorModel model = new AnimatorModelImpl(shapes1, animes1, empty, 200, 200, 700,
//            700);
//
//    SVGView view = new SVGView(model.getAnimations(), model.getStarterShapes(),
//            model.getShapeTable(), model.getFrames(), 1, 500, 500,
//            System.out);
//    assertEquals(view.getSVG(), "<svg width=\"500\" height=\"500\" version=\"1.1\"\n     " +
//            "xmlns=\"http://www.w3.org/2000/svg\"><rect id=\"R\" x=\"200.0\" y=\"200.0\"" +
//            " width=\"50.0\" height=\"100.0\"
//            + "fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\">" +
//            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"99000ms\" " +
//            "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />" +
//            "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" " +
//            "attributeName=\"x\"" +
//            " from=\"200.0\" to=\"300.0\" fill=\"freeze\" /><animate attributeType=\"xml\" " +
//            "begin=\"10000ms\" dur=\"40000ms\" attributeName=\"y\" from=\"200.0\" " +
//            "to=\"300.0\"" +
//            " fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"51000ms\" " +
//            "dur=\"19000ms\" " +
//            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" /><animate " +
//            "attributeType=\"xml\" begin=\"51000ms\" dur=\"19000ms\" attributeName=\"height\" " +
//            "from=\"100.0\" to=\"100.0\" fill=\"freeze\" /><animate attributeType=\"xml\" " +
//            "begin=\"70000ms\" dur=\"30000ms\" attributeName=\"x\" from=\"300.0\" to=\"200.0\" " +
//            "fill=\"freeze\" /><animate attributeType=\"xml\"
//            + "begin=\"70000ms\" dur=\"30000ms\" " +
//            "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" /></rect><ellipse " +
//            "id=\"C\" cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"70.0\" " +
//            "fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" ><animate attributeType=\"xml\"" +
//            " begin=\"6000ms\" dur=\"94000ms\" attributeName=\"visibility\" from=\"hidden\" " +
//            "to=\"visible\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"20000ms\"" +
//            " dur=\"30000ms\" attributeName=\"cx\" from=\"440.0\" to=\"440.0\" " +
//            "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"20000ms\" dur=\"30000ms\"" +
//            " attributeName=\"cy\" from=\"70.0\" to=\"250.0\" fill=\"freeze\" />" +
//            "<animate attributeType=\"xml\" begin=\"50000ms\" dur=\"20000ms\" " +
//            "attributeName=\"cx\" from=\"440.0\" to=\"440.0\" fill=\"freeze\" />" +
//            "<animate attributeType=\"xml\" begin=\"50000ms\" dur=\"20000ms\"" +
//            " attributeName=\"cy\" from=\"250.0\" to=\"370.0\" fill=\"freeze\" />" +
//            "<animate attributeType=\"CSS\" begin=\"50000ms\" dur=\"20000ms\"" +
//            " attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\"" +
//            " fill=\"freeze\" />" +
//            "<animate attributeType=\"CSS\" begin=\"70000ms\" dur=\"10000ms\"" +
//            " attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" " +
//            "fill=\"freeze\" />" +
//            "</ellipse></svg>");
//  }
//}
