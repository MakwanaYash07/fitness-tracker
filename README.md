# Fitness Tracker (finished)

## Prereqs
- Java 17+
- Maven 3.6+
- MySQL 

## Build & run
1. Configure DB in `src/main/resources/application.properties` (default uses MySQL).
2. Build: `mvn clean package`
3. Run: `mvn spring-boot:run` or `java -jar target/tracker-0.0.1-SNAPSHOT.jar`

## Endpoints (examples)
- Register: `POST /api/users/register`
- Login (Basic Auth): use registered user's email/password
- Get activity logs: `GET /api/activity-logs` (auth required)
