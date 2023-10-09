package com.kaue.ticketservice.application.dto;

import java.time.Instant;

public record TicketResponseDTO (String title, String description, Instant createDate, String requesterEmail){}
