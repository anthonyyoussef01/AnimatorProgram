ur cs3500.animator.model consists of two interfaces:
- IFrame, which represents the state of a shape during a given tick.
- IAnimatorModel, which represents the screen onto which the animation will be rendered, and the frames contained within that animation.

IFrame has three methods, which are toString, equals and hash.
The class Frame implements IFrame and has the following fields: a string shapeName to represent the name the shape goes by, the String shapeType to represent the type of shape the Frame is describing (e.g. "rectangle"), ints representing x and y coordinates for the position, ints representing the shape's width and height, ints representing the shape's color's RGB coordinates, and an int that represents the tick of the Frame.
The constructor enforces the constraints of these fields (no null or empty strings, no negative coordinates, and no non-supported shapes).
A private boolean, isValidShapeType, checks that the shapeType in the constructor is either "rectangle", "oval", or "ellipse". Otherwise, an IllegalArgumentException is thrown.
The Frame class has the methods toString, equals and hash. It also has getters for the fields that the AnimatorModel needs to access.
A Frame describes the position, size and color of a given shape at a given time.

IAnimatorModel has the methods toString, addFrame, removeFrame, removeShape, getFramesByShapeName, getShapeNames, and getShapeNamesAndTypes.
The toString method creates the text output rendering of our assignment.
AddFrame and remove frame can be used either by passing all of the required fields or by simply passing a Frame.
RemoveShape removes all instances of a given shape from the animation.
GetFramesByShapeName gets a list of Strings with all the frames with a given shapeName.
GetShapeNames gets a list of Strings of all the unique shapeNames in the animation.
GetShapeNamesAndTypes gets a HashMap of shapeNames to their respective shapeTypes.
IllegalArgumentExceptions are thrown to ensure that no null fields or strings are passed, and to ensure that no frames are duplicated.
The class AnimatorModel implements IAnimatorModel. It has the field frames, which is a List of Frames in the animation. Frames is ordered by tick, so that the frames are ordered and rendered in chronological order.
It has the private boolean HasDuplicatesOrNulls, which ensures a list has no duplicates or nulls, which ensures that the list of Frames in the constructor is valid.
A "motion" in this AnimatorModel is has a start point of any given Frame and an end point of the next Frame in the list with the same shapeName.

A class invariant in our code is that the r, g and b coordinates for each Frame will always be between 0 and 255. This is enforced in the Frame constructor, which throws an IllegalArgumentException if r, g or b are below 0 or above 255.

CHANGES FOR ASSIGNMENT 6:
I implemented the Builder pattern in the AnimatorModel.
I made the canvas details (leftmost and topmost bounds and width and height) fields in the model.
I added additional getters and setters to my model as needed for the Builder and Views (my Builder implementation in the AnimatorModel uses this these to set the canvas parameters, and my main method uses a speed setter to set the model's speed depending on the user input).
I removed my constructors for AddFrame and RemoveFrame that take a Frame instead of a Frame's parameters, as that would allow the client to construct a frame instance.
I added a field to the model that is a map of shape names to shape types. I originally had a method that retrieved this map, but making it a field made implementing declareShape in the builder implementation much easier.

I created a view interface for the three views (text, visual, and SVG) with the void render, which renders the view as needed.
The text and SVG interfaces both have two constructors: one that just takes a model, and one that takes a model and an appendable. If an appendable is not passed, default ones are set. The visual view has just one constructor that takes a model.

I added a class AnimatorViewCreator which implements a factory pattern for the view. The method create takes a string, compares it to an enum AnimatorViewType, and creates the corresponding type of view.

The class Excellence has my main method, which takes input, output, speed, and view arguments (input and view arguments are required, the others are not) and creates a view from those arguments.

CHANGES FOR ASSIGNMENT 7:

We implemented the AnimatorInteractiveView so that it implements our view interface and extends JFrame. We display the animation on an AnimatorPanel, which is the same panel we use for our visual view (the class AnimatorPanel extends Panel and the constructor takes a model). The interactive view differs from the visual view in that it has a second panel, which we call a buttonPanel, that has a series of JButtons and a JCheckBox allowing the user to start, pause, resume, restart, loop and change the speed of the animation.\

We created a controller interface with the method start(), and replaced the view.render() in Excellence with controller.start().
The controller class instance takes a model and a view for its constructor. It implements IAnimatorController, ActionListener and ItemListener. The go() method renders the view and sets listeners in the interactive view if applicable. The actionPerformed() and itemStateChanged() methods respond to user actions (clicking buttons or checkboxes).

The speed was initially a field in the model, but we moved it to the view to minimize interaction between the view and the model.

We changed the tweening method and the animation now runs smoother and more accurately.

We made one manual animation, which represents a sun setting over water, which is located in the resources package in animation1.txt.
We created one programmed animation, which represents MergeSort. The program for this animation is located in MergeSort.java and the text for the animation is located in the resources package in animation2.txt.

We also set bounds for the animatorPanel in our visual view and interactive view, using the model's leftmost X and topmost Y values, which we had not done before.

CHANGES FOR ASSIGNMENT 8:

A customer pointed out to us that we calculate the speed printed in our textual view wrong, so we updated the code.

As we describe in codeReview.txt in the resources folder, we were able to get our provider's textual view
and interactive view working but functioning properly. We are able to create the model and output, but not start the
animation. So when the textual view is called, only the first motion for each shape will be printed, instead of all
the motions. When the interactive view is called, the canvas and user interface appear, but the animation cannot be played.

EXTRA CREDIT:
To toggle between outline and fill, we added a new field to the view that was a boolean (fill).
If the boolean is true, the shape is filled, and if it false, the shape is drawn. We use buttons
to toggle between the booleans in the interactive view, with listeners in the controller.

We introduced a new shape, a plus sign, using Java Swing's polygon feature. We added an additional
string to the boolean isValidShape, which is used in the Frame constructor. We also throw an error
in the Frame constructor if the user attempts to initialize a plus sign using different widths
and heights.
We also implemented the plus sign in the SVG view using two rectangles layered on top of each other.

To toggle between discrete and continuous, we added a new field to the view that was a boolean
(fill). If the boolean is true, the animation is played continuously, and if it false, the view only
animates at the beginning or end of a motion. We use buttons to toggle between the types of
animation in the interactive view, with listeners in the
controller.

To run the animation in slomo, we implemented a new command in the AnimationReader, which is "slomo"
followed by two integers, the start and end times of a slomo interval. We implemented a method in
AnimationBuilder, addSlomoInterval, to add the interval to the animation. We added a new field to
the model, a list of int arrays representing the intervals, which we used in the view to reduce the
speed to a factor of 4 during those intervals.