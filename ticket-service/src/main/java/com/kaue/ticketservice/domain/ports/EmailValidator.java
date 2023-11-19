package com.kaue.ticketservice.domain.ports;

public interface EmailValidator {
  void enforceValid(String email);
}
