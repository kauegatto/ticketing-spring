package com.kaue.emailservice.Infrastructure.consumer;

import com.kaue.emailservice.domain.event.TicketEventsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TicketConsumer {
  @RabbitListener(queues = "${broker.queue.ticket.name}")
  public void listenEmailQueue(@Payload TicketEventsEnum event){
    log.info("Received: {}", event.name());
  }
}
