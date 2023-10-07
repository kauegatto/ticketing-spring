package com.kaue.ticketservice.domain.model;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.UUID;

@Document /* Refactor later - Annotating this with entity on Domain Class?*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    @Schema(description = "Unique UUID Identifier")
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
    @CreatedDate
    @Schema(description = "Create date")
    private OffsetDateTime createDate;
    @Getter @Setter @LastModifiedDate
    private OffsetDateTime  updatedDate;
    @Schema(description = "Resolution date")
    private OffsetDateTime resolutionDate;
}

