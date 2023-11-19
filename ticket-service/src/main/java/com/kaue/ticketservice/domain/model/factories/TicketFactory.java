package com.kaue.ticketservice.domain.model.factories;

import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.domain.model.state.TicketStatusState;
import com.kaue.ticketservice.domain.ports.EmailValidator;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

/**
 * Provides a factory for creating domain-reasonable tickets
 * <a href="https://docs.spring.io/spring-data/commons/docs/current/reference/html/#mapping.general-recommendations">...</a>
 * Use factory methods instead of overloaded constructors to avoid @PersistenceCreator With an all-argument constructor needed for optimal performance, we usually want to expose more application use case specific constructors that omit things like auto-generated identifiers etc. It’s an established pattern to rather use static factory methods to expose these variants of the all-args constructor.
 */
@Component
public class TicketFactory {
  public static Ticket createTicket(String requesterEmail, String title, String description){
    return new Ticket(UUID.randomUUID().toString(), requesterEmail,null,title,description, TicketStatusState.State.NEW,Instant.now(), Instant.now(), null);
  }
}
