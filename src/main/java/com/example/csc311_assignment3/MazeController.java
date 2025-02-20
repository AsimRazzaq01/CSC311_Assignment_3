package com.example.csc311_assignment3;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MazeController {

    @FXML
    private AnchorPane mazeAnchor;
    @FXML
    private ImageView mazeImage;



    @FXML
    public void initialize() {

        ImageView mazeImage = new ImageView(new Image(getClass().getResourceAsStream("/images/maze.png")));
        mazeImage.setPreserveRatio(true);

    }
}
