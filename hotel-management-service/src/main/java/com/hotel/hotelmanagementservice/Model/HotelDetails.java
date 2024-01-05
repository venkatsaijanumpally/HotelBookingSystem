package com.hotel.hotelmanagementservice.Model;

import jakarta.persistence.GeneratedValue;


public class HotelDetails {
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String location;

    public HotelDetails(Hotel hotel){
        this.name = hotel.getName();
        this.description = hotel.getDescription();
        this.location = hotel.getLocation();
    }

    public HotelDetails(){}

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
