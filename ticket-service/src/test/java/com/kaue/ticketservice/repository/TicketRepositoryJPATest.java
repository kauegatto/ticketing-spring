package com.kaue.ticketservice.repository;

import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.domain.model.factories.TicketFactory;
import com.kaue.ticketservice.infrastructure.repository.TicketRepositoryJPA;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketRepositoryJPATest {
  @Autowired
  private TicketRepositoryJPA ticketRepository;
  @Autowired
  private TicketFactory ticketFactory;

  @Container
  // @ServiceConnection spring 3.1+ - makes DynamicPropertySource unnecessary
  // declared as static to save time in execution, could make field-specific creating & killing containers
  private static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:latest");
  @DynamicPropertySource
  static void mongoDbProperties(DynamicPropertyRegistry registry) {
    MONGO_DB_CONTAINER.start();
    registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
  }

  @Test
  @Order(1)
  void InsertTicket_Success(){
    Ticket t = ticketFactory.createTicket("validemail@gmail.com", "I have a problem", "hellp");
    ticketRepository.insert(t);
  }
  @Test
  @Order(2)
  void FindAll_findsOne(){
    var tickets = ticketRepository.findAll();
    assertEquals(1, tickets.size());
  }
  @Test
  @Order(3)
  void AddAndCheckAgain_findsOne(){
    Ticket t = ticketFactory.createTicket("validEmail2@gmail.com", "I have another problem", "help");
    ticketRepository.insert(t);
    var tickets = ticketRepository.findAll();
    assertEquals(2, tickets.size());
  }
}
