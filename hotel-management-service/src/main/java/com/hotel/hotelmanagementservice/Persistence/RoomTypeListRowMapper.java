package com.hotel.hotelmanagementservice.Persistence;

import com.hotel.hotelmanagementservice.Model.RoomType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomTypeListRowMapper implements RowMapper<RoomType> {
    @Override
    public RoomType mapRow(ResultSet rs, int rowNum) throws SQLException {
        RoomType roomType = new RoomType();
        roomType.setId(rs.getLong("id"));
        roomType.setNumRooms(rs.getInt("number_of_rooms"));
        roomType.setPrice(rs.getString("price"));
        roomType.setName(rs.getString("room_type"));
        return roomType;
    }
}
