package com.hotel.hotelmanagementservice.Model;

public class Booking {
    private String bookingReferenceId;
    private String userId;
    private String hotelId;
    private String checkinDate;
    private String checkoutDate;
    private String roomType;
    private BookingStatus status = BookingStatus.PENDING;

    public Booking(String bookingReferenceId, String userId, String hotelId, String checkinDate, String checkoutDate, String roomType) {
        this.bookingReferenceId = bookingReferenceId;
        this.userId = userId;
        this.hotelId = hotelId;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.roomType = roomType;
    }

    public Booking() {
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Booking{" +
                "bookingReferenceId='" + bookingReferenceId + '\'' +
                ", userId='" + userId + '\'' +
                ", hotelId='" + hotelId + '\'' +
                ", checkinDate='" + checkinDate + '\'' +
                ", checkoutDate='" + checkoutDate + '\'' +
                ", roomType='" + roomType + '\'' +
                ", status=" + status +
                '}';
    }
}
