package com.example.csc311_assignment3;

import javafx.scene.image.ImageView;

import java.io.InputStream;

public class Car extends ImageView {
    private final static String CAR_FILE = "/images/car.png";
    private final static InputStream CAR = MazeController.class.getResourceAsStream(CAR_FILE);
}
