package com.hotel.bookingservice.Model;

import com.hotel.bookingservice.DataTransferObject.BookingRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//@Entity(name = "booking_details")
public class BookingRecord {

    //@Id
    //@GeneratedValue
    private Long id = 1L;

    //@Column(name = "user_id")
    private String userId;
    //@Column(name = "checkin_date")
    private String checkinDate;
    //@Column(name = "checkout_date")
    private String checkoutDate;
    //@Column(name = "hotel")
    private String hotel;
    //@Column(name = "room_type")
    private String roomType;

    public BookingRecord() {
    }

    public BookingRecord(BookingRequestDTO bookingRequestDTO) {
        this.userId=bookingRequestDTO.getUserID();
        this.hotel= bookingRequestDTO.getHotelName();
        this.checkinDate=bookingRequestDTO.getCheckinDate();
        this.checkoutDate= bookingRequestDTO.getCheckOutDate();
        this.roomType = bookingRequestDTO.getRoomType();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId(long id){
        return id;
    }
}
