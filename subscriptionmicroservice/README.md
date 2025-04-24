# 🚆 Travel Subscription Platform - Microservices Architecture

## 📌 Description

This project is a **microservices-based travel subscription management platform** designed with Spring Boot and Docker. It enables users to subscribe to different travel plans and facilitates service registration and discovery using Eureka.

## 🧱 Architecture

```
                +-----------------------+
                |   Postman / Browser   |
                +----------+------------+
                           |
                           v
                +-----------------------+
                |   API Gateway (8080)  |
                +----------+------------+
                           |
         +----------------+------------------+
         |                                   |
         v                                   v
+-------------------+             +------------------------+
| subscription-service | <-----> |    Eureka Server (8761) |
|     (Port: 8081)     |         +------------------------+
+-------------------+
```

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Cloud Eureka**
- **Spring Cloud Gateway**
- **Spring Data JPA**
- **H2 In-Memory Database**
- **Lombok**
- **Docker**

## 📦 Microservices

### ✅ subscription-service

- Handles CRUD operations for subscriptions.
- Connected to an in-memory H2 database.
- Registers with Eureka for service discovery.

### 🚪 API Gateway

- Routes requests to the appropriate microservices.
- Registers with Eureka.

### 🧭 Eureka Server

- Service discovery and registry.

## 🐳 Dockerization

Each microservice includes a `Dockerfile` to containerize the application.

```
docker build -t subscription-service .
docker run -p 8081:8081 subscription-service
```

## 🔮 Future Improvements

- `docker-compose.yml` for orchestration
- Kubernetes deployment
- CI/CD with GitHub Actions
- OpenFeign for inter-service communication
- Keycloak for authentication and authorization

## 🧪 Testing

Use **Postman** to test all the endpoints via the API Gateway (port 8080).

---

> Developed with ❤️ by Yahya Elwafi