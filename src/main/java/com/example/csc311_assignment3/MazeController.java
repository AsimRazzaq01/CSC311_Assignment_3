package com.example.csc311_assignment3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.InputStream;

public class MazeController {
    @FXML
    private Pane maze1Pane;
    @FXML
    private AnchorPane robotAnchorPane;
    @FXML
    private ImageView maze1ImageView;
    @FXML
    private AnchorPane carAnchorPane;
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
    private final static String ROBOT_FILE = "/images/robot.png";
    private final static String CAR_FILE = "/images/car.png";
    private final static InputStream MAZE1 = MazeController.class.getResourceAsStream(MAZE1_FILE);
    private final static InputStream MAZE2 = MazeController.class.getResourceAsStream(MAZE2_FILE);
    private final static InputStream ROBOT = MazeController.class.getResourceAsStream(ROBOT_FILE);
    private final static InputStream CAR = MazeController.class.getResourceAsStream(CAR_FILE);

    @FXML
    public void initialize() {
        if (MAZE1 != null) {
            maze1ImageView.setImage(new Image(MAZE1));
            //Makes the image responsive with window size by binding it to the parent pane's width and height
            maze1ImageView.fitWidthProperty().bind(maze1Pane.widthProperty());
            maze1ImageView.fitHeightProperty().bind(maze1Pane.heightProperty());
            //Makes the anchor pane containing the robot responsive with window size by binding it to the parent pane's width and height
            robotAnchorPane.prefWidthProperty().bind(maze1Pane.widthProperty());
            robotAnchorPane.prefHeightProperty().bind(maze1Pane.heightProperty());
        }
        if (MAZE2 != null) {
            maze2ImageView.setImage(new Image(MAZE2));
            //Makes the anchor pane containing the car responsive with window size by binding it to the parent pane's width and height
            carAnchorPane.prefWidthProperty().bind(maze2Pane.widthProperty());
            carAnchorPane.prefHeightProperty().bind(maze2Pane.heightProperty());
            //Makes the image responsive with window size by binding it to the parent pane's width and height
            maze2ImageView.fitWidthProperty().bind(maze2Pane.widthProperty());
            maze2ImageView.fitHeightProperty().bind(maze2Pane.heightProperty());
        }
    }
}
