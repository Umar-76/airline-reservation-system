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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class viewTicketUser implements Initializable {

    @FXML
    private TableColumn<ticketTableSearch, String> ticketIdCol;

    @FXML
    private TableColumn<ticketTableSearch, String> passengerNameCol;

    @FXML
    private TableColumn<ticketTableSearch, String> passengerAgeCol;

    @FXML
    private TableColumn<ticketTableSearch, String> passengerEmailCol;

    @FXML
    private TableColumn<ticketTableSearch, String> passengerPhoneCol;

    @FXML
    private TableColumn<ticketTableSearch, String> deptDateCol;

    @FXML
    private TableColumn<ticketTableSearch, String> deptTimeCol;

    @FXML
    private TableColumn<ticketTableSearch, String> deptFromCol;

    @FXML
    private TableColumn<ticketTableSearch, String> destinationCol;

    @FXML
    private TableColumn<ticketTableSearch, String> flightNameCol;

    @FXML
    private TableColumn<ticketTableSearch, String> flightClassCol;

    @FXML
    private TableColumn<ticketTableSearch, String> priceCol;

    @FXML
    private TableView<ticketTableSearch> TicketTableView;

    @FXML
    private Button closeButton;

    @FXML
    private Label idLabel;

    @FXML
    private ImageView searchImage;

    @FXML
    private TextField searchTextField;

    @FXML
    private AnchorPane viewTicketStage;
    int index;
    String id;

    ObservableList<ticketTableSearch> ticketTableSearchObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File search = new File("C:\\Users\\Aslam\\IdeaProjects\\AirlineReservationSystem\\images\\icons8_search_26px.png");
        Image searchicon = new Image(search.toURI().toString());
        searchImage.setImage(searchicon);
        viewTicketTable();
    }

    @FXML
    void closeBtnClicked(ActionEvent event) throws Exception {
        Node[] node = new Node[2];
        node[0] = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        viewTicketStage.getChildren().add(node[0]);
    }

    public void viewTicketTable() {
        databaseConnection connect = new databaseConnection();
        Connection conn = connect.getConnection();

        String flightViewQuery = "SELECT ticket_id, p_full_name, p_age, p_email, p_phone, depart_date, depart_time, depart_from, destination, flight_name, flight_class, flight_price FROM ticket_tbl;";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(flightViewQuery);

            while (resultSet.next()) {
                String queryTicketId = resultSet.getString("ticket_id");
                String queryPassName = resultSet.getString("p_full_name");
                String queryPassAge = resultSet.getString("p_age");
                String queryPassEmail = resultSet.getString("p_email");
                String queryPassPhone = resultSet.getString("p_phone");
                String queryDepartDate = resultSet.getString("depart_date");
                String queryDepartTime = resultSet.getString("depart_time");
                String queryDepartFrom = resultSet.getString("depart_from");
                String queryDestination = resultSet.getString("destination");
                String queryFlightName = resultSet.getString("flight_name");
                String queryFlightClass = resultSet.getString("flight_class");
                String queryFlightPrice = resultSet.getString("flight_price");

                ticketTableSearchObservableList.add(new ticketTableSearch(queryTicketId, queryPassName, queryPassAge, queryPassEmail, queryPassPhone, queryDepartDate, queryDepartTime, queryDepartFrom, queryDestination, queryFlightName, queryFlightClass, queryFlightPrice));
            }

            ticketIdCol.setCellValueFactory(new PropertyValueFactory<>("ticket_id"));
            passengerNameCol.setCellValueFactory(new PropertyValueFactory<>("p_full_name"));
            passengerAgeCol.setCellValueFactory(new PropertyValueFactory<>("p_age"));
            passengerEmailCol.setCellValueFactory(new PropertyValueFactory<>("p_email"));
            passengerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("p_phone"));
            deptDateCol.setCellValueFactory(new PropertyValueFactory<>("depart_date"));
            deptTimeCol.setCellValueFactory(new PropertyValueFactory<>("depart_time"));
            deptFromCol.setCellValueFactory(new PropertyValueFactory<>("depart_from"));
            destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
            flightNameCol.setCellValueFactory(new PropertyValueFactory<>("flight_name"));
            flightClassCol.setCellValueFactory(new PropertyValueFactory<>("flight_class"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("flight_price"));

            TicketTableView.setItems(ticketTableSearchObservableList);

            FilteredList<ticketTableSearch> filteredData = new FilteredList<>(ticketTableSearchObservableList, b -> true);

            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(ticketTableSearch -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (ticketTableSearch.getTicket_id().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (ticketTableSearch.getP_full_name().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (ticketTableSearch.getP_age().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (ticketTableSearch.getP_email().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (ticketTableSearch.getP_phone().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (ticketTableSearch.getDepart_date().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (ticketTableSearch.getDepart_time().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (ticketTableSearch.getDepart_from().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (ticketTableSearch.getDestination().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (ticketTableSearch.getFlight_name().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (ticketTableSearch.getFlight_class().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (ticketTableSearch.getFlight_price().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<ticketTableSearch> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(TicketTableView.comparatorProperty());

            TicketTableView.setItems(sortedData);
        } catch (SQLException e) {
            Logger.getLogger(Flight.class.getName()).log(Level.SEVERE, null, e);
        }

        TicketTableView.setRowFactory( tv -> {
            TableRow<ticketTableSearch> myTableRow = new TableRow<>();
            myTableRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myTableRow.isEmpty())) {
                    index = TicketTableView.getSelectionModel().getSelectedIndex();

                    id = TicketTableView.getItems().get(index).getTicket_id();
                    idLabel.setText(TicketTableView.getItems().get(index).getTicket_id());
                }
            });
            return myTableRow;
        });
    }

}
