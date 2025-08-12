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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class viewFlight implements Initializable {

    @FXML
    private AnchorPane flightStage;

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
    private ImageView searchImage;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button closeButton;

    ObservableList<flightTableSearch> flightTableSearchObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File search = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_search_26px.png");
        Image searchicon = new Image(search.toURI().toString());
        searchImage.setImage(searchicon);
        viewTable();
    }

    @FXML
    void closeBtnClicked(ActionEvent event) throws Exception {
        Node[] node = new Node[2];
        node[0] = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        flightStage.getChildren().add(node[0]);
    }

    public void viewTable() {
        databaseConnection connect = new databaseConnection();
        Connection conn = connect.getConnection();

        String flightViewQuery = "SELECT flight_id, flight_name, flight_type, flight_place_of_dept, flight_destination, flight_arr_time, flight_dept_time, flight_status FROM flight_tbl;";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(flightViewQuery);

            while (resultSet.next()) {
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

            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(flightTableSearch -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (flightTableSearch.getFlight_id().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (flightTableSearch.getFlight_name().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (flightTableSearch.getFlight_type().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (flightTableSearch.getFlight_place_of_dept().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (flightTableSearch.getFlight_destination().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (flightTableSearch.getFlight_arr_time().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (flightTableSearch.getFlight_dept_time().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (flightTableSearch.getFlight_status().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<flightTableSearch> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(flightTableView.comparatorProperty());

            flightTableView.setItems(sortedData);
        } catch (SQLException e) {
            Logger.getLogger(Flight.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
