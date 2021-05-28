import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Scanner;
import java.util.Stack;

public class TowerOfHanoiApplication extends Application {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    Stack<Integer> stack3 = new Stack<Integer>();
    public final int totalHeight = 300;
    int numberOfDisks;
    int diskHeight;
    public final int minDiskWidth = 100;
    public final int maxDiskWidth = 300;
    public static final int SELECTING = 0;
    public static final int SWITCHING = 1;
    public static final int GAME_OVER = 2;
    int status = SELECTING;
    int diskWidth;
    int diskWidthChange;
    int[] arrayOfWidths;
    int selected = -1;
    int chosenSpot=0;
    int turnCounter=0;
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Tower of Hanoi");
        Scanner keyboard = new Scanner(System.in);
        System.out.println("How many discs would you like to play with (3-7)?");
        numberOfDisks = keyboard.nextInt();
        diskHeight = totalHeight/numberOfDisks;
        diskWidthChange = ((maxDiskWidth-minDiskWidth)/(numberOfDisks-1));
        //diskValue is the number in which the disk is in the stack
        //diskWidth = (diskValue-1)*diskWidthChange+minDiskWidth;
        arrayOfWidths = new int[numberOfDisks];
        for(int i=0;i<numberOfDisks;i++)
        {
            stack1.push(numberOfDisks-(i+1));
            arrayOfWidths[i] = (i*diskWidthChange)+minDiskWidth;
        }
        Group g = new Group();
        Canvas c = new Canvas(1000,500);
        c.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String movement = event.getCharacter();
                if(movement.equals("1") && status==SELECTING)
                {
                    selected=1;
                    //chosenSpot=stack1.peek();
                }
                else if(movement.equals("2") && status==SELECTING)
                {
                    selected=2;
                    //chosenSpot=stack2.peek();
                }
                else if(movement.equals("3") && status==SELECTING)
                {
                    selected=3;
                    //chosenSpot=stack3.peek();
                }
                else if(movement.equals("1") && status==SWITCHING)
                {
                    if(stack1.empty())
                    {
                        if (selected == 1) {
                            selected = -1;
                            turnCounter--;
                        }
                        else if (selected == 2 && !stack2.empty()) {
                            chosenSpot = stack2.peek();
                            stack2.pop();
                            stack1.push(chosenSpot);
                            selected = -1;
                        }
                        else if (selected == 3 && !stack3.empty()) {
                            chosenSpot = stack3.peek();
                            stack3.pop();
                            stack1.push(chosenSpot);
                            selected = -1;
                        }
                    }
                    else if(!stack1.empty()) {
                        if (selected == 1) {
                            selected = -1;
                            turnCounter--;
                        }
                        else if (selected == 2 && !stack2.empty()) {
                            chosenSpot = stack2.peek();
                            if(chosenSpot<stack1.peek()) {
                                stack2.pop();
                                stack1.push(chosenSpot);
                                selected = -1;
                            }
                        }
                        else if (selected == 3 && !stack3.empty()) {
                            chosenSpot = stack3.peek();
                            if(chosenSpot<stack1.peek()) {
                                stack3.pop();
                                stack1.push(chosenSpot);
                                selected = -1;
                            }
                        }
                    }
                }
                else if(movement.equals("2") && status==SWITCHING)
                {
                    if(stack2.empty())
                    {
                        if (selected == 2) {
                            selected = -1;
                            turnCounter--;
                        }
                        else if (selected == 1 && !stack1.empty()) {
                            chosenSpot = stack1.peek();
                            stack1.pop();
                            stack2.push(chosenSpot);
                            selected = -1;
                        }
                        else if (selected == 3 && !stack3.empty()) {
                            chosenSpot = stack3.peek();
                            stack3.pop();
                            stack2.push(chosenSpot);
                            selected = -1;
                        }
                    }
                    else if(!stack1.empty()) {
                        if (selected == 2) {
                            selected = -1;
                            turnCounter--;
                        }
                        else if (selected == 1 && !stack1.empty()) {
                            chosenSpot = stack1.peek();
                            if(chosenSpot<stack2.peek()) {
                                stack1.pop();
                                stack2.push(chosenSpot);
                                selected = -1;
                            }
                        }
                        else if (selected == 3 && !stack3.empty()) {
                            chosenSpot = stack3.peek();
                            if(chosenSpot<stack2.peek()) {
                                stack3.pop();
                                stack2.push(chosenSpot);
                                selected = -1;
                            }
                        }
                    }
                }

                else if(movement.equals("3") && status==SWITCHING)
                {
                    if(stack3.empty())
                    {
                        if (selected == 3) {
                            selected = -1;
                            turnCounter--;
                        }
                        else if (selected == 2 && !stack2.empty()) {
                            chosenSpot = stack2.peek();
                            stack2.pop();
                            stack3.push(chosenSpot);
                            selected = -1;
                        }
                        else if (selected == 1 && !stack1.empty()) {
                            chosenSpot = stack1.peek();
                            stack1.pop();
                            stack3.push(chosenSpot);
                            selected = -1;
                        }
                    }
                    else if(!stack3.empty()) {
                        if (selected == 3) {
                            selected = -1;
                            turnCounter--;
                        }
                        else if (selected == 2 && !stack2.empty()) {
                            chosenSpot = stack2.peek();
                            if(chosenSpot<stack3.peek()) {
                                stack2.pop();
                                stack3.push(chosenSpot);
                                selected = -1;
                            }
                        }
                        else if (selected == 1 && !stack1.empty()) {
                            chosenSpot = stack1.peek();
                            if(chosenSpot<stack3.peek()) {
                                stack1.pop();
                                stack3.push(chosenSpot);
                                selected = -1;
                            }
                        }
                    }
                }
                if(stack3.size()==numberOfDisks)
                    status=GAME_OVER;
                paint(c.getGraphicsContext2D());
                if(status==SELECTING) {
                    status = SWITCHING;
                    turnCounter++;
                }
                else if(status==SWITCHING)
                    status=SELECTING;
            }
        });
        g.getChildren().add(c);
        Scene sc = new Scene(g);
        stage.setScene(sc);
        paint(c.getGraphicsContext2D());
        c.requestFocus();
        stage.show();
    }
    public void paint(GraphicsContext g)
    {
        g.setFill(Color.WHITE);
        g.fillRect(0,0,1000,600);
        g.setFill(Color.BLACK);
        g.fillRect(0,500,1000,100);
        if(selected==1) {
            g.setFill(Color.RED);
            g.fillRect(200,100,50,400);
            g.setFill(Color.rgb(153,163,164));
            g.fillRect(500,100,50,400);
            g.fillRect(800,100,50,400);
        }
        else if(selected==2){
            g.setFill(Color.rgb(153,163,164));
            g.fillRect(200,100,50,400);
            g.setFill(Color.RED);
            g.fillRect(500,100,50,400);
            g.setFill(Color.rgb(153,163,164));
            g.fillRect(800,100,50,400);
        }
        else if(selected==3)
        {
            g.setFill(Color.rgb(153,163,164));
            g.fillRect(200,100,50,400);
            g.fillRect(500,100,50,400);
            g.setFill(Color.RED);
            g.fillRect(800,100,50,400);
        }
        else if(selected==-1)
        {
            g.setFill(Color.rgb(153,163,164));
            g.fillRect(200,100,50,400);
            g.fillRect(500,100,50,400);
            g.fillRect(800,100,50,400);
        }
        g.setFill(Color.rgb(0,255,51));
        for(int i=0;i<stack1.size();i++) {
            //g.fillRect(225 - ((diskWidthChange * (i + 1)) / 2), 500 - ((stack1.size() - i) * diskHeight), arrayOfWidths[stack1.get(stack1.size()-(i+1))], diskHeight);
            g.fillRect(200 - (arrayOfWidths[stack1.get(stack1.size()-(i+1))]-50)/2, 500 - ((stack1.size() - i) * diskHeight), arrayOfWidths[stack1.get(stack1.size()-(i+1))], diskHeight);
            g.setStroke(Color.BLACK);
            g.strokeRect(200 - (arrayOfWidths[stack1.get(stack1.size()-(i+1))]-50)/2, 500 - ((stack1.size() - i) * diskHeight), arrayOfWidths[stack1.get(stack1.size()-(i+1))], diskHeight);
        }
        for(int i=0;i<stack2.size();i++) {
            g.fillRect(500 - (arrayOfWidths[stack2.get(stack2.size()-(i+1))]-50)/2, 500 - ((stack2.size() - i) * diskHeight), arrayOfWidths[stack2.get(stack2.size()-(i+1))], diskHeight);
            g.setStroke(Color.BLACK);
            g.strokeRect(500 - (arrayOfWidths[stack2.get(stack2.size()-(i+1))]-50)/2, 500 - ((stack2.size() - i) * diskHeight), arrayOfWidths[stack2.get(stack2.size()-(i+1))], diskHeight);
        }
        for(int i=0;i<stack3.size();i++) {
            g.fillRect(800 - (arrayOfWidths[stack3.get(stack3.size()-(i+1))]-50)/2, 500 - ((stack3.size() - i) * diskHeight), arrayOfWidths[stack3.get(stack3.size()-(i+1))], diskHeight);
            g.setStroke(Color.BLACK);
            g.strokeRect(800 - (arrayOfWidths[stack3.get(stack3.size()-(i+1))]-50)/2, 500 - ((stack3.size() - i) * diskHeight), arrayOfWidths[stack3.get(stack3.size()-(i+1))], diskHeight);
        }
        if(status==GAME_OVER) {
            g.setFont(new Font("Times New Roman", 50));
            g.setFill(Color.ORANGE);
            g.fillText("Game Over. You won in " + turnCounter + " moves", 200, 50);
        }
    }
}
