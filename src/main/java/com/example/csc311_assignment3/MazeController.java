package com.example.csc311_assignment3;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
public class MazeController{

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
    void Automatic_Animation(ActionEvent event) {
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

    private final static String MAZE1_FILE = "/images/maze.png";
    private final static String MAZE2_FILE = "/images/maze2.png";
    private final static String CAR_FILE = "/images/car.png";
    private final static InputStream MAZE1 = MazeController.class.getResourceAsStream(MAZE1_FILE);
    private final static InputStream MAZE2 = MazeController.class.getResourceAsStream(MAZE2_FILE);
    private final static InputStream CAR = MazeController.class.getResourceAsStream(CAR_FILE);

    // new object robot
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