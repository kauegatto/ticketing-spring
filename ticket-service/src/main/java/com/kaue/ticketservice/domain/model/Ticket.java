package com.kaue.ticketservice.domain.model;
import com.kaue.ticketservice.domain.exceptions.InvalidParameterException;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.State;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@ToString
@AllArgsConstructor
public class Ticket {
    @Getter
    final private String id;
    @Schema(description = "User email")
    @Getter
    final private String requesterEmail;
    @Schema(description = "Assignee / Agent email")
    @Getter
    private String assigneeEmail;
    @Schema(description = "Title of the ticket")
    @Getter
    final private String title;
    @Schema(description = "Ticket description")
    @Getter @Setter
    private String description;
    @Getter @Setter @Schema(description = "Ticket state, follows specific state machine found on docs")
    private State state; // todo rethink about getters and setters here, also shouldn't each ticket have a state machine?
    @Schema(description = "Create date")
    @Getter
    final private Instant createDate;
    @Getter @Setter @LastModifiedDate
    private Instant  updatedDate; // todo remove it
    @Schema(description = "Resolution date")
    @Getter @Setter
    private OffsetDateTime resolutionDate;

    public void setAssignee(String assigneeEmail){
        if (assigneeEmail.isEmpty()){
            throw new InvalidParameterException("assigneeEmail", "Assignee Email must not be empty");
        }
        this.assigneeEmail = assigneeEmail;
        // Send event. Make it in progress
    }
    /**
     * Use factory methods instead of overloaded constructors to avoid @PersistenceCreator.
     * With an all-argument constructor needed for optimal performance, we usually want to expose more application use case specific constructors that omit things like auto-generated identifiers etc
     * It’s an established pattern to rather use static factory methods to expose these variants of the all-args constructor. - Spring Docs
     * @return Domain Specific Constructor.
     */
    public static Ticket createTicket(String requesterEmail, String title, String description){
        return new Ticket(UUID.randomUUID().toString(), requesterEmail,null,title,description, State.NEW,Instant.now(), Instant.now(), null);
    }
}

