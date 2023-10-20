package com.kaue.emailservice.domain.event;

public enum TicketEventsEnum {
  TICKET_CREATED("TICKET_CREATED"),
  TICKET_SOLVED("TICKET_SOLVED"),
  TICKET_CLOSED("TICKET_CLOSED");

  TicketEventsEnum(String name) {
  }
}
