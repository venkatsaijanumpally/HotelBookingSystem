package com.hotel.bookingservice.Service;

import com.hotel.bookingservice.Model.BookingRecord;
import com.hotel.bookingservice.Model.HotelDetailsKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import com.hotel.bookingservice.Repository.BookingRecordRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class BookingService {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private KafkaTemplate<String, HotelDetailsKafka> kafkaTemplate;
    private String hotelTopic = "hotel_topic";

    public BookingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BookingRecord> findAll(String hotel) {
        String hotelLowerCase = hotel.toLowerCase().replace(" ", "");
        String tableName = hotelLowerCase + "_booking_details";

        if (hotelDoesNotExist(tableName)) {
            return Collections.emptyList();
        }

        String sql = "SELECT * FROM " + tableName;
        return jdbcTemplate.query(sql, new BookingRecordRowMapper());
    }

    public BookingRecord createBooking(BookingRecord bookingRecord) {
        String hotel = bookingRecord.getHotel();
        String hotelTable = hotel.toLowerCase().replace(" ","") + "_booking_details";
        if(hotelDoesNotExist(hotelTable))
            createHotelTable(hotel);
        String sql = "INSERT INTO " + hotelTable + " (user_id, checkin_date, checkout_date, hotel, room_type) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                bookingRecord.getUserId(),
                bookingRecord.getCheckinDate(),
                bookingRecord.getCheckoutDate(),
                bookingRecord.getHotel(),
                bookingRecord.getRoomType());

        return bookingRecord;
    }

    private void createHotelTable(String hotel) {
        String hotelTable = hotel.toLowerCase().replace(" ","") + "_booking_details";
        System.out.println("Creating table ");
        String sql = "CREATE TABLE " + hotelTable + " (" +
                "id SERIAL PRIMARY KEY, " +
                "user_id VARCHAR(255), " +
                "checkin_date VARCHAR(255), " +
                "checkout_date VARCHAR(255), " +
                "hotel VARCHAR(255), " +
                "room_type VARCHAR(255))";
        jdbcTemplate.execute(sql);

        // Send hotel details to Kafka
        HotelDetailsKafka hotelDetails = new HotelDetailsKafka(hotel);
        kafkaTemplate.send(hotelTopic, hotelDetails);
    }

    private boolean hotelDoesNotExist(String hotel) {
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";
        System.out.println(sql);
        return jdbcTemplate.queryForObject(sql, Integer.class, hotel) == 0;
    }
}
