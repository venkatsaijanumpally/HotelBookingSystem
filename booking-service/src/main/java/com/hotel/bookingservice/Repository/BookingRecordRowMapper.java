package com.hotel.bookingservice.Repository;

import com.hotel.bookingservice.Model.BookingRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRecordRowMapper implements RowMapper<BookingRecord> {
    @Override
    public BookingRecord mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        BookingRecord bookingRecord = new BookingRecord();
        bookingRecord.setId(resultSet.getLong("id"));
        bookingRecord.setUserId(resultSet.getString("user_id"));
        bookingRecord.setCheckinDate(resultSet.getString("checkin_date"));
        bookingRecord.setCheckoutDate(resultSet.getString("checkout_date"));
        bookingRecord.setHotel(resultSet.getString("hotel"));
        bookingRecord.setRoomType(resultSet.getString("room_type"));
        return bookingRecord;
    }
}

