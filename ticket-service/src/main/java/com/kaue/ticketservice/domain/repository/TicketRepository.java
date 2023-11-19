package com.kaue.ticketservice.domain.repository;

import com.kaue.ticketservice.domain.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
  Ticket save(Ticket ticket);
  Optional<Ticket> findById(String id);
  List<Ticket> findAll();
}
