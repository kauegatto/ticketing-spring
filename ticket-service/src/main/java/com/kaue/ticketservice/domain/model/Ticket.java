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

    /**
     * Use factory methods instead of overloaded constructors to avoid @PersistenceCreator.
     * With an all-argument constructor needed for optimal performance, we usually want to expose more application use case specific constructors that omit things like auto-generated identifiers etc
     * It’s an established pattern to rather use static factory methods to expose these variants of the all-args constructor. - Spring Docs
     * @return Domain Specific Constructor.
     */
    public static Ticket createTicket(String requesterEmail, String title, String description){
        return new Ticket(requesterEmail,null,title,description,Instant.now(), Instant.now(), null);
    }
}

