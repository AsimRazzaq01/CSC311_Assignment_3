package com.example.csc311_assignment3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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

    private final static String MAZE1_FILE = "/images/maze.png";
    private final static String MAZE2_FILE = "/images/maze2.png";
    private final static String CAR_FILE = "/images/car.png";
    private final static InputStream MAZE1 = MazeController.class.getResourceAsStream(MAZE1_FILE);
    private final static InputStream MAZE2 = MazeController.class.getResourceAsStream(MAZE2_FILE);
    private final static InputStream CAR = MazeController.class.getResourceAsStream(CAR_FILE);

    Robot robot = new Robot();

    @FXML
    public void initialize() {
        if (MAZE1 != null) {
            maze1ImageView.setImage(new Image(MAZE1));
            //Makes the image responsive with window size by binding it to the parent pane's width and height
            maze1ImageView.fitWidthProperty().bind(maze1Pane.widthProperty());
            maze1ImageView.fitHeightProperty().bind(maze1Pane.heightProperty());

            //Created a robot object and add it to the maze 1 pane
            maze1Pane.getChildren().add(robot);
            maze1Pane.widthProperty().addListener(mz1Width);
            maze1Pane.heightProperty().addListener(mz1Height);
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
}
