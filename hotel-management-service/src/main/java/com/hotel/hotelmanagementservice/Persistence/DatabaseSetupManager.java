package com.hotel.hotelmanagementservice.Persistence;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSetupManager {
    private static JdbcTemplate jdbcTemplate;
    private static final String HOTELS_INFO_TABLE = "hotels_info";
    private static final String ROOM_INFO_TABLE = "room_info";
    private static final String ROOM_STATUS_TABLE = "room_status";
    private static final String BOOKING_INFO_TABLE = "booking_info";

    @Autowired
    public DatabaseSetupManager(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void createHotelInfoTable() {
        System.out.println("CHECK");
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
                "hotel_id VARCHAR(255), " +
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
    public static void createRoomInfoTableIfNotExist() {
        if(QueryRunner.tableDoesNotExist(ROOM_INFO_TABLE))
            createHotelRoomInfoTable(ROOM_INFO_TABLE);
    }

    public static void createRoomStatusTableIfNotExist() {
        if(QueryRunner.tableDoesNotExist(ROOM_STATUS_TABLE))
            createRoomStatusTable(ROOM_STATUS_TABLE);
    }

    public static void createBookingInfoTableIfNotExist() {
        if(QueryRunner.tableDoesNotExist(BOOKING_INFO_TABLE))
            createBookingInfoTable(BOOKING_INFO_TABLE);
    }

    public static void createHotelInfoTableIfNotExist() {
        if(QueryRunner.tableDoesNotExist(HOTELS_INFO_TABLE))
            createHotelInfoTable();
    }
}
