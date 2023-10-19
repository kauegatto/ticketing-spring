package com.kaue.ticketservice.domain.events;

public enum TicketEventsEnum {
  TICKET_CREATED("TICKET_CREATED"),
  TICKET_SOLVED("TICKET_SOLVED"),
  TICKET_CLOSED("TICKET_CLOSED");

  TicketEventsEnum(String ticketCreated) {
  }
}
