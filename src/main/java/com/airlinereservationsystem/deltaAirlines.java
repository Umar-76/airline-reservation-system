package com.airlinereservationsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class deltaAirlines implements Initializable {

    @FXML
    private ImageView logoImage;

    @FXML
    private Button exitButton;

    @FXML
    private Button logInButton;

    @FXML
    private Button registerButton;

    public void initialize(URL url, ResourceBundle resourceBundle){
        File logo = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\20220521_141749_0000.png");
        Image logoImageView = new Image(logo.toURI().toString());
        logoImage.setImage(logoImageView);

    }

    @FXML
    void exitBtnClicked(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void logInBtnClicked(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(deltaAirlines.class.getResource("loginpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 583, 423);
        stage.setTitle("Airline Reservation System - Login");
        stage.setScene(scene);
        stage.show();

        Stage st1 = (Stage) logInButton.getScene().getWindow();
        st1.close();

    }

    @FXML
    void registerBtnClicked(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(deltaAirlines.class.getResource("Register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 558, 715);
        stage.setTitle("Airline Reservation System - Register");
        stage.setScene(scene);
        stage.show();

        Stage st1 = (Stage) registerButton.getScene().getWindow();
        st1.close();
    }

}
