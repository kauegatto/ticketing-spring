### Disclaimer
This project has educational purposes of learning tools, DevOps principles, Spring Boot, microservices and Java by practice. Don't take the code as an absolute best-practices guide, there will be bugs which I won't fix because I may judge it's not worth it, there may be refactors I won't do for same purpose. Take everything with a grain of salt 🧂

# Sobre
## Hexagonal Architecture
Esse projeto usa arquitetura hexagonal (ports and adapters), deixando a camada de domínio protegida de regras de aplicação e/ou infraestrutura. Trabalhamos em cima de abstrações e tentamos fortalecer também os princípios SOLID. [Veja meu post sobre solid!](https://dev.to/kauegatto/solid-um-guia-diferente-162m)

[![Readme Quotes](https://quotes-github-readme.vercel.app/api?type=horizontal&theme=dracula&quote=Dependa%20de%20abstra%C3%A7%C3%B5es%20e%20n%C3%A3o%20de%20implementa%C3%A7%C3%B5es&author=Bob%20Martin)](test)
[![Readme Quotes](https://quotes-github-readme.vercel.app/api?type=horizontal&theme=dracula&quote=Programe%20voltado%20%C3%A0%20interface%2C%20n%C3%A3o%20%C3%A0%20implementa%C3%A7%C3%A3o&author=GoF)](test)
### Exemplo:
```java
  public Ticket save(Ticket ticket){
    messagePublisher.Notify(TicketEventsEnum.TICKET_CREATED);
    return repository.save(ticket);
  }

  public Ticket assignAndUpdate(String id, String assigneeEmail){
    var ticket = findById(id);
    ticket.setAssignee(assigneeEmail);
    ticket.setState(stateMachine.sendEvent(START_SOLVING, id));
    return repository.save(ticket);
  }
```
Aqui, stateMachine  é uma abstração própria que se comunica com o framework de máquinas de estado, outro exemplo legal é o `messagePublisher.notify()`, que também é uma abstração própria, e por baixo dos panos se comunica com um Rabbit. A ideia aqui  é separar código de infraestrutura e domínio, permitindo reúso e fácil troca de componentes de infra (rabbit para kafka, por exemplo).

## SPRING STATE MACHINES
Cuide de estados de uma maneira mais elegante, enviando eventos de domínio e garantindo que o framework de estados cuide de mover os status!
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
[![](https://mermaid.ink/img/pako:eNp1kFFrgzAUhf-K3McRJUZNjA-DsUoRnBYdK9scJdTQClOLi2Od-N-XKmNrx0IeDt-5J5ecAbZtKSGANyWUXFRi14nafCdFY-jzfPVimOa1kYTrGWgxgds4zcPFOYuSzSpLl1mY57PxC_wJXXp5Gj_8561vovsoWW6ycBU_ziNn6HI7IKhlV4uq1P8aToEC1F7WsoBAy9dqt1cFFM2oB0Wv2vzYbCFQXS8R9Ifyp4dveBANBAN8QEAItzDBhGLXYfoyF8FRY2Y53OMO9TGljHLsjgg-21a_gC3GbOp5vm1r4VOXI5Blpdrubi5-6n_a8TQFTivHLx04cqw?type=jpg)](https://mermaid.live/edit#pako:eNp1kFFrgzAUhf-K3McRJUZNjA-DsUoRnBYdK9scJdTQClOLi2Od-N-XKmNrx0IeDt-5J5ecAbZtKSGANyWUXFRi14nafCdFY-jzfPVimOa1kYTrGWgxgds4zcPFOYuSzSpLl1mY57PxC_wJXXp5Gj_8561vovsoWW6ycBU_ziNn6HI7IKhlV4uq1P8aToEC1F7WsoBAy9dqt1cFFM2oB0Wv2vzYbCFQXS8R9Ifyp4dveBANBAN8QEAItzDBhGLXYfoyF8FRY2Y53OMO9TGljHLsjgg-21a_gC3GbOp5vm1r4VOXI5Blpdrubi5-6n_a8TQFTivHLx04cqw)
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
Tipos válidos de exchange são: `direct`, `topic`, `fanout` e `headers`
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

# Service Discovery

Aqui usamos o Netflix Eureka Server em um microserviço como servidor de service discovery, permitindo que as chamadas cheguem aos nossos serviços independente da quantidade de instâncias, ip's, et cetera.

# Testes de repositórios de verdade:
Esse projeto conta com (poucos, por certa preguiça) testes de integração, mas testes de verdade, onde chamo um service, que posteriormente acessa um repositório (que acessa um banco de dados real) e um basePublisher, que usa um rabbitMQ real.
Essa configuração  é feita usando `testContainers`, framework open source. 

Toda vez que um teste começa, a última imagem de rabbitmq e docker são baixadas e sobem em portas aleatórias disponíveis.
Então, nosso projeto dá override nas configurações antigas e se conecta à esse container com uma porta aleatória configurado, e usa ele até o fim do ciclo de vida da classe de teste.
  
Ainda tem algumas melhorias que gostaria de fazer (como a imagem ser setada no `application-test.yml`, mas funciona 😃
```java
  @Container
  private static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:latest");
  @Container
  private static final RabbitMQContainer RABBIT_MQ_CONTAINER = new RabbitMQContainer("rabbitmq:management");
  @DynamicPropertySource
  static void mongoDbProperties(DynamicPropertyRegistry registry) {
    MONGO_DB_CONTAINER.start();
    registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
    registry.add("spring.rabbitmq.addresses",() -> "amqp://guest:guest@localhost:"+RABBIT_MQ_CONTAINER.getAmqpPort());
  }
```
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
