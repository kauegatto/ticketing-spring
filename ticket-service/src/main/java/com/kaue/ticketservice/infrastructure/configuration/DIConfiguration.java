package com.kaue.ticketservice.infrastructure.configuration;

import com.kaue.ticketservice.domain.model.state.TicketStatusState;
import com.kaue.ticketservice.domain.ports.TicketRepository;
import com.kaue.ticketservice.domain.services.Notifier;
import com.kaue.ticketservice.domain.services.TicketService;
import com.kaue.ticketservice.infrastructure.amqp.RabbitBasePublisher;
import com.kaue.ticketservice.infrastructure.properties.BrokerConfigurationProperties;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
@AllArgsConstructor
public class DIConfiguration {
  private TicketRepository ticketRepository;
  private BrokerConfigurationProperties brokerConfigurationProperties;
  private RabbitTemplate rabbitTemplate;
  private StateMachine<TicketStatusState.states, TicketStatusState.events> ticketStatusStateMachine;
  @Bean
  public TicketService ticketService() {
    return new TicketService(ticketRepository, ticketsMessagePublisher(),ticketStatusStateMachine);
  }
  @Bean
  public Notifier ticketsMessagePublisher(){
    return new RabbitBasePublisher(brokerConfigurationProperties, rabbitTemplate, "ticket", "default.ticket");
  }
}
