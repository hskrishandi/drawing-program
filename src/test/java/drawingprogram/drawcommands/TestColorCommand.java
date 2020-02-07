package drawingprogram.drawcommands;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestColorCommand {
    ColorCommand command = new ColorCommand();
    char[][] grid;
    int width = 15;
    int height = 10;

    @BeforeClass
    public static void setBoundaries() {
        DrawCommand.setBoundaries(15, 10);
    }

    @Before
    public void setupGrid(){
        grid = createGrid(width, height, ' ');
    }

    @Test
    public void testParseArgs() {
        command.parseArgs(new String[]{"2", "3", "o"});

        assertEquals('o', command.getColor());
        assertEquals(new Point(2 - 1, 3 - 1), command.getStart());
    }

    @Test
    public void testValidateTrue() {
        command.parseArgs(new String[]{"10", "3", "d"});
        assertTrue(command.validate());
    }

    @Test
    public void testValidateFalseInside() {
        command.parseArgs(new String[]{"10", "25", "d"});
        assertFalse(command.validate());
    }

    @Test
    public void testDrawWithLines() {
        char[][] resultGrid = createGrid(width, height, 'c');
        LineCommand lineCommand = new LineCommand();

        //draw some line
        lineCommand.parseArgs(new String[]{"5", "3", "2", "3"});
        lineCommand.draw(resultGrid);
        lineCommand.draw(grid);

        //draw some line
        lineCommand.parseArgs(new String[]{"6", "1", "6", "9"});
        lineCommand.draw(resultGrid);
        lineCommand.draw(grid);

        command.parseArgs(new String[]{"1", "2", "c"});
        command.draw(grid);

        assertTrue(checkTwoGrids(resultGrid, grid));
    }

    @Test
    public void testDrawWithRectanglesColorOutside() {
        char[][] resultGrid = createGrid(width, height, 'c');
        RectangleCommand rectCommand = new RectangleCommand();

        // draw some rectangle
        rectCommand.parseArgs(new String[]{"5", "3", "10", "7"});
        rectCommand.draw(resultGrid);
        rectCommand.draw(grid);

        // the hollow part
        for(int j = 3; j < 6 ; j++){
            for(int i = 5; i < 9; i++){
                resultGrid[j][i] = ' ';
            }
        }

        command.parseArgs(new String[]{"2", "4", "c"});
        command.draw(grid);
        assertTrue(checkTwoGrids(resultGrid, grid));
    }

    @Test
    public void testDrawWithRectanglesColorInside() {
        char[][] resultGrid = createGrid(width, height, ' ');
        RectangleCommand rectCommand = new RectangleCommand();

        // draw some rectangle
        rectCommand.parseArgs(new String[]{"5", "3", "10", "7"});
        rectCommand.draw(grid);
        rectCommand.draw(resultGrid);

        // the color inside part
        for(int j = 3; j < 6 ; j++){
            for(int i = 5; i < 9; i++){
                resultGrid[j][i] = 'c';
            }
        }

        command.parseArgs(new String[]{"6", "5", "c"});
        command.draw(grid);
        assertTrue(checkTwoGrids(resultGrid, grid));
    }

    @Test
    public void testDrawOnLines() {
        char[][] resultGrid = createGrid(width, height, ' ');
        LineCommand lineCommand = new LineCommand();

        //draw some line
        lineCommand.parseArgs(new String[]{"5", "3", "2", "3"});
        lineCommand.draw(grid);
        for(int i = 1; i <= 4; i++) {
            resultGrid[2][i] = 'c';
        }

        //draw some line
        lineCommand.parseArgs(new String[]{"6", "1", "6", "9"});
        lineCommand.draw(grid);
        for(int i = 0; i <= 8; i++) {
            resultGrid[i][5] = 'c';
        }

        //color on line
        command.parseArgs(new String[]{"3", "3", "c"});
        command.draw(grid);

        assertTrue(checkTwoGrids(resultGrid, grid));
    }

    @Test
    public void testDrawColorOnColored() {
        char[][] coloredGrid = createGrid(width, height, 'd');
        char[][] resultGrid = createGrid(width, height, 'c');
        LineCommand lineCommand = new LineCommand();

        //draw some line
        lineCommand.parseArgs(new String[]{"5", "3", "2", "3"});
        lineCommand.draw(resultGrid);
        lineCommand.draw(coloredGrid);

        //draw some line
        lineCommand.parseArgs(new String[]{"6", "1", "6", "9"});
        lineCommand.draw(resultGrid);
        lineCommand.draw(coloredGrid);

        command.parseArgs(new String[]{"1", "2", "c"});
        command.draw(coloredGrid);

        assertTrue(checkTwoGrids(resultGrid, coloredGrid));
    }

    private char[][] createGrid(int w, int h, char bgChar) {
        char[][] resultGrid = new char[h][];
        for(int i = 0; i < h; i++) {
            resultGrid[i] = new char[w];
            for(int j = 0; j < w; j++) {
                resultGrid[i][j] = bgChar;
            }
        }
        return resultGrid;
    }

    private boolean checkTwoGrids(char[][] expected, char[][] actual) {
        if (expected.length != actual.length || expected[0].length != actual[0].length)
            return false;

        for(int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                if(expected[i][j]!=actual[i][j])
                    return false;
            }
        }
        return true;
    }
}
