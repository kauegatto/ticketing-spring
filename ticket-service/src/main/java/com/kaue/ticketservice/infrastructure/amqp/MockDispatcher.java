package com.kaue.ticketservice.infrastructure.amqp;

import com.kaue.ticketservice.domain.events.TicketEventsEnum;
import com.kaue.ticketservice.domain.services.EventDispatcher;
import org.springframework.stereotype.Component;

@Component
public class MockDispatcher implements EventDispatcher {
  @Override
  public void notify(TicketEventsEnum event) {
  }
}
