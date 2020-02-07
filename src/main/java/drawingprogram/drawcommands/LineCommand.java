package drawingprogram.drawcommands;

import drawingprogram.Validator;

import java.awt.Point;

public class LineCommand extends DrawCommand {
    private Point cor1 = new Point();
    private Point cor2 = new Point();
    private char fill = 'x';

    LineCommand() {
        super(4);
    }

    public Point getCor1() {
        return cor1;
    }

    public Point getCor2() {
        return cor2;
    }

    @Override
    void parseArgs(String[] args) {
        cor1.setLocation(Integer.parseInt(args[0]) - 1, Integer.parseInt(args[1]) - 1);
        cor2.setLocation(Integer.parseInt(args[2]) - 1, Integer.parseInt(args[3]) - 1);
    }

    @Override
    boolean validate() {
        if (
            Validator.inside(cor1, boundaries) &&
            Validator.inside(cor2, boundaries) &&
            Validator.notDiagonal(cor1, cor2)
        )
            return true;
        return false;
    }

    @Override
    void draw(char[][] grid) {
        if (cor1.x == cor2.x) {
            for(int i = Math.min(cor1.y, cor2.y); i <= Math.max(cor1.y, cor2.y); i++) {
                grid[i][cor1.x] = fill;
            }
        } else if (cor1.y == cor2.y){
            for (int i = Math.min(cor1.x, cor2.x); i <= Math.max(cor1.x, cor2.x); i++) {
                grid[cor1.y][i] = fill;
            }
        }
    }
}
