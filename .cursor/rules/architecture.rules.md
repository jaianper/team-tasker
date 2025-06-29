# 🧭 Project Rules – Architecture and Best Practices

## 🔧 Code Style and Cleanliness

- Code must follow **Clean Code principles**: descriptive names, small focused functions, no duplication.
- Apply **SOLID principles** when they improve clarity, extensibility, or maintainability (e.g., SRP for services, DIP using ports and adapters).
- Use consistent formatting: **4-space indentation**, aligned braces, and clear code separation by concerns.

## 🏛️ Architecture and Structure

- This project follows **Hexagonal Architecture (Ports and Adapters)**:
  - `domain/`: pure business logic and entities
  - `application/`: use cases and service interfaces
  - `infrastructure/`: adapters such as controllers, repositories, external integrations
  - `config/`: Spring Boot configuration and setup

- The domain layer must not depend on any external frameworks or technologies.
- External systems (databases, APIs, etc.) must be abstracted through **ports** (interfaces) in `application` or `domain`, and implemented as **adapters** in `infrastructure`.

## 🧪 Testing

- There must be **unit tests** for at least:
  - Use case services (`application`)
  - Domain logic (`domain`)
- There must also be **integration tests** for at least:
  - Real repositories (e.g., JPA-backed)
  - REST endpoints (`infrastructure/web`)
- Tests should be placed under `src/test/java`, mirroring the package structure of the class under test:
  - e.g., `src/main/java/com/jaianper/teamtasker/...` → `src/test/java/com/jaianper/teamtasker/...`

## 🧰 Design Patterns

- Use design patterns when they clearly improve code quality:
  - **Strategy** for business logic selection
  - **Factory** for constructing complex objects
  - **Adapter** for external service integration
  - **Decorator** for extending behavior without modifying base code

## 📚 API Documentation

- All **endpoints must be documented** using **Swagger/OpenAPI annotations** (`@Operation`, `@Schema`, etc.).
- Names, descriptions, and examples must be clear and aligned with business language.
- If an endpoint involves specific domain rules, describe them in `@Operation` summaries or `@ApiResponses`.

## 🧠 Additional Notes

- Avoid placing business logic in controllers or external adapters.
- Input validation should be performed in DTOs using annotations like `@Valid`, `@NotBlank`, etc.
- Exceptions should be handled using `@ControllerAdvice`, avoiding technical leakages in client responses.
