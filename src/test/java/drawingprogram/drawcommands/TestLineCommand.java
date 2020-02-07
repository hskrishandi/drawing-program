package drawingprogram.drawcommands;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;

public class TestLineCommand {
    LineCommand command = new LineCommand();
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
        command.parseArgs(new String[]{"10", "3", "4", "5"});

        assertEquals(new Point(10 - 1, 3 - 1), command.getCor1());
        assertEquals(new Point(4 - 1, 5 - 1), command.getCor2());
    }

    @Test
    public void testValidateTrue() {
        command.parseArgs(new String[]{"10", "3", "7", "3"});
        assertTrue(command.validate());
    }

    @Test
    public void testValidateFalseInside() {
        command.parseArgs(new String[]{"10", "3", "20", "3"});
        assertFalse(command.validate());
    }

    @Test
    public void testValidateFalseDiagonal() {
        command.parseArgs(new String[]{"1", "2", "3", "4"});
        assertFalse(command.validate());
    }

    @Test
    public void testDrawHorizontalLine() {
        command.parseArgs(new String[]{"5", "7", "10", "7"});
        command.draw(grid);

        for(int i=4; i<=9; i++) {
            assertEquals('x', grid[6][i]);
        }
    }

    @Test
    public void testDrawVerticalLine() {
        command.parseArgs(new String[]{"10", "2", "10", "8"});
        command.draw(grid);

        for(int i=1; i<=7; i++) {
            assertEquals('x', grid[i][9]);
        }
    }
}
