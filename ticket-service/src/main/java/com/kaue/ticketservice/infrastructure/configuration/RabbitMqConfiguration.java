package com.kaue.ticketservice.infrastructure.configuration;

import com.kaue.ticketservice.infrastructure.properties.TicketQueueProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RabbitMqConfiguration {
  private final TicketQueueProperties ticketQueueProperties;

  @Bean
  public Queue queue(){
    log.info("Looking for queue: {}", ticketQueueProperties.getName());
    return new Queue(ticketQueueProperties.getName(), true);
  }
}
