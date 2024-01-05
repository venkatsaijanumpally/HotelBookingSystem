package com.hotel.hotelmanagementservice.DataTransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HotelRequestDTO {
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Room Types")
    private List<RoomTypeDTO> roomTypeList;
    @JsonProperty(value = "Description")
    private String description;
    @JsonProperty(value = "Location")
    private String location;

    public HotelRequestDTO() {
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
