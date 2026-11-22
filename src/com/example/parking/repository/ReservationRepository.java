package com.example.parking.repository;

import com.example.parking.config.DbConfig;
import com.example.parking.model.Reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    private static final String INSERT_SQL =
            "INSERT INTO reservations (spot_code, vehicle_number, reserved_date) VALUES (?, ?, ?)";

    private static final String SELECT_ALL_SQL =
            "SELECT id, spot_code, vehicle_number, reserved_date, created_at " +
            "FROM reservations ORDER BY reserved_date ASC, id ASC";

    public void save(Reservation reservation) throws SQLException {
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setString(1, reservation.getSpotCode());
            pstmt.setString(2, reservation.getVehicleNumber());
            pstmt.setDate(3, Date.valueOf(reservation.getReservedDate()));

            pstmt.executeUpdate();
        }
    }

    public List<Reservation> findAll() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String spotCode = rs.getString("spot_code");
                String vehicleNumber = rs.getString("vehicle_number");

                Date sqlDate = rs.getDate("reserved_date");
                LocalDate reservedDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                Timestamp sqlTimestamp = rs.getTimestamp("created_at");
                LocalDateTime createdAt = (sqlTimestamp != null) ? sqlTimestamp.toLocalDateTime() : null;

                Reservation item = new Reservation(id, spotCode, vehicleNumber, reservedDate, createdAt);
                reservations.add(item);
            }
        }

        return reservations;
    }
}