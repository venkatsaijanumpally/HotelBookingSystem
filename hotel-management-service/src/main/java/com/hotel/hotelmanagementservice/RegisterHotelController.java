package com.hotel.hotelmanagementservice;

import com.hotel.hotelmanagementservice.DataTransferObject.HotelRequestDTO;
import com.hotel.hotelmanagementservice.DataTransferObject.HotelResponseDTO;
import com.hotel.hotelmanagementservice.Model.Hotel;
import com.hotel.hotelmanagementservice.Service.RegisterHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterHotelController {
    private RegisterHotelService registerHotelService;

    public RegisterHotelController(RegisterHotelService registerHotelService) {
        this.registerHotelService = registerHotelService;
    }

    @PostMapping(value = "/hotel-management/register/hotel")
    public HotelResponseDTO registerHotel(
            @RequestBody HotelRequestDTO registerRequest
    ) {
        Hotel hotel = new Hotel(registerRequest);
        registerHotelService.saveHotel(hotel);
        return new HotelResponseDTO(hotel);
    }

    @GetMapping(value = "/hotel-management/register/hotel/{hotelId}")
    public HotelResponseDTO getHotel(@PathVariable String hotelId) {
        Hotel hotel = registerHotelService.getHotel(hotelId);
        return new HotelResponseDTO(hotel);
    }
}
