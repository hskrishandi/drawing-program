package drawingprogram.drawcommands;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestRectangleCommand {
    RectangleCommand command = new RectangleCommand();
    char[][] grid;
    int width = 15;
    int height = 10;

    @BeforeClass
    public static void setBoundaries() {
        DrawCommand.setBoundaries(15, 10);
    }

    @Before
    public void setupGrid(){
        grid = new char[height][];
        for(int i = 0; i < height; i++) {
            grid[i] = new char[width];
            for(int j = 0; j < width; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    @Test
    public void testParseArgs() {
        command.parseArgs(new String[]{"2", "3", "4", "5"});

        assertEquals(new Point(2 - 1, 3 - 1), command.getTopLeft());
        assertEquals(new Point(4 - 1, 5 - 1), command.getBottomRight());
    }

    @Test
    public void testValidateTrue() {
        command.parseArgs(new String[]{"2", "3", "10", "7"});
        assertTrue(command.validate());
    }

    @Test
    public void testValidateFalseInside() {
        command.parseArgs(new String[]{"-10", "3", "8", "7"});
        assertFalse(command.validate());
    }

    @Test
    public void testValidateFalseRectanglePoints() {
        command.parseArgs(new String[]{"5", "2", "3", "4"});
        assertFalse(command.validate());
    }

    @Test
    public void testDraw() {
        command.parseArgs(new String[]{"4", "5", "8", "7"});
        command.draw(grid);
        for(int i = 5-1; i <= 7-1; i++) {
            assertEquals('x', grid[i][4-1]);
            assertEquals('x', grid[i][8-1]);
        }

        for (int i = 4-1; i <= 8-1; i++) {
            assertEquals('x', grid[5-1][i]);
            assertEquals('x', grid[7-1][i]);
        }
    }
}
