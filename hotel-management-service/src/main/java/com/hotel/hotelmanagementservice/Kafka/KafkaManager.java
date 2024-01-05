package com.hotel.hotelmanagementservice.Kafka;

import com.hotel.hotelmanagementservice.Model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
public class KafkaManager {
    /*@Autowired
    private KafkaTemplate<String, Hotel> hotelKafkaTemplate;
    private static final String HOTELS_TOPIC = "HOTELS_TOPIC";

    public void sendHotel(String key, Hotel hotel) {
        hotelKafkaTemplate.send(HOTELS_TOPIC, key, hotel);
    }*/
}
