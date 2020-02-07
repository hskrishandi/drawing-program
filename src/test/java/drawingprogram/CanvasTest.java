package drawingprogram;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CanvasTest {
    Canvas canvas = new Canvas();

    @Test
    public void testInitGrid() {
        int w = 5;
        int h = 6;
        char[] row = {' ',' ',' ',' ',' '};
        canvas.initGrid(5,6);

        assertEquals(w, canvas.getWidth());
        assertEquals(h, canvas.getHeight());
        assertEquals(h, canvas.getGrid().length);
        assertEquals(w, canvas.getGrid()[0].length);
        assertArrayEquals(row, canvas.getGrid()[1]);
    }

    @Test
    public void testInitGridZeroNegativeFalse() {
        int w = 3;
        int h = 6;
        canvas.initGrid(w, h);
        char[][] currentGrid = canvas.getGrid();

        canvas.initGrid(2, -10);
        assertEquals(w, canvas.getWidth());
        assertEquals(h, canvas.getHeight());
        assertArrayEquals(currentGrid, canvas.getGrid());

        canvas.initGrid(0, 5);
        assertEquals(w, canvas.getWidth());
        assertEquals(h, canvas.getHeight());
        assertArrayEquals(currentGrid, canvas.getGrid());
    }
}
