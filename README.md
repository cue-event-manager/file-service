# File Service

## Overview

The **File Service** manages the storage, retrieval, and access control of files across the CUE Event Management System. It provides secure integration with Amazon S3 for file uploads, downloads, and generation of temporary access links. This service ensures consistent and centralized handling of all digital assets used by other microservices.

---

## Purpose

The File Service is designed to handle all file-related operations while maintaining scalability, security, and traceability. It is responsible for:

* Uploading and storing files in AWS S3.
* Generating signed URLs for temporary access.
* Managing file metadata (owner, type, size, timestamps, etc.).
* Ensuring proper access control based on user roles and service permissions.
* Supporting various file types (images, documents, videos, etc.).

This service decouples file management from other modules, allowing independent scalability and reliability.

---

## Versions

| Component                                   | Version |
| ------------------------------------------- | ------- |
| **Java**                                    | 21      |
| **Spring Boot**                             | 3.5.4   |
| **Gradle**                                  | 8.14.3  |
| **Bancolombia Clean Architecture Scaffold** | 3.26.1  |

---

## Architecture

The File Service follows the **Bancolombia Clean Architecture Scaffold**, providing a clean separation of business logic from external dependencies.

```
file-service/
├── applications/             # Application entry points and configurations
├── domain/                   # Core entities, use cases, and models
├── infrastructure/            # Adapters for AWS S3, repositories, and controllers
├── build.gradle               # Gradle configuration
└── settings.gradle            # Project settings
```

### Layers

* **Domain:** Defines entities such as `File`, `FileMetadata`, and `FileType`.
* **Use Cases:** Handles operations for upload, deletion, retrieval, and signed URL generation.
* **Infrastructure:** Contains AWS SDK integration, S3 client configuration, and REST controllers.
* **Entry Points:** Exposes REST endpoints for other services and administrative tools.

---

## Environment Variables

Below are the required environment variables for the File Service:

```bash
# -----------------------------------
# Server Configuration
# -----------------------------------
SERVER_PORT=8080
SPRING_PROFILES_ACTIVE=dev

# -----------------------------------
# Database Configuration
# -----------------------------------
DB_URL=jdbc:mysql://mysql-file:3306/file_service?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
DB_USERNAME=file_user
DB_PASSWORD=file_password

# -----------------------------------
# AWS S3 Configuration
# -----------------------------------
AWS_REGION=us-east-1
AWS_ACCESS_KEY_ID=your-aws-access-key
AWS_SECRET_ACCESS_KEY=your-aws-secret-key
AWS_S3_BUCKET_NAME=cue-event-files

# -----------------------------------
# Internal Communication
# -----------------------------------
INTERNAL_SECRET=your-internal-service-secret
EUREKA_URL=http://discovery-service:8761/eureka/

# -----------------------------------
# Logging Configuration
# -----------------------------------
LOGGING_LEVEL_ROOT=INFO
LOGGING_LEVEL_CO.EDU.CUE=DEBUG

# -----------------------------------
# CORS Configuration
# -----------------------------------
CORS_ALLOWED_ORIGINS=http://localhost:4200,http://localhost:3000
```

---

## Security

* All requests between services are validated using `INTERNAL_SECRET`.
* File uploads are validated for allowed MIME types and size restrictions.
* Private files are served only through **signed URLs** with limited expiration.
* Public files (logos, images) use a separate S3 bucket with open access policies.
