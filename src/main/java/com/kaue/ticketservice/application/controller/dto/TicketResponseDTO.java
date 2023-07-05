package com.kaue.ticketservice.application.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;

import java.time.OffsetDateTime;
import java.util.UUID;

public class TicketResponseDTO {
    @Schema(description = "Unique UUID Identifier")
    @Id
    private UUID id;
    @Schema(description = "Title of the ticket")
    private String title;
    @Schema(description = "Ticket description")
    private String description;
    //private TicketStatus status;
    @Schema(description = "Create date")
    private OffsetDateTime createDate;
    @Schema(description = "Create date")
    private OffsetDateTime updateDate;
}
