package com.kaue.ticketservice.domain.model;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.State;
import com.kaue.ticketservice.domain.ports.EmailValidator;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.Instant;
import java.time.OffsetDateTime;

@Getter
@ToString
@AllArgsConstructor
public class Ticket {
    final private String id;
    @Schema(description = "User email")
    final private String requesterEmail;
    @Schema(description = "Assignee / Agent email")
    private String assigneeEmail;
    @Schema(description = "Title of the ticket")
    final private String title;
    @Schema(description = "Ticket description")
    @Setter
    private String description;
    @Setter @Schema(description = "Ticket state, follows specific state machine found on docs")
    private State state; // todo rethink about getters and setters here, also shouldn't each ticket have a state machine?
    @Schema(description = "Create date")
    final private Instant createDate;
    @Setter @LastModifiedDate
    private Instant  updatedDate; // todo remove it
    @Schema(description = "Resolution date")
    @Setter
    private OffsetDateTime resolutionDate;

    public void setAssignee(String assigneeEmail){
        // validation (todo)
        this.assigneeEmail = assigneeEmail;
    }
}

