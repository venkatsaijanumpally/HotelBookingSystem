package com.hotel.hotelmanagementservice.Persistence;

import com.hotel.hotelmanagementservice.Model.Hotel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelRecordRowMapper implements RowMapper<Hotel> {
    @Override
    public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getString("id"));
        hotel.setName(rs.getString("name"));
        hotel.setDescription(rs.getString("description"));
        hotel.setLocation(rs.getString("location"));
        return hotel;
    }
}
