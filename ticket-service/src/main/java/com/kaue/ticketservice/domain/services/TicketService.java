package com.kaue.ticketservice.domain.services;

import com.kaue.ticketservice.domain.events.TicketEventsEnum;
import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.Event;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.State;
import com.kaue.ticketservice.domain.ports.TicketRepository;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.kaue.ticketservice.domain.model.state.TicketStatusState.Event.START_SOLVING;

@AllArgsConstructor
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
    return repository.save(ticket);
  }

  public Ticket assignAndUpdate(String id, String assigneeEmail){
    var ticket = findById(id);
    ticket.setAssignee(assigneeEmail);
    stateMachine.sendEvent(START_SOLVING);
    ticket.setState(stateMachine.getState());
    return repository.save(ticket);
  }
}
