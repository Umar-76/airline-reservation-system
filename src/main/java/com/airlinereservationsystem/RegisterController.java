package com.airlinereservationsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.*;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextArea AddressTextArea;

    @FXML
    private PasswordField ConfirmPasswordField;

    @FXML
    private TextField EmailTextField;

    @FXML
    private TextField FirstnameTextField;

    @FXML
    private TextField LastnameTextField;

    @FXML
    private TextField PhoneTextField;

    @FXML
    private TextField UsernameTextField;

    @FXML
    private Button closeButton;

    @FXML
    private Label confirmPassLabel;

    @FXML
    private Button registerButton;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private ImageView userIcon;

    @FXML
    private Label idLabel;

    public void initialize(URL url, ResourceBundle resourceBundle){
        File usericon = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8-registration-100.png");
        Image regicon = new Image(usericon.toURI().toString());
        userIcon.setImage(regicon);

        userId();
    }

    @FXML
    void regBtnClicked(ActionEvent event) {
        if(FirstnameTextField.getText().isEmpty() && LastnameTextField.getText().isEmpty() && EmailTextField.getText().isEmpty() && PhoneTextField.getText().isEmpty() && AddressTextArea.getText().isEmpty() && UsernameTextField.getText().isEmpty() && setPasswordField.getText().isEmpty() && ConfirmPasswordField.getText().isEmpty()){
            JPanel panel9= new JPanel();
            JOptionPane.showMessageDialog(panel9,"All Fields can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(FirstnameTextField.getText().isEmpty()){
            JPanel panel1= new JPanel();
            JOptionPane.showMessageDialog(panel1,"First Name can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(LastnameTextField.getText().isEmpty()){
            JPanel panel2= new JPanel();
            JOptionPane.showMessageDialog(panel2,"Last Name can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(EmailTextField.getText().isEmpty()){
            JPanel panel3= new JPanel();
            JOptionPane.showMessageDialog(panel3,"E-mail can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(PhoneTextField.getText().isEmpty()){
            JPanel panel4= new JPanel();
            JOptionPane.showMessageDialog(panel4,"Phone can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(AddressTextArea.getText().isEmpty()){
            JPanel panel5= new JPanel();
            JOptionPane.showMessageDialog(panel5,"Address can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(UsernameTextField.getText().isEmpty()){
            JPanel panel6= new JPanel();
            JOptionPane.showMessageDialog(panel6,"Username can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(setPasswordField.getText().isEmpty()){
            JPanel panel7= new JPanel();
            JOptionPane.showMessageDialog(panel7,"Password can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }

        else{
            if(setPasswordField.getText().equals(ConfirmPasswordField.getText())){
                registerUser();
                confirmPassLabel.setText("");
            }
            else{
                confirmPassLabel.setText("Password does not match!");
            }
        }
    }

    @FXML
    void closeBtnClicked(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterController.class.getResource("deltaAirlines.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 628, 480);
        stage.setTitle("Airline Reservation System");
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) closeButton.getScene().getWindow();
        stage1.close();
    }

    public void userId(){
        databaseConnection connect = new databaseConnection();
        Connection conn = connect.getConnection();

        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(user_id) FROM user_account;");
            rs.next();

            rs.getString("MAX(user_id)");

            if(rs.getString("MAX(user_id)") == null){
                idLabel.setText("UI-001");
            }
            else {
                long id = Long.parseLong(rs.getString("MAX(user_id)").substring(3,rs.getString("MAX(user_id)").length()));
                id++;
                idLabel.setText("UI-" + String.format("%03d", id));
            }
        }
        catch (Exception e){
            e.getCause();
            e.getStackTrace();
        }
    }

    public void registerUser(){
        databaseConnection connectnow = new databaseConnection();
        Connection connectDB = connectnow.getConnection();

        String UserId = idLabel.getText();
        String Firstname = FirstnameTextField.getText();
        String Lastname = LastnameTextField.getText();
        String Email = EmailTextField.getText();
        String Phone = PhoneTextField.getText();
        String Address = AddressTextArea.getText();
        String Username = UsernameTextField.getText();
        String Password = setPasswordField.getText();

        String insertFields = "INSERT INTO user_account(user_id, firstname, lastname, emailid, Phone, Address, username, password) VALUES ('";
        String insertValues = UserId + "','" + Firstname + "','" + Lastname + "','" + Email + "','" + Phone + "','" + Address + "','" + Username + "','" + Password + "');";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            JPanel panel1= new JPanel();
            JOptionPane.showMessageDialog(panel1,"User Registered Successfully!","Message!",JOptionPane.INFORMATION_MESSAGE);

            userId();
            FirstnameTextField.setText("");
            LastnameTextField.setText("");
            EmailTextField.setText("");
            PhoneTextField.setText("");
            AddressTextArea.setText("");
            UsernameTextField.setText("");
            setPasswordField.setText("");
            ConfirmPasswordField.setText("");
        }
        catch (Exception e){
            e.getCause();
            e.getStackTrace();
        }
    }
}
