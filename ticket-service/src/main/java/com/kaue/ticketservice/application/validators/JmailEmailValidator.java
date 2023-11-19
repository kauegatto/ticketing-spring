package com.kaue.ticketservice.application.validators;

import com.kaue.ticketservice.domain.exceptions.InvalidParameterException;
import com.kaue.ticketservice.domain.ports.EmailValidator;
import com.sanctionco.jmail.JMail;

public class JmailEmailValidator implements EmailValidator {
  @Override
  public void enforceValid(String email) {
    if(JMail.isValid(email)){
     throw new InvalidParameterException("Email", "Provided email is invalid");
    }
  }
}
