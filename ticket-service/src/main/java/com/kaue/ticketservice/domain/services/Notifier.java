package com.kaue.ticketservice.domain.services;

import com.kaue.ticketservice.domain.events.TicketEventsEnum;

public interface Notifier{
  void Notify(TicketEventsEnum event);
}
