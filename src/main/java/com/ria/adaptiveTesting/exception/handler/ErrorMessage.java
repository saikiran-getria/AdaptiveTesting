package com.ria.adaptiveTesting.exception.handler;

import java.time.Instant;

public class ErrorMessage {

    Instant timestamp;
    String message;

    public ErrorMessage(Instant timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public ErrorMessage() {
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
