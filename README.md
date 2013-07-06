mow-it-now
==========
This code based on Xebia's technical test. It builds with Maven 3 and runs with Java 6.


How to execute
--------------
First, build the code:

    # mvn package

Then, to execute the program with the instructions provided in the exercise:

    # java -cp target\mow-it-now.jar com.seigneurin.mowitnow.MowFromFile src/main/resources/instructions.txt

Alternatively, to execute the program with the ability to provide the instructions through the command line:

    # java -cp target\mow-it-now.jar com.seigneurin.mowitnow.MowFromConsole


Domain model
------------
The domain model is made of:
* Field: the area that is mown.
* Mower: the mower that goes over the field.
* Orientation: an orientation defined with a cardinal point (North, South, East, West).
* Position: a combination of a location (x and y coordinates) and of an orientation.


Instructions
------------
Instructions are simple (turn left, turn right, move forward) and are defined with an enumeration.

The InstructionScanner class is responsible for reading instructions from an input stream and writing the position of each mower to an output stream.

Abstract streams are used so as to provide the caller with the ability to provide various concrete streams: files, console, etc.


Service
-------
The MowingService is responsible for managing a mower on a field. It can be used directly (API) or through an InstructionScanner.

This class is unit-tested by the TestMowingService class.

*Notice there was no request for supporting collisions. Therefore, this service does not handle collisions.*