package com.example.parking;

import com.example.parking.model.Reservation;
import com.example.parking.repository.ReservationRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ReservationRepository repository = new ReservationRepository();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                printMenu();
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        handleRegister(scanner, repository);
                        break;
                    case "2":
                        handleList(repository);
                        break;
                    case "0":
                        running = false;
                        System.out.println("終了します。");
                        break;
                    default:
                        System.out.println("1、2、0 のいずれかを入力してください。");
                        break;
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== 駐車場予約 CLI ===");
        System.out.println("1. 登録");
        System.out.println("2. 参照");
        System.out.println("0. 終了");
        System.out.print("選択> ");
    }

    private static void handleRegister(Scanner scanner, ReservationRepository repository) {
        System.out.print("区画番号（例: A-01）> ");
        String spotCode = scanner.nextLine().trim();

        System.out.print("車両番号（例: 横浜 500 あ 12-34）> ");
        String vehicleNumber = scanner.nextLine().trim();

        System.out.print("予約日（YYYY-MM-DD、例: 2026-11-25）> ");
        String dateText = scanner.nextLine().trim();

        if (spotCode.isEmpty() || vehicleNumber.isEmpty() || dateText.isEmpty()) {
            System.out.println("空欄の項目があるため登録をキャンセルしました。");
            return;
        }

        try {
            LocalDate reservedDate = LocalDate.parse(dateText);
            Reservation reservation = new Reservation(spotCode, vehicleNumber, reservedDate);
            repository.save(reservation);
            System.out.println("登録しました。");
        } catch (DateTimeParseException e) {
            System.out.println("日付形式が不正です。YYYY-MM-DD で入力してください。");
        } catch (SQLException e) {
            System.out.println("DB登録エラー: " + e.getMessage());
        }
    }

    private static void handleList(ReservationRepository repository) {
        try {
            List<Reservation> reservations = repository.findAll();
            if (reservations.isEmpty()) {
                System.out.println("予約データはありません。");
                return;
            }

            System.out.println("--- 予約一覧 ---");
            for (Reservation r : reservations) {
                System.out.printf("ID=%d | 区画=%s | 車両=%s | 予約日=%s | 登録日時=%s%n",
                        r.getId(),
                        r.getSpotCode(),
                        r.getVehicleNumber(),
                        r.getReservedDate(),
                        r.getCreatedAt());
            }
        } catch (SQLException e) {
            System.out.println("DB参照エラー: " + e.getMessage());
        }
    }
}