package com.kaue.ticketservice.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("broker.queue.ticket")
@Component
@Getter
@Setter
public class TicketQueueProperties {
  private String name;
}
