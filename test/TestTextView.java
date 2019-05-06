//import org.junit.Test;
//
//
//import java.io.CharArrayWriter;
//import java.util.ArrayList;
//
//import cs3500.easyanimator.animation.AnimationModel;
//import cs3500.easyanimator.model.AnimatorModel;
//import cs3500.easyanimator.model.AnimatorModelImpl;
//import cs3500.easyanimator.animation.EasyAnimation;
//import cs3500.easyanimator.shape.Ellipse;
//import cs3500.easyanimator.shape.Rectangle;
//import cs3500.easyanimator.shape.ShapesModelAbs;
//
//import cs3500.easyanimator.view.TextualView;
//
//import static junit.framework.TestCase.assertEquals;
//
//public class TestTextView {
//
//  @Test
//  public void testDisplay() {
//    ArrayList<ShapesModelAbs> shapes1 = new ArrayList<ShapesModelAbs>();
//    ArrayList<AnimationModel> animes1 = new ArrayList<AnimationModel>();
//    ShapesModelAbs c = new Ellipse("C", 0, 100, 440,
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
//    AnimatorModel model = new AnimatorModelImpl(shapes1, animes1,
//            200, 200, 700, 700);
//
//    Appendable a = new CharArrayWriter();
//    TextualView view = new TextualView(model.textOutput(), a);
//    assertEquals(a.toString(), "canvas 200 200 700 700\n" +
//            "shape R rectangle\n" +
//            "motion R 1 200 200 50 100 255 0 0    10 200 200 50 100 255 0 0\n" +
//            "motion R 10 200 200 50 100 255 0 0    50 300 300 50 100 255 0 0\n" +
//            "motion R 50 300 300 50 100 255 0 0    51 300 300 50 100 255 0 0\n" +
//            "motion R 51 300 300 50 100 255 0 0    70 300 300 25 100 255 0 0\n" +
//            "motion R 70 300 300 25 100 255 0 0    100 200 200 25 100 255 0 0\n" +
//            "shape C ellipse\n" +
//            "motion C 6 440 70 120 60 0 0 255    20 440 70 120 60 0 0 255\n" +
//            "motion C 20 440 70 120 60 0 0 255    50 440 250 120 60 0 0 255\n" +
//            "motion C 50 440 250 120 60 0 0 255    70 440 370 120 60 0 170 85\n" +
//            "motion C 70 440 370 120 60 0 170 85    80 440 370 120 60 0 255 0\n" +
//            "motion C 80 440 370 120 60 0 255 0    100 440 370 120 60 0 255 0\n");
//
//    ArrayList<ShapesModelAbs> empty1 = new ArrayList<ShapesModelAbs>();
//    ArrayList<AnimationModel> empty2 = new ArrayList<AnimationModel>();
//    AnimatorModel empty = new AnimatorModelImpl(empty1, empty2,
//            0, 0, 0, 0);
//    Appendable b = new CharArrayWriter();
//    TextualView view2 = new TextualView(empty.textOutput(), b);
//    assertEquals(b.toString(), "canvas 0 0 0 0\n");
//  }
//}
//
//
