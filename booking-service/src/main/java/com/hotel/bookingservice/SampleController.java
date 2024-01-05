package com.hotel.bookingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    private Logger logger =
            LoggerFactory.getLogger(SampleController.class);

    @GetMapping("/hotel-booking/sample-api")
    public String sampleApi() {
        logger.info("Sample api call received");
        return "sample-api";
    }

    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
