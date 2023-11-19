package com.kaue.ticketservice.infrastructure.mappers;

import com.kaue.ticketservice.application.dto.TicketCreationDTO;
import com.kaue.ticketservice.application.dto.TicketResponseDTO;
import com.kaue.ticketservice.domain.model.Ticket;

import com.kaue.ticketservice.domain.model.factories.TicketFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = TicketFactory.class)
@NoArgsConstructor
@AllArgsConstructor
public abstract class TicketMapper {
    private TicketFactory ticketFactory;
    public abstract TicketResponseDTO ticketToTicketResponseDTO(Ticket ticket);
    public abstract List<TicketResponseDTO> TicketListToTicketResponseDTOList(List<Ticket> tickets);
    public Ticket ticketCreationDTOToTicket(TicketCreationDTO ticketCreationDTO){
        return ticketFactory.createTicket(ticketCreationDTO.requesterEmail(),ticketCreationDTO.title(), ticketCreationDTO.description());
    }
}

