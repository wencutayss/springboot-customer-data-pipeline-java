# Customer Data Pipeline Services

This project consists of two Spring Boot microservices that work together to provide a customer data pipeline solution.

## Services Overview

### 1. Mock Server (Port 5000)
A mock service that provides sample customer data.

### 2. Pipeline Service (Port 8000)
A data ingestion service that fetches data from the mock server and stores it in a PostgreSQL database.

## API Endpoints

## Mock Server (Port 5000)

### Customer Endpoints

#### GET /api
- **Description**: Ping endpoint to check if the service is running
- **Response**: "Pong"
- **Example**: `curl http://localhost:5000/api`

#### GET /api/customers
- **Description**: Retrieve paginated list of customers
- **Parameters**:
  - `page` (int): Page number
  - `limit` (int): Number of records per page
- **Response**: Map containing customer data
- **Example**: `curl "http://localhost:5000/api/customers?page=1&limit=10"`

#### GET /api/customers/{id}
- **Description**: Retrieve a specific customer by ID
- **Parameters**:
  - `id` (String): Customer ID
- **Response**: Customer object
- **Example**: `curl http://localhost:5000/api/customers/123`

### Actuator/Health Endpoints (Mock Server)

#### GET /actuator/health
- **Description**: Health check endpoint
- **Response**: Health status with detailed information
- **Example**: `curl http://localhost:5000/actuator/health`

#### GET /actuator
- **Description**: List all available actuator endpoints
- **Response**: Links to all actuator endpoints
- **Example**: `curl http://localhost:5000/actuator`

#### GET /actuator/info
- **Description**: Application information
- **Example**: `curl http://localhost:5000/actuator/info`

#### GET /actuator/metrics
- **Description**: Application metrics
- **Example**: `curl http://localhost:5000/actuator/metrics`

### Swagger/OpenAPI Endpoints (Mock Server)

#### GET /swagger-ui.html
- **Description**: Swagger UI interface for API documentation
- **Example**: Open in browser: `http://localhost:5000/swagger-ui.html`

#### GET /v3/api-docs
- **Description**: OpenAPI 3.0 specification in JSON format
- **Example**: `curl http://localhost:5000/v3/api-docs`

#### GET /v3/api-docs/swagger-config
- **Description**: Swagger configuration
- **Example**: `curl http://localhost:5000/v3/api-docs/swagger-config`

#### GET /v3/api-docs/{group}
- **Description**: OpenAPI specification for specific group
- **Example**: `curl http://localhost:5000/v3/api-docs/public`

## Pipeline Service (Port 8000)

### Data Pipeline Endpoints

#### POST /api/ingest
- **Description**: Trigger data ingestion from mock server to database
- **Response**: Map with status and number of records processed
- **Example**: `curl -X POST http://localhost:8000/api/ingest`

### Customer Endpoints

#### GET /api/customers
- **Description**: Retrieve paginated list of customers from database
- **Parameters**:
  - `page` (int): Page number (1-based)
  - `limit` (int): Number of records per page
- **Response**: Page object with customer data
- **Example**: `curl "http://localhost:8000/api/customers?page=1&limit=10"`

#### GET /api/customers/{id}
- **Description**: Retrieve a specific customer by ID from database
- **Parameters**:
  - `id` (String): Customer ID
- **Response**: Customer object
- **Example**: `curl http://localhost:8000/api/customers/123`

### Actuator/Health Endpoints (Pipeline Service)

#### GET /actuator/health
- **Description**: Health check endpoint including database connectivity
- **Response**: Health status with detailed information
- **Example**: `curl http://localhost:8000/actuator/health`

#### GET /actuator
- **Description**: List all available actuator endpoints
- **Response**: Links to all actuator endpoints
- **Example**: `curl http://localhost:8000/actuator`

#### GET /actuator/info
- **Description**: Application information
- **Example**: `curl http://localhost:8000/actuator/info`

#### GET /actuator/metrics
- **Description**: Application metrics including database metrics
- **Example**: `curl http://localhost:8000/actuator/metrics`

### Swagger/OpenAPI Endpoints (Pipeline Service)

#### GET /swagger-ui.html
- **Description**: Swagger UI interface for API documentation
- **Example**: Open in browser: `http://localhost:8000/swagger-ui.html`

#### GET /v3/api-docs
- **Description**: OpenAPI 3.0 specification in JSON format
- **Example**: `curl http://localhost:8000/v3/api-docs`

#### GET /v3/api-docs/swagger-config
- **Description**: Swagger configuration
- **Example**: `curl http://localhost:8000/v3/api-docs/swagger-config`

#### GET /v3/api-docs/{group}
- **Description**: OpenAPI specification for specific group
- **Example**: `curl http://localhost:8000/v3/api-docs/public`

## Getting Started

### Prerequisites
- Docker and Docker Compose
- Java 17+ (for local development)
- Maven

### Running with Docker Compose

1. Clone the repository
2. Run the following command:
```bash
docker-compose up --build
```

This will start:
- PostgreSQL database on port 5432
- Mock Server on port 5000
- Pipeline Service on port 8000

### Health Check Sequence

1. Verify both services are running:
   ```bash
   curl http://localhost:5000/actuator/health
   curl http://localhost:8000/actuator/health
   ```

2. Test the data ingestion:
   ```bash
   curl -X POST http://localhost:8000/api/ingest
   ```

3. Verify data was ingested:
   ```bash
   curl "http://localhost:8000/api/customers?page=1&limit=10"
   ```

4. Access Swagger UI for API documentation:
   - Mock Server: http://localhost:5000/swagger-ui.html
   - Pipeline Service: http://localhost:8000/swagger-ui.html

## Architecture

```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│  Mock       │    │  Pipeline   │    │ PostgreSQL  │
│  Server     │───▶│  Service    │───▶│  Database   │
│  (Port 5000)│    │  (Port 8000)│    │  (Port 5432)│
└─────────────┘    └─────────────┘    └─────────────┘
```

## Notes

- Mock Server provides sample customer data
- Pipeline Service ingests data from Mock Server and stores it in PostgreSQL
- Both services expose Spring Boot Actuator endpoints for monitoring
- All actuator endpoints are exposed for development purposes
- Database schema is automatically created/updated using Hibernate DDL
