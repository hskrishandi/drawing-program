package drawingprogram.drawcommands;

import drawingprogram.Canvas;

import java.awt.Point;
import java.util.HashMap;

public abstract class DrawCommand {
    private static HashMap<Character, DrawCommand> list = new HashMap<Character, DrawCommand>() {
        {
            put('L', new LineCommand());
            put('R', new RectangleCommand());
            put('B', new ColorCommand());
        }
    };
    protected int numArgs;
    protected static Point boundaries = new Point();

    DrawCommand(int numArgs) {
        this.numArgs = numArgs;
    }

    public static DrawCommand getCommand(char c) {
        return list.get(c);
    }

    public static void setBoundaries(int width, int height) {
        boundaries.setLocation(width, height);
    }

    public void execute(Canvas canvas, String[] args) {
        if (canvas.getGrid() == null) {
            System.out.println("Grid is not initialized");
            return;
        }

        if(!this.checkNumArgs(args))
            return;

        parseArgs(args);
        if (validate()) {
            draw(canvas.getGrid());
            canvas.print();
        }
    }

    boolean checkNumArgs(String[] args) {
        if (args.length < numArgs) {
            System.out.println("Not enough arguments");
            return false;
        }
        return true;
    }

    abstract void parseArgs(String[] args);

    abstract boolean validate();

    abstract void draw(char[][] grid);
}
