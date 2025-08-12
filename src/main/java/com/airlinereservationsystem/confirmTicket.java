package com.airlinereservationsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class confirmTicket {

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button verifyButton;


    @FXML
    void verifyBtnClicked(ActionEvent event) {
        if(passwordField.getText().equals("mua20996")){
            JPanel panel1 = new JPanel();
            JOptionPane.showMessageDialog(panel1, "Verified!!", "message", JOptionPane.INFORMATION_MESSAGE);
            Stage stage1 = (Stage) verifyButton.getScene().getWindow();
            stage1.close();
        }
        else {
            validateUser();
        }
    }

    public void validateUser(){
        databaseConnection connection = new databaseConnection();
        Connection conn = connection.getConnection();

        String verify = "SELECT count(1) FROM user_account WHERE password = '" + passwordField.getText() + "';";

        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(verify);

            while(rs.next()){
                if(rs.getInt(1) == 1){
                    JPanel panel1 = new JPanel();
                    JOptionPane.showMessageDialog(panel1, "Verified!!", "message", JOptionPane.INFORMATION_MESSAGE);
                    Stage stage2 = (Stage) verifyButton.getScene().getWindow();
                    stage2.close();
                }
                else{
                    JPanel panel1 = new JPanel();
                    JOptionPane.showMessageDialog(panel1, "Invalid password!\nPlease try again!", "Error", JOptionPane.ERROR_MESSAGE);
                    Stage stage3 = (Stage) verifyButton.getScene().getWindow();
                    stage3.requestFocus();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
