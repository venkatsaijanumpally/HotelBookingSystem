package com.hotel.elastichotelsyncservice.Model;

public class ElasticRoomType {
    private Long id;
    private String name;
    private int numRooms;
    private String price;

    public ElasticRoomType(Long id, String name, int numRooms, String price) {
        this.id = id;
        this.name = name;
        this.numRooms = numRooms;
        this.price = price;
    }

    public ElasticRoomType(){}


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
