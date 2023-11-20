package com.kaue.ticketservice.integration;

import com.kaue.ticketservice.domain.model.factories.TicketFactory;
import com.kaue.ticketservice.domain.repository.TicketRepository;
import com.kaue.ticketservice.domain.services.TicketService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.kaue.ticketservice.domain.model.state.TicketStatusState.State.IN_PROGRESS;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketIntegrationTest {
  @Autowired
  private TicketService ticketService;
  @Autowired
  private TicketRepository ticketRepository;
  @Autowired
  private TicketFactory ticketFactory;

  @Container
  private static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:latest");
  @Container
  private static final RabbitMQContainer RABBIT_MQ_CONTAINER = new RabbitMQContainer("rabbitmq:management");
  @DynamicPropertySource
  static void mongoDbProperties(DynamicPropertyRegistry registry) {
    MONGO_DB_CONTAINER.start();
    registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
    registry.add("spring.rabbitmq.addresses",() -> "amqp://guest:guest@localhost:"+RABBIT_MQ_CONTAINER.getAmqpPort());
  }
  @Test
  @DisplayName("[INTEGRATION] Create a ticket and make sure it was inserted.")
  @Order(1)
  void integrateTicketCreation(){
    var ticket = ticketFactory.createTicket("email@valid.com", "title","I need support");
    ticketService.save(ticket);
    Assertions.assertEquals(1,ticketService.findAll().size());
  }
  @Test
  @DisplayName("[INTEGRATION] Get ticket created previously and make it in progress.")
  @Order(2)
  void checkTicketAssignAndProgressChange(){
    var id = ticketService.findAll().get(0).getId();
    ticketService.assignAndUpdate(id,"Assignee@email.com");
    Assertions.assertEquals(IN_PROGRESS,ticketService.findById(id).getState());
  }
}
