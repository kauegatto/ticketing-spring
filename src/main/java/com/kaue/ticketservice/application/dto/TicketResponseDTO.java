package com.kaue.ticketservice.application.dto;

import lombok.*;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class TicketResponseDTO {
    private String title;
    private String description;
    private Instant createDate;
    private Instant lastModified;
    private String requesterEmail;
}
