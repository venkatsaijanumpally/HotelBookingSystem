package com.hotel.bookingservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//@Entity(name = "booking_details")
public class BookingRecordDTO {
    @Id
    private Long id;

    private String user_id;

    private String checkin_date;

    private String checkout_date;

    private String hotel;
    private String room_type;
}
