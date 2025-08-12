package com.airlinereservationsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Flight implements Initializable {
    @FXML
    private TableView<flightTableSearch> flightTableView;

    @FXML
    private TableColumn<flightTableSearch, String> flightidCol;

    @FXML
    private TableColumn<flightTableSearch, String> flightnameCol;

    @FXML
    private TableColumn<flightTableSearch, String> flighttypeCol;

    @FXML
    private TableColumn<flightTableSearch, String> placeDeptCol;

    @FXML
    private TableColumn<flightTableSearch, String> placeArriveCol;

    @FXML
    private TableColumn<flightTableSearch, String> arrivalTimeCol;

    @FXML
    private TableColumn<flightTableSearch, String> deptTimeCol;

    @FXML
    private TableColumn<flightTableSearch, String> statusCol;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField arrivalTimeTextField;

    @FXML
    private Button closeButton;

    @FXML
    private TextField departTimeTextField;

    @FXML
    private TextField flightNameTextField;

    @FXML
    private TextField flightTypeTextField;

    @FXML
    private Label idLabel;

    @FXML
    private TextField poaTextField;

    @FXML
    private TextField podTextField;

    @FXML
    private ImageView searchImage;

    @FXML
    private TextField searchTextField;

    @FXML
    private TextField statusTextField;

    @FXML
    private Button updateButton;

    @FXML
    private AnchorPane flightStage;

    int myIndex;
    String id;

    ObservableList<flightTableSearch> flightTableSearchObservableList = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle resourceBundle){
        File search = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_search_26px.png");
        Image searchicon = new Image(search.toURI().toString());
        searchImage.setImage(searchicon);

        flightId();
        viewTable();
    }

    @FXML
    void addBtnClicked(ActionEvent event) {
        Register();
        flightTableView.refresh();

    }

    @FXML
    void deleteBtnClicked(ActionEvent event){
        if(flightNameTextField.getText().isEmpty() && flightTypeTextField.getText().isEmpty() && podTextField.getText().isEmpty() && poaTextField.getText().isEmpty() && arrivalTimeTextField.getText().isEmpty() && departTimeTextField.getText().isEmpty() && statusTextField.getText().isEmpty()){
            JPanel panel= new JPanel();
            JOptionPane.showMessageDialog(panel,"All Fields can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(flightNameTextField.getText().isEmpty()){
            JPanel panel1= new JPanel();
            JOptionPane.showMessageDialog(panel1,"Flight Name can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(flightTypeTextField.getText().isEmpty()){
            JPanel panel2= new JPanel();
            JOptionPane.showMessageDialog(panel2,"Flight Type can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(podTextField.getText().isEmpty()){
            JPanel panel3= new JPanel();
            JOptionPane.showMessageDialog(panel3,"Place of Departure can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(poaTextField.getText().isEmpty()){
            JPanel panel4= new JPanel();
            JOptionPane.showMessageDialog(panel4,"Place of Arrival can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(arrivalTimeTextField.getText().isEmpty()){
            JPanel panel5= new JPanel();
            JOptionPane.showMessageDialog(panel5,"Arrival Time can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(departTimeTextField.getText().isEmpty()){
            JPanel panel6= new JPanel();
            JOptionPane.showMessageDialog(panel6,"Departure Time can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(statusTextField.getText().isEmpty()){
            JPanel panel7= new JPanel();
            JOptionPane.showMessageDialog(panel7,"Status can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }

        else{
            databaseConnection connection = new databaseConnection();
            Connection connectDB = connection.getConnection();

            myIndex = flightTableView.getSelectionModel().getSelectedIndex();
            id = flightTableView.getItems().get(myIndex).getFlight_id();

            String Flightid = idLabel.getText();

            String updateQuery = "DELETE FROM flight_tbl WHERE flight_id = '" + Flightid + "';";

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(updateQuery);
                JPanel panel1 = new JPanel();
                JOptionPane.showMessageDialog(panel1, "Flight Deleted Successfully!", "Message!", JOptionPane.INFORMATION_MESSAGE);
                flightTableView.refresh();
            } catch (SQLException e) {
                Logger.getLogger(Flight.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    void closeBtnClicked(ActionEvent event) throws Exception {
        Node [] node = new Node[2];
        node[0] = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        flightStage.getChildren().add(node[0]);
        
    }

    @FXML
    void updateBtnClicked(ActionEvent event) {
        if(flightNameTextField.getText().isEmpty() && flightTypeTextField.getText().isEmpty() && podTextField.getText().isEmpty() && poaTextField.getText().isEmpty() && arrivalTimeTextField.getText().isEmpty() && departTimeTextField.getText().isEmpty() && statusTextField.getText().isEmpty()){
            JPanel panel= new JPanel();
            JOptionPane.showMessageDialog(panel,"All Fields can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(flightNameTextField.getText().isEmpty()){
            JPanel panel1= new JPanel();
            JOptionPane.showMessageDialog(panel1,"Flight Name can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(flightTypeTextField.getText().isEmpty()){
            JPanel panel2= new JPanel();
            JOptionPane.showMessageDialog(panel2,"Flight Type can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(podTextField.getText().isEmpty()){
            JPanel panel3= new JPanel();
            JOptionPane.showMessageDialog(panel3,"Place of Departure can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(poaTextField.getText().isEmpty()){
            JPanel panel4= new JPanel();
            JOptionPane.showMessageDialog(panel4,"Place of Arrival can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(arrivalTimeTextField.getText().isEmpty()){
            JPanel panel5= new JPanel();
            JOptionPane.showMessageDialog(panel5,"Arrival Time can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(departTimeTextField.getText().isEmpty()){
            JPanel panel6= new JPanel();
            JOptionPane.showMessageDialog(panel6,"Departure Time can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(statusTextField.getText().isEmpty()){
            JPanel panel7= new JPanel();
            JOptionPane.showMessageDialog(panel7,"Status can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }

        else{
            databaseConnection connection = new databaseConnection();
            Connection connectDB = connection.getConnection();

            myIndex = flightTableView.getSelectionModel().getSelectedIndex();
            id = flightTableView.getItems().get(myIndex).getFlight_id();

            String Flightid = idLabel.getText();
            String Flightname = flightNameTextField.getText();
            String Flighttype = flightTypeTextField.getText();
            String placeOfDept = podTextField.getText();
            String placeOfArr = poaTextField.getText();
            String timeOfArr = arrivalTimeTextField.getText();
            String timeOfDept = departTimeTextField.getText();
            String flightStatus = statusTextField.getText();

            String updateField = "UPDATE flight_tbl SET flight_name = '";
            String updateField1 = Flightname + "', flight_type = '";
            String updateField2 = Flighttype + "', flight_place_of_dept = '";
            String updateField3 = placeOfDept + "', flight_destination = '";
            String updateField4 = placeOfArr + "', flight_arr_time = '";
            String updateField5 = timeOfArr + "', flight_dept_time = '";
            String updateField6 = timeOfDept + "', flight_status = '";
            String updateField7 = flightStatus + "' WHERE flight_id = '" + Flightid + "';";

            String updateQuery = updateField + updateField1 + updateField2 + updateField3 + updateField4 + updateField5 + updateField6 + updateField7;

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(updateQuery);
                JPanel panel1 = new JPanel();
                JOptionPane.showMessageDialog(panel1, "Flight Updated Successfully!", "Message!", JOptionPane.INFORMATION_MESSAGE);
                flightTableView.refresh();
            } catch (SQLException e) {
                Logger.getLogger(Flight.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void flightId(){
        databaseConnection connect = new databaseConnection();
        Connection conn = connect.getConnection();

        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(flight_id) FROM flight_tbl;");
            rs.next();

            rs.getString("MAX(flight_id)");

            if(rs.getString("MAX(flight_id)") == null){
                idLabel.setText("FL-001");
            }
            else {
                long id = Long.parseLong(rs.getString("MAX(flight_id)").substring(3,rs.getString("MAX(flight_id)").length()));
                id++;
                idLabel.setText("FL-" + String.format("%03d", id));
            }
        }
        catch (Exception e){
            e.getCause();
            e.getStackTrace();
        }
    }

    public void viewTable(){
        databaseConnection connect = new databaseConnection();
        Connection conn = connect.getConnection();

        String flightViewQuery = "SELECT flight_id, flight_name, flight_type, flight_place_of_dept, flight_destination, flight_arr_time, flight_dept_time, flight_status FROM flight_tbl;";

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(flightViewQuery);

            while(resultSet.next()){
                String queryFlightId = resultSet.getString("flight_id");
                String queryFlightName = resultSet.getString("flight_name");
                String queryFlightType = resultSet.getString("flight_type");
                String queryFlightDept = resultSet.getString("flight_place_of_dept");
                String queryFlightArrival = resultSet.getString("flight_destination");
                String queryFlightTimeArr = resultSet.getString("flight_arr_time");
                String queryFlightTimeDept = resultSet.getString("flight_dept_time");
                String queryFlightStatus = resultSet.getString("flight_status");

                flightTableSearchObservableList.add(new flightTableSearch(queryFlightId, queryFlightName, queryFlightType, queryFlightDept, queryFlightArrival, queryFlightTimeArr, queryFlightTimeDept, queryFlightStatus));
            }

            flightidCol.setCellValueFactory(new PropertyValueFactory<>("flight_id"));
            flightnameCol.setCellValueFactory(new PropertyValueFactory<>("flight_name"));
            flighttypeCol.setCellValueFactory(new PropertyValueFactory<>("flight_type"));
            placeDeptCol.setCellValueFactory(new PropertyValueFactory<>("flight_place_of_dept"));
            placeArriveCol.setCellValueFactory(new PropertyValueFactory<>("flight_destination"));
            arrivalTimeCol.setCellValueFactory(new PropertyValueFactory<>("flight_arr_time"));
            deptTimeCol.setCellValueFactory(new PropertyValueFactory<>("flight_dept_time"));
            statusCol.setCellValueFactory(new PropertyValueFactory<>("flight_status"));

            flightTableView.setItems(flightTableSearchObservableList);

            FilteredList<flightTableSearch> filteredData = new FilteredList<>(flightTableSearchObservableList, b -> true);

            searchTextField.textProperty().addListener((observable, oldValue, newValue) ->{
                filteredData.setPredicate(flightTableSearch -> {

                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if(flightTableSearch.getFlight_id().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(flightTableSearch.getFlight_name().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(flightTableSearch.getFlight_type().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(flightTableSearch.getFlight_place_of_dept().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(flightTableSearch.getFlight_destination().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(flightTableSearch.getFlight_arr_time().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(flightTableSearch.getFlight_dept_time().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if(flightTableSearch.getFlight_status().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }
                    else {
                        return false;
                    }
                });
            });

            SortedList<flightTableSearch> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(flightTableView.comparatorProperty());

            flightTableView.setItems(sortedData);
        }
        catch(SQLException e){
            Logger.getLogger(Flight.class.getName()).log(Level.SEVERE, null, e);
        }

        flightTableView.setRowFactory( tv -> {
            TableRow<flightTableSearch> myTableRow = new TableRow<>();
            myTableRow.setOnMouseClicked( event -> {
                if(event.getClickCount() == 1 && (!myTableRow.isEmpty())){
                    myIndex = flightTableView.getSelectionModel().getSelectedIndex();

                    id = flightTableView.getItems().get(myIndex).getFlight_id();
                    idLabel.setText(flightTableView.getItems().get(myIndex).getFlight_id());
                    flightNameTextField.setText(flightTableView.getItems().get(myIndex).getFlight_name());
                    flightTypeTextField.setText(flightTableView.getItems().get(myIndex).getFlight_type());
                    podTextField.setText(flightTableView.getItems().get(myIndex).getFlight_place_of_dept());
                    poaTextField.setText(flightTableView.getItems().get(myIndex).getFlight_destination());
                    arrivalTimeTextField.setText(flightTableView.getItems().get(myIndex).getFlight_arr_time());
                    departTimeTextField.setText(flightTableView.getItems().get(myIndex).getFlight_dept_time());
                    statusTextField.setText(flightTableView.getItems().get(myIndex).getFlight_status());
                }
            });
            return myTableRow;
        });
    }

    public void Register(){
        if(flightNameTextField.getText().isEmpty() && flightTypeTextField.getText().isEmpty() && podTextField.getText().isEmpty() && poaTextField.getText().isEmpty() && arrivalTimeTextField.getText().isEmpty() && departTimeTextField.getText().isEmpty() && statusTextField.getText().isEmpty()){
            JPanel panel= new JPanel();
            JOptionPane.showMessageDialog(panel,"All Fields can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(flightNameTextField.getText().isEmpty()){
            JPanel panel1= new JPanel();
            JOptionPane.showMessageDialog(panel1,"Flight Name can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(flightTypeTextField.getText().isEmpty()){
            JPanel panel2= new JPanel();
            JOptionPane.showMessageDialog(panel2,"Flight Type can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(podTextField.getText().isEmpty()){
            JPanel panel3= new JPanel();
            JOptionPane.showMessageDialog(panel3,"Place of Departure can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(poaTextField.getText().isEmpty()){
            JPanel panel4= new JPanel();
            JOptionPane.showMessageDialog(panel4,"Place of Arrival can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(arrivalTimeTextField.getText().isEmpty()){
            JPanel panel5= new JPanel();
            JOptionPane.showMessageDialog(panel5,"Arrival Time can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(departTimeTextField.getText().isEmpty()){
            JPanel panel6= new JPanel();
            JOptionPane.showMessageDialog(panel6,"Departure Time can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(statusTextField.getText().isEmpty()){
            JPanel panel7= new JPanel();
            JOptionPane.showMessageDialog(panel7,"Status can not be Empty!","Error",JOptionPane.ERROR_MESSAGE);
        }

        else{
            registerFlight();
        }
    }


    public void registerFlight(){
        databaseConnection connectnow = new databaseConnection();
        Connection connectDB = connectnow.getConnection();

        String Flightid = idLabel.getText();
        String Flightname = flightNameTextField.getText();
        String Flighttype = flightTypeTextField.getText();
        String placeOfDept = podTextField.getText();
        String placeOfArr = poaTextField.getText();
        String timeOfArr = arrivalTimeTextField.getText();
        String timeOfDept = departTimeTextField.getText();
        String flightStatus = statusTextField.getText();

        String insertFields = "INSERT INTO flight_tbl(flight_id, flight_name, flight_type, flight_place_of_dept, flight_destination, flight_arr_time, flight_dept_time, flight_status) VALUES ('";
        String insertValues = Flightid + "','" + Flightname + "','" + Flighttype + "','" + placeOfDept + "','" + placeOfArr + "','" + timeOfArr + "','" + timeOfDept + "','" + flightStatus + "');";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            JPanel panel1= new JPanel();
            JOptionPane.showMessageDialog(panel1,"Flight Added Successfully!","Message!",JOptionPane.INFORMATION_MESSAGE);

            flightNameTextField.setText("");
            flightTypeTextField.setText("");
            podTextField.setText("");
            poaTextField.setText("");
            arrivalTimeTextField.setText("");
            departTimeTextField.setText("");
            statusTextField.setText("");
        }
        catch (Exception e){
            e.getCause();
            e.getStackTrace();
        }
    }
}
