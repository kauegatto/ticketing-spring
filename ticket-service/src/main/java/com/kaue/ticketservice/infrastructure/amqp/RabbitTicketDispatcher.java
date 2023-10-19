package com.kaue.ticketservice.infrastructure.amqp;

import com.kaue.ticketservice.domain.events.TicketEventsEnum;
import com.kaue.ticketservice.domain.services.EventDispatcher;
import com.kaue.ticketservice.infrastructure.properties.TicketQueueProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
@AllArgsConstructor
public class RabbitTicketDispatcher implements EventDispatcher {
  private final TicketQueueProperties ticketQueueProperties;
  private final RabbitTemplate rabbitTemplate;

  @Override
  public void notify(TicketEventsEnum event) {
    log.info("Notifying queue: {} of event {}", ticketQueueProperties.getName(), event);
    rabbitTemplate.send(ticketQueueProperties.getName(), new Message(event.name().getBytes()));
  }
}
