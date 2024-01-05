package com.hotel.hotelmanagementservice.Model;

import com.hotel.hotelmanagementservice.DataTransferObject.HotelRequestDTO;
import com.hotel.hotelmanagementservice.DataTransferObject.RoomTypeDTO;
import com.hotel.hotelmanagementservice.Impl.SnowflakeIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String id;
    private String name;
    private List<RoomType> roomTypeList;
    private String description;
    private String location;

    public Hotel(HotelRequestDTO hotelRequestDTO) {
        id = "H" + SnowflakeIdGenerator.getInstance().generateId();
        name = hotelRequestDTO.getName();
        description = hotelRequestDTO.getDescription();
        roomTypeList = new ArrayList<>();
        for (RoomTypeDTO dto : hotelRequestDTO.getRoomTypeList()) {
            roomTypeList.add(new RoomType(dto));
        }
        location = hotelRequestDTO.getLocation();
    }

    public Hotel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoomType> getRoomTypeList() {
        return roomTypeList;
    }

    public void setRoomTypeList(List<RoomType> roomTypeList) {
        this.roomTypeList = roomTypeList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
