## 🗂️ Architecture Diagram

```mermaid
graph
    B[React UI]
    C[Vue UI]
    D[Spring Boot API]
    E[Fast API]
    F[Postgres]

    B --> D
    C --> E
    D --> F
    E --> F
```
