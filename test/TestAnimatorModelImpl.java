//import org.junit.Test;
//
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
//import static org.junit.Assert.assertEquals;
//
//
//public class TestAnimatorModelImpl {
//
//  // Check whether we get the right number of frames initialized
//  // it should be equal to longest life time of a single shape
//  @Test
//  public void testGetFrames() {
//    ArrayList<ShapesModelAbs> empty1 = new ArrayList<ShapesModelAbs>();
//    ArrayList<AnimationModel> empty2 = new ArrayList<AnimationModel>();
//    assertEquals(0, new AnimatorModelImpl(empty1, empty2, 200, 200, 700,
//            700).getFrames().length);
//    ShapesModelAbs jeff = new Ellipse("Jeff", 0, 100, 15,
//            15, 15, 15, 1, 1, 1, 0);
//    empty1.add(0, jeff);
//    assertEquals(jeff.getEnd(), new AnimatorModelImpl(empty1, empty2, 200, 200,
//            700, 700).getFrames().length);
//
//    ArrayList<ShapesModelAbs> shapes1 = new ArrayList<ShapesModelAbs>();
//    ArrayList<AnimationModel> animes1 = new ArrayList<AnimationModel>();
//    shapes1.add(new Ellipse("oval", 0, 10, 100, 100,
//            5, 7, 155, 0, 0, 0));
//    animes1.add(new EasyAnimation(7, 9, "oval", 100,
//            100,
//            5, 7, 155, 0, 0, 0,
//            100, 100,
//            5, 7, 155, 0, 0, 0));
//    AnimatorModel model1 = new AnimatorModelImpl(shapes1, animes1, 200, 200,
//            700, 700);
//    assertEquals(10, model1.getFrames().length);
//
//  }
//
//  // Check whether initial shapes are added correctly
//  // Test constructor.
//  @Test
//  public void testGetStarterShapes() {
//    ArrayList<ShapesModelAbs> empty1 = new ArrayList<ShapesModelAbs>();
//    ArrayList<AnimationModel> empty2 = new ArrayList<AnimationModel>();
//    assertEquals(0, new AnimatorModelImpl(empty1, empty2, 200, 200, 700,
//            700).getStarterShapes().size());
//    ShapesModelAbs jeff = new Ellipse("Jeff", 0, 100, 15,
//            15, 15, 15, 1, 1, 1, 0);
//    empty1.add(0, jeff);
//    assertEquals(1, new AnimatorModelImpl(empty1, empty2, 200, 200, 700,
//            700).getStarterShapes().size());
//  }
//
//  //Check that it is impossible to add overlapping animations.
//  @Test
//  public void testInconsistentAnimes() {
//    ArrayList<ShapesModelAbs> shapes1 = new ArrayList<ShapesModelAbs>();
//    ArrayList<AnimationModel> animes1 = new ArrayList<AnimationModel>();
//    ShapesModelAbs c = new Ellipse("C", 6, 100, 440,
//            70, 120, 60, 0, 0, 255, 0);
//    shapes1.add(c);
//
//    //Move ellipse up from 6 to 20
//    AnimationModel move1 = new EasyAnimation(6, 20, "C",
//            440, 70, 120, 60, 0, 0,
//            255,
//            0, 440, 80, 120, 60, 0, 0,
//            255, 0);
//    AnimationModel move2 = new EasyAnimation(6, 20, "C",
//            440, 70, 120, 60, 0, 0,
//            255,
//            0, 440, 60, 120, 60, 0,
//            0, 255, 0);
//    animes1.add(move1);
//    animes1.add(move2);
//    boolean thrown = false;
//    try {
//      AnimatorModel model = new AnimatorModelImpl(shapes1, animes1, 200, 200,
//              700, 700);
//    } catch (IllegalArgumentException e) {
//      thrown = true;
//
//      assertEquals("Animations of the same type cannot occur at the" +
//              " same time on the same shape", e.getMessage());
//    }
//    assertEquals(true, thrown);
//
//
//  }
//
//  //Check null args for Animator constructor
//  @Test
//  public void testIllegalArgs() {
//    boolean thrown = false;
//
//    try {
//      AnimatorModel model = new AnimatorModelImpl(null, null,
//              200, 200, 700, 700);
//    } catch (IllegalArgumentException e) {
//      thrown = true;
//      assertEquals("Shapes and animations can't be null", e.getMessage());
//    }
//    assertEquals(true, thrown);
//
//  }
//
//  // Check whether initial animations are added correctly
//  @Test
//  public void testAnimations() {
//    ArrayList<ShapesModelAbs> empty1 = new ArrayList<ShapesModelAbs>();
//    ArrayList<AnimationModel> empty2 = new ArrayList<AnimationModel>();
//    assertEquals(0, new AnimatorModelImpl(empty1, empty2, 200, 200, 700,
//            700).getAnimations().size());
//    ShapesModelAbs jeff = new Ellipse("Jeff", 0, 100, 15,
//            15, 15, 15, 1, 1, 1, 0);
//    AnimationModel walk = new EasyAnimation(20, 25, "Jeff", 15,
//            15, 15, 15, 1, 1, 1,
//            0,
//            15, 15, 15, 15,
//            1, 1, 1, 0);
//    empty1.add(0, jeff);
//    empty2.add(0, walk);
//    assertEquals(1, new AnimatorModelImpl(empty1, empty2, 200, 200, 700,
//            700).getAnimations().size());
//  }
//
//
//  // Test String description of the model
//  // It also tests:
//  // 1)Correct execution of animations
//  // 2)Correct execution of 2 or more animations at the same time (line 139)
//  // 3)Correct constructor initialization
//  // 4)Correct sorting of animations by run time
//  // 5)Correct representation of share State at a specific time
//  @Test
//  public void testDescription() {
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
//    AnimatorModel model = new AnimatorModelImpl(shapes1, animes1, 200, 200, 700,
//            700);
//
//    assertEquals("Rectangle: R\n" +
//            "Position: 227, 227;\n" +
//            "Size: 50, 100\n" +
//            "Color: R: 255, G: 0, B: 0;", model.shapeState(22, "R"));
//
//    assertEquals("Shapes:\n" +
//                    "shape R rectangle\n" +
//                    "motion R 1 200 200 50 100 255 0 0    10 200 200 50 100 255 0 0\n" +
//                    "motion R 10 200 200 50 100 255 0 0    50 300 300 50 100 255 0 0\n" +
//                    "motion R 50 300 300 50 100 255 0 0    51 300 300 50 100 255 0 0\n" +
//                    "motion R 51 300 300 50 100 255 0 0    70 300 300 25 100 255 0 0\n" +
//                    "motion R 70 300 300 25 100 255 0 0    100 200 200 25 100 255 0 0\n" +
//                    "\n" +
//                    "shape C ellipse\n" +
//                    "motion C 6 440 70 120 60 0 0 255    20 440 70 120 60 0 0 255\n" +
//                    "motion C 20 440 70 120 60 0 0 255    50 440 250 120 60 0 0 255\n" +
//                    "motion C 50 440 250 120 60 0 0 255    70 440 370 120 60 0 170 85\n" +
//                    "motion C 70 440 370 120 60 0 170 85    80 440 370 120 60 0 255 0\n" +
//                    "motion C 80 440 370 120 60 0 255 0    100 440 370 120 60 0 255 0",
//            model.textOutput().trim());
//  }
//
//
//  @Test
//  //Tests that after two modifications were in place, shape was indeed modified in two parameters.
//  // Particularly we check moving and recoloring at the same time.
//  public void testSimultaneousAnimations() {
//
//    ArrayList<ShapesModelAbs> shapes1 = new ArrayList<ShapesModelAbs>();
//    ArrayList<AnimationModel> animes1 = new ArrayList<AnimationModel>();
//
//    //Create exactly the same shapes and animations as in the assignment example
//
//
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
//    AnimatorModel model = new AnimatorModelImpl(shapes1, animes1, 200, 200, 700,
//            700);
//
//    //We should have exactly 100 frames (100 is the longest life time of a shape)
//    assertEquals(100, model.getFrames().length);
//
//    // Starting at t = 50, we perform 2 operations on elipse: move and recolor:
//    // therefore in the ellipse from 50th frame should have modified position and color
//    // compared to itself from 49th frame
//
//    String ellipseState49 = ((AnimatorModelImpl) model).shapeState(49, "C");
//    String ellipseState77 = ((AnimatorModelImpl) model).shapeState(77, "C");
//
//    // Check that if two different modifications happen we still have only 2 two shapes
//    // and not creating a new one.
//    assertEquals(2, model.getFrames()[77].getLos().size());
//
//
//    assertEquals("Ellipse: C\n" +
//            "Position: 440, 238;\n" +
//            "Size: 120, 60\n" +
//            "Color: R: 0, G: 0, B: 255;", ellipseState49);
//    assertEquals("Ellipse: C\n" +
//            "Position: 440, 370;\n" +
//            "Size: 120, 60\n" +
//            "Color: R: 0, G: 221, B: 34;", ellipseState77);
//  }
//}
