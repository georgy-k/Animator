//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import cs3500.easyanimator.animation.AnimationModel;
//import cs3500.easyanimator.animation.EasyAnimation;
//import cs3500.easyanimator.controller.Controller;
//import cs3500.easyanimator.model.AnimatorModel;
//import cs3500.easyanimator.model.AnimatorModelImpl;
//import cs3500.easyanimator.model.IKeyFrame;
//import cs3500.easyanimator.model.KeyFrame;
//import cs3500.easyanimator.shape.Ellipse;
//import cs3500.easyanimator.shape.Rectangle;
//import cs3500.easyanimator.shape.ShapesModelAbs;
//import cs3500.easyanimator.view.EditView;
//import cs3500.easyanimator.provider.view.IView;
//
//import static junit.framework.TestCase.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
//public class TestContoller {
//
//  @Test
//  public void testChangeSpeed() {
//    ArrayList<ShapesModelAbs> shapes1 = new ArrayList<ShapesModelAbs>();
//    ArrayList<AnimationModel> animes1 = new ArrayList<AnimationModel>();
//    ArrayList<AnimationModel> animes2 = new ArrayList<AnimationModel>();
//    ArrayList<IKeyFrame> key1 = new ArrayList<IKeyFrame>();
//    ArrayList<IKeyFrame> key2 = new ArrayList<IKeyFrame>();
//    HashMap<String, ArrayList<IKeyFrame>> key = new HashMap<String, ArrayList<IKeyFrame>>();
//    HashMap<String, ArrayList<AnimationModel>> animes = new HashMap<String,
// ArrayList<AnimationModel>>();
//    ShapesModelAbs c = new Ellipse("C", 6, 100, 440,
//            70, 120, 60, 0, 0, 255, 0);
//    ShapesModelAbs r;
//    r = new Rectangle("R", 1, 100, 200, 200,
//            50, 100, 255, 0, 0, 0);
//    shapes1.add(r);
//    shapes1.add(c);
//
//    //All animations for a rectangle R
//    AnimationModel still = new EasyAnimation(1, 10, "R", 200,
//            200, 50, 100, 255, 0, 0,
//            0, 200, 200, 50, 100, 255,
//            0, 0, 0);
//    IKeyFrame RT1 = new KeyFrame("R", 1, 200,
//            200, 50, 100, 255, 0, 0,
//            true);
//    IKeyFrame RT10 = new KeyFrame("R", 10, 200,
//            200, 50, 100, 255, 0, 0,
//            true);
//    AnimationModel moveR1 = new EasyAnimation(10, 50, "R", 200,
//            200, 50, 100, 255, 0, 0,
//            0, 300, 300, 50, 100, 255,
//            0, 0, 0);
//    IKeyFrame RT50 = new KeyFrame("R", 50, 300, 300, 50, 100, 255,
//            0, 0, true);
//    AnimationModel moveR2 = new EasyAnimation(50, 51, "R", 300,
//            300, 50, 100, 255, 0, 0,
//            0, 300, 300, 50, 100, 255,
//            0, 0, 0);
//    IKeyFrame RT51 = new KeyFrame("R", 51, 300, 300, 50, 100, 255,
//            0, 0, true);
//    AnimationModel resizeR1 = new EasyAnimation(51, 70, "R",
//            300, 300, 50, 100, 255, 0,
//            0, 0, 300, 300, 25, 100,
//            255, 0, 0, 0);
//    IKeyFrame RT70 = new KeyFrame("R", 70, 300, 300, 25, 100,
//            255, 0, 0, true);
//    AnimationModel moveR3 = new EasyAnimation(70, 100, "R",
//            300, 300, 25, 100, 255, 0,
//            0, 0, 200, 200, 25, 100,
//            255, 0, 0, 0);
//    IKeyFrame RT100 = new KeyFrame("R", 100, 200, 200, 25, 100,
//            255, 0, 0, true);
//
//    //All animations for ellipse C
//    AnimationModel still1 = new EasyAnimation(6, 20, "C",
//            440, 70, 120, 60, 0, 0,
//            255, 0, 440, 70, 120, 60, 0,
//            0, 255, 0);
//    IKeyFrame CT6 = new KeyFrame("C", 6, 440, 70, 120, 60, 0, 0,
//            255, true);
//    IKeyFrame CT20 = new KeyFrame("C", 20, 440, 70, 120, 60, 0,
//            0, 255, true);
//    AnimationModel moveC1 = new EasyAnimation(20, 50, "C",
//            440, 70, 120, 60, 0, 0,
//            255, 0, 440, 250, 120, 60,
//            0, 0, 255, 0);
//    IKeyFrame CT50 = new KeyFrame("C", 50, 440, 250, 120, 60,
//            0, 0, 255, true);
//    AnimationModel moveC2 = new EasyAnimation(50, 70, "C",
//            440, 250, 120, 60, 0, 0,
//            255, 0, 440, 370, 120, 60,
//            0, 170, 85, 0);
//    IKeyFrame CT70 = new KeyFrame("C", 70, 440, 370, 120, 60,
//            0, 170, 85, true);
//    AnimationModel recolorC2 = new EasyAnimation(70, 80, "C",
//            440, 370, 120, 60, 0, 170,
//            85, 0, 440, 370, 120, 60, 0,
//            255, 0, 0);
//    IKeyFrame CT80 = new KeyFrame("C", 80, 440, 370, 120, 60, 0,
//            255, 0, true);
//
//    AnimationModel moveC3 = new EasyAnimation(80, 100, "C",
//            440, 370, 120, 60, 0, 255,
//            0, 0, 440, 370, 120, 60, 0,
//            255, 0, 0);
//    IKeyFrame CT100 = new KeyFrame("C", 100, 440, 370, 120, 60, 0,
//            255, 0, true);
//
//    animes1.add(still);
//    animes1.add(moveR1);
//    animes1.add(moveR2);
//    animes1.add(resizeR1);
//    animes1.add(moveR3);
//    animes2.add(still1);
//    animes2.add(moveC1);
//    animes2.add(moveC2);
//    animes2.add(recolorC2);
//    animes2.add(moveC3);
//    animes.put("R", animes1);
//    animes.put("C", animes2);
//    key1.add(RT1);
//    key1.add(RT10);
//    key1.add(RT50);
//    key1.add(RT51);
//    key1.add(RT70);
//    key1.add(RT100);
//    key2.add(CT6);
//    key2.add(CT20);
//    key2.add(CT50);
//    key2.add(CT70);
//    key2.add(CT80);
//    key2.add(CT100);
//    key.put("R", key1);
//    key.put("C", key2);
//    AnimatorModel model = new AnimatorModelImpl(shapes1, animes, key, 500, 500,
//            500, 500);
//
//    IView view = new EditView(model.getFrames(), 20, 500, 500, 500,
//            500);
//    int initial = 20;
//    Controller control = new Controller(model, view);
//    control.changeSpeed(5, false);
//    assertEquals(25, control.getTickerVal());
//    control.changeSpeed(5, true);
//    assertEquals(initial, control.getTickerVal());
//
//  }
//
//  @Test
//  public void testSetKeyFrameSpeed() {
//    ArrayList<ShapesModelAbs> shapes1 = new ArrayList<ShapesModelAbs>();
//    ArrayList<AnimationModel> animes1 = new ArrayList<AnimationModel>();
//    ArrayList<AnimationModel> animes2 = new ArrayList<AnimationModel>();
//    ArrayList<IKeyFrame> key1 = new ArrayList<IKeyFrame>();
//    ArrayList<IKeyFrame> key2 = new ArrayList<IKeyFrame>();
//    HashMap<String, ArrayList<IKeyFrame>> key = new HashMap<String, ArrayList<IKeyFrame>>();
//    HashMap<String, ArrayList<AnimationModel>> animes = new HashMap<String,
//    ArrayList<AnimationModel>>();
//    ShapesModelAbs c = new Ellipse("C", 6, 100, 440,
//            70, 120, 60, 0, 0, 255, 0);
//    ShapesModelAbs r;
//    r = new Rectangle("R", 1, 100, 200, 200,
//            50, 100, 255, 0, 0, 0);
//    shapes1.add(r);
//    shapes1.add(c);
//
//    //All animations for a rectangle R
//    AnimationModel still = new EasyAnimation(1, 10, "R", 200,
//            200, 50, 100, 255, 0, 0,
//            0, 200, 200, 50, 100, 255,
//            0, 0, 0);
//    IKeyFrame RT1 = new KeyFrame("R", 1, 200,
//            200, 50, 100, 255, 0, 0,
//            true);
//    IKeyFrame RT10 = new KeyFrame("R", 10, 200,
//            200, 50, 100, 255, 0, 0,
//            true);
//    AnimationModel moveR1 = new EasyAnimation(10, 50, "R", 200,
//            200, 50, 100, 255, 0, 0,
//            0, 300, 300, 50, 100, 255,
//            0, 0, 0);
//    IKeyFrame RT50 = new KeyFrame("R", 50, 300, 300, 50, 100, 255,
//            0, 0, true);
//    AnimationModel moveR2 = new EasyAnimation(50, 51, "R", 300,
//            300, 50, 100, 255, 0, 0,
//            0, 300, 300, 50, 100, 255,
//            0, 0, 0);
//    IKeyFrame RT51 = new KeyFrame("R", 51, 300, 300, 50, 100, 255,
//            0, 0, true);
//    AnimationModel resizeR1 = new EasyAnimation(51, 70, "R",
//            300, 300, 50, 100, 255, 0,
//            0, 0, 300, 300, 25, 100,
//            255, 0, 0, 0);
//    IKeyFrame RT70 = new KeyFrame("R", 70, 300, 300, 25, 100,
//            255, 0, 0, true);
//    AnimationModel moveR3 = new EasyAnimation(70, 100, "R",
//            300, 300, 25, 100, 255, 0,
//            0, 0, 200, 200, 25, 100,
//            255, 0, 0, 0);
//    IKeyFrame RT100 = new KeyFrame("R", 100, 200, 200, 25, 100,
//            255, 0, 0, true);
//
//    //All animations for ellipse C
//    AnimationModel still1 = new EasyAnimation(6, 20, "C",
//            440, 70, 120, 60, 0, 0,
//            255, 0, 440, 70, 120, 60, 0,
//            0, 255, 0);
//    IKeyFrame CT6 = new KeyFrame("C", 6, 440, 70, 120, 60, 0, 0,
//            255, true);
//    IKeyFrame CT20 = new KeyFrame("C", 20, 440, 70, 120, 60, 0,
//            0, 255, true);
//    AnimationModel moveC1 = new EasyAnimation(20, 50, "C",
//            440, 70, 120, 60, 0, 0,
//            255, 0, 440, 250, 120, 60,
//            0, 0, 255, 0);
//    IKeyFrame CT50 = new KeyFrame("C", 50, 440, 250, 120, 60,
//            0, 0, 255, true);
//    AnimationModel moveC2 = new EasyAnimation(50, 70, "C",
//            440, 250, 120, 60, 0, 0,
//            255, 0, 440, 370, 120, 60,
//            0, 170, 85, 0);
//    IKeyFrame CT70 = new KeyFrame("C", 70, 440, 370, 120, 60,
//            0, 170, 85, true);
//    AnimationModel recolorC2 = new EasyAnimation(70, 80, "C",
//            440, 370, 120, 60, 0, 170,
//            85, 0, 440, 370, 120, 60, 0,
//            255, 0, 0);
//    IKeyFrame CT80 = new KeyFrame("C", 80, 440, 370, 120, 60, 0,
//            255, 0, true);
//
//    AnimationModel moveC3 = new EasyAnimation(80, 100, "C",
//            440, 370, 120, 60, 0, 255,
//            0, 0, 440, 370, 120, 60, 0,
//            255, 0, 0);
//    IKeyFrame CT100 = new KeyFrame("C", 100, 440, 370, 120, 60, 0,
//            255, 0, true);
//
//    animes1.add(still);
//    animes1.add(moveR1);
//    animes1.add(moveR2);
//    animes1.add(resizeR1);
//    animes1.add(moveR3);
//    animes2.add(still1);
//    animes2.add(moveC1);
//    animes2.add(moveC2);
//    animes2.add(recolorC2);
//    animes2.add(moveC3);
//    animes.put("R", animes1);
//    animes.put("C", animes2);
//    key1.add(RT1);
//    key1.add(RT10);
//    key1.add(RT50);
//    key1.add(RT51);
//    key1.add(RT70);
//    key1.add(RT100);
//    key2.add(CT6);
//    key2.add(CT20);
//    key2.add(CT50);
//    key2.add(CT70);
//    key2.add(CT80);
//    key2.add(CT100);
//    key.put("R", key1);
//    key.put("C", key2);
//    AnimatorModel model = new AnimatorModelImpl(shapes1, animes, key, 500, 500,
//            500, 500);
//    String init = model.textOutput();
//
//    IView view = new EditView(model.getFrames(), 20, 500, 500, 500,
//            500);
//    int initial = 20;
//    Controller control = new Controller(model, view);
//    control.setKeyFrame("R", "120");
//    assertEquals(init, model.textOutput());
//
//    control.editKeyFrame("R", "0", "0", "0", "0", "0", "0",
//            "0", "0");
//    control.refreshAnimations();
//    assertNotEquals(init, model.textOutput());
//
//  }
//}
