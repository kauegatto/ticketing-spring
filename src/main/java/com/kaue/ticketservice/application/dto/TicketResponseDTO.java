package com.kaue.ticketservice.application.dto;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponseDTO {
    private String id;
    private String title;
    private String description;
    private Instant createDate;
    private String requesterEmail;
}
