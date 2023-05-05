package com.kaue.ticketservice.infrastructure.repository;

import com.kaue.ticketservice.domain.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository extends CrudRepository<Ticket, UUID> {
}
