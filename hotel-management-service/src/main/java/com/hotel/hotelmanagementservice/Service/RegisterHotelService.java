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
    private static final String ROOM_INFO_TABLE = "room_info";
    private static final String ROOM_STATUS_TABLE = "room_status";
    private static final String BOOKING_INFO_TABLE = "booking_info";
    Logger logger = LoggerFactory.getLogger(RegisterHotelService.class);
    @Autowired
    private KafkaTemplate<String, Hotel> hotelKafkaTemplate;

    static {
        DatabaseSetupManager.createHotelInfoTableIfNotExist();
        DatabaseSetupManager.createRoomInfoTableIfNotExist();
        DatabaseSetupManager.createRoomStatusTableIfNotExist();
        DatabaseSetupManager.createBookingInfoTableIfNotExist();
    }

    public Hotel saveHotel(Hotel hotel) {
        System.out.println("CHECK");
        String hotelId = String.valueOf(hotel.getId());
        performTransactionCreateHotelTables(hotel);
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

    private void performTransactionCreateHotelTables(Hotel hotel) {
        try {
            insertHotelInfoRecord(hotel);
            insertRoomStatusRecords(hotel.getId(), ROOM_STATUS_TABLE, hotel.getRoomTypeList());
            insertRoomInfoRecords(ROOM_INFO_TABLE, hotel);
        }
        catch (Exception e){
            //TODO Have not tested
            logger.info("Exception when registering hotel " + hotel.getName() + " : " + e.getMessage());
            QueryRunner.deleteHotelRecord(String.valueOf(hotel.getId()));
            QueryRunner.deleteRoomStatusRecords(hotel.getId());
            QueryRunner.deleteRoomInfoRecords(hotel.getId());
            throw e;
        }
    }

    private void insertHotelInfoRecord(Hotel hotel) {
        String[] values = {String.valueOf(hotel.getId()), hotel.getName(), hotel.getDescription(), hotel.getLocation()};
        QueryRunner.insertHotelInfoRecord(values);
    }

    private void insertRoomInfoRecords(String infoTable, Hotel hotel) {
        QueryRunner.insertRoomInfoRecord(infoTable, hotel.getRoomTypeList(), hotel.getId());
    }

    private void insertRoomStatusRecords(String hotelId, String roomStatusTable, List<RoomType> roomTypeList) {
        String insertSql = "INSERT INTO " + roomStatusTable + " (room_id, room_type, status) VALUES (?, ?, ?)";
        QueryRunner.insertRoomStatusRecords(insertSql, roomTypeList, hotelId);
    }
}
