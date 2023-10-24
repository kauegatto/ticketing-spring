package com.kaue.ticketservice.infrastructure.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.kaue.ticketservice.infrastructure.properties.BrokerConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This classes creates all queues, exchanges and bindings based on application.yaml when they're needed (called by a consumer or posted a message into).
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class RabbitMqConfiguration {
  private final BrokerConfigurationProperties brokerConfig;
  private final List<Queue> definedQueues = new ArrayList<>();
  private final List<Exchange> definedExchanges = new ArrayList<>();

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter());
    return rabbitTemplate;
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public Declarables queues() {
    if (brokerConfig == null || brokerConfig.getQueues() == null) {
      return new Declarables(); // Return an empty list if no queues are configured
    }

    var queueList = brokerConfig.getQueues().values().stream()
      .filter(Objects::nonNull)
      .map(queueProperties -> new Queue(queueProperties.getName(), true))
      .toList();

    definedQueues.addAll(queueList);
    log.info("Declared queues");
    return new Declarables(queueList);
  }
  @Bean
  public Declarables exchanges() {
    if (brokerConfig == null || brokerConfig.getExchanges() == null) {
      return new Declarables(); // Return an empty list if no exchanges are configured
    }

    var exchangesList = brokerConfig.getExchanges().values().stream()
      .filter(Objects::nonNull)
      .map(exchangeProperties -> new DirectExchange(exchangeProperties.getName())) // todo use correct exchange type
      .toList();

    definedExchanges.addAll(exchangesList);
    log.info("Declared exchanges");
    return new Declarables(exchangesList);
  }
  @Bean
  public Declarables bindings() {
    if (brokerConfig == null || brokerConfig.getBindings() == null) {
      return new Declarables();
    }

    var bindingsList = brokerConfig.getBindings().values().stream()
        .map(bindingProperties -> {
          log.info("Creating binding between exchange {} and queue {} with routing key {}",
                  bindingProperties.getExchange(), bindingProperties.getQueue(), bindingProperties.getRoutingKey());
          Queue queue = findQueueByName(bindingProperties.getQueue());
          Exchange exchange = findExchangeByName(bindingProperties.getExchange());

          return BindingBuilder.bind(queue)
                  .to(exchange)
                  .with(bindingProperties.getRoutingKey())
                  .noargs();
        })
        .toList();
    return new Declarables(bindingsList);
  }

  private Queue findQueueByName (String queueName){
      return definedQueues.stream()
              .filter(queue -> queueName.equals(queue.getName()))
              .findFirst()
              .orElse(null);
    }

    private Exchange findExchangeByName (String exchangeName){
      return definedExchanges.stream()
              .filter(exchange -> exchangeName.equals(exchange.getName()))
              .findFirst()
              .orElse(null);
    }
  }