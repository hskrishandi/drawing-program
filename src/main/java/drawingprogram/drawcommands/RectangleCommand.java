package drawingprogram.drawcommands;

import drawingprogram.Validator;

import java.awt.Point;

public class RectangleCommand extends DrawCommand {
    private Point topLeft = new Point();
    private Point bottomRight = new Point();
    private char fill = 'x';

    RectangleCommand() {
        super(4);
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    void parseArgs(String[] args) {
        topLeft.setLocation(Integer.parseInt(args[0]) - 1, Integer.parseInt(args[1]) - 1);
        bottomRight.setLocation(Integer.parseInt(args[2]) - 1, Integer.parseInt(args[3]) - 1);
    }

    @Override
    boolean validate() {
        if (
            Validator.isRectanglePoints(topLeft, bottomRight) &&
            Validator.inside(topLeft, boundaries) &&
            Validator.inside(bottomRight, boundaries)
        )
            return true;
        return false;
    }

    @Override
    void draw(char[][] grid) {
        for(int i = topLeft.y; i <= bottomRight.y; i++) {
            grid[i][topLeft.x] = fill;
            grid[i][bottomRight.x] = fill;
        }

        for (int i = topLeft.x; i <= bottomRight.x; i++) {
            grid[topLeft.y][i] = fill;
            grid[bottomRight.y][i] = fill;
        }
    }
}
