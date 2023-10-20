package com.kaue.ticketservice.infrastructure.configuration;

import com.kaue.ticketservice.infrastructure.properties.TicketQueueProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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
  @Bean
  public Exchange ticketDirectExchange(){
    final String EXCHANGE_NAME = "direct.ticket";
    log.info("Creating exchange: direct.ticket");
    return new DirectExchange(EXCHANGE_NAME);
  }
  @Bean
  public Binding ticketBinding(){
    log.info("Create ticket binding");
    return BindingBuilder.bind(queue()).to(ticketDirectExchange()).with(ticketQueueProperties.getName()).noargs();
  }
  @Bean
  RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter());
    return rabbitTemplate;
  }
  @Bean
  MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
