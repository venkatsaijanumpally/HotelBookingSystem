package com.hotel.bookingservice.DataTransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.bookingservice.Util.ConstantValues;

public class BookingRequestDTO implements RequestDTO{
    @JsonProperty(value = ConstantValues.HOTEL_BOOKING_USERID)
    private String userID;
    @JsonProperty(value = ConstantValues.HOTEL_BOOKING_HOTELNAME)
    private String hotelName;
    @JsonProperty(value = ConstantValues.HOTEL_BOOKING_CHECKIN_DATE)
    private String checkinDate;
    @JsonProperty(value = ConstantValues.HOTEL_BOOKING_CHECKOUT_DATE)
    private String checkOutDate;
    @JsonProperty(value = ConstantValues.HOTEL_BOOKING_ROOM_TYPE)
    private String roomType;

    public BookingRequestDTO() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "BookingRequestDTO{" +
                "UserID='" + userID + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", checkinDate='" + checkinDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
