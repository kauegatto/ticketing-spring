### Disclaimer
This project doesn't aim to provide best practices of any regards and has educational purposes of learning some SpringBoot and DevOps code by practice. 

Check Todos for a better "roadmap".

# Features
## Hexagonal Architecture
todo
## Dockerized Services & Infrastructure
todo
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

### Grafana Configuration
O login padrão é ``admin : admin``
Você deve então adicionar prometheus como um data source: caso esteja usando o docker-compose de infraestrutura, pode usar o DNS: `prometheus:9090`

Criando dashboard micrometer:

1. Entre na parte de dashboards -> import
2. Em Id, coloque `4701`
3. Selecione seu data source e seja feliz :)
4. Se quiser adicionar outros dashboards, fique a vontade, temos diversos do spring, busque-os em: https://grafana.com/grafana/dashboards/

Como as configurações do grafana estão salvas em um volume, elas não serão perdidas!
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
    - [+-] Create Rich Domain Classes
    - [X] Create Appropriate Mappers (Ports and Adapters)
- [ ] Figure out how to map the id correctly without sacrificing a POJO  with Spring Data MongoDB
- [X] Dockerize Java+Spring and make it start the whole app in compose
- [X] Create different services
- [ ] Connect different services
- [X] Connect to rabbit
- [X] Consume messages
- [X] Publish messages
- [X] Dockerize Rabbit
- [X] Refactor rabbitMq ConfigurationProperties
- [X] Allow creation of declarative lists, exchanges and bindings
- [ ] Create TicketStatus
    - [ ] Implement state machine
- [ ] Create User
- [ ] Create SLA's associated with each status
    - [ ] Cache it
