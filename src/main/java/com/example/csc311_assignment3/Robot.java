package com.example.csc311_assignment3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class Robot extends ImageView {
    private final static String ROBOT_FILE = "/images/robot.png";
    private final static InputStream ROBOT = MazeController.class.getResourceAsStream(ROBOT_FILE);

    private double robotX;
    private double robotY;
    private double scaleFactorX;
    private double scaleFactorY;

    public Robot() {
        if (ROBOT != null) {
            this.setImage(new Image(ROBOT));
            this.setPreserveRatio(false);
            this.setFitWidth(25);
            this.setFitHeight(25);
            this.robotX = 15;
            this.robotY = 258;
        }
    }

    public double getRobotX() {
        return robotX;
    }

    public void setRobotX(double robotX) {
        this.robotX = robotX;
    }

    public double getRobotY() {
        return robotY;
    }

    public void setRobotY(double robotY) {
        this.robotY = robotY;
    }

    public void setScaleFactorX(double scaleFactorX) {
        this.scaleFactorX = scaleFactorX;
    }

    public void setScaleFactorY(double scaleFactorY) {
        this.scaleFactorY = scaleFactorY;
    }

    public void updateSpritePosition() {
        this.setLayoutX(robotX * scaleFactorX);
        this.setLayoutY(robotY * scaleFactorY);
    }
}
