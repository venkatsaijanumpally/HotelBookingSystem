package com.hotel.hotelmanagementservice.Service;

import com.hotel.hotelmanagementservice.Exception.HotelNotFoundException;
import com.hotel.hotelmanagementservice.Model.Hotel;
import com.hotel.hotelmanagementservice.Model.RoomType;
import com.hotel.hotelmanagementservice.Persistence.DatabaseSetupManager;
import com.hotel.hotelmanagementservice.Persistence.HotelRecordRowMapper;
import com.hotel.hotelmanagementservice.Persistence.QueryRunner;
import com.hotel.hotelmanagementservice.Persistence.RoomTypeListRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterHotelService {
    private static final String HOTELS_INFO_TABLE = "hotels_info";
    Logger logger = LoggerFactory.getLogger(RegisterHotelService.class);
    @Autowired
    private KafkaTemplate<String, Hotel> hotelKafkaTemplate;

    public Hotel saveHotel(Hotel hotel) {
        String hotelId = String.valueOf(hotel.getId());
        String detailsTable = hotelId + "_hotel_details";
        String infoTable = hotelId + "_room_info";
        String availabilityTable = hotelId + "_rooms_availability";
        String roomStatusTable = hotelId + "_room_status";
        String bookingStatusTable = hotelId + "_booking_status";
        String bookingInfoTable = hotelId + "_booking_info";
        System.out.println("Creating tables");
        performTransactionCreateHotelTables(detailsTable, infoTable, roomStatusTable, bookingInfoTable, hotel);
        hotelKafkaTemplate.send("hotels_topic", hotel);
        return hotel;
    }

    public Hotel getHotel(String hotelId) {
        String sqlHotelDetails = "SELECT * FROM " + HOTELS_INFO_TABLE + " WHERE id = ?";
        String sqlRoomInfo = "SELECT * FROM " + hotelId + "_room_info";

        try {
            // Use RowMapper to map the result set to Hotel and RoomType objects
            Hotel hotel = QueryRunner.queryForObject(sqlHotelDetails, new HotelRecordRowMapper(), hotelId);
            List<RoomType> roomTypeList = QueryRunner.query(sqlRoomInfo, new RoomTypeListRowMapper());

            hotel.setRoomTypeList(roomTypeList);

            return hotel;
        } catch (EmptyResultDataAccessException e) {
            throw new HotelNotFoundException("Hotel with ID " + hotelId + " not found.");
        }
    }

    private void performTransactionCreateHotelTables(String detailsTable, String infoTable, String roomStatusTable, String bookingInfoTable, Hotel hotel) {
        try {
            if(hotelsTableDoesNotExist(HOTELS_INFO_TABLE))
                DatabaseSetupManager.createHotelInfoTable();
            //createHotelDetailsTable(detailsTable);
            DatabaseSetupManager.createHotelRoomInfoTable(infoTable);
            DatabaseSetupManager.createRoomStatusTable(roomStatusTable);
            DatabaseSetupManager.createBookingInfoTable(bookingInfoTable);

            insertRoomStatusRecords(roomStatusTable, hotel.getRoomTypeList());
            insertHotelInfoRecord(hotel);
            insertRoomInfoRecords(infoTable, hotel);
        }
        catch (Exception e){
            logger.info("Exception when registering hotel " + hotel.getName() + " : " + e.getMessage());
            //QueryRunner.deleteTableIfExist(detailsTable);
            QueryRunner.deleteTableIfExist(infoTable);
            QueryRunner.deleteTableIfExist(roomStatusTable);
            QueryRunner.deleteTableIfExist(bookingInfoTable);
            QueryRunner.deleteHotelRecord(String.valueOf(hotel.getId()));
            throw e;
        }
    }

    private boolean hotelsTableDoesNotExist(String hotelsTable) {
        return QueryRunner.hotelDoesNotExist(hotelsTable);
    }

    private void insertHotelInfoRecord(Hotel hotel) {
        String[] values = {String.valueOf(hotel.getId()), hotel.getName(), hotel.getDescription(), hotel.getLocation()};
        QueryRunner.insertHotelInfoRecord(values);
    }

    private void insertRoomInfoRecords(String infoTable, Hotel hotel) {
        QueryRunner.insertRoomInfoRecord(infoTable, hotel.getRoomTypeList());
    }

    private void insertRoomStatusRecords(String roomStatusTable, List<RoomType> roomTypeList) {
        String insertSql = "INSERT INTO " + roomStatusTable + " (room_id, room_type, status) VALUES (?, ?, ?)";
        QueryRunner.insertRoomStatusRecords(insertSql, roomTypeList);
    }
}
