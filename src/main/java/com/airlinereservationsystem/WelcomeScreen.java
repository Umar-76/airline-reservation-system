package com.airlinereservationsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeScreen implements Initializable {

    @FXML
    private ImageView backgroundImageView;

    public void initialize(URL url, ResourceBundle resourceBundle){
        File background = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\annapurna_massif_mountains_4k-1366x768.jpg");
        Image backImage = new Image(background.toURI().toString());
        backgroundImageView.setImage(backImage);
    }

}
