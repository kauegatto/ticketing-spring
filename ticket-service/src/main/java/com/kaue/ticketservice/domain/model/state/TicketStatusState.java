package com.kaue.ticketservice.domain.model.state;
public class TicketStatusState {
  public enum states {
    NEW,
    IN_PROGRESS,
    WAITING_REPLY,
    COMPLETED,
    CLOSED
  }
  public enum events {
    START_SOLVING,
    CLOSE,
    COMPLETE,
    NEEDS_REPLY
  }

}
