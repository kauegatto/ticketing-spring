### Disclaimer
This project has educational purposes of learning tools, DevOps principles, Spring Boot, microservices and Java by practice. Don't take the code as an absolute best-practices guide, there will be bugs which I won't fix because I may judge it's not worth it, there may be refactors I won't do for same purpose. Take everything with a grain of salt 🧂

# Features
## Hexagonal Architecture
Esse projeto usa arquitetura hexagonal (ports and adapters), deixando a camada de domínio protegida de regras de aplicação e/ou infraestrutura. Trabalhamos em cima de abstrações e tentamos fortalecer também os princípios SOLID. [Veja meu post sobre solid!](https://dev.to/kauegatto/solid-um-guia-diferente-162m)

[![Readme Quotes](https://quotes-github-readme.vercel.app/api?type=horizontal&theme=dracula&quote=Dependa%20de%20abstra%C3%A7%C3%B5es%20e%20n%C3%A3o%20de%20implementa%C3%A7%C3%B5es&author=Bob%20Martin)](test)
[![Readme Quotes](https://quotes-github-readme.vercel.app/api?type=horizontal&theme=dracula&quote=Programe%20voltado%20%C3%A0%20interface%2C%20n%C3%A3o%20%C3%A0%20implementa%C3%A7%C3%A3o&author=GoF)](test)
## SPRING STATE MACHINES
Cuide de estados de uma maneira mais elegante:
```java
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
```
![SSM](https://github.com/kauegatto/ticketing-spring-microservices/blob/main/docs/mermaid_states.png?raw=true)
## Dockerized Services & Infrastructure
Serviços e principalmente infraestrutura são dockerizados e já estão configurados, com um simples `docker compose-up`, você já possui mongoDB, rabbitMQ, grafana, prometheus configurados e rodando :)
## Declarative RabbitMQ
Declare RabbitMQ (or any other amqp) declarables in a simple yaml format:
```yaml
broker:
    queues:
        ticket:
            name: default.ticket
    exchanges:
        ticket:
            name: direct.ticket
            type: direct
    bindings:
        ticket:
            exchange: direct.ticket
            queue: default.ticket
            routingKey: default.ticket
```
## Prometheus & Grafana
We use spring actuator to provide metrics, prometheus to collect & STORE metrics, in order to provide data to Grafana. Everything is containerized and can be ran with a simple `docker-compose up`.

Aqui usamos o Micrometer para permitir a exportação de dados do actuator em um formato que o prometheus aceita.
![prometheus](https://raw.githubusercontent.com/kauegatto/ticketing-spring-microservices/main/docs/prometheus001.jpg)
### Grafana Configuration
O login padrão é ``admin : admin``
Você deve então adicionar prometheus como um data source: caso esteja usando o docker-compose de infraestrutura, pode usar o DNS: `prometheus:9090`

Criando dashboard micrometer:
1. Entre na parte de dashboards -> import
2. Em Id, coloque `4701`
3. Selecione seu data source e seja feliz :)
4. Se quiser adicionar outros dashboards, fique a vontade, temos diversos do spring, busque-os em: https://grafana.com/grafana/dashboards/
[Check for help](https://raw.githubusercontent.com/kauegatto/ticketing-spring-microservices/main/docs/grafana-importing.jpg)

Como as configurações do grafana estão salvas em um volume, elas não serão perdidas!
![Grafana](https://raw.githubusercontent.com/kauegatto/ticketing-spring-microservices/main/docs/grafana-functional.jpg)
## Important: 
Add `--add-opens=java.base/java.time=all-unnamed` to your vm options

If you're on Windows, add it to `JAVA_TOOL_OPTIONS` env variable

reason: https://github.com/spring-projects/spring-data-mongodb/issues/3893 ; https://stackoverflow.com/questions/70412805/what-does-this-error-mean-java-lang-reflect-inaccessibleobjectexception-unable

## Todos
- [X]  Main Scaffold
- [X]  Setup problem details and Controller Advice
- [X] Make correct controller advices and exceptions for Tickets
- [X] Setup Spring Doc
    -  [X] Document Tickets
- [X]  Setup database
- [X] Remove ResponseEntity of object
- [X] Use MongoDB
- [X] Refactor Project Structure
    - [X] Create Services
    - [ ] Create Rich Domain Classes
    - [X] Create Appropriate Mappers (Ports and Adapters)
- [X] Figure out how to map the id correctly without sacrificing a POJO  with Spring Data MongoDB
- [X] Dockerize Java+Spring and make it start the whole app in compose
- [X] Create different services
- [X] Connect different services
- [X] Connect to rabbit
- [X] Consume messages
- [X] Publish messages
- [X] Dockerize Rabbit
- [X] Refactor rabbitMq ConfigurationProperties
- [X] Allow creation of declarative AMQP lists, exchanges and bindings
- [X] Add Grafana
- [X] Create TicketStatus
    - [X] Implement state machine using spring state machine
- [ ] Create User
- [ ] Create SLA's associated with each status
    - [ ] Cache it
