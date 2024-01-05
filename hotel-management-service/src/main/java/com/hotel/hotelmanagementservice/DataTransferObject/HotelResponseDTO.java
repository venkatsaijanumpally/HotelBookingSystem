package com.hotel.hotelmanagementservice.DataTransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.hotelmanagementservice.Model.Hotel;
import com.hotel.hotelmanagementservice.Model.RoomType;

import java.util.ArrayList;
import java.util.List;

public class HotelResponseDTO {
    @JsonProperty(value = "Hotel ID")
    private String id;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Room Types")
    private List<RoomTypeDTO> roomTypeList;
    @JsonProperty(value = "Description")
    private String description;
    @JsonProperty(value = "Location")
    private String location;

    public HotelResponseDTO() {
    }

    public HotelResponseDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.description = hotel.getDescription();
        this.location = hotel.getLocation();
        this.roomTypeList = new ArrayList<>();
        for (RoomType roomType : hotel.getRoomTypeList())
            roomTypeList.add(new RoomTypeDTO(roomType));
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

    public List<RoomTypeDTO> getRoomTypeList() {
        return roomTypeList;
    }

    public void setRoomTypeList(List<RoomTypeDTO> roomTypeList) {
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
