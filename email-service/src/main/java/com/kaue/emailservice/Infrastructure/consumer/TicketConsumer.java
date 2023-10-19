package com.kaue.emailservice.Infrastructure.consumer;

import com.kaue.emailservice.domain.event.TicketEventsEnum;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

public class TicketConsumer {
  @RabbitListener(queues = "${broker.queue.ticket.name}")
  public void listenEmailQueue(@Payload TicketEventsEnum event){

  }
}
