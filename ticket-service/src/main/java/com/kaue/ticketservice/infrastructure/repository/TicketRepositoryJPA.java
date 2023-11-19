package com.kaue.ticketservice.infrastructure.repository;

import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.domain.repository.TicketRepository;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TicketRepositoryJPA extends MongoRepository<Ticket, String>, TicketRepository {
}
