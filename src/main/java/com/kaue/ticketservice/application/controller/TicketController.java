package com.kaue.ticketservice.application.controller;

import com.kaue.ticketservice.application.controller.dto.TicketCreationDTO;
import com.kaue.ticketservice.application.controller.dto.TicketResponseDTO;
import com.kaue.ticketservice.domain.exceptions.InvalidTicketBodyException;
import com.kaue.ticketservice.domain.exceptions.TicketNotFoundException;
import com.kaue.ticketservice.domain.model.Ticket;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "Ticket", description = "Ticket API")
@SecurityRequirement(name = "bearer")
public interface TicketController {
    @Operation(summary = "Get all tickets", description = "This can only be done by an authorized user.")
    public ResponseEntity<List<TicketResponseDTO>> FetchAll();
    @Operation(summary = "Get a ticket given it's UUID", description = "This can only be done either by an administrator or the requester.")
    public ResponseEntity<Object> GetById(@PathVariable String id)
            throws TicketNotFoundException;
    public ResponseEntity<TicketResponseDTO> Create(@RequestBody TicketCreationDTO ticket);
    public ResponseEntity<Iterable<Ticket>> getExceptionTest() throws InvalidTicketBodyException;
}
