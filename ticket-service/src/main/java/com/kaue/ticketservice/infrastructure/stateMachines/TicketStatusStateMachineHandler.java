package com.kaue.ticketservice.infrastructure.stateMachines;

import com.kaue.ticketservice.domain.model.state.TicketStatusState.Event;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.State;
import com.kaue.ticketservice.domain.services.StateMachineHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketStatusStateMachineHandler implements StateMachineHandler<State, Event>{
  private final StateMachine<State, Event> stateMachine;
  @Override
  public void sendEvent(Event Event) {
    stateMachine.sendEvent(Event);
  }
  @Override
  public State getState() {
    return stateMachine.getState().getId();
  }
}
