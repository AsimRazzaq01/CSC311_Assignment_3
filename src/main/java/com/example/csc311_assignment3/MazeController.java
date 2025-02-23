package com.example.csc311_assignment3;

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
    private void onStartButtonClick() {
        movementEnabled = true;
        maze1Pane.requestFocus();
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