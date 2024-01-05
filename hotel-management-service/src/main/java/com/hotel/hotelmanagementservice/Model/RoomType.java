package com.hotel.hotelmanagementservice.Model;

import com.hotel.hotelmanagementservice.DataTransferObject.RoomTypeDTO;
import jakarta.persistence.GeneratedValue;

public class RoomType {
    @GeneratedValue
    private Long id;
    private String name;
    private int numRooms;
    private String price;

    public RoomType(Long id, String name, int numRooms, String price) {
        this.id = id;
        this.name = name;
        this.numRooms = numRooms;
        this.price = price;
    }

    public RoomType(RoomTypeDTO roomTypeDTO) {
        this.name = roomTypeDTO.getName();
        this.numRooms = roomTypeDTO.getNumRooms();
        this.price = String.valueOf(roomTypeDTO.getPrice());
    }

    public RoomType(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
