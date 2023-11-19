package com.kaue.ticketservice.infrastructure.configuration;

import com.kaue.ticketservice.domain.repository.TicketRepository;
import com.kaue.ticketservice.domain.ports.Notifier;
import com.kaue.ticketservice.domain.services.TicketService;
import com.kaue.ticketservice.infrastructure.amqp.RabbitBasePublisher;
import com.kaue.ticketservice.infrastructure.properties.BrokerConfigurationProperties;
import com.kaue.ticketservice.infrastructure.stateMachines.TicketStatusStateMachineHandler;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class DIConfiguration {
  private TicketRepository ticketRepository;
  private BrokerConfigurationProperties brokerConfigurationProperties;
  private RabbitTemplate rabbitTemplate;
  private TicketStatusStateMachineHandler ticketStatusStateMachine;
  @Bean
  public TicketService ticketService() {
    return new TicketService(ticketRepository, ticketsMessagePublisher(),ticketStatusStateMachine);
  }
  @Bean
  public Notifier ticketsMessagePublisher(){
    return new RabbitBasePublisher(brokerConfigurationProperties, rabbitTemplate, "ticket", "default.ticket");
  }
}
