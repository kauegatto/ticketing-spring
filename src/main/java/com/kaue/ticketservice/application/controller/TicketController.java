package com.kaue.ticketservice.application.controller;

import com.kaue.ticketservice.application.controller.dto.TicketCreationDTO;
import com.kaue.ticketservice.application.controller.dto.TicketResponseDTO;
import com.kaue.ticketservice.domain.exceptions.InvalidTicketBodyException;
import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.infrastructure.mappers.TicketMapper;
import com.kaue.ticketservice.infrastructure.repository.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketController {
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);
    TicketRepository repo;
    TicketMapper ticketMapper;

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

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketResponseDTO> Create(@RequestBody TicketCreationDTO ticket) {
        var domainTicket = ticketMapper.ticketCreationDTOToTicket(ticket);
        repo.save(domainTicket);
        /* Think on how to create some fields automatically */
        return ResponseEntity.ok(ticketMapper.ticketToTicketResponseDTO(domainTicket));
    }

    @GetMapping("/exception")
    public ResponseEntity<Iterable<Ticket>> getExceptionTest() throws InvalidTicketBodyException {
        throw new InvalidTicketBodyException();
    }
}
