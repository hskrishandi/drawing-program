package drawingprogram.drawcommands;

import drawingprogram.Validator;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class ColorCommand extends DrawCommand {
    private char color;
    private Point start = new Point();

    ColorCommand() {
        super(3);
    }

    public char getColor() {
        return color;
    }

    public Point getStart() {
        return start;
    }

    @Override
    void parseArgs(String[] args) {
        start.setLocation(Integer.parseInt(args[0]) - 1, Integer.parseInt(args[1]) - 1);
        color = args[2].charAt(0);
    }

    @Override
    boolean validate() {
        return Validator.inside(start, boundaries);
    }

    @Override
    void draw(char[][] grid) {
        char originalColor = grid[start.y][start.x];
        if(originalColor == color)
            return;

        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            Point pos = queue.poll();
            grid[pos.y][pos.x] = color;

            if((pos.y - 1) >= 0) {
                if(grid[pos.y - 1][pos.x] == originalColor)
                    queue.add(new Point(pos.x, pos.y - 1));
            }

            if((pos.y + 1) < boundaries.y) {
                if(grid[pos.y + 1][pos.x] == originalColor)
                    queue.add(new Point(pos.x, pos.y + 1));
            }

            if((pos.x - 1) >= 0) {
                if(grid[pos.y][pos.x - 1] == originalColor)
                    queue.add(new Point(pos.x - 1, pos.y));
            }

            if((pos.x + 1) < boundaries.x) {
                if(grid[pos.y][pos.x + 1] == originalColor)
                    queue.add(new Point(pos.x + 1, pos.y));
            }
        }
    }
}
