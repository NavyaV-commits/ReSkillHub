# 🚀 ReSkillHub – Career Reboot Platform

**ReSkillHub** is a Spring Boot-based microservices application designed to help professionals bridge career gaps by showcasing skills, certifications, and real-world projects. This is a personal full-stack development project to strengthen hands-on experience with backend architecture, OAuth2, and secure service communication.

---

## 🔧 Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Cloud Gateway**
- **Spring Security + OAuth2 + JWT**
- **PostgreSQL**
- **Maven**
- **Docker (planned)**
- **Angular (planned UI)**

---

## 📦 Architecture

- **API Gateway** – Routes external requests, handles JWT validation
- **Auth Service** – Google OAuth2 login, JWT generation
- **User Service** – Handles user profiles and role-based access
- **(Planned)** Skill Service, Project Showcase, Admin Dashboard

---

## ✅ Completed Features

- [x] Google OAuth2 Login via Auth Service
- [x] JWT Access Token with RS256
- [x] Custom Login Page with styled "Login with Google" button
- [x] API Gateway with secure routing and token verification
- [x] Protected `/profile` endpoint with role-based access

---

## 🛠 In Progress

- [ ] Token Refresh Endpoint
- [ ] Role-based authorization
- [ ] User registration and skill tracking APIs
- [ ] Dockerize services
- [ ] Frontend (Angular)

---

## 📂 How to Run (Backend)

1. Clone the repository
2. Set up environment:
   - Java 17+
   - PostgreSQL running locally
3. Set Google OAuth2 credentials in `application.properties`:
