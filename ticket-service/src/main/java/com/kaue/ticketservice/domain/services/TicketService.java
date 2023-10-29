package com.kaue.ticketservice.domain.services;

import com.kaue.ticketservice.domain.events.TicketEventsEnum;
import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.domain.model.state.TicketStatusState;
import com.kaue.ticketservice.domain.ports.TicketRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TicketService {
  private final TicketRepository repository;
  private final Notifier messagePublisher;
  private final StateMachineHandler<TicketStatusState.states, TicketStatusState.events> stateMachine;
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

  public Ticket assignAndUpdate(Ticket ticket, String assigneeEmail){
    ticket.setAssignee(assigneeEmail);
    ticket.setState(stateMachine.getState());
    return repository.save(ticket);
  }
}
