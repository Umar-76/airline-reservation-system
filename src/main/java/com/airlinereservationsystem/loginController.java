package com.airlinereservationsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private ImageView callimage;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView fbImage;

    @FXML
    private ImageView instaImage;

    @FXML
    private ImageView lockimage;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView logoimage;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ImageView twitterImage;

    @FXML
    private TextField usernameTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File logo = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\20220521_141749_0000.png");
        Image logoImage = new Image(logo.toURI().toString());
        logoimage.setImage(logoImage);

        File followInsta = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_Instagram_50px.png");
        Image insImage = new Image(followInsta.toURI().toString());
        instaImage.setImage(insImage);

        File followfb = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_facebook_50px.png");
        Image fb = new Image(followfb.toURI().toString());
        fbImage.setImage(fb);

        File followTwitter = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_twitter_50px.png");
        Image twitter = new Image(followTwitter.toURI().toString());
        twitterImage.setImage(twitter);

        File call = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_phone_50px.png");
        Image phone = new Image(call.toURI().toString());
        callimage.setImage(phone);

        File Lock = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_lock_50px.png");
        Image lockImageView = new Image(Lock.toURI().toString());
        lockimage.setImage(lockImageView);
    }

    @FXML
    void cancelBtnClicked(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(loginController.class.getResource("deltaAirlines.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 628, 480);
        stage.setTitle("Airline Reservation System");
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) cancelButton.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void loginBtnClicked(ActionEvent event) {
        if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false){
            validateLogin();
        }
        else {
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "username and password are empty!","info!!", JOptionPane.INFORMATION_MESSAGE);
        }

    }


    public void validateLogin() {
        if(usernameTextField.getText().equals("admin") && passwordTextField.getText().equals("mua20996")){
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(loginController.class.getResource("AdminHomeDashboard.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1028, 594);
                stage.setTitle("Airline Reservation System - Dashboard");
                stage.setScene(scene);
                stage.show();

                Stage st1 = (Stage) loginButton.getScene().getWindow();
                st1.close();
            }
            catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
        else {
            databaseConnection connection = new databaseConnection();
            Connection conn = connection.getConnection();

            String verify = "SELECT count(1) FROM user_account WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordTextField.getText() + "';";

            try {
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(verify);

                while (rs.next()) {
                    if (rs.getInt(1) == 1) {
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(loginController.class.getResource("UserHomeDashboard.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 1028, 594);
                        stage.setTitle("Airline Reservation System - Dashboard");
                        stage.setScene(scene);
                        stage.show();

                        Stage st2 = (Stage) loginButton.getScene().getWindow();
                        st2.close();
                    } else {
                        JPanel panel1 = new JPanel();
                        JOptionPane.showMessageDialog(panel1, "Invalid Username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }

}
