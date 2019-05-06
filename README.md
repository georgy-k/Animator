Animator Notes

These notes should provide a top level view of how our code operates with out getting into the nitty gritty of how everything is actually
implemented.
-----------------------------
  AnimationModel: Interface for animations, which are set up to act as visitor methods for shapes.
                  Animations must be able to provide certain information including Name, Start/End Time and Type.
                  Animations must be able to accept and work with any shape, and thus must have the proper "applyto..." methods here/

  AnimationModelAbs: Abstracted animation, creating a super constructor, and setting certain data up, 
                     including most of the starting information of the shape. The animation here will know most everything about the 
                     shape it is working with, excluding the actual type of shape (because any animation should be able to work with any shape)
                     getters for much of this information is found here.

  AnimatorModel: Interface for the animating process, making all the public outputs for the animating function. 
                 This should precompute the animations for the display and provide any information the display
                 might need to create a new viewing option. We give the information in an array of Frame Array
                 list of starter shapes, array list of animations, and a string description. This is also where the animations
                 are sorted based on when they begin.

  Frame : Interface for a frame, Maybe should have been better named as a cell, but a frame should have information about 
          each of the shapes on the screen at any one second. We have to option to add shapes to the frame, and we have an option 
          to give a list of all the shapes that are currently on the frame

 ShapeModelAbs : Abstract class for a shape. This has all the generic information that we want each shape to keep track of
                 Including each shapes individual name, color, rotation and any animation that apply specifically to that shape.
                 These animations are set up by a setter in AnimatorModels constructor, and because all these fields are private, 
                 there are getters for these field found here.

IView : Interface for a view, this creates the display for any available view idea. IView requires the following funcitonality:
        setSpeed pause restart resume start askForFrameParams askForFrameParams editParams deleteParams update looping setListeners.
        Many of these methods only are only really implemented fully in some of the views. This is because the options for views are 
        really one set thing a view is.

AnimationBuilder : This is the util interface that was provided for use to use to build from text documents after recieving informaiton from
                   the reader class. We didn't really write any of this interface up, but we did implement this Builder style class as a
                   nested class inside of our implementation of AnimatorModel.

IKeyFrame : This is the interface for a new kind of way of creating animations. This was made to help facilitate editting by choosing a single point 
            in the life of a shape and editting it. Instead of having to slice animations every time a new point was set, each point being set and the
            beginning and end of all animations are now represented as IKeyFrames, and animations are then recreated from the IKeyFrames. For this, we
            require IKeyFrames to have getters to retrieve all the information needed by  the program in order to create animations. We also have setters 
           in order to reformat the KeyFrame on the fly.

IController : Interface that represents basic functionality required to be Animator Model Controller. It has basic methods like pause, resume, restart etc.

EditListener: Interface that extends basic IController interface and adds specific functionality required to be able to handle set/edit/delet Key Frame.

Controller: Our controller class that extends EditListener and implements both basic functionality from IController and specific functionality from EditListener. It will pass instance of it to the EditView to make it be able to callback when key frame modifying operation should happen.





Modified  :Added builder methods to the AnimatorModel in order to implement the builder itself.
Previously Added a new shape constructor that takes in only one String in order to create a shape that initializes to the first animation provided.
            Initialization methods were added for shapes, in order to insure that shapes always start at the right position for the first animation.
           In order to properly implement SVG, we needed to add some public getters to the animations, which was relatively easy as we just added 
            them to the abstract class  AnimationModelAbs. This allowed us to pretty easily get the numbers we need to construct a SVG
           We added a new field for animator where we put all the shapes in a hashmap of their names to the shape. This was just to increase the 
            efficiency of our program.
           We added a new field frame width and frame height to animator, because we found that the canvas needed to be set at the point of creating 
            or Animatormodel
           We added a getShapeTable method which returned a hashmap filled with clones of all the shapes in the animator. These clones of the shapes 
            are made to be completely seperated from the original shapes so that no user can modify them.    

Recently Modified : AnimationModel : New constructor to take in IKeyFrames, in order to more easily recognize relationship between IKeyFrames and animations
                                     Change data stored in animations to be two IKeyFrames. These two IKeyFrames contain almost the exact same information as before.
                                     Changed previous constructor from setting all the variables to creating two keyframes that contain the same information
                    IView : Added additional method calls to help construct the independent but linked functionality of the constructor and the view
                            All implementing classes were required to implement these methods. 
                            Also included methods that were used in the narraration of the view (pause, start, rewind, restart)
                            Also combed through IViews children hoping to limit the extra data stored in th views
                    AnimatorModelImpl : Changed the nested builder class to most fully protect from unordered data (since the introduction of IKeyFrames and
                                         the assumption of use of buildKeyFrame ment that we could not build the list of keyframes without know the order of 
                                         animations first.)
                                        Changed the data stored to being easier to use hashmaps only paired with the ArrayList of shapes Instead of duplicating 
                                         The same data with different data storage types.

  
