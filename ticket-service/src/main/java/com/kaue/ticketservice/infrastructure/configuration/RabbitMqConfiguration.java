package com.kaue.ticketservice.infrastructure.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfiguration {
  @Value("${broker.queue.ticket.name}")
  private String ticketQueueName;

  @Bean
  public Queue ticketQueue(){
    return new Queue(ticketQueueName, true);
  }
}
