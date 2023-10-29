package com.kaue.ticketservice.infrastructure.stateMachines;

import com.kaue.ticketservice.domain.model.state.TicketStatusState.events;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.states;
import com.kaue.ticketservice.domain.services.StateMachineHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketStatusStateMachineHandler implements StateMachineHandler<states,events>{
  private final StateMachine<states, events> stateMachine;
  @Override
  public void sendEvent(events events) {
    stateMachine.sendEvent(events);
  }
  @Override
  public states getState() {
    return stateMachine.getState().getId();
  }
}
