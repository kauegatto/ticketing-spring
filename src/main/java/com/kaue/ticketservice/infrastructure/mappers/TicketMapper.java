package com.kaue.ticketservice.infrastructure.mappers;

import com.kaue.ticketservice.application.controller.dto.TicketCreationDTO;
import com.kaue.ticketservice.application.controller.dto.TicketResponseDTO;
import com.kaue.ticketservice.domain.model.Ticket;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketMapper {
    Ticket ticketCreationDTOToTicket(TicketCreationDTO ticketCreationDTO);
    TicketResponseDTO ticketToTicketResponseDTO(Ticket ticket);
}

