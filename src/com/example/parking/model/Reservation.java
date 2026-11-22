package com.example.parking.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {

    private Long id;
    private String spotCode;
    private String vehicleNumber;
    private LocalDate reservedDate;
    private LocalDateTime createdAt;

    public Reservation() {
    }

    public Reservation(String spotCode, String vehicleNumber, LocalDate reservedDate) {
        this.spotCode = spotCode;
        this.vehicleNumber = vehicleNumber;
        this.reservedDate = reservedDate;
    }

    public Reservation(Long id, String spotCode, String vehicleNumber,
                       LocalDate reservedDate, LocalDateTime createdAt) {
        this.id = id;
        this.spotCode = spotCode;
        this.vehicleNumber = vehicleNumber;
        this.reservedDate = reservedDate;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpotCode() {
        return spotCode;
    }

    public void setSpotCode(String spotCode) {
        this.spotCode = spotCode;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", spotCode='" + spotCode + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", reservedDate=" + reservedDate +
                ", createdAt=" + createdAt +
                '}';
    }
}