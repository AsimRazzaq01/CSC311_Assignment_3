package com.example.csc311_assignment3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class Robot extends ImageView {
    private final static String ROBOT_FILE = "/images/robot.png";
    private final static String CAR_FILE = "/images/car.png";
    private final static InputStream ROBOT = MazeController.class.getResourceAsStream(ROBOT_FILE);
    private final static InputStream CAR = MazeController.class.getResourceAsStream(CAR_FILE);
    private final Image carImage = new Image(CAR);
    private final Image robotImage = new Image(ROBOT);

    private boolean isRobot = true;
    private boolean facingRight = true;

    private double robotX;
    private double robotY;
    private double scaleFactorX;
    private double scaleFactorY;

    public Robot() {
        if (ROBOT != null) {
            this.setImage(new Image(ROBOT));
            this.setImage(robotImage);
            this.setPreserveRatio(false);
            facingRight = true;
            isRobot = true;
            //Coordinates that position robot at entrance of maze
            this.robotX = 15;
            this.robotY = 259;
        }
    }

    //swaps player between car and robot
    public void swapPlayer(){
        if (isRobot)
            this.setImage(carImage);
        else
            this.setImage(robotImage);
        this.setPreserveRatio(true);
        isRobot = !isRobot;
    }

    public void flip(){
        this.setScaleX(this.getScaleX() * -1);
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
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
        //Multiply the original coordinates by the calculated scale factor yields the new relative position of the robot
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
