
# Reto Técnico: Plataforma de Gestión de Tareas para Equipos (TeamTasker)

## Objetivo
Desarrollar un sistema backend de microservicios para gestionar tareas dentro de equipos de trabajo. El sistema debe permitir:
- Crear equipos.
- Agregar miembros a un equipo.
- Asignar tareas a miembros.
- Actualizar el estado de las tareas.
- Consultar tareas por estado, prioridad o responsable.

---

## Requisitos funcionales
1. **Gestión de Equipos**
    - Crear, actualizar, eliminar y listar equipos.
    - Agregar y eliminar miembros a un equipo.

2. **Gestión de Usuarios**
    - Registro e inicio de sesión (token JWT).
    - Roles: `ADMIN`, `MEMBER`.
    - Seguridad: solo usuarios con rol `ADMIN` pueden crear equipos y asignar tareas.

3. **Gestión de Tareas**
    - CRUD de tareas: título, descripción, prioridad, estado (`TODO`, `IN_PROGRESS`, `DONE`), fecha límite.
    - Asignación de tareas a un miembro.
    - Filtros: por estado, prioridad, asignado.

4. **API REST + Swagger**
    - Toda la funcionalidad debe estar disponible como API RESTful, bien documentada con Swagger/OpenAPI.

---

## Requisitos técnicos
- **Java 17+**
- **Spring Boot + WebFlux**
- **JWT para autenticación**
- **MongoDB o PostgreSQL (puedes elegir)**
- **Swagger/OpenAPI**
- **Arquitectura limpia (Clean Architecture)**
- **Separación por capas (Domain, Application, Infrastructure)**
- **Aplicar principios SOLID y buenas prácticas de Clean Code**
- **Manejo adecuado de errores y validaciones**
- **Tests unitarios y de integración (mínimo en 2 capas)**
- **Uso de patrones de diseño donde sea pertinente (ej. Factory, Strategy, etc.)**
- **Uso de DTOs y mapeo con ModelMapper o MapStruct**

---

## Plus opcional
- Contenerización con Docker.
- Uso de Spring Cloud Config o Eureka para simular un ecosistema de microservicios.
- CI/CD básico con GitHub Actions.
- Rate limiting en endpoints sensibles.
- Auditing y logging estructurado (ej. con SLF4J + Logback/Logstash format).

---

## Ejemplo de escenario de prueba
Un usuario admin:
1. Se registra e inicia sesión.
2. Crea un equipo llamado "DevOps".
3. Agrega a dos usuarios al equipo.
4. Crea tres tareas y asigna una a cada miembro.
5. Consulta tareas del equipo filtrando por estado y prioridad.

---

## Estructura esperada del repositorio
```
teamtasker-backend/
├── docs/                  # Documentación y Swagger
├── domain/                # Entidades, repositorios y lógica de negocio
├── application/           # Casos de uso
├── infrastructure/        # Adaptadores externos, base de datos, Web, JWT
├── config/                # Beans, security, mapeadores
├── tests/                 # Unitarios e integración
├── Dockerfile             # Opcional
├── README.md              # Explica cómo correr la app y probarla
├── pom.xml
```
