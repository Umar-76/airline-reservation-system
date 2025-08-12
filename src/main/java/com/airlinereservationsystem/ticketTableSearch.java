package com.airlinereservationsystem;

public class ticketTableSearch {
    String ticket_id, p_full_name, p_age, p_email, p_phone, depart_date, depart_time;
    String depart_from, destination, flight_name, flight_class, flight_price;

    public ticketTableSearch(String ticket_id, String p_full_name, String p_age, String p_email, String p_phone, String depart_date, String depart_time, String depart_from, String destination, String flight_name, String flight_class, String flight_price) {
        this.ticket_id = ticket_id;
        this.p_full_name = p_full_name;
        this.p_age = p_age;
        this.p_email = p_email;
        this.p_phone = p_phone;
        this.depart_date = depart_date;
        this.depart_time = depart_time;
        this.depart_from = depart_from;
        this.destination = destination;
        this.flight_name = flight_name;
        this.flight_class = flight_class;
        this.flight_price = flight_price;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public String getP_full_name() {
        return p_full_name;
    }

    public String getP_age() {
        return p_age;
    }

    public String getP_email() {
        return p_email;
    }

    public String getP_phone() {
        return p_phone;
    }

    public String getDepart_date() {
        return depart_date;
    }

    public String getDepart_time() {
        return depart_time;
    }

    public String getDepart_from() {
        return depart_from;
    }

    public String getDestination() {
        return destination;
    }

    public String getFlight_name() {
        return flight_name;
    }

    public String getFlight_class() {
        return flight_class;
    }

    public String getFlight_price() {
        return flight_price;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public void setP_full_name(String p_full_name) {
        this.p_full_name = p_full_name;
    }

    public void setP_age(String p_age) {
        this.p_age = p_age;
    }

    public void setP_email(String p_email) {
        this.p_email = p_email;
    }

    public void setP_phone(String p_phone) {
        this.p_phone = p_phone;
    }

    public void setDepart_date(String depart_date) {
        this.depart_date = depart_date;
    }

    public void setDepart_time(String depart_time) {
        this.depart_time = depart_time;
    }

    public void setDepart_from(String depart_from) {
        this.depart_from = depart_from;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFlight_name(String flight_name) {
        this.flight_name = flight_name;
    }

    public void setFlight_class(String flight_class) {
        this.flight_class = flight_class;
    }

    public void setFlight_price(String flight_price) {
        this.flight_price = flight_price;
    }
}
