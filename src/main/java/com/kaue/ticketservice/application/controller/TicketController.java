package com.kaue.ticketservice.application.controller;

import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.infrastructure.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    public TicketController(TicketRepository repo) {
        this.repo = repo;
    }
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);
    TicketRepository repo;

    @GetMapping("")
    public String HelloWorld (){
        log.info("inserting tickets " + this.repo.save(Ticket.builder().id(UUID.randomUUID()).build()));
        return "Hello, world";
    }
    @PostMapping("")
    public ResponseEntity<Ticket> Create(){
        Ticket ticket = Ticket.builder().
                id(UUID.randomUUID())
                .title("Title")
                .description("I had a problem yesterday")
                .build();
        System.out.println(ticket);
        repo.save(ticket);
        return ResponseEntity.ok(ticket);
    }
    @GetMapping("/all")
    public ResponseEntity<Iterable<Ticket>> getAll(){
        return ResponseEntity.ok(repo.findAll());
    }
}
