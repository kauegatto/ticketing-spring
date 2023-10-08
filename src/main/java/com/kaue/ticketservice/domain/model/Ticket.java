package com.kaue.ticketservice.domain.model;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.OffsetDateTime;


@Document /* Refactor later - Annotating this with entity on Domain Class?*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    @Schema(description = "User email")
    private String requesterEmail;
    @Schema(description = "Assignee / Agent email")
    private Long assigneeEmail;
    @Schema(description = "Title of the ticket")
    private String title;
    @Schema(description = "Ticket description")
    private String description;
    @CreatedDate
    @Schema(description = "Create date")
    private Instant createDate;
    @Getter @Setter @LastModifiedDate
    private Instant  updatedDate;
    @Schema(description = "Resolution date")
    private OffsetDateTime resolutionDate;
}

