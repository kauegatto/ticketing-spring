package com.kaue.ticketservice.domain.services;

import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.domain.ports.TicketRepository;

import java.time.Instant;
import java.util.List;

public class TicketService {
  TicketRepository repository;

  public TicketService(TicketRepository repository) {
    this.repository = repository;
  }
  public List<Ticket> findAll(){
    return repository.findAll();
  }
  public Ticket findById(String Id){
    return repository.findById(Id).orElseThrow(
            () -> new TicketNotFoundException("Ticket not found")
    );
  }
  public Ticket save(Ticket ticket){
    return repository.save(ticket);
  }
}
