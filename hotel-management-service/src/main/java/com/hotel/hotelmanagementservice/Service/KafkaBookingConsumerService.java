package com.hotel.hotelmanagementservice.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.hotelmanagementservice.Model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaBookingConsumerService {
    Logger logger = LoggerFactory.getLogger(KafkaBookingConsumerService.class);
    private final ObjectMapper objectMapper;

    public KafkaBookingConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "booking_topic", groupId = "bookings_group",
            containerFactory = "bookingKafkaListenerContainerFactory")
    void listenerWithMessageConverter(Booking booking) {
        logger.info("Received message through MessageConverterUserListener [{}]", booking);
    }

    /*@KafkaListener(topics = "booking_topic", groupId = "bookings_group")
    public void consumeBookingMessage(String bookingJson) {
        try {
            System.out.println("KAFKA BOOKING MESSAGE RECEIVED");
            System.out.println(bookingJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
