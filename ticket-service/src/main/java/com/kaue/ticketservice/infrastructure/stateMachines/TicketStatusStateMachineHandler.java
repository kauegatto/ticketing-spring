package com.kaue.ticketservice.infrastructure.stateMachines;

import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.Event;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.State;
import com.kaue.ticketservice.domain.repository.TicketRepository;
import com.kaue.ticketservice.domain.ports.StateMachineHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TicketStatusStateMachineHandler implements StateMachineHandler<State, Event>{
  private final StateMachineFactory<State, Event> stateMachineFactory;
  private final TicketRepository ticketRepository;
  @Override
  public State sendEvent(Event Event, String ticketId) {
    var sm = stateMachine(ticketId);
    sm.sendEvent(Event);
    return sm.getState().getId();
  }
  private StateMachine<State, Event> stateMachine(String id){
    Optional<Ticket> ticket = ticketRepository.findById(id);
    ticket.orElseThrow(() -> new TicketNotFoundException(id));

    StateMachine<State, Event> sm = stateMachineFactory.getStateMachine(id);
    sm.stop();
    rehydrateSession(ticket, sm);
    sm.start();
    return sm;
  }

  private static void rehydrateSession(Optional<Ticket> ticket, StateMachine<State, Event> sm) {
    sm.getStateMachineAccessor().doWithAllRegions(
            stateEventStateMachineAccess ->
              stateEventStateMachineAccess.resetStateMachine(
                new DefaultStateMachineContext<>(ticket.get().getState(),null, null, null))
    );
  }
}
