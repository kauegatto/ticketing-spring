package com.kaue.ticketservice.infrastructure.stateMachines;

import com.kaue.ticketservice.domain.model.state.TicketStatusState.events;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.states;
import com.kaue.ticketservice.domain.services.StateMachineHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketStatusStateMachineHandler implements StateMachineHandler<events,states> {
  private final StateMachine<events, states> stateMachine;
  @Override
  public void sendEvent(states state) {
    stateMachine.sendEvent(state);
  }

  @Override
  public events getState() {
    return stateMachine.getState().getId();
  }
}
