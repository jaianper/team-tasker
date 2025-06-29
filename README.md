```
# Technical Challenge: Team Task Management Platform (TeamTasker)

## Objective
Develop a microservices backend system for managing tasks within work teams. The system must allow:
- Creating teams
- Adding members to a team
- Assigning tasks to members
- Updating task status
- Querying tasks by status, priority, or assignee

---

## Functional Requirements
1. **Team Management**
    - Create, update, delete, and list teams
    - Add and remove team members

2. **User Management**
    - Registration and login (JWT token)
    - Roles: `ADMIN`, `MEMBER`
    - Security: only users with `ADMIN` role can create teams and assign tasks

3. **Task Management**
    - Task CRUD: title, description, priority, status (`TODO`, `IN_PROGRESS`, `DONE`), deadline
    - Task assignment to members
    - Filters: by status, priority, assignee

4. **REST API + Swagger**
    - All functionality must be available as a RESTful API, well documented with Swagger/OpenAPI

---

## Technical Requirements
- **Java 17+**
- **Spring Boot + WebFlux**
- **JWT for authentication**
- **MongoDB or PostgreSQL (your choice)**
- **Swagger/OpenAPI**
- **Clean Architecture**
- **SOLID principles and Clean Code practices**
- **Proper error handling and validations**
- **Unit and integration tests (minimum in 2 layers)**
- **Design patterns where appropriate (e.g., Factory, Strategy, etc.)**
- **DTOs and mapping with ModelMapper or MapStruct**

---

## Optional Extras
- Docker containerization
- Spring Cloud Config or Eureka for microservices ecosystem simulation
- Basic CI/CD with GitHub Actions
- Rate limiting on sensitive endpoints
- Structured auditing and logging (e.g., with SLF4J + Logback/Logstash format)

---

## Test Scenario Example
An admin user:
1. Registers and logs in
2. Creates a team called "DevOps"
3. Adds two users to the team
4. Creates three tasks and assigns one to each member
5. Queries team tasks filtering by status and priority

---

## Project Structure

The project follows Hexagonal Architecture (also known as Ports and Adapters) with three main layers:

- **Domain**: Contains the core business logic and entities
- **Application**: Houses the use cases and ports (interfaces)
- **Infrastructure**: Contains the adapters that implement the ports and framework-specific code

This architecture ensures:
- Clear separation of concerns
- Business logic independence from external frameworks
- Easy testing and maintenance
- High modularity and flexibility