package com.kaue.ticketservice.infrastructure.repository;

import com.kaue.ticketservice.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
