package com.kaue.ticketservice.domain.services;

import com.kaue.ticketservice.domain.events.TicketEventsEnum;
import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.domain.ports.TicketRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TicketService {
  private final TicketRepository repository;
  private final EventDispatcher eventDispatcher;
  public List<Ticket> findAll(){
    return repository.findAll();
  }
  public Ticket findById(String Id){
    return repository.findById(Id).orElseThrow(
            () -> new TicketNotFoundException("Ticket not found")
    );
  }
  public Ticket save(Ticket ticket){
    eventDispatcher.notify(TicketEventsEnum.TICKET_CREATED);
    return repository.save(ticket);
  }
}
