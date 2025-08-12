package com.airlinereservationsystem;

public class flightTableSearch {

    String flight_id, flight_name, flight_type, flight_place_of_dept;
    String flight_destination, flight_arr_time, flight_dept_time;
    String flight_status;


    public flightTableSearch(String flight_id, String flight_name, String flight_type, String flight_place_of_dept, String flight_destination, String flight_arr_time, String flight_dept_time, String flight_status) {
        this.flight_id = flight_id;
        this.flight_name = flight_name;
        this.flight_type = flight_type;
        this.flight_place_of_dept = flight_place_of_dept;
        this.flight_destination = flight_destination;
        this.flight_arr_time = flight_arr_time;
        this.flight_dept_time = flight_dept_time;
        this.flight_status = flight_status;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public String getFlight_name() {
        return flight_name;
    }

    public String getFlight_type() {
        return flight_type;
    }

    public String getFlight_place_of_dept() {
        return flight_place_of_dept;
    }

    public String getFlight_destination() {
        return flight_destination;
    }

    public String getFlight_arr_time() {
        return flight_arr_time;
    }

    public String getFlight_dept_time() {
        return flight_dept_time;
    }

    public String getFlight_status() {
        return flight_status;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    public void setFlight_name(String flight_name) {
        this.flight_name = flight_name;
    }

    public void setFlight_type(String flight_type) {
        this.flight_type = flight_type;
    }

    public void setFlight_place_of_dept(String flight_place_of_dept) {
        this.flight_place_of_dept = flight_place_of_dept;
    }

    public void setFlight_destination(String flight_destination) {
        this.flight_destination = flight_destination;
    }

    public void setFlight_arr_time(String flight_arr_time) {
        this.flight_arr_time = flight_arr_time;
    }

    public void setFlight_dept_time(String flight_dept_time) {
        this.flight_dept_time = flight_dept_time;
    }

    public void setFlight_status(String flight_status) {
        this.flight_status = flight_status;
    }
}
