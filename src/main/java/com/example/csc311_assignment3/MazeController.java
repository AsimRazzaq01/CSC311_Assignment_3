package com.example.csc311_assignment3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;

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
    private Button manualModeButton;
    @FXML
    private Button startAnimationButton2;

    private final static String MAZE1_FILE = "/images/maze.png";
    private final static String MAZE2_FILE = "/images/maze2.png";
    private final static InputStream MAZE1 = MazeController.class.getResourceAsStream(MAZE1_FILE);
    private final static InputStream MAZE2 = MazeController.class.getResourceAsStream(MAZE2_FILE);
    private boolean movementEnabled = false;

    Robot robot = new Robot();
    PixelReader robotPixelImage;

    @FXML
    public void initialize() {
        if (MAZE1 != null) {
            maze1ImageView.setImage(new Image(MAZE1));
            //Makes the image responsive with window size by binding it to the parent pane's width and height
            maze1ImageView.fitWidthProperty().bind(maze1Pane.widthProperty());
            maze1ImageView.fitHeightProperty().bind(maze1Pane.heightProperty());

            robotPixelImage = maze1ImageView.getImage().getPixelReader();
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

    @FXML
    void manualMode() {
        movementEnabled = true;
        maze1Pane.requestFocus();
    }

    @FXML
    private void automaticAnimation() {
        Timeline robotAnimation = new Timeline();
        Stage stage = (Stage) maze1Pane.getScene().getWindow();

        //First move
        robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(200), _ -> {
            stage.setResizable(false);
            startAnimationButton1.setDisable(true);
            manualModeButton.setDisable(true);
            robot.setRobotX(15);
            robot.setRobotY(259);
            robot.updateRobotRelativePosition();
        }));
        for (double i = 25, time = 300; i <= 45 && time <= 500; i += 10, time += 100) {
            double robotNewX = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotX(robotNewX);
                robot.updateRobotRelativePosition();
            }));
        }

        //Second move
        for (double i = 249, time = 600; i >= 159 && time <= 1500; i -= 10, time += 100) {
            double robotNewY = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotY(robotNewY);
                robot.updateRobotRelativePosition();
            }));
        }
        robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(1600), _ -> {
            robot.setRobotY(148);
            robot.updateRobotRelativePosition();
        }));

        //Third move
        for (double i = 55, time = 1700; i <= 265 && time <= 3800; i += 10, time += 100) {
            double robotNewX = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotX(robotNewX);
                robot.updateRobotRelativePosition();
            }));
        }

        //Fourth move
        for (double i = 148, time = 3900; i >= 89 && time <= 4500; i -= 10, time += 100) {
            double robotNewY = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotY(robotNewY);
                robot.updateRobotRelativePosition();
            }));
        }
        robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(4600), _ -> {
            robot.setRobotY(91);
            robot.updateRobotRelativePosition();
        }));

        //Fifth move
        for (double i = 275, time = 4700; i <= 325 && time <= 5200; i += 10, time += 100) {
            double robotNewX = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotX(robotNewX);
                robot.updateRobotRelativePosition();
            }));
        }

        //Sixth move
        for (double i = 91, time = 5300; i <= 311 && time <= 7500; i += 10, time += 100) {
            double robotNewY = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotY(robotNewY);
                robot.updateRobotRelativePosition();
            }));
        }
        robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(7600), _ -> {
            robot.setRobotY(317);
            robot.updateRobotRelativePosition();
        }));

        //Seventh move
        for (double i = 335, time = 7700; i <= 375 && time <= 8100; i += 10, time += 100) {
            double robotNewX = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotX(robotNewX);
                robot.updateRobotRelativePosition();
            }));
        }
        robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(8200), _ -> {
            robot.setRobotX(380);
            robot.updateRobotRelativePosition();
        }));

        //Eighth move
        for (double i = 315, time = 8300; i >= 205 && time <= 9400; i -= 10, time += 100) {
            double robotNewY = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotY(robotNewY);
                robot.updateRobotRelativePosition();
            }));
        }

        //Ninth move
        for (double i = 385, time = 9500; i <= 495 && time <= 10600; i += 10, time += 100) {
            double robotNewX = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotX(robotNewX);
                robot.updateRobotRelativePosition();
            }));
        }

        //Tenth move
        for (double i = 195, time = 10700; i >= 95 && time <= 11700; i -= 10, time += 100) {
            double robotNewY = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotY(robotNewY);
                robot.updateRobotRelativePosition();
            }));
        }

        //Eleventh move
        for (double i = 505, time = 11800; i <= 555 && time <= 12300; i += 10, time += 100) {
            double robotNewX = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotX(robotNewX);
                robot.updateRobotRelativePosition();
            }));
        }

        //Twelfth move
        for (double i = 105, time = 12400; i <= 245 && time <= 13800; i += 10, time += 100) {
            double robotNewY = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotY(robotNewY);
                robot.updateRobotRelativePosition();
            }));
        }

        //Thirteenth move
        for (double i = 565, time = 13900; i <= 615 && time <= 14400; i += 10, time += 100) {
            double robotNewX = i;
            robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                robot.setRobotX(robotNewX);
                robot.updateRobotRelativePosition();
            }));
        }
        robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(14500), _ -> {
            stage.setResizable(true);
            startAnimationButton1.setDisable(false);
            manualModeButton.setDisable(false);
        }));
        robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(14700), _ -> {
            robot.setRobotX(15);
            robot.setRobotY(259);
            robot.updateRobotRelativePosition();
        }));

        //Play Timeline
        robotAnimation.play();
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

    private void moveRobot(KeyCode key) {
        double step = 3;

        switch (key) {
            case UP:
                moveRobotSprite(0, -step);
                break;
            case DOWN:
                moveRobotSprite(0, step);
                break;
            case LEFT:
                moveRobotSprite(-step,0);
                break;
            case RIGHT:
                moveRobotSprite(step,0);
        }
    }

    private void moveRobotSprite(double newX, double newY) {
        //If the new edge position of the robot is not on the wall, then the robot will move to its new position
        if (checkRobotEdges(newX, newY)) {
            robot.setRobotX(robot.getRobotX() + newX);
            robot.setRobotY(robot.getRobotY() + newY);
            //Updates the robot's layoutX() and layoutY() to be in a relative position
            robot.updateRobotRelativePosition();
        }
    }

    private boolean checkRobotEdges(double newX, double newY) {
        int maze1WallArgb = -16755815;
        int robotLeftEdge = (int) (robot.getRobotX() + newX);
        int robotRightEdge = (int) (robot.getRobotX() + newX + robot.getImage().getWidth());
        int robotTopEdge = (int) (robot.getRobotY() + newY);
        int robotBottomEdge = (int) (robot.getRobotY() + newY + robot.getImage().getHeight());

        int robotImageWidth = (int) (maze1ImageView.getImage().getWidth());
        int robotImageHeight = (int) (maze1ImageView.getImage().getHeight());

        if (newX > 0) {
            //Checks right edge of robot from top to down
            for (int y = robotTopEdge; y < robotBottomEdge; y++) {
                if (robotRightEdge >= robotImageWidth || robotPixelImage.getArgb(robotRightEdge, y) == maze1WallArgb) {
                    return false;
                }
            }
        }
        else if (newX < 0) {
            //Checks left edge of robot from top to down
            for (int y = robotTopEdge; y < robotBottomEdge; y++) {
                if (robotLeftEdge <= 0 || robotPixelImage.getArgb(robotLeftEdge, y) == maze1WallArgb) {
                    return false;
                }
            }
        }
        else if (newY > 0) {
            //Checks bottom edge of robot from left to right
            for (int x = robotLeftEdge; x < robotRightEdge; x++) {
                if (robotBottomEdge >= robotImageHeight || robotPixelImage.getArgb(x, robotBottomEdge) == maze1WallArgb) {
                    return false;
                }
            }
        }
        else if (newY < 0) {
            //Checks top edge of robot from left to right
            for (int x = robotLeftEdge; x < robotRightEdge; x++) {
                if (robotTopEdge <= 0 || robotPixelImage.getArgb(x, robotTopEdge) == maze1WallArgb) {
                    return false;
                }
            }
        }
        return true;
    }
}