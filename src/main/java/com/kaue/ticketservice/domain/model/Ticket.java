package com.kaue.ticketservice.domain.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Entity /*Annotating this with entity on Domain Class?*/
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Schema(description = "Unique UUID Identifier")
    @UuidGenerator
    @Id
    private UUID id;
    @Schema(description = "User email")
    private String requesterEmail;
    @Schema(description = "Assignee / Agent email")
    private Long assigneeEmail;
    @Schema(description = "Title of the ticket")
    private String title;
    @Schema(description = "Ticket description")
    private String description;
    //private TicketStatus status;
    @Schema(description = "Create date")
    private OffsetDateTime createDate;
    @Schema(description = "Create date")
    private OffsetDateTime updateDate;
    @Schema(description = "Resolution date")
    private OffsetDateTime resolutionDate;
}

