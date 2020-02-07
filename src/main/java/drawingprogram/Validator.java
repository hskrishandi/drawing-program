package drawingprogram;

import java.awt.Point;

public class Validator {
    public static boolean inside(Point cor, Point boundaries) {
        if(cor.x < 0 || cor.x >= boundaries.x || cor.y < 0 || cor.y >= boundaries.y) {
            System.out.println("Point must be inside canvas");
            return false;
        }
        return true;
    }

    public static boolean notDiagonal(Point cor1, Point cor2) {
        if (cor1.x != cor2.x && cor1.y != cor2.y) {
            System.out.println("Line can't be diagonal");
            return false;
        }
        return true;
    }

    public static boolean isRectanglePoints(Point topLeft, Point bottomRight) {
        if(topLeft.x < bottomRight.x && topLeft.y < bottomRight.y) {
            return true;
        } else {
            System.out.println("Rectangle points are incorrect");
            return false;
        }
    }
}
