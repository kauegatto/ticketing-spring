package com.kaue.ticketservice.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@AllArgsConstructor
@Setter
@Getter
public class TicketCreationDTO {
    @Schema(description = "Title of the ticket")
    private String title;
    @Schema(description = "Ticket description")
    private String description;
    private String requesterEmail;
}
