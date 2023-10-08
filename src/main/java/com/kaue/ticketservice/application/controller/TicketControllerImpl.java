package com.kaue.ticketservice.application.controller;

import com.kaue.ticketservice.application.controller.dto.TicketCreationDTO;
import com.kaue.ticketservice.application.controller.dto.TicketResponseDTO;
import com.kaue.ticketservice.domain.exceptions.InvalidTicketBodyException;
import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import com.kaue.ticketservice.domain.model.Ticket;
import com.kaue.ticketservice.infrastructure.mappers.TicketMapper;
import com.kaue.ticketservice.infrastructure.repository.TicketRepository;
import com.kaue.ticketservice.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketControllerImpl implements TicketController {
    private static final Logger log = LoggerFactory.getLogger(TicketControllerImpl.class);
    TicketRepository repo;
    TicketMapper ticketMapper;
    UserRepository userRepo;

    @GetMapping("")
    public ResponseEntity<List<TicketResponseDTO>> FetchAll() {
        var result = repo.findAll();
        if (result.size() == 0 || result == null) {
            throw new TicketNotFoundException("No tickets registered");
        }
        return ResponseEntity.ok(ticketMapper.TicketListToTicketResponseDTOList(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> GetById(String id) {
        return ResponseEntity.ok(repo.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + id)));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketResponseDTO> Create(TicketCreationDTO ticket) {
        var domainTicket = ticketMapper.ticketCreationDTOToTicket(ticket);
        repo.save(domainTicket);
        /* Think on how to create some fields automatically */
        return ResponseEntity.ok(ticketMapper.ticketToTicketResponseDTO(domainTicket));
    }

    @GetMapping("/exception")
    public ResponseEntity<Iterable<Ticket>> getExceptionTest() {
        throw new InvalidTicketBodyException();
    }
}
