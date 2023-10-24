package com.kaue.ticketservice.infrastructure.properties;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "broker")
@Data
public class BrokerConfigurationProperties {
  private Map<String, QueueProperties> queues;
  private Map<String, ExchangeProperties> exchanges;
  private Map<String, BindingProperties> bindings;

  @Data
  public static class QueueProperties {
    @NotEmpty
    private String name;
  }

  @Data
  public static class ExchangeProperties {
    @NotEmpty
    private String name;
    private String type;
  }

  @Data
  public static class BindingProperties {
    @NotEmpty
    private String exchange;
    @NotEmpty
    private String queue;
    @NotEmpty
    private String routingKey;
  }
}
