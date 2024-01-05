package com.hotel.bookingservice.Model;

public class HotelDetailsKafka {
    private String name;
    private int standard_suites;
    private int deluxe_suites;
    private int executive_suites;

    public HotelDetailsKafka() {
    }

    public HotelDetailsKafka(String name, int standard_suites, int deluxe_suites, int executive_suites) {
        this.name = name;
        this.standard_suites = standard_suites;
        this.deluxe_suites = deluxe_suites;
        this.executive_suites = executive_suites;
    }

    public HotelDetailsKafka(String hotel) {
        this.name = hotel;
        this.standard_suites = 10;
        this.deluxe_suites = 8;
        this.executive_suites = 5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStandard_suites() {
        return standard_suites;
    }

    public void setStandard_suites(int standard_suites) {
        this.standard_suites = standard_suites;
    }

    public int getDeluxe_suites() {
        return deluxe_suites;
    }

    public void setDeluxe_suites(int deluxe_suites) {
        this.deluxe_suites = deluxe_suites;
    }

    public int getExecutive_suites() {
        return executive_suites;
    }

    public void setExecutive_suites(int executive_suites) {
        this.executive_suites = executive_suites;
    }

    @Override
    public String toString() {
        return "HotelDetailsKafka{" +
                "name='" + name + '\'' +
                ", standard_suites=" + standard_suites +
                ", deluxe_suites=" + deluxe_suites +
                ", executive_suites=" + executive_suites +
                '}';
    }
}
