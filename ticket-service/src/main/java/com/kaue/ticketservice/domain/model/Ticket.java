package com.kaue.ticketservice.domain.model;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.Instant;
import java.time.OffsetDateTime;

@ToString
public class Ticket {
    @Schema(description = "User email")
    @Getter
    final private String requesterEmail;
    @Schema(description = "Assignee / Agent email")
    @Getter @Setter
    private String assigneeEmail;
    @Schema(description = "Title of the ticket")
    @Getter
    final private String title;
    @Schema(description = "Ticket description")
    @Getter @Setter
    private String description;
    @Schema(description = "Create date")
    @Getter
    final private Instant createDate;
    @Getter @Setter @LastModifiedDate
    private Instant  updatedDate;
    @Schema(description = "Resolution date")
    @Getter @Setter
    private OffsetDateTime resolutionDate;

    public Ticket(String requesterEmail, String assigneeEmail, String title, String description, Instant createDate, Instant updatedDate, OffsetDateTime resolutionDate) {
        this.requesterEmail = requesterEmail;
        this.assigneeEmail = assigneeEmail;
        this.title = title;
        this.description = description;
        this.createDate = createDate;
        this.updatedDate = updatedDate;
        this.resolutionDate = resolutionDate;
    }
}

