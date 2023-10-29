package com.kaue.ticketservice.application.controller.advice;

import com.kaue.ticketservice.application.controller.TicketControllerImpl;
import com.kaue.ticketservice.domain.exceptions.InvalidParameterException;
import com.kaue.ticketservice.domain.exceptions.InvalidTicketBodyException;
import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import io.micrometer.core.instrument.config.validate.Validated;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice(assignableTypes = TicketControllerImpl.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TicketControllerAdvice {
    @ExceptionHandler(InvalidTicketBodyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail invalidTicketBodyException(final InvalidTicketBodyException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail InvalidParameterException(final InvalidParameterException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProblemDetail ticketNotFoundException(final TicketNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NO_CONTENT, e.getMessage());
    }
}
