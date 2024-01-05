package com.hotel.hotelmanagementservice.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSetupManager {
    private static JdbcTemplate jdbcTemplate;
    private static final String HOTELS_INFO_TABLE = "hotels_info";

    @Autowired
    public DatabaseSetupManager(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void createHotelInfoTable() {
        String sql = "CREATE TABLE " + HOTELS_INFO_TABLE + " (" +
                "id VARCHAR(255) PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "description VARCHAR(255)," +
                "location VARCHAR(255))";
        QueryRunner.execute(sql);
    }

    public static void createRoomStatusTable(String roomStatusTable) {
        String sql = "CREATE TABLE " + roomStatusTable + " (" +
                "room_id VARCHAR(255) PRIMARY KEY, " +
                "room_type VARCHAR(255), " +
                "status VARCHAR(20) CHECK (status IN ('pending', 'available')) NOT NULL)";
        QueryRunner.execute(sql);
    }

    public static void createBookingInfoTable(String bookingInfoTable) {
        String sql = "CREATE TABLE " + bookingInfoTable + " (" +
                "booking_id VARCHAR(255) PRIMARY KEY, " +
                "room_id VARCHAR(255), " +
                "user_id VARCHAR(255), " +
                "from_date DATE, " +
                "to_date DATE, " +
                "booking_status VARCHAR(20) CHECK (booking_status IN ('booked', 'pending', 'cancelled', 'reserved')) NOT NULL)";
        QueryRunner.execute(sql);
    }


    public static void createHotelRoomInfoTable(String infoTable) {
        String sql = "CREATE TABLE " + infoTable + " (" +
                "id SERIAL, " +
                "room_type VARCHAR(255), " +
                "number_of_rooms VARCHAR(255), " +
                "price VARCHAR(255))";
        QueryRunner.execute(sql);
    }

    private static void createHotelDetailsTable(String detailsTable) {
        String sql = "CREATE TABLE " + detailsTable + " (" +
                "id SERIAL PRIMARY KEY, " +
                "room_type VARCHAR(255), " +
                "number_of_rooms VARCHAR(255), " +
                "price VARCHAR(255))";
        QueryRunner.execute(sql);
    }
}
