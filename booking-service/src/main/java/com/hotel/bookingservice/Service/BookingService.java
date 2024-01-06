package com.hotel.bookingservice.Service;

import com.hotel.bookingservice.Model.Booking;
import com.hotel.bookingservice.Persistence.DatabaseSetupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookingService {
    @Autowired
    private KafkaTemplate<String, Booking> kafkaTemplate;
    private String hotelTopic = "booking_topic";

    public void createBooking(Booking booking) {
        DatabaseSetupManager.createUserInfoTableIfNotExist();
        kafkaTemplate.send(hotelTopic, booking);
    }
}
