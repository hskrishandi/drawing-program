/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package drawingprogram;

import drawingprogram.drawcommands.DrawCommand;

import java.util.Scanner;

public class App {
    private boolean running = true;
    private Canvas canvas = new Canvas();

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.print("enter command: ");
            String s = input.nextLine();

            char commandChar = s.charAt(0);
            String[] args = s.substring(1).trim().split(" ");

            interpret(commandChar, args);
            System.out.println();
        }
    }

    private void interpret(char commandChar, String[] args) {
        switch (commandChar) {
            case 'C':
                createCanvas(args);
                break;
            case 'Q':
                stop();
                break;
            default:
                drawCanvas(commandChar, args);
        }
    }

    private void createCanvas(String[] args) {
        if(args.length < 2) {
            System.out.println("Not enough arguments");
            return;
        }
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        if (canvas.initGrid(width, height)){
            DrawCommand.setBoundaries(width, height);
            canvas.print();
        }
    }

    private void drawCanvas(char commandChar, String[] args){
        DrawCommand command = DrawCommand.getCommand(commandChar);
        if(command != null) {
            command.execute(canvas, args);
        } else {
            System.out.println("Command not found");
        }
    }

    private void stop() {
        running = false;
    }
}