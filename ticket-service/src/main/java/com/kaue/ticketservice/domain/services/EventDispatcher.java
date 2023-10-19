package com.kaue.ticketservice.domain.services;

import com.kaue.ticketservice.domain.events.TicketEventsEnum;

public interface EventDispatcher {
  void notify(TicketEventsEnum event);
}
