package com.kaue.ticketservice.infrastructure.amqp;

import com.kaue.ticketservice.domain.ports.Notifier;
import com.kaue.ticketservice.infrastructure.properties.BrokerConfigurationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class RabbitBasePublisher implements Notifier {
  private final BrokerConfigurationProperties brokerConfigurationProperties;
  private final RabbitTemplate rabbitTemplate;
  private final String exchangeKey;
  private final String routingKey;

  @Override
  public void Notify(Object message) {
    var exchangeName = getExchangeName(exchangeKey);
    if (exchangeName.isPresent()) {
      log.info("Sending message to exchange: {} with routing key of: {}", exchangeName.get(), routingKey);
      rabbitTemplate.convertAndSend(exchangeName.get(), routingKey, message);
    } else {
      log.error("Queue name is null. Unable to notify.");
    }
  }
  private Optional<String> getExchangeName(String exchangeKey) {
    return Optional.ofNullable(brokerConfigurationProperties.getExchanges())
            .map(exchanges -> exchanges.get(exchangeKey))
            .map(BrokerConfigurationProperties.ExchangeProperties::getName);
  }
}
