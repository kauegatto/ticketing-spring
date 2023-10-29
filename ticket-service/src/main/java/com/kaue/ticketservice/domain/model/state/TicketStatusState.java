package com.kaue.ticketservice.domain.model.state;
public class TicketStatusState {
  public enum State {
    NEW,
    IN_PROGRESS,
    WAITING_REPLY,
    COMPLETED,
    CLOSED
  }
  public enum Event {
    START_SOLVING,
    CLOSE,
    COMPLETE,
    NEEDS_REPLY
  }

}
