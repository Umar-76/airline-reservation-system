package com.airlinereservationsystem;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class UserHomeDashboardController implements Initializable {

    @FXML
    private ImageView TicketIcon;

    @FXML
    private ImageView airlinesImage;

    @FXML
    private ImageView airportIcon;

    @FXML
    private Button bookTicketButton;

    @FXML
    private Label dateLabel;

    @FXML
    private Button flightButton;

    @FXML
    private Button logoutButton;

    @FXML
    private ImageView logoutIcon;

    @FXML
    private AnchorPane mainStage;

    @FXML
    private Label timeLabel;

    @FXML
    private ImageView userImageview;

    @FXML
    private TextField userNameTextField;

    @FXML
    private Button viewTicketButton;

    @FXML
    private ImageView viewTicketIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            Node[] node = new Node[2];
            node[0] = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
            mainStage.getChildren().add(node[0]);
        }
        catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }
        File flight = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\kisspng-airplane-flight-aircraft-drawing-image-5d2b447b36b9c3.7960426615631166672242.png");
        Image flightimage = new Image(flight.toURI().toString());
        airlinesImage.setImage(flightimage);

        File airplane = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_airport_26px_1.png");
        Image flighticon = new Image(airplane.toURI().toString());
        airportIcon.setImage(flighticon);

        File ticket = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_new_ticket_24px_1.png");
        Image ticketicon = new Image(ticket.toURI().toString());
        TicketIcon.setImage(ticketicon);

        File viewTicket = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_details_26px_1.png");
        Image viewticketIcon = new Image(viewTicket.toURI().toString());
        viewTicketIcon.setImage(viewticketIcon);

        File logout = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_logout_rounded_left_26px.png");
        Image logouticon = new Image(logout.toURI().toString());
        logoutIcon.setImage(logouticon);

        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = dt.format(df);
        dateLabel.setText(date);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                timeLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        };
        timer.start();
    }

    @FXML
    void bookBtnClicked(ActionEvent event) throws Exception {
        Node [] node = new Node[2];
        node[0] = FXMLLoader.load(getClass().getResource("TicketBooking.fxml"));
        mainStage.getChildren().add(node[0]);
    }

    @FXML
    void flightBtnClicked(ActionEvent event) throws Exception {
        Node [] node = new Node[2];
        node[0] = FXMLLoader.load(getClass().getResource("viewFlight.fxml"));
        mainStage.getChildren().add(node[0]);
    }

    @FXML
    void logoutBtnClicked(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeDashboardController.class.getResource("loginpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 583, 423);
        stage.setTitle("Airline Reservation System - Login");
        stage.setScene(scene);
        stage.show();

        Stage st1 = (Stage) logoutButton.getScene().getWindow();
        st1.close();
    }

    @FXML
    void viewBtnClicked(ActionEvent event) throws Exception {
        Node [] node = new Node[2];
        node[0] = FXMLLoader.load(getClass().getResource("viewTicketUser.fxml"));
        mainStage.getChildren().add(node[0]);
    }

}
