package com.kaue.ticketservice.domain.services;

public interface StateMachineHandler<State, Event> {
  State sendEvent(Event event, String ticketId);
}
