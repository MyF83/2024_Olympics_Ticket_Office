# AI Coding Agent Instructions - 2024 Olympics Ticket Office

## Project Architecture

This is a **full-stack Olympics ticketing system** with:
- **Backend**: Spring Boot 3.4.5 API (Java 17) with MySQL database
- **Frontend**: Angular 19 SPA with Angular Material UI
- **Container**: Docker Compose setup with MySQL, Spring Boot, and Angular services

## Key Architecture Patterns

### Backend Structure (`Backend/src/main/java/com/myriamfournier/olympics_ticket_office/`)
- **POJOs**: Entity classes in `pojo/` (e.g., `events.java`, `users.java`, `offers.java`)
- **Web Services**: REST controllers in `ws/` with naming pattern `*Ws.java`
- **Services**: Business logic in `service/` 
- **Repositories**: Data access layer using Spring Data JPA
- **API Base Path**: All endpoints use `/api/*` prefix via `ApiRegistration` constants

### Frontend Structure (`Frontend/src/`)
- **Components**: Feature-based in `components/` (games, offers, account, admin, modals)
- **Services**: HTTP clients in `services/` ending with `.service.ts`
- **Interfaces**: TypeScript models in `interfaces/` ending with `interface.ts`
- **Interceptors**: JWT auth in `interceptor/jwt-interceptor.ts`

## Development Workflows

### Local Development (non-Docker)
```bash
# Backend (from project root)
cd Backend
mvn spring-boot:run "-Dspring.profiles.active=local"

# Frontend (from project root) 
cd Frontend
ng serve
```
Access at `http://localhost:4200` (auto-redirects to `/games`)

### Docker Development
```bash
docker-compose up
```
Requires stopping local MySQL on port 3306 if running.

### Database Profiles
- **Local**: `application.properties` - MySQL on localhost:3306, DB: `JO2024`
- **Docker**: `application-docker.properties` - MySQL container `db:3306`, DB: `jo2024`
- **Dev**: `application-dev.properties` - Development-specific configs

## Critical Project Conventions

### API Communication
- **Proxy Config**: Frontend uses `/api/*` proxy to backend `localhost:8080`
- **CORS**: Backend allows `http://localhost:4200` explicitly
- **Auth**: JWT tokens in sessionStorage, intercepted via `jwt-interceptor.ts`

### Database Patterns
- **Naming Strategy**: `PhysicalNamingStrategyStandardImpl` preserves camelCase in DB
- **Init Mode**: `spring.sql.init.mode=always` + `import.sql` for data seeding
- **DDL**: `create-drop` for development, creates fresh schema on startup

### Code Structure Patterns
- **Backend Controllers**: Use `@CrossOrigin` for specific frontend URL
- **Entity Relationships**: Nested objects in interfaces match JPA entity structure
- **Service Layer**: Angular services use `providedIn: 'root'` singleton pattern
- **Component Routing**: Uses Angular standalone components with defined routes

### Special Files to Note
- **`import.sql`**: 454 lines of Olympic venues, events, offers seed data
- **`Application.java`**: Uses `@ComponentScan` for QRCode package inclusion  
- **`proxy.config.json`**: Essential for dev - routes `/api` to Spring Boot
- **`docker-compose.yml`**: Complex healthchecks and startup dependencies

## Domain-Specific Context

### Core Business Entities
- **Events**: Olympics ceremonies and sports competitions with venues
- **Offers**: Solo (5%), Duo (15%), Family (35%) discount tiers
- **Users/Accounts**: Visitor, admin, employee roles with JWT auth
- **Tickets**: QR code generation for purchased events
- **Cart**: Shopping cart with offer-based pricing calculations

### Key Integration Points
- **Frontend→Backend**: All via `/api/*` HTTP calls using Angular HttpClient
- **Database**: MySQL with comprehensive Olympics seed data (venues, sports, countries)
- **Auth Flow**: Register/login → JWT token → session storage → HTTP interceptor
- **File Storage**: Static assets in `Frontend/public/` (images, policies)

### Development Anti-Patterns to Avoid
- Don't modify the `PhysicalNamingStrategyStandardImpl` - it prevents camelCase→snake_case conversion
- Don't change the CORS origins without updating both backend `@CrossOrigin` and proxy config
- Don't skip the proxy configuration - direct backend calls will fail in development
- Don't modify `import.sql` structure without understanding the complex entity relationships