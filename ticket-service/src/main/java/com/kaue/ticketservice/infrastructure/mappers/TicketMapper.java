package com.kaue.ticketservice.infrastructure.mappers;

import com.kaue.ticketservice.application.dto.TicketCreationDTO;
import com.kaue.ticketservice.application.dto.TicketResponseDTO;
import com.kaue.ticketservice.domain.model.Ticket;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketMapper {
    TicketResponseDTO ticketToTicketResponseDTO(Ticket ticket);
    List<TicketResponseDTO> TicketListToTicketResponseDTOList(List<Ticket> tickets);
    default Ticket ticketCreationDTOToTicket(TicketCreationDTO ticketCreationDTO){
        return Ticket.createTicket(ticketCreationDTO.requesterEmail(),ticketCreationDTO.title(), ticketCreationDTO.description());
    }
}

