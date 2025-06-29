# Team Module - Implemented Features

## Overview
This document describes the features implemented in the team module of the TeamTasker application.

## ✅ Implemented Features

### 1. Team Management
- **Create Teams**: Create new teams with name, description, and creator
- **Update Teams**: Modify team information (name, description)
- **Delete Teams**: Remove teams and all associated members
- **List Teams**: Retrieve all teams or teams by creator
- **Get Team Details**: Retrieve specific team information

### 2. Team Member Management
- **Add Members**: Add users to teams with specific roles (ADMIN, MEMBER)
- **Remove Members**: Remove users from teams
- **List Members**: Get all members of a specific team
- **Role Management**: Assign and manage member roles within teams

### 3. Team Roles
- **ADMIN**: Full control over team and members
- **MEMBER**: Basic team membership

### 4. Security & Authorization
- **Admin-Only Access**: All team operations require ADMIN role
- **Protected Endpoints**: All team endpoints require authentication
- **Role-Based Authorization**: Different permissions for different roles

## 🔧 Technical Implementation

### Architecture
- **Clean Architecture**: Maintains separation of concerns
- **Hexagonal Architecture**: Domain-driven design with ports and adapters
- **SOLID Principles**: Follows clean code practices

### Database Design
- **Teams Table**: Stores team information
- **Team Members Table**: Stores team membership with roles
- **Relationships**: Many-to-many relationship between teams and users

### Security Implementation
- **Spring Security**: Framework for authentication and authorization
- **Role-Based Access**: `@PreAuthorize` annotations for team operations
- **Admin-Only Operations**: All team endpoints restricted to ADMIN users

## 🚀 API Endpoints

### Team Management
```bash
# Create a new team
POST /api/v1/teams
{
  "name": "Development Team",
  "description": "Software development team",
  "createdBy": "user-uuid"
}

# Get all teams
GET /api/v1/teams

# Get team by ID
GET /api/v1/teams/{teamId}

# Update team
PUT /api/v1/teams/{teamId}
{
  "name": "Updated Team Name",
  "description": "Updated description"
}

# Delete team
DELETE /api/v1/teams/{teamId}
```

### Team Member Management
```bash
# Add member to team
POST /api/v1/teams/{teamId}/members
{
  "userId": "user-uuid",
  "role": "MEMBER"
}

# Remove member from team
DELETE /api/v1/teams/{teamId}/members/{userId}

# Get team members
GET /api/v1/teams/{teamId}/members
```

## 📋 Domain Models

### Team
```java
public class Team {
    private UUID id;
    private String name;
    private String description;
    private UUID createdBy;
    private List<TeamMember> members;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

### TeamMember
```java
public class TeamMember {
    private UUID id;
    private UUID teamId;
    private UUID userId;
    private TeamMemberRole role;
    private LocalDateTime joinedAt;
    private LocalDateTime updatedAt;
}
```

### TeamMemberRole
```java
public enum TeamMemberRole {
    ADMIN, MEMBER
}
```

## 🔒 Security Notes
- All team endpoints require ADMIN role
- Team creation automatically adds creator as ADMIN member
- Team deletion removes all associated members
- Member operations are restricted to team admins

## 🧪 Testing
Run the tests to verify functionality:
```bash
./gradlew test
```

## 📚 API Documentation
Access the Swagger UI at: `http://localhost:8080/swagger-ui.html`

## 🔄 Integration with Other Modules

### User Module Integration
- Teams reference users by UUID
- Team members are linked to user entities
- User validation should be implemented for production

### Task Module Integration (Future)
- Tasks can be associated with teams
- Team-based task filtering
- Team member task assignment validation

## 🎯 Usage Examples

### 1. Create a Team
```bash
# Login as admin
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "password"}'

# Create team
curl -X POST http://localhost:8080/api/v1/teams \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "DevOps Team",
    "description": "Infrastructure and deployment team",
    "createdBy": "admin-user-uuid"
  }'
```

### 2. Add Member to Team
```bash
curl -X POST http://localhost:8080/api/v1/teams/{teamId}/members \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "member-user-uuid",
    "role": "MEMBER"
  }'
```

### 3. Get Team Members
```bash
curl -X GET http://localhost:8080/api/v1/teams/{teamId}/members \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🔄 Next Steps
The following features are planned for future implementation:
1. Team-based task filtering
2. Team member task assignment validation
3. Team invitation system
4. Team activity logging
5. Team statistics and analytics
6. Bulk member operations
7. Team templates and cloning 