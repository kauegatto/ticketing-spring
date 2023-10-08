package com.kaue.ticketservice.domain.model;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.OffsetDateTime;

@ToString
public class Ticket {
    @Schema(description = "User email")
    @Getter
    final private String requesterEmail;
    @Schema(description = "Assignee / Agent email")
    private Long assigneeEmail;
    @Schema(description = "Title of the ticket")
    @Getter
    final private String title;
    @Schema(description = "Ticket description")
    @Getter
    private String description;
    @CreatedDate
    @Schema(description = "Create date")
    @Getter
    final private Instant createDate;
    @Getter @Setter @LastModifiedDate
    private Instant  updatedDate;
    @Schema(description = "Resolution date")
    private OffsetDateTime resolutionDate;

    public Ticket(String requesterEmail, String title, String description, Instant createDate) {
        this.requesterEmail = requesterEmail;
        this.title = title;
        this.description = description;
        this.createDate = createDate;
    }
}

