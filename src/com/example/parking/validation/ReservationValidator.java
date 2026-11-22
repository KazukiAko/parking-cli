package com.example.parking.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ReservationValidator {

    private ReservationValidator() {
    }

    public static String normalizeAndValidateSpotCode(String rawSpotCode) {
        if (rawSpotCode == null || rawSpotCode.isBlank()) {
            throw new IllegalArgumentException("区画番号は必須です。");
        }
        return rawSpotCode.trim().toUpperCase();
    }

    public static String normalizeAndValidateVehicleNumber(String rawVehicleNumber) {
        if (rawVehicleNumber == null || rawVehicleNumber.isBlank()) {
            throw new IllegalArgumentException("車両番号は必須です。");
        }
        return rawVehicleNumber.trim();
    }

    public static LocalDate parseReservedDate(String rawDateText) {
        if (rawDateText == null || rawDateText.isBlank()) {
            throw new IllegalArgumentException("予約日（YYYY-MM-DD）は必須です。");
        }
        try {
            return LocalDate.parse(rawDateText.trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("日付形式が不正です。YYYY-MM-DD で入力してください。", e);
        }
    }
}