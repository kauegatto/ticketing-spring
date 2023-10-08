package com.kaue.ticketservice.application.controller;

import com.kaue.ticketservice.application.dto.TicketCreationDTO;
import com.kaue.ticketservice.application.dto.TicketResponseDTO;
import com.kaue.ticketservice.domain.services.TicketService;
import com.kaue.ticketservice.infrastructure.mappers.TicketMapper;
import com.kaue.ticketservice.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketControllerImpl implements TicketController {
    TicketService ticketService;
    TicketMapper ticketMapper;
    UserRepository userRepo;

    @GetMapping("")
    public ResponseEntity<List<TicketResponseDTO>> getAll() {
        return ResponseEntity.ok(ticketMapper.TicketListToTicketResponseDTOList(ticketService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getById(String id) {
        var ticketResponse = ticketMapper.ticketToTicketResponseDTO(ticketService.findById(id));
        return ResponseEntity.ok(ticketResponse);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketResponseDTO> create(TicketCreationDTO ticket) {
        var domainTicket = ticketMapper.ticketCreationDTOToTicket(ticket);
        ticketService.save(domainTicket);
        var ticketResponse = ticketMapper.ticketToTicketResponseDTO(domainTicket);
        return ResponseEntity.ok(ticketResponse);
    }
}
