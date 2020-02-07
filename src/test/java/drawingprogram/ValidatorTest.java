package drawingprogram;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    @Test
    public void testNotDiagonalFalse() {
        Point cor1 = new Point(2,3);
        Point cor2 = new Point(3,5);

        assertFalse(Validator.notDiagonal(cor1, cor2));
    }

    @Test
    public void testNotDiagonalTrueVertical() {
        Point cor1 = new Point(2,3);
        Point cor2 = new Point(2,5);

        assertTrue(Validator.notDiagonal(cor1, cor2));
    }

    @Test
    public void testCheckDiagonalTrueHorizontal() {
        Point cor1 = new Point(5,6);
        Point cor2 = new Point(2,6);

        assertTrue(Validator.notDiagonal(cor1, cor2));
    }

    @Test
    public void testInsideTrue() {
        Point cor = new Point(1,2);
        Point boundaries = new Point(10,5);

        assertTrue(Validator.inside(cor, boundaries));
    }

    @Test
    public void testInsideXOutsideFalse() {
        Point cor1 = new Point(11,2);
        Point cor2 = new Point(-1, 3);
        Point boundaries = new Point(10,5);

        assertFalse(Validator.inside(cor1, boundaries));
        assertFalse(Validator.inside(cor2, boundaries));
    }

    @Test
    public void testInsideYOutsideFalse() {
        Point cor1 = new Point(3,-1);
        Point cor2 = new Point(3, 10);

        Point boundaries = new Point(10,5);

        assertFalse(Validator.inside(cor1, boundaries));
        assertFalse(Validator.inside(cor2, boundaries));
    }

    @Test
    public void testIsRectanglePointsTrue() {
        Point topLeft = new Point(3,5);
        Point bottomRight = new Point(7, 9);

        assertTrue(Validator.isRectanglePoints(topLeft, bottomRight));
    }
    @Test
    public void testIsRectanglePointsFalse() {
        Point topLeft = new Point(8,6);
        Point bottomRight = new Point(7, 9);

        assertFalse(Validator.isRectanglePoints(topLeft, bottomRight));
    }
}
