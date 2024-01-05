package com.hotel.hotelmanagementservice.Exception;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Define a custom ErrorResponse class
public class ErrorResponse {
    private final Timestamp timestamp;
    private final int status;
    private final String error;
    private final String message;

    public ErrorResponse(int status, String message, Timestamp timestamp) {
        this.status = status;
        this.error = HttpStatus.valueOf(status).getReasonPhrase();
        this.message = message;
        this.timestamp = timestamp;
    }

    // Add getters for timestamp, status, error, and message

    public String getTimestamp() {
        return String.valueOf(timestamp);
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
