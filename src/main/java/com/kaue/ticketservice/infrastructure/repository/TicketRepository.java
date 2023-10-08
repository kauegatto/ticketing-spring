package com.kaue.ticketservice.infrastructure.repository;

import com.kaue.ticketservice.domain.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TicketRepository extends MongoRepository<Ticket, String> {
}
