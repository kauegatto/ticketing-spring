package com.kaue.ticketservice.domain.services;

public interface StateMachineHandler<State, Event> {
  void sendEvent(Event event);
  State getState();
}
