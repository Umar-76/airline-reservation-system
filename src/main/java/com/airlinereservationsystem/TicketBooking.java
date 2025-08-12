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
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

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

public class TicketBooking implements Initializable {

    @FXML
    private TextField ageTextField;

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
    private TableView<flightTableSearch> flightTableView;

    @FXML
    private Button bookButton;

    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<String> classComboBox;

    @FXML
    private TextField departTextField;

    @FXML
    private TextField deptDateTextField;

    @FXML
    private TextField deptTimeTextField;

    @FXML
    private TextField destinationTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField flightNameTextField;

    @FXML
    private Label idLabel;

    @FXML
    private TextField passengerNameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView searchImage;

    @FXML
    private TextField searchTextField;

    @FXML
    private AnchorPane ticketStage;

    int myIndex;
    String id;

    ObservableList<String> list1 = FXCollections.observableArrayList("Economy", "Business", "First");
    ObservableList<flightTableSearch> flightTableSearchObservableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File search = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_search_26px.png");
        Image searchicon = new Image(search.toURI().toString());
        searchImage.setImage(searchicon);
        ticketId();
        viewTable();

        classComboBox.setItems(list1);

    }

    @FXML
    void bookBtnClicked(ActionEvent event) throws Exception {
        Stage st = new Stage();
        FXMLLoader book = new FXMLLoader(TicketBooking.class.getResource("confirmTicket.fxml"));
        Scene scene = new Scene(book.load(), 353,262);
        st.initStyle(StageStyle.UNDECORATED);
        st.setScene(scene);
        st.showAndWait();

        registerPassenger();
        ticketId();

    }

    @FXML
    void closeBtnClicked(ActionEvent event) throws Exception {
        Node[] node = new Node[2];
        node[0] = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        ticketStage.getChildren().add(node[0]);
    }

    @FXML
    void comboBoxClicked(ActionEvent event) {
        if(classComboBox.getValue().equals("Economy")){
            priceLabel.setText("18000.00");
        }
        else if(classComboBox.getValue().equals("Business")){
            priceLabel.setText("35000.00");
        }
        else if(classComboBox.getValue().equals("First")){
            priceLabel.setText("60000.00");
        }
        else {
            priceLabel.setText("");
        }
    }

    public void ticketId(){
        databaseConnection connect = new databaseConnection();
        Connection conn = connect.getConnection();

        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(ticket_id) FROM ticket_tbl;");
            rs.next();

            rs.getString("MAX(ticket_id)");

            if(rs.getString("MAX(ticket_id)") == null){
                idLabel.setText("TP-001");
            }
            else {
                long id = Long.parseLong(rs.getString("MAX(ticket_id)").substring(3,rs.getString("MAX(ticket_id)").length()));
                id++;
                idLabel.setText("TP-" + String.format("%03d", id));
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
                    flightNameTextField.setText(flightTableView.getItems().get(myIndex).getFlight_name());
                    departTextField.setText(flightTableView.getItems().get(myIndex).getFlight_place_of_dept());
                    destinationTextField.setText(flightTableView.getItems().get(myIndex).getFlight_destination());
                    deptTimeTextField.setText(flightTableView.getItems().get(myIndex).getFlight_dept_time());
                }
            });
            return myTableRow;
        });
    }

    public void registerPassenger(){
        databaseConnection connectnow = new databaseConnection();
        Connection connectDB = connectnow.getConnection();

        String passengerId = idLabel.getText();
        String pFullName = passengerNameTextField.getText();
        String pAge = ageTextField.getText();
        String pEmail = emailTextField.getText();
        String pPhone = phoneTextField.getText();
        String deptDate = deptDateTextField.getText();
        String deptTime = deptTimeTextField.getText();
        String deptFrom = departTextField.getText();
        String destination = destinationTextField.getText();
        String flightName = flightNameTextField.getText();
        String flightClass = classComboBox.getValue();
        String flightPrice = priceLabel.getText();

        String insertFields = "INSERT INTO ticket_tbl(ticket_id, p_full_name, p_age, p_email, p_phone, depart_date, depart_time, depart_from, destination, flight_name, flight_class, flight_price) VALUES ('";
        String insertValues = passengerId + "','" + pFullName + "','" + pAge + "','" + pEmail + "','" + pPhone + "','" + deptDate + "','" + deptTime + "','" + deptFrom + "','" + destination + "','" + flightName + "','" + flightClass + "','" + flightPrice + "');";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            JPanel panel1= new JPanel();
            JOptionPane.showMessageDialog(panel1,"Ticket Booked Successfully!","Message!",JOptionPane.INFORMATION_MESSAGE);
            JPanel panel2= new JPanel();
            JOptionPane.showMessageDialog(panel2,"Your E-Ticket has been sent\nto your registered E-mail","Message!",JOptionPane.INFORMATION_MESSAGE);
            setFieldsEmpty();
        }
        catch (Exception e){
            e.getCause();
            e.getStackTrace();
        }
    }

    public void setFieldsEmpty(){
        passengerNameTextField.setText("");
        ageTextField.setText("");
        emailTextField.setText("");
        phoneTextField.setText("");
        deptDateTextField.setText("");
        deptTimeTextField.setText("");
        departTextField.setText("");
        destinationTextField.setText("");
        flightNameTextField.setText("");
        priceLabel.setText("");
    }

}
