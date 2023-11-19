package com.kaue.ticketservice.domain.ports;

public interface StateMachineHandler<State, Event> {
  State sendEvent(Event event, String ticketId);
}
