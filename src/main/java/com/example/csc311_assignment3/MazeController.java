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
    private Button startAnimationButton2;
    @FXML
    private Button manualModeButton;
    @FXML
    private Button manualModeButton2;
    @FXML
    private Button swapSprite;

    private final static String MAZE1_FILE = "/images/maze.png";
    private final static String MAZE2_FILE = "/images/maze2.png";
    private final static InputStream MAZE1 = MazeController.class.getResourceAsStream(MAZE1_FILE);
    private final static InputStream MAZE2 = MazeController.class.getResourceAsStream(MAZE2_FILE);
    private boolean movement1Enabled = false;
    private boolean movement2Enabled = false;
    private boolean robotEnabled;

    Robot robot = new Robot();
    Car carImage1 = new Car(15,259,1);
    Car carImage2 = new Car(10,20,2);
    PixelReader robotPixelImage;
    PixelReader carPixelImage;

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
            maze1Pane.getChildren().add(carImage1.getCarImageView());
            carImage1.getCarImageView().setVisible(false);
            robotEnabled = true;
            maze1Pane.widthProperty().addListener(mz1Width);
            maze1Pane.heightProperty().addListener(mz1Height);

            startAnimationButton1.setFocusTraversable(false);
            maze1Pane.setFocusTraversable(true);

            maze1Pane.setOnKeyPressed(event -> {
                if (movement1Enabled && robotEnabled) {  // Only move if enabled
                    moveRobot(event.getCode());
                    event.consume(); // Prevent tab switching
                }
                if (movement1Enabled && !robotEnabled) {
                    moveCarImage(event.getCode());
                    event.consume();
                }
            });
        }

        if (MAZE2 != null) {
            maze2ImageView.setImage(new Image(MAZE2));
            //Makes the image responsive with window size by binding it to the parent pane's width and height
            maze2Pane.getChildren().add(carImage2.getCarImageView());
            maze2ImageView.fitWidthProperty().bind(maze2Pane.widthProperty());
            maze2ImageView.fitHeightProperty().bind(maze2Pane.heightProperty());
            carPixelImage = maze2ImageView.getImage().getPixelReader();
            maze2Pane.widthProperty().addListener(mz2Width);
            maze2Pane.heightProperty().addListener(mz2Height);

            maze2Pane.setOnKeyPressed(event -> {
                if (movement2Enabled) {
                    moveCarImage(event.getCode());
                    event.consume();
                }
            });
        }
    }

    @FXML
    void manualMode() {
        movement1Enabled = true;
        movement2Enabled = false;
        maze1Pane.requestFocus();
    }
    @FXML
    public void manualMode2() {
        movement2Enabled = true;
        movement1Enabled = false;
        maze2Pane.requestFocus();
    }

    //swaps player between car and robot
    @FXML
    void swapPlayer(){
        if (robotEnabled) {
            robot.setVisible(false);
            carImage1.getCarImageView().setVisible(true);
            robotEnabled = false;
        }
        else {
            robot.setVisible(true);
            carImage1.getCarImageView().setVisible(false);
            robotEnabled = true;
        }

    }

    @FXML
    private void automaticAnimation() {
        Timeline robotAnimation = new Timeline();
        Stage stage = (Stage) maze1Pane.getScene().getWindow();

        //First move
        robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(200), _ -> {
            stage.setResizable(false);
            robot.setVisible(true);
            carImage1.getCarImageView().setVisible(false);
            robotEnabled = true;
            startAnimationButton1.setDisable(true);
            manualModeButton.setDisable(true);
            swapSprite.setDisable(true);
            movement1Enabled = false;
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
            swapSprite.setDisable(false);
        }));
        robotAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(14700), _ -> {
            robot.setRobotX(15);
            robot.setRobotY(259);
            robot.updateRobotRelativePosition();
        }));

        //Play Timeline
        robotAnimation.play();
    }

    @FXML
    public void automaticCarAnimation() {
        Timeline carAnimation = new Timeline();
        Stage stage = (Stage) maze1Pane.getScene().getWindow();

        //First move
        carAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(200), _ -> {
            stage.setResizable(false);
            startAnimationButton2.setDisable(true);
            manualModeButton2.setDisable(true);
            movement2Enabled = false;
            carImage2.setCarImageX(10);
            carImage2.setCarImageY(20);
            carImage2.updateCarImageRelativePosition();
            if (carImage2.isFacingLeft()) {
                carImage2.flip();
                carImage2.setFacingRight(true);
                carImage2.setFacingLeft(false);
            }
        }));
        for (double i = 25, time = 300; i <= 315 && time <= 3200; i += 10, time += 100) {
            double carNewY = i;
            carAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                carImage2.setCarImageY(carNewY);
                carImage2.updateCarImageRelativePosition();
            }));
        }

        //Second move
        for (double i = 15, time = 3300; i <= 165 && time <= 4800; i += 10, time += 100) {
            double carNewX = i;
            carAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                carImage2.setCarImageX(carNewX);
                carImage2.updateCarImageRelativePosition();
            }));
        }

        //Third move
        for (double i = 305, time = 4900; i >= 155 && time <= 6400; i -= 10, time += 100) {
            double carNewY = i;
            carAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                carImage2.setCarImageY(carNewY);
                carImage2.updateCarImageRelativePosition();
            }));
        }

        //Fourth move
        for (double i = 175, time = 6500; i <= 295 && time <= 7700; i += 10, time += 100) {
            double carNewX = i;
            carAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                carImage2.setCarImageX(carNewX);
                carImage2.updateCarImageRelativePosition();
            }));
        }

        //Fifth move
        for (double i = 145, time = 7800; i >= 15 && time <= 9100; i -= 10, time += 100) {
            double carNewY = i;
            carAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                carImage2.setCarImageY(carNewY);
                carImage2.updateCarImageRelativePosition();
            }));
        }

        //Sixth move
        for (double i = 305, time = 9200; i <= 415 && time <= 10300; i += 10, time += 100) {
            double carNewX = i;
            carAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                carImage2.setCarImageX(carNewX);
                carImage2.updateCarImageRelativePosition();
            }));
        }

        //Seventh move
        for (double i = 25, time = 10300; i <= 315 && time <= 13200; i += 10, time += 100) {
            double carNewY = i;
            carAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(time), _ -> {
                carImage2.setCarImageY(carNewY);
                carImage2.updateCarImageRelativePosition();
            }));
        }

        carAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(13300), _ -> {
            stage.setResizable(true);
            startAnimationButton2.setDisable(false);
            manualModeButton2.setDisable(false);
        }));
        carAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(13500), _ -> {
            carImage2.setCarImageX(10);
            carImage2.setCarImageY(20);
            carImage2.updateCarImageRelativePosition();
        }));

        //Play Timeline
        carAnimation.play();
    }

    ChangeListener<Number> mz1Width = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
            //Listens to the width of maze 1 image and scales robot position according to its size
            //Passes the value of the calculated scale factor (new width/old width)
            robot.setScaleFactorX(newValue.doubleValue() / maze1ImageView.getImage().getWidth());
            carImage1.setScaleFactorX(newValue.doubleValue() / maze1ImageView.getImage().getWidth());
            //Listens to the width of maze 1 pane and scales robot size accordingly to be a consistent size
            robot.updateRobotSize(maze1Pane.getWidth(), maze1Pane.getHeight());
            carImage1.updateCarImageSize(maze1Pane.getWidth(), maze1Pane.getHeight());
            carImage1.updateCarImageRelativePosition();
            robot.updateRobotRelativePosition();
        }
    };

    ChangeListener<Number> mz1Height = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
            //Listens to the height of maze 1 image and scales robot position according to its size
            //Passes the value of the calculated scale factor (new width/old width)
            robot.setScaleFactorY(newValue.doubleValue() / maze1ImageView.getImage().getHeight());
            carImage1.setScaleFactorY(newValue.doubleValue() / maze1ImageView.getImage().getHeight());
            //Listens to the height of maze 1 pane and scales robot size accordingly to be a consistent size
            robot.updateRobotSize(maze1Pane.getWidth(), maze1Pane.getHeight());
            carImage1.updateCarImageSize(maze1Pane.getWidth(), maze1Pane.getHeight());
            carImage1.updateCarImageRelativePosition();
            robot.updateRobotRelativePosition();
        }
    };

    ChangeListener<Number> mz2Width = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
            //Listens to the width of maze 2 image and scales robot position according to its size
            //Passes the value of the calculated scale factor (new width/old width)
            carImage2.setScaleFactorX(newValue.doubleValue() / maze2ImageView.getImage().getWidth());
            //Listens to the width of maze 2 pane and scales robot size accordingly to be a consistent size
            carImage2.updateCarImageSize(maze2Pane.getWidth(), maze2Pane.getHeight());
            carImage2.updateCarImageRelativePosition();
        }
    };

    ChangeListener<Number> mz2Height = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
            //Listens to the height of maze 2 image and scales robot position according to its size
            //Passes the value of the calculated scale factor (new width/old width)
            carImage2.setScaleFactorY(newValue.doubleValue() / maze2ImageView.getImage().getHeight());
            //Listens to the height of maze 2 pane and scales robot size accordingly to be a consistent size
            carImage2.updateCarImageSize(maze2Pane.getWidth(), maze2Pane.getHeight());
            carImage2.updateCarImageRelativePosition();
        }
    };

    private void moveRobot(KeyCode key) {
        double robotStep = 3;

        switch (key) {
            case UP:
                moveRobotSprite(0, -robotStep);
                break;
            case DOWN:
                moveRobotSprite(0, robotStep);
                break;
            case LEFT:
                moveRobotSprite(-robotStep,0);
                break;
            case RIGHT:
                moveRobotSprite(robotStep,0);
        }
    }

    private void moveCarImage(KeyCode key) {
        double carImageStep = 3;

        switch (key) {
            case UP:
                moveCarImageSprite(0, -carImageStep);
                break;
            case DOWN:
                moveCarImageSprite(0, carImageStep);
                break;
            case LEFT:
                if (carImage1.isFacingRight() && movement1Enabled) {
                    carImage1.flip();
                    carImage1.setFacingRight(false);
                    carImage1.setFacingLeft(true);
                }
                if (carImage2.isFacingRight() && movement2Enabled) {
                    carImage2.flip();
                    carImage2.setFacingRight(false);
                    carImage2.setFacingLeft(true);
                }
                moveCarImageSprite(-carImageStep,0);
                break;
            case RIGHT:
                if (carImage1.isFacingLeft() && movement1Enabled) {
                    carImage1.flip();
                    carImage1.setFacingRight(true);
                    carImage1.setFacingLeft(false);
                }
                if (carImage2.isFacingLeft() && movement2Enabled) {
                    carImage2.flip();
                    carImage2.setFacingRight(true);
                    carImage2.setFacingLeft(false);
                }
                moveCarImageSprite(carImageStep,0);
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

    private void moveCarImageSprite(double newX, double newY) {
        if (checkCarImageEdges(newX, newY) && movement1Enabled) {
            carImage1.setCarImageX(carImage1.getCarImageX() + newX);
            carImage1.setCarImageY(carImage1.getCarImageY() + newY);
            carImage1.updateCarImageRelativePosition();
        }
        if (checkCarImageEdges(newX, newY) && movement2Enabled) {
            carImage2.setCarImageX(carImage2.getCarImageX() + newX);
            carImage2.setCarImageY(carImage2.getCarImageY() + newY);
            carImage2.updateCarImageRelativePosition();
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

    private boolean checkCarImageEdges(double newX, double newY) {
        int currentMazeWallArgb = 0;
        PixelReader currentMazeReader = null;
        int carWidthInImage, carHeightInImage;
        int carImageLeftEdge = 0;
        int carImageRightEdge = 0;
        int carImageTopEdge = 0;
        int carImageBottomEdge = 0;

        int carImageHeight = 0;
        int carImageWidth = 0;

        if (movement1Enabled) {
            carWidthInImage = 25;
            carHeightInImage = 25;
            currentMazeWallArgb = -16755815;
            currentMazeReader = robotPixelImage;
            carImageLeftEdge = (int) (carImage1.getCarImageX() + newX);
            carImageRightEdge = (int) (carImage1.getCarImageX() + newX + carWidthInImage);
            carImageTopEdge = (int) (carImage1.getCarImageY() + newY);
            carImageBottomEdge = (int) (carImage1.getCarImageY() + newY + carHeightInImage);

            carImageHeight = (int) (maze1ImageView.getImage().getHeight());
            carImageWidth = (int) (maze1ImageView.getImage().getWidth());
        }
        if (movement2Enabled) {
            carWidthInImage = 60;
            carHeightInImage = 41;
            currentMazeWallArgb = -16760833;
            currentMazeReader = carPixelImage;
            carImageLeftEdge = (int) (carImage2.getCarImageX() + newX);
            carImageRightEdge = (int) (carImage2.getCarImageX() + newX + carWidthInImage);
            carImageTopEdge = (int) (carImage2.getCarImageY() + newY);
            carImageBottomEdge = (int) (carImage2.getCarImageY() + newY + carHeightInImage);

            carImageHeight = (int) (maze2ImageView.getImage().getHeight());
            carImageWidth = (int) (maze2ImageView.getImage().getWidth());
        }

        if (newX > 0) {
            //Checks right edge of car from top to down
            for (int y = carImageTopEdge; y < carImageBottomEdge; y++) {
                if (carImageRightEdge >= carImageWidth || currentMazeReader.getArgb(carImageRightEdge, y) == currentMazeWallArgb) {
                    return false;
                }
            }
        }
        else if (newX < 0) {
            //Checks left edge of car from top to down
            for (int y = carImageTopEdge; y < carImageBottomEdge; y++) {
                if (carImageLeftEdge <= 0 || currentMazeReader.getArgb(carImageLeftEdge, y) == currentMazeWallArgb) {
                    return false;
                }
            }
        }
        else if (newY > 0) {
            //Checks bottom edge of car from left to right
            for (int x = carImageLeftEdge; x < carImageRightEdge; x++) {
                if (carImageBottomEdge >= carImageHeight || currentMazeReader.getArgb(x, carImageBottomEdge) == currentMazeWallArgb) {
                    return false;
                }
            }
        }
        else if (newY < 0) {
            //Checks top edge of car from left to right
            for (int x = carImageLeftEdge; x < carImageRightEdge; x++) {
                if (carImageTopEdge <= 0 || currentMazeReader.getArgb(x, carImageTopEdge) == currentMazeWallArgb) {
                    return false;
                }
            }
        }
        return true;
    }
}