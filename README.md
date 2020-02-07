# Drawing Program
This is a simple console drawing program written in Java.
Currently only supports making lines, rectangles, and flood fill coloring


## Prerequisite
Make sure Java SE and JDK are installed in the machine.


## Getting Started
Extract the zip file to a directory. Make sure the root directory is named 'drawingprogram'. Then, navigate to the program root directory.

To run the app:

### Windows
```bash
.\gradlew run --console=plain
```

### Linux
```bash
./gradlew run --console=plain
```

To run tests:

### Windows
```bash
.\gradlew test
```

### Linux
```bash
./gradlew test
```

## Commands 

Command         Description
C w h           Create a new canvas of width w and height h.

L x1 y1 x2 y2   Create a new line from (x1,y1) to (x2,y2). Currently only
                horizontal or vertical lines are supported. Horizontal and vertical lines
                will be drawn using the 'x' character.

R x1 y1 x2 y2   Create a new rectangle, whose upper left corner is (x1,y1) and
                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
                using the 'x' character.

B x y c         Fill the entire area connected to (x,y) with "colour" c. The
                behaviour of this is the same as that of the "bucket fill" tool in paint
                programs.

Q               Should quit the program.


## Assumptions
-   User will always input the correct type arguments with correct spacing. Arguments maybe too few or result in points
    outside canvas, but integer arguments should always receive integer input, etc. This is merely to simplify the validation.
-   If the starting point for the color command falls on a line or rectangle edge, the line or rectangle edge will change
    to the color argument, just like Paint
-   Like Paint, drawing always overwrites the state before (e.g line xxx goes over a 'c' colored space)
-   Since the sample I/O does not cover the error inputs, message of the error is assumed to be given for a wrong input.

## Troubleshooting

If `./gradlew: Permission denied.`  is encountered, run the following command to enable the execution permission in the local machine.

```bash
$ chmod +x gradlew
```
