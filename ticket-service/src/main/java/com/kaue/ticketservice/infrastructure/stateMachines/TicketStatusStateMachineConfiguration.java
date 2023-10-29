package com.kaue.ticketservice.infrastructure.stateMachines;

import com.kaue.ticketservice.domain.model.state.TicketStatusState;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.events;
import com.kaue.ticketservice.domain.model.state.TicketStatusState.states;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class TicketStatusStateMachineConfiguration extends EnumStateMachineConfigurerAdapter<states,
        events> {
  @Override
  public void configure(StateMachineConfigurationConfigurer<states, events> config) throws Exception {
      config
        .withConfiguration()
        .autoStartup(true)
        .listener(listener());
    }

  public void configure(StateMachineStateConfigurer<states, events> states) throws Exception {
    states
            .withStates()
            .initial(TicketStatusState.states.NEW)
            .states(EnumSet.allOf(TicketStatusState.states.class));
  }

  public void configure(StateMachineTransitionConfigurer<states, events> transitions) throws Exception {
    transitions
            .withExternal()
            .source(states.NEW).target(states.IN_PROGRESS)
            .event(events.START_SOLVING)

            .and().withExternal()
            .source(states.IN_PROGRESS).target(states.WAITING_REPLY)
            .event(events.NEEDS_REPLY)

            .and().withExternal()
            .source(states.IN_PROGRESS).target(states.COMPLETED)
            .event(events.COMPLETE)

            .and().withExternal()
            .source(states.IN_PROGRESS).target(states.CLOSED)
            .event(events.CLOSE)

            .and().withExternal()
            .source(states.NEW).target(states.CLOSED)
            .event(events.CLOSE)
    ;
  }
  @Bean
  public StateMachineListener<states, events> listener() {
    return new StateMachineListenerAdapter<>() {
      @Override
      public void stateChanged(State<states, events> from, State<states, events> to) {
        System.out.println("Ticket state changed from " + from.getId().name() + " to " + to.getId().name());
      }
    };
  }
}
