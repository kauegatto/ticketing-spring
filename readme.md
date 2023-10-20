### Disclaimer
This project doesn't aim to provide best practices of any regards and has educational purposes of learning some SpringBoot and DevOps code by practice. 

Check Todos for a better "roadmap".

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
- [ ] Create TicketStatus
    - [ ] Implement state machine
- [ ] Create User
- [ ] Create SLA's associated with each status
    - [ ] Cache it
