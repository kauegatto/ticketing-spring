package com.kaue.ticketservice.application.dto;

import com.kaue.ticketservice.domain.model.state.TicketStatusState.State;

import java.time.Instant;

public record TicketResponseDTO (String id,String title, String description, Instant createDate, String requesterEmail, State state){}
