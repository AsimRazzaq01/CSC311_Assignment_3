package com.example.csc311_assignment3;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class MazeController {
    @FXML
    private Pane maze1Pane;
    @FXML
    private ImageView maze1ImageView;
    @FXML
    private Pane maze2Pane;
    @FXML
    private ImageView maze2ImageView;
    @FXML
    private Button startAnimationButton1;
    @FXML
    private Button startAnimationButton2;

    private final Set<String> validPixels = new HashSet<>();

    private final static String MAZE1_FILE = "/images/maze.png";
    private final static String MAZE2_FILE = "/images/maze2.png";
    private final static String CAR_FILE = "/images/car.png";
    private final static InputStream MAZE1 = MazeController.class.getResourceAsStream(MAZE1_FILE);
    private final static InputStream MAZE2 = MazeController.class.getResourceAsStream(MAZE2_FILE);
    private final static InputStream CAR = MazeController.class.getResourceAsStream(CAR_FILE);
    private boolean movementEnabled = false;

    Robot robot = new Robot();

    @FXML
    public void initialize() {
        if (MAZE1 != null) {
            maze1ImageView.setImage(new Image(MAZE1));
            //Makes the image responsive with window size by binding it to the parent pane's width and height
            maze1ImageView.fitWidthProperty().bind(maze1Pane.widthProperty());
            maze1ImageView.fitHeightProperty().bind(maze1Pane.heightProperty());

            loadValidPixels();
            System.out.println("Valid pixels size: " + validPixels.size()); // Debugging

            //Created a robot object and add it to the maze 1 pane
            maze1Pane.getChildren().add(robot);
            maze1Pane.widthProperty().addListener(mz1Width);
            maze1Pane.heightProperty().addListener(mz1Height);

            startAnimationButton1.setFocusTraversable(false);
            maze1Pane.setFocusTraversable(true);

            maze1Pane.setOnKeyPressed(event -> {
                if (movementEnabled) {  // Only move if enabled
                    moveRobot(event.getCode());
                    event.consume(); // Prevent tab switching
                }
            });
        }


        if (MAZE2 != null) {
            maze2ImageView.setImage(new Image(MAZE2));
            //Makes the image responsive with window size by binding it to the parent pane's width and height
            maze2ImageView.fitWidthProperty().bind(maze2Pane.widthProperty());
            maze2ImageView.fitHeightProperty().bind(maze2Pane.heightProperty());
        }


    }

    ChangeListener<Number> mz1Width = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
            //Listens to the width of maze 1 image and scales robot position according to its size
            //Passes the value of the calculated scale factor (new width/old width)
            robot.setScaleFactorX(newValue.doubleValue() / maze1ImageView.getImage().getWidth());
            //Listens to the width of maze 1 pane and scales robot size accordingly to be a consistent size
            robot.updateRobotSize(maze1Pane.getWidth(), maze1Pane.getHeight());
            robot.updateRobotRelativePosition();
        }
    };

    ChangeListener<Number> mz1Height = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
            //Listens to the height of maze 1 image and scales robot position according to its size
            //Passes the value of the calculated scale factor (new width/old width)
            robot.setScaleFactorY(newValue.doubleValue() / maze1ImageView.getImage().getHeight());
            //Listens to the height of maze 1 pane and scales robot size accordingly to be a consistent size
            robot.updateRobotSize(maze1Pane.getWidth(), maze1Pane.getHeight());
            robot.updateRobotRelativePosition();
        }
    };


    @FXML
    void Manual_Mode() {
        movementEnabled = true;
        maze1Pane.requestFocus();
    }



    @FXML
    private void Automatic_Animation() {
//        movementEnabled = true;
//        maze1Pane.requestFocus();

        // Animation of car (Automatically) on button click
        // initial Move 0
        TranslateTransition translate = new TranslateTransition(Duration.millis(500), robot);
        translate.setByX(35);
        //1st move
        TranslateTransition translate1 = new TranslateTransition(Duration.millis(700), robot);
        translate1.setByY(-110);
        //2nd move
        TranslateTransition translate2 = new TranslateTransition(Duration.millis(1000), robot);
        translate2.setByX(255);
        //3rd move
        TranslateTransition translate3 = new TranslateTransition(Duration.millis(500), robot);
        translate3.setByY(-55);
        //4th move
        TranslateTransition translate4 = new TranslateTransition(Duration.millis(500), robot);
        translate4.setByX(62);
        //5th move
        TranslateTransition translate5 = new TranslateTransition(Duration.millis(1000), robot);
        translate5.setByY(222);
        //6th move
        TranslateTransition translate6 = new TranslateTransition(Duration.millis(500), robot);
        translate6.setByX(68);
        //7th move
        TranslateTransition translate7 = new TranslateTransition(Duration.millis(700), robot);
        translate7.setByY(-110);
        //8th move
        TranslateTransition translate8 = new TranslateTransition(Duration.millis(700), robot);
        translate8.setByX(128);
        //9th move
        TranslateTransition translate9 = new TranslateTransition(Duration.millis(700), robot);
        translate9.setByY(-110);
        //10th move
        TranslateTransition translate10 = new TranslateTransition(Duration.millis(500), robot);
        translate10.setByX(70);
        //11th move
        TranslateTransition translate11 = new TranslateTransition(Duration.millis(700), robot);
        translate11.setByY(150);
        //12th move
        TranslateTransition translate12 = new TranslateTransition(Duration.millis(1000), robot);
        translate12.setByX(200);

        // Add all the transitions to a sequence
        SequentialTransition sequentialTransition = new SequentialTransition(translate,
                translate1, translate2, translate3, translate4, translate5, translate6,
                translate7, translate8, translate9, translate10, translate11, translate12);
        //Play Sequence
        sequentialTransition.play();
    }

    private void moveRobot(KeyCode key) {
        double step = 5;
        double newX = robot.getLayoutX();
        double newY = robot.getLayoutY();

        switch (key) {
            case UP -> newY -= step;
            case DOWN -> newY += step;
            case LEFT -> newX -= step;
            case RIGHT -> newX += step;
        }

        if (isValidMove(newX, newY)) {
            Rectangle trail = new Rectangle(
                    robot.getLayoutX() + robot.getFitWidth() / 2, // Center x
                    robot.getLayoutY() + robot.getFitHeight() / 2, // Center y
                    3, 3 // Small square
            );
            trail.setFill(Color.RED);
            maze1Pane.getChildren().add(trail);

            robot.setLayoutX(newX);
            robot.setLayoutY(newY);
        }
    }


    private void loadValidPixels() {
        if (maze1ImageView.getImage() == null || maze1ImageView.getImage().getPixelReader() == null) {
            System.out.println("Error: Image not found or cannot be read.");
            return;
        }

        int width = (int) maze1ImageView.getImage().getWidth();
        int height = (int) maze1ImageView.getImage().getHeight();

        System.out.println("Scanning maze image of size: " + width + "x" + height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = maze1ImageView.getImage().getPixelReader().getColor(x, y);

                // Check if the pixel is NOT blue
                if (!isBlue(color)) {
                    validPixels.add(x + "," + y);
                }
            }
        }

        System.out.println("Valid pixels loaded: " + validPixels.size());
    }
    private boolean isBlue(Color color) {
        double blueThreshold = 0.5;
        //checks for the color blue
        return color.getBlue() > blueThreshold && color.getRed() < 0.4 && color.getGreen() < 0.4;
    }


// mostly checks to see where it is able to move
    private boolean isValidMove(double x, double y) {
        if (validPixels.isEmpty()) {
            System.out.println("Warning: No valid pixels loaded!");
            return false;
        }

        int pixelX = (int) ((x / maze1ImageView.getFitWidth()) * maze1ImageView.getImage().getWidth());
        int pixelY = (int) ((y / maze1ImageView.getFitHeight()) * maze1ImageView.getImage().getHeight());

        if (pixelX < 0 || pixelY < 0 || pixelX >= maze1ImageView.getImage().getWidth() || pixelY >= maze1ImageView.getImage().getHeight()) {
            return false;
        }

        // Block movement if it's not in the valid pixel set
        boolean isAllowed = validPixels.contains(pixelX + "," + pixelY);

        System.out.println("Checking move to (" + pixelX + ", " + pixelY + "): " + (isAllowed ? "Allowed" : "Blocked"));

        return isAllowed;
    }

}