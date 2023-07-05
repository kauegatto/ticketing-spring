package com.kaue.ticketservice.application.controller;

import com.kaue.ticketservice.domain.exceptions.InvalidTicketBodyException;
import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.infrastructure.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> FetchAll() {
        var result = repo.findAll();
        if (result.size() == 0 || repo.findAll() == null) {
            ResponseEntity.noContent();
            /* Response entity of object is questionable */
        }
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> GetById(@PathVariable UUID id) throws TicketNotFoundException {
        return ResponseEntity.ok(repo.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + id)));
    }

    @PostMapping("")
    public ResponseEntity<Ticket> Create() {
        Ticket ticket = Ticket.builder().
                id(UUID.randomUUID())
                .title("Title")
                .description("I had a problem yesterday")
                .build();
        System.out.println(ticket);
        repo.save(ticket);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/exception")
    public ResponseEntity<Iterable<Ticket>> getExceptionTest() throws InvalidTicketBodyException {
        throw new InvalidTicketBodyException();
    }
}
