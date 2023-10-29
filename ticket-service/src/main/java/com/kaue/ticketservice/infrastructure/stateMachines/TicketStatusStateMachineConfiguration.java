package com.kaue.ticketservice.infrastructure.stateMachines;

import com.kaue.ticketservice.domain.model.state.TicketStatusState.Event;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.State;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;

import java.util.EnumSet;
import java.util.Objects;

@Configuration
@EnableStateMachineFactory
@Slf4j
public class TicketStatusStateMachineConfiguration extends EnumStateMachineConfigurerAdapter<State,
        Event> {
  @Override
  public void configure(StateMachineConfigurationConfigurer<State, Event> config) throws Exception {
      config
        .withConfiguration()
        .autoStartup(true)
        .listener(listener());
    }

  public void configure(StateMachineStateConfigurer<State, Event> states) throws Exception {
    states
            .withStates()
            .initial(State.NEW)
            .states(EnumSet.allOf(State.class))
            .end(State.CLOSED)
            .end(State.COMPLETED);
  }

  public void configure(StateMachineTransitionConfigurer<State, Event> transitions) throws Exception {
    transitions
            .withExternal()
            .source(State.NEW).target(State.IN_PROGRESS)
            .event(Event.START_SOLVING)

            .and().withExternal()
            .source(State.IN_PROGRESS).target(State.WAITING_REPLY)
            .event(Event.NEEDS_REPLY)

            .and().withExternal()
            .source(State.IN_PROGRESS).target(State.COMPLETED)
            .event(Event.COMPLETE)

            .and().withExternal()
            .source(State.IN_PROGRESS).target(State.CLOSED)
            .event(Event.CLOSE)

            .and().withExternal()
            .source(State.NEW).target(State.CLOSED)
            .event(Event.CLOSE)
    ;
  }
  @Bean
  public StateMachineListener<State, Event> listener() {
    return new StateMachineListenerAdapter<>() {
      @Override
      public void stateChanged(org.springframework.statemachine.state.State<State, Event> from, org.springframework.statemachine.state.State<State, Event> to) {
        if(Objects.isNull(from)) {
          log.info("Ticket state set to " + to.getId().name());
          return;
        }
        Objects.requireNonNull(from.getId());
        log.info("Ticket state changed from " + from.getId().name() + " to " + to.getId().name());
      }
    };
  }
}
