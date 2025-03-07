package com.example.csc311_assignment3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class Robot extends ImageView {

    private double robotX;
    private double robotY;
    private double scaleFactorX;
    private double scaleFactorY;

    public Robot() {
        String ROBOT_FILE = "/images/robot.png";
        InputStream ROBOT = MazeController.class.getResourceAsStream(ROBOT_FILE);
        if (ROBOT != null) {
            this.setImage(new Image(ROBOT));
            this.setPreserveRatio(false);
            //Coordinates that position robot at entrance of maze
            this.robotX = 15;
            this.robotY = 259;
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

    public void updateRobotRelativePosition() {
        //Multiplies the original coordinates by the calculated scale factor yields the new relative position of the robot
        this.setLayoutX(robotX * scaleFactorX);
        this.setLayoutY(robotY * scaleFactorY);
    }

    public void updateRobotSize(double width, double height) {
        //The smaller the number (robot image width or height / 700 the original scene width) that the width and height of maze 1 pane are multiplied,
        //the dimensions of the robot become smaller
        this.setFitWidth(width * 0.036);
        this.setFitHeight(height * 0.056);
    }


}
