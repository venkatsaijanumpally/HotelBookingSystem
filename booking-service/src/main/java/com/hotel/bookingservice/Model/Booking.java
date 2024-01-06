package com.hotel.bookingservice.Model;

import com.hotel.bookingservice.DataTransferObject.BookingRequestDTO;
import com.hotel.bookingservice.Util.BookingReferenceIDGenerator;

public class Booking {
    private String bookingReferenceId;
    private String userId;
    private String hotelId;
    private String checkinDate;
    private String checkoutDate;
    private String roomType;

    public Booking(String userId, String hotelId, String checkinDate, String checkoutDate, String roomType) {
        this.bookingReferenceId = "PNR" + BookingReferenceIDGenerator.getInstance().generateId().toString();
        this.userId = userId;
        this.hotelId = hotelId;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.roomType = roomType;
    }

    public Booking() {
    }

    public Booking(BookingRequestDTO bookingRequest) {
        this.bookingReferenceId = "PNR" + BookingReferenceIDGenerator.getInstance().generateId().toString();
        this.userId = bookingRequest.getUserID();
        this.hotelId = bookingRequest.getHotelId();
        this.checkinDate = bookingRequest.getCheckinDate();
        this.checkoutDate = bookingRequest.getCheckOutDate();
        this.roomType = bookingRequest.getRoomType();
    }

    public String getBookingReferenceId() {
        return bookingReferenceId;
    }

    public void setBookingReferenceId(String bookingReferenceId) {
        this.bookingReferenceId = bookingReferenceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
