package com.kaue.emailservice.Infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitMqConfiguration {
  @Value("${broker.queue.ticket.name}")
  private String ticketQueueName;

  @Bean
  public Queue queue(){
    log.info("Looking for queue: {}", ticketQueueName);
    return new Queue(ticketQueueName, true);
  }
}