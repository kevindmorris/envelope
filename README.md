# Architecture Diagram

```mermaid
graph TD
  subgraph Docker
    direction TB

    postgres[Postgres]
    springboot[Spring Boot]

    springboot --> postgres
  end
```
