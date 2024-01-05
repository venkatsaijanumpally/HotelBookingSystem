package com.hotel.bookingservice;

import com.hotel.bookingservice.DataTransferObject.BookingRequestDTO;
import com.hotel.bookingservice.Model.BookingRecord;
import com.hotel.bookingservice.Repository.BookingRecordRepository;
import com.hotel.bookingservice.Service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelBookingController {
    private final Logger logger =
            LoggerFactory.getLogger(HotelBookingController.class);

    private final BookingService bookingService;
    private final JdbcTemplate jdbcTemplate;

    public HotelBookingController(BookingService bookingService, JdbcTemplate jdbcTemplate) {
        this.bookingService = bookingService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/hotel-booking/reservation/{hotelName}")
    public BookingRequestDTO performBooking(
            @PathVariable String hotelName,
            @RequestBody BookingRequestDTO bookingRequest
    ){
        logger.info("Booking call " + bookingRequest.getHotelName());
        BookingRecord bookingRecord = new BookingRecord(bookingRequest);
        BookingRecord savedRecord = bookingService.createBooking(bookingRecord);
        return convertBookingRecordToDTO(savedRecord);
    }

    @GetMapping("/hotel-booking/reservations/{hotel}")
    public List<BookingRequestDTO> retrieveAllBookings(
            @PathVariable String hotel
    ){
        List<BookingRecord> records = bookingService.findAll(hotel);
        return convertBookingRecordsToDTO(records);
    }

    private BookingRequestDTO convertBookingRecordToDTO(BookingRecord record) {
        BookingRequestDTO dto = new BookingRequestDTO();
        dto.setUserID(record.getUserId());
        dto.setHotelName(record.getHotel());
        dto.setCheckinDate(record.getCheckinDate());
        dto.setCheckOutDate(record.getCheckoutDate());
        dto.setRoomType(record.getRoomType());
        return dto;
    }
    private List<BookingRequestDTO> convertBookingRecordsToDTO(List<BookingRecord> records) {
        List<BookingRequestDTO> dtos = new ArrayList<>();

        for (BookingRecord record : records) {
            BookingRequestDTO dto = convertBookingRecordToDTO(record);
            dtos.add(dto);
        }

        return dtos;
    }

}
