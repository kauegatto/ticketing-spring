package com.kaue.ticketservice.infrastructure.configuration;

import com.kaue.ticketservice.domain.ports.TicketRepository;
import com.kaue.ticketservice.domain.services.EventDispatcher;
import com.kaue.ticketservice.domain.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIConfiguration {
  @Autowired
  private TicketRepository ticketRepository;
  @Autowired
  private EventDispatcher eventDispatcher;
  @Bean
  public TicketService ticketService() {
    return new TicketService(ticketRepository, eventDispatcher);
  }
}
