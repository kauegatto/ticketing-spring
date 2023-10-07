package com.kaue.ticketservice.application.controller.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class TicketResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private OffsetDateTime createDate;
    private String requesterEmail;
}
