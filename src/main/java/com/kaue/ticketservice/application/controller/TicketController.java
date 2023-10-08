package com.kaue.ticketservice.application.controller;

import com.kaue.ticketservice.application.dto.TicketCreationDTO;
import com.kaue.ticketservice.application.dto.TicketResponseDTO;
import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Ticket", description = "Ticket API")
@SecurityRequirement(name = "bearer")
public interface TicketController {
    @Operation(summary = "Get all tickets", description = "This can only be done by an authorized user.")
    ResponseEntity<List<TicketResponseDTO>> getAll();
    @Operation(summary = "Get a ticket given it's UUID", description = "This can only be done either by an administrator or the requester.")
    ResponseEntity<TicketResponseDTO> getById(@PathVariable String id)
            throws TicketNotFoundException;
    ResponseEntity<TicketResponseDTO> create(@RequestBody TicketCreationDTO ticket);
}
