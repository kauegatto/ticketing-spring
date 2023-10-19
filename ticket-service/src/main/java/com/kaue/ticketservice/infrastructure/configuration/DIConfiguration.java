package com.kaue.ticketservice.infrastructure.configuration;

import com.kaue.ticketservice.domain.ports.TicketRepository;
import com.kaue.ticketservice.domain.services.EventDispatcher;
import com.kaue.ticketservice.domain.services.TicketService;
import com.kaue.ticketservice.infrastructure.amqp.RabbitTicketDispatcher;
import com.kaue.ticketservice.infrastructure.properties.TicketQueueProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIConfiguration {
  @Autowired
  private TicketRepository ticketRepository;
  @Autowired
  private TicketQueueProperties ticketQueueProperties;
  @Autowired
  private RabbitTemplate rabbitTemplate;
  @Bean
  public TicketService ticketService() {
    return new TicketService(ticketRepository, ticketsEventDispatcher());
  }
  @Bean
  public EventDispatcher ticketsEventDispatcher(){
    return new RabbitTicketDispatcher(ticketQueueProperties, rabbitTemplate);
  }
}
