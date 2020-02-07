package drawingprogram.drawcommands;

import drawingprogram.Canvas;
import org.junit.Test;
import org.mockito.Mockito;
import java.awt.Point;

import static org.junit.Assert.*;

public class TestDrawCommand {

    DrawCommand drawCommand = Mockito.mock(DrawCommand.class);

    @Test
    public void testGetCommand() {
        assertTrue(DrawCommand.getCommand('L') instanceof LineCommand);
        assertTrue(DrawCommand.getCommand('R') instanceof RectangleCommand);
        assertTrue(DrawCommand.getCommand('B') instanceof ColorCommand);
    }

    @Test
    public void testSetBoundaries() {
        DrawCommand.setBoundaries(15, 10);
        assertEquals(new Point(15, 10), DrawCommand.boundaries);
    }

    @Test
    public void testCheckNumArgsTrue() {
        // Concrete Instance: LineCommand with numArgs = 4
        LineCommand command = new LineCommand();
        assertTrue(command.checkNumArgs(new String[]{"12", "10", "3", "4"}));
    }

    @Test
    public void testCheckNumArgsFalse() {
        // Concrete Instance: ColorCommand with numArgs = 3
        ColorCommand command = new ColorCommand();
        assertFalse(command.checkNumArgs(new String[]{"12"}));
    }

    @Test
    public void testExecuteFailAtCanvasNotInitialized() {
        Canvas canvas = new Canvas();
        String[] args = new String[]{"10", "2", "c"};
        Mockito.when(drawCommand.checkNumArgs(args)).thenReturn(true);
        Mockito.doCallRealMethod().when(drawCommand).execute(canvas, args);

        drawCommand.execute(canvas, args);

        Mockito.verify(drawCommand, Mockito.never()).checkNumArgs(args);
        Mockito.verify(drawCommand, Mockito.never()).parseArgs(args);
        Mockito.verify(drawCommand, Mockito.never()).validate();
        Mockito.verify(drawCommand, Mockito.never()).draw(canvas.getGrid());
    }

    @Test
    public void testExecuteFailAtNumArgs() {
        Canvas canvas = new Canvas();
        canvas.initGrid(15, 10);
        String[] args = new String[]{"10"};
        Mockito.when(drawCommand.checkNumArgs(args)).thenReturn(false);
        Mockito.doCallRealMethod().when(drawCommand).execute(canvas, args);

        drawCommand.execute(canvas, args);

        Mockito.verify(drawCommand).checkNumArgs(args);
        Mockito.verify(drawCommand, Mockito.never()).parseArgs(args);
        Mockito.verify(drawCommand, Mockito.never()).validate();
        Mockito.verify(drawCommand, Mockito.never()).draw(canvas.getGrid());
    }

    @Test
    public void testExecuteFailAtValidate() {
        Canvas canvas = new Canvas();
        canvas.initGrid(15, 10);
        String[] args = new String[]{"10"};
        Mockito.when(drawCommand.checkNumArgs(args)).thenReturn(true);
        Mockito.when(drawCommand.validate()).thenReturn(false);
        Mockito.doCallRealMethod().when(drawCommand).execute(canvas, args);

        drawCommand.execute(canvas, args);

        Mockito.verify(drawCommand).checkNumArgs(args);
        Mockito.verify(drawCommand).parseArgs(args);
        Mockito.verify(drawCommand).validate();
        Mockito.verify(drawCommand, Mockito.never()).draw(canvas.getGrid());
    }

    @Test
    public void testExecuteSuccess() {
        Canvas canvas = new Canvas();
        canvas.initGrid(15, 10);
        String[] args = new String[]{"10"};
        Mockito.when(drawCommand.checkNumArgs(args)).thenReturn(true);
        Mockito.when(drawCommand.validate()).thenReturn(true);
        Mockito.doCallRealMethod().when(drawCommand).execute(canvas, args);

        drawCommand.execute(canvas, args);

        Mockito.verify(drawCommand).checkNumArgs(args);
        Mockito.verify(drawCommand).parseArgs(args);
        Mockito.verify(drawCommand).validate();
        Mockito.verify(drawCommand).draw(canvas.getGrid());
    }
}
