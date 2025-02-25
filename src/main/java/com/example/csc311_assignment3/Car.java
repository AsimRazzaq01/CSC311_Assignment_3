package com.example.csc311_assignment3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class Car {
    private final static String CAR_FILE = "/images/car.png";
    private final static InputStream CAR = MazeController.class.getResourceAsStream(CAR_FILE);

    private ImageView carImageView;

    private boolean facingRight = true;
    private boolean facingLeft = false;
    private double carX;
    private double carY;
    private double scaleFactorX;
    private double scaleFactorY;

    public Car(double startingX, double startingY) {
        if (CAR != null) {
            carImageView = new ImageView();
            carImageView.setImage(new Image(CAR));
            carImageView.setPreserveRatio(false);
            this.carX = startingX;
            this.carY = startingY;
        }
    }

    public ImageView getCarImageView() {
        return carImageView;
    }

    public void flip(){
        carImageView.setScaleX(carImageView.getScaleX() * -1);
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public double getCarImageX() {
        return carX;
    }

    public void setCarImageX(double carImageX) {
        this.carX = carImageX;
    }

    public double getCarImageY() {
        return carY;
    }

    public void setCarImageY(double carImageY) {
        this.carY = carImageY;
    }

    public void setScaleFactorX(double scaleFactorX) {
        this.scaleFactorX = scaleFactorX;
    }

    public void setScaleFactorY(double scaleFactorY) {
        this.scaleFactorY = scaleFactorY;
    }

    public void updateCarImageRelativePosition() {
        //Multiply the original coordinates by the calculated scale factor yields the new relative position of the robot
        carImageView.setLayoutX(carX * scaleFactorX);
        carImageView.setLayoutY(carY * scaleFactorY);
    }

    public void updateCarImageSize(double width, double height) {
        //The smaller the number (robot image width or height / 700 the original scene width) that the width and height of maze 1 pane are multiplied,
        //the dimensions of the robot become smaller
        carImageView.setFitWidth(width * 0.036);
        carImageView.setFitHeight(height * 0.056);
    }
}
