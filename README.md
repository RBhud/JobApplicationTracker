# Jobtrack

A job application tracker with a Spring Boot API, React frontend, and local PostgreSQL database.

## Prerequisites

- Java 21+
- Maven 3.9+
- Node.js 20+
- Docker Desktop

## Start the database

```bash
docker compose up -d postgres
```

## Start the backend

```bash
cd backend
mvn spring-boot:run
```

The API runs at `http://localhost:8080`. Check it at `http://localhost:8080/api/applications`.

## Start the frontend

In another terminal:

```bash
cd frontend
npm run dev
```

The UI runs at `http://localhost:5173` and proxies `/api` requests to Spring Boot.

## Stop the database

```bash
docker compose down
```
