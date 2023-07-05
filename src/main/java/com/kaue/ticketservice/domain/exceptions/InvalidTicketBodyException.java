package com.kaue.ticketservice.domain.exceptions;

public class InvalidTicketBodyException extends RuntimeException{
    public InvalidTicketBodyException() {
        super("Invalid ticket body sent"); // todo make this better. made as a test / sample.
    }
}
