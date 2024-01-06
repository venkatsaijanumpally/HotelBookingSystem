package com.hotel.bookingservice;

import com.hotel.bookingservice.DataTransferObject.BookingRequestDTO;
import com.hotel.bookingservice.Model.Booking;
import com.hotel.bookingservice.Service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelBookingController {
    private final Logger logger =
            LoggerFactory.getLogger(HotelBookingController.class);

    private final BookingService bookingService;

    public HotelBookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/hotel-booking/reservation")
    public String performBooking(
            @RequestBody BookingRequestDTO bookingRequest
    ){
        logger.info("Booking call: " + bookingRequest.getHotelId());
        Booking booking = new Booking(bookingRequest);
        bookingService.createBooking(booking);
        return booking.getBookingReferenceId();
    }

    /*private BookingRequestDTO convertBookingRecordToDTO(BookingRecord record) {
        BookingRequestDTO dto = new BookingRequestDTO();
        dto.setUserID(record.getUserId());
        dto.setHotelName(record.getHotel());
        dto.setCheckinDate(record.getCheckinDate());
        dto.setCheckOutDate(record.getCheckoutDate());
        dto.setRoomType(record.getRoomType());
        return dto;
    }*/
    /*private List<BookingRequestDTO> convertBookingRecordsToDTO(List<BookingRecord> records) {
        List<BookingRequestDTO> dtos = new ArrayList<>();

        for (BookingRecord record : records) {
            BookingRequestDTO dto = convertBookingRecordToDTO(record);
            dtos.add(dto);
        }

        return dtos;
    }*/

}
