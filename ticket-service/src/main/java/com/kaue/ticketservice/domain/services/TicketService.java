package com.kaue.ticketservice.domain.services;

import com.kaue.ticketservice.domain.events.TicketEventsEnum;
import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.Event;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.State;
import com.kaue.ticketservice.domain.ports.Notifier;
import com.kaue.ticketservice.domain.ports.StateMachineHandler;
import com.kaue.ticketservice.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.kaue.ticketservice.domain.model.state.TicketStatusState.Event.START_SOLVING;

@AllArgsConstructor
@Slf4j
public class TicketService {
  private final TicketRepository repository;
  private final Notifier messagePublisher;
  private final StateMachineHandler<State, Event> stateMachine;
  public List<Ticket> findAll(){
    return repository.findAll();
  }
  public Ticket findById(String Id){
    return repository.findById(Id).orElseThrow(
            () -> new TicketNotFoundException("Ticket not found")
    );
  }
  public Ticket save(Ticket ticket){
    messagePublisher.Notify(TicketEventsEnum.TICKET_CREATED);
    log.info("Saving ticket of id {} in database", ticket.getId());
    return repository.save(ticket);
  }

  public Ticket assignAndUpdate(String id, String assigneeEmail){
    var ticket = findById(id);
    ticket.setAssignee(assigneeEmail);
    ticket.setState(stateMachine.sendEvent(START_SOLVING, id));
    return repository.save(ticket);
  }
}
