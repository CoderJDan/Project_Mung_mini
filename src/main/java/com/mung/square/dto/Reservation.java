package com.mung.square.dto;

public class Reservation {
    private String id;
    private String name;
    private String petName;
    private String reservationDate;
    private String location;
    private String status;

    public Reservation() {

    }

    public Reservation(String id, String name, String petName, String reservationDate, String location, String status) {
        this.id = id;
        this.name = name;
        this.petName = petName;
        this.reservationDate = reservationDate;
        this.location = location;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPetName() {
        return petName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

