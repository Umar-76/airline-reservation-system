package com.airlinereservationsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<userTableSearch, String> useridCol;

    @FXML
    private TableColumn<userTableSearch, String> firstnameCol;

    @FXML
    private TableColumn<userTableSearch, String> lastnameCol;

    @FXML
    private TableColumn<userTableSearch, String> emailidCol;

    @FXML
    private TableColumn<userTableSearch, String> phoneCol;

    @FXML
    private TableColumn<userTableSearch, String> addressCol;

    @FXML
    private TableColumn<userTableSearch, String> usernameCol;

    @FXML
    private TableColumn<userTableSearch, String> passwordCol;

    @FXML
    private TableView<userTableSearch> userTableView;

    @FXML
    private TextField addressTextField;

    @FXML
    private Button closeButton;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField emailIdTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Label idLabel;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ImageView searchImage;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button updateButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private AnchorPane userStage;

    int index;
    String id;

    ObservableList<userTableSearch> userTableSearchObservableList = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        File search = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_search_26px.png");
        Image searchicon = new Image(search.toURI().toString());
        searchImage.setImage(searchicon);

        userId();
        userViewTable();
    }

    @FXML
    void addBtnClicked(ActionEvent event) {
        register();

    }

    @FXML
    void closeBtnClicked(ActionEvent event) throws Exception {
        Node [] node = new Node[2];
        node[0] = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        userStage.getChildren().add(node[0]);

    }

    @FXML
    void deleteBtnClicked(ActionEvent event) {
        if (firstNameTextField.getText().isEmpty() && lastNameTextField.getText().isEmpty() && emailIdTextField.getText().isEmpty() && phoneTextField.getText().isEmpty() && addressTextField.getText().isEmpty() && userNameTextField.getText().isEmpty() && passwordTextField.getText().isEmpty() && confirmPasswordTextField.getText().isEmpty()) {
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "All Fields can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (firstNameTextField.getText().isEmpty()) {
            JPanel panel1 = new JPanel();
            JOptionPane.showMessageDialog(panel1, "First Name can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lastNameTextField.getText().isEmpty()) {
            JPanel panel2 = new JPanel();
            JOptionPane.showMessageDialog(panel2, "Last Name can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (emailIdTextField.getText().isEmpty()) {
            JPanel panel3 = new JPanel();
            JOptionPane.showMessageDialog(panel3, "E-mail can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (phoneTextField.getText().isEmpty()) {
            JPanel panel4 = new JPanel();
            JOptionPane.showMessageDialog(panel4, "Phone can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (addressTextField.getText().isEmpty()) {
            JPanel panel5 = new JPanel();
            JOptionPane.showMessageDialog(panel5, "Address can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (userNameTextField.getText().isEmpty()) {
            JPanel panel6 = new JPanel();
            JOptionPane.showMessageDialog(panel6, "User Name can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (passwordTextField.getText().isEmpty()) {
            JPanel panel7 = new JPanel();
            JOptionPane.showMessageDialog(panel7, "Password can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            databaseConnection connection = new databaseConnection();
            Connection connectDB = connection.getConnection();

            index = userTableView.getSelectionModel().getSelectedIndex();
            id = userTableView.getItems().get(index).getUser_id();

            String Userid = idLabel.getText();

            String updateQuery = "DELETE FROM user_account WHERE user_id = '" + Userid + "';";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(updateQuery);
                JPanel panel1 = new JPanel();
                JOptionPane.showMessageDialog(panel1, "User Deleted Successfully!", "Message!", JOptionPane.INFORMATION_MESSAGE);
                userTableView.refresh();
            } catch (SQLException e) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    void updateBtnClicked(ActionEvent event) {
        if (firstNameTextField.getText().isEmpty() && lastNameTextField.getText().isEmpty() && emailIdTextField.getText().isEmpty() && phoneTextField.getText().isEmpty() && addressTextField.getText().isEmpty() && userNameTextField.getText().isEmpty() && passwordTextField.getText().isEmpty() && confirmPasswordTextField.getText().isEmpty()) {
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "All Fields can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (firstNameTextField.getText().isEmpty()) {
            JPanel panel1 = new JPanel();
            JOptionPane.showMessageDialog(panel1, "First Name can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lastNameTextField.getText().isEmpty()) {
            JPanel panel2 = new JPanel();
            JOptionPane.showMessageDialog(panel2, "Last Name can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (emailIdTextField.getText().isEmpty()) {
            JPanel panel3 = new JPanel();
            JOptionPane.showMessageDialog(panel3, "E-mail can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (phoneTextField.getText().isEmpty()) {
            JPanel panel4 = new JPanel();
            JOptionPane.showMessageDialog(panel4, "Phone can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (addressTextField.getText().isEmpty()) {
            JPanel panel5 = new JPanel();
            JOptionPane.showMessageDialog(panel5, "Address can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (userNameTextField.getText().isEmpty()) {
            JPanel panel6 = new JPanel();
            JOptionPane.showMessageDialog(panel6, "User Name can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (passwordTextField.getText().isEmpty()) {
            JPanel panel7 = new JPanel();
            JOptionPane.showMessageDialog(panel7, "Password can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (passwordTextField.getText().equals(confirmPasswordTextField.getText())) {

                databaseConnection connection = new databaseConnection();
                Connection connectDB = connection.getConnection();

                index = userTableView.getSelectionModel().getSelectedIndex();
                id = userTableView.getItems().get(index).getUser_id();

                String Userid = idLabel.getText();
                String Firstname = firstNameTextField.getText();
                String Lastname = lastNameTextField.getText();
                String EmailId = emailIdTextField.getText();
                String Phone = phoneTextField.getText();
                String Address = addressTextField.getText();
                String Username = userNameTextField.getText();
                String Password = passwordTextField.getText();

                String updateField = "UPDATE user_account SET firstname = '";
                String updateField1 = Firstname + "', lastname = '";
                String updateField2 = Lastname + "', emailid = '";
                String updateField3 = EmailId + "', Phone = '";
                String updateField4 = Phone + "', Address = '";
                String updateField5 = Address + "', username = '";
                String updateField6 = Username + "', password = '";
                String updateField7 = Password + "' WHERE user_id = '" + Userid + "';";

                String updateQuery = updateField + updateField1 + updateField2 + updateField3 + updateField4 + updateField5 + updateField6 + updateField7;

                try {
                    Statement statement = connectDB.createStatement();
                    statement.executeUpdate(updateQuery);
                    JPanel panel1 = new JPanel();
                    JOptionPane.showMessageDialog(panel1, "User Updated Successfully!", "Message!", JOptionPane.INFORMATION_MESSAGE);
                    userTableView.refresh();
                } catch (SQLException e) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                JPanel panel8 = new JPanel();
                JOptionPane.showMessageDialog(panel8, "Password does not match!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void userViewTable() {
        databaseConnection connect = new databaseConnection();
        Connection conn = connect.getConnection();

        String flightViewQuery = "SELECT user_id, firstname, lastname, emailid, Phone, Address, username, password FROM user_account;";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(flightViewQuery);

            while (resultSet.next()) {
                String queryUserId = resultSet.getString("user_id");
                String queryFirstName = resultSet.getString("firstname");
                String queryLastName = resultSet.getString("lastname");
                String queryEmailId = resultSet.getString("emailid");
                String queryPhone = resultSet.getString("Phone");
                String queryAddress = resultSet.getString("Address");
                String queryUserName = resultSet.getString("username");
                String queryPassword = resultSet.getString("password");

                userTableSearchObservableList.add(new userTableSearch(queryUserId, queryFirstName, queryLastName, queryEmailId, queryPhone, queryAddress, queryUserName, queryPassword));
            }

            useridCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            lastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            emailidCol.setCellValueFactory(new PropertyValueFactory<>("emailid"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
            usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
            passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

            userTableView.setItems(userTableSearchObservableList);

            FilteredList<userTableSearch> filteredData = new FilteredList<>(userTableSearchObservableList, b -> true);

            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(userTableSearch -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (userTableSearch.getUser_id().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (userTableSearch.getFirstname().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (userTableSearch.getLastname().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (userTableSearch.getEmailid().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (userTableSearch.getPhone().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (userTableSearch.getAddress().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (userTableSearch.getUsername().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (userTableSearch.getPassword().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<userTableSearch> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(userTableView.comparatorProperty());

            userTableView.setItems(sortedData);
        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }

        userTableView.setRowFactory(tv -> {
            TableRow<userTableSearch> myTableRow = new TableRow<>();
            myTableRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myTableRow.isEmpty())) {
                    index = userTableView.getSelectionModel().getSelectedIndex();

                    id = userTableView.getItems().get(index).getUser_id();
                    idLabel.setText(userTableView.getItems().get(index).getUser_id());
                    firstNameTextField.setText(userTableView.getItems().get(index).getFirstname());
                    lastNameTextField.setText(userTableView.getItems().get(index).getLastname());
                    emailIdTextField.setText(userTableView.getItems().get(index).getEmailid());
                    phoneTextField.setText(userTableView.getItems().get(index).getPhone());
                    addressTextField.setText(userTableView.getItems().get(index).getAddress());
                    userNameTextField.setText(userTableView.getItems().get(index).getUsername());
                    passwordTextField.setText(userTableView.getItems().get(index).getPassword());
                }
            });
            return myTableRow;
        });
    }

    public void register() {
        if (firstNameTextField.getText().isEmpty() && lastNameTextField.getText().isEmpty() && emailIdTextField.getText().isEmpty() && phoneTextField.getText().isEmpty() && addressTextField.getText().isEmpty() && userNameTextField.getText().isEmpty() && passwordTextField.getText().isEmpty() && confirmPasswordTextField.getText().isEmpty()) {
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "All Fields can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (firstNameTextField.getText().isEmpty()) {
            JPanel panel1 = new JPanel();
            JOptionPane.showMessageDialog(panel1, "First Name can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lastNameTextField.getText().isEmpty()) {
            JPanel panel2 = new JPanel();
            JOptionPane.showMessageDialog(panel2, "Last Name can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (emailIdTextField.getText().isEmpty()) {
            JPanel panel3 = new JPanel();
            JOptionPane.showMessageDialog(panel3, "E-mail can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (phoneTextField.getText().isEmpty()) {
            JPanel panel4 = new JPanel();
            JOptionPane.showMessageDialog(panel4, "Phone can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (addressTextField.getText().isEmpty()) {
            JPanel panel5 = new JPanel();
            JOptionPane.showMessageDialog(panel5, "Address can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (userNameTextField.getText().isEmpty()) {
            JPanel panel6 = new JPanel();
            JOptionPane.showMessageDialog(panel6, "User Name can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (passwordTextField.getText().isEmpty()) {
            JPanel panel7 = new JPanel();
            JOptionPane.showMessageDialog(panel7, "Password can not be Empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
                registerUser();
            } else {
                JPanel panel8 = new JPanel();
                JOptionPane.showMessageDialog(panel8, "Password does not match!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void registerUser() {
        databaseConnection connectnow = new databaseConnection();
        Connection connectDB = connectnow.getConnection();

        String UserId = idLabel.getText();
        String Firstname = firstNameTextField.getText();
        String Lastname = lastNameTextField.getText();
        String Email = emailIdTextField.getText();
        String Phone = phoneTextField.getText();
        String Address = addressTextField.getText();
        String Username = userNameTextField.getText();
        String Password = passwordTextField.getText();

        String insertFields = "INSERT INTO user_account(user_id, firstname, lastname, emailid, Phone, Address, username, password) VALUES ('";
        String insertValues = UserId + "','" + Firstname + "','" + Lastname + "','" + Email + "','" + Phone + "','" + Address + "','" + Username + "','" + Password + "');";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            JPanel panel1 = new JPanel();
            JOptionPane.showMessageDialog(panel1, "User Registered Successfully!", "Message!", JOptionPane.INFORMATION_MESSAGE);

            firstNameTextField.setText("");
            lastNameTextField.setText("");
            emailIdTextField.setText("");
            phoneTextField.setText("");
            addressTextField.setText("");
            userNameTextField.setText("");
            passwordTextField.setText("");
            confirmPasswordTextField.setText("");
        } catch (Exception e) {
            e.getCause();
            e.getStackTrace();
        }
    }


    public void userId() {
        databaseConnection connect = new databaseConnection();
        Connection conn = connect.getConnection();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(user_id) FROM user_account;");
            rs.next();

            rs.getString("MAX(user_id)");

            if (rs.getString("MAX(user_id)") == null) {
                idLabel.setText("UI-001");
            } else {
                long id = Long.parseLong(rs.getString("MAX(user_id)").substring(3, rs.getString("MAX(user_id)").length()));
                id++;
                idLabel.setText("UI-" + String.format("%03d", id));
            }
        } catch (Exception e) {
            e.getCause();
            e.getStackTrace();
        }
    }
}
