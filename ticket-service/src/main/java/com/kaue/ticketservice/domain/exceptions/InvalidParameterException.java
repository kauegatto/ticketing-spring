package com.kaue.ticketservice.domain.exceptions;

public class InvalidParameterException extends RuntimeException{
  public InvalidParameterException(String parameter, String reason) {
    super("Invalid parameter: "+parameter+ " - " + reason);
  }
}
