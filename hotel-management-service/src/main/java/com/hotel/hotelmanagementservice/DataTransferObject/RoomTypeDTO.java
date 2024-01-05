package com.hotel.hotelmanagementservice.DataTransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.hotelmanagementservice.Model.RoomType;

public class RoomTypeDTO {
    @JsonProperty(value = "Type")
    private String name;
    @JsonProperty(value = "Rooms")
    private int numRooms;
    @JsonProperty(value = "Price")
    private int price;

    public RoomTypeDTO() {
    }

    public RoomTypeDTO(RoomType roomType) {
        this.name = roomType.getName();
        this.numRooms = roomType.getNumRooms();
        this.price = Integer.parseInt(roomType.getPrice());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }
}
