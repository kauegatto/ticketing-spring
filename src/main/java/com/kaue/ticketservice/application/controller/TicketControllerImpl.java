package com.kaue.ticketservice.application.controller;

import com.kaue.ticketservice.application.dto.TicketCreationDTO;
import com.kaue.ticketservice.application.dto.TicketResponseDTO;
import com.kaue.ticketservice.domain.services.TicketService;
import com.kaue.ticketservice.infrastructure.mappers.TicketMapper;
import com.kaue.ticketservice.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
@Slf4j
public class TicketControllerImpl implements TicketController {
    TicketService ticketService;
    TicketMapper ticketMapper;
    UserRepository userRepo;

    @GetMapping("")
    public ResponseEntity<List<TicketResponseDTO>> getAll() {
        log.info("Starting search: GET All tickets");
        var ticketResponse = ticketMapper.TicketListToTicketResponseDTOList(ticketService.findAll());
        log.info("found {} tickets", ticketResponse.size());
        return ResponseEntity.ok(ticketResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getById(String id) {
        log.info("Starting search: GET ticket by id: {}", id);
        var ticketResponse = ticketMapper.ticketToTicketResponseDTO(ticketService.findById(id));
        log.info("Found: {}", ticketResponse);
        return ResponseEntity.ok(ticketResponse);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketResponseDTO> create(TicketCreationDTO ticket) {
        log.info("Received insert for ticket {}", ticket);
        var domainTicket = ticketMapper.ticketCreationDTOToTicket(ticket);
        ticketService.save(domainTicket);
        var ticketResponse = ticketMapper.ticketToTicketResponseDTO(domainTicket);
        return ResponseEntity.ok(ticketResponse);
    }
}
