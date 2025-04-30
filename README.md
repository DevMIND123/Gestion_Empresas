# 📦 Microservicio: Gestión de Contenido

Este microservicio forma parte de un sistema distribuido y está diseñado para **administrar y modificar el contenido de una página web**. Permite gestionar menús, ubicaciones, promociones, precios, preguntas frecuentes, y más, a través de endpoints RESTful.

> ⚠️ **Este proyecto se encuentra en etapa de desarrollo**. No es apto para entornos de producción, ya que las credenciales de la base de datos están actualmente **públicas y hardcodeadas** tanto en `docker-compose.yml` como en `application.properties`.

## 🚀 Tecnologías

- Java 17+
- Spring Boot 3.x
- PostgreSQL (Docker)
- Maven
- Docker Compose (para base de datos)

## 🧩 Funcionalidades principales

- Crear, actualizar y obtener contenido de la web
- Gestión de:
  - Menús
  - Ubicaciones
  - Promociones
  - Bonos de descuento
  - Precios de membresía
  - Preguntas frecuentes (FAQ)


## ⚙️ Configuración local

### 🐘 Base de datos PostgreSQL con Docker

Lanza PostgreSQL usando Docker:

```bash
docker-compose up -d
```

Verifica que el contenedor esté corriendo en `localhost:5432`.

### ⚙️ Variables de entorno / application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestion_contenido
spring.datasource.username=user
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=org.postgresql.Driver
```

> Asegúrate de que las credenciales coincidan con las definidas en `docker-compose.yml`.

### ▶️ Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

o si tienes Maven instalado:

```bash
mvn spring-boot:run
```
---
## 🔍 Endpoints REST

| Recurso             | Método | Endpoint                   | Descripción                        |
|---------------------|--------|----------------------------|------------------------------------|
| Bonos de descuento  | GET    | `/api/bonos`              | Listar todos los bonos             |
|                     | POST   | `/api/bonos`              | Crear nuevo bono                   |
|                     | PUT    | `/api/bonos/{id}`         | Editar bono existente              |
|                     | DELETE | `/api/bonos/{id}`         | Eliminar bono                      |
| FAQ                 | GET    | `/api/faqs`               | Listar preguntas frecuentes        |
|                     | POST   | `/api/faqs`               | Crear nueva FAQ                    |
|                     | PUT    | `/api/faqs/{id}`          | Editar FAQ existente               |
|                     | DELETE | `/api/faqs/{id}`          | Eliminar FAQ                       |
| Menú                | GET    | `/api/menus`              | Listar menú actual                 |
|                     | PUT    | `/api/menus/{id}`         | Actualizar menú                    |
| Ubicación           | GET    | `/api/ubicaciones`        | Listar ubicación actual            |
|                     | PUT    | `/api/ubicaciones/{id}`   | Actualizar ubicación               |
| Promociones         | GET    | `/api/promociones`        | Listar todas las promociones       |
|                     | POST   | `/api/promociones`        | Crear nueva promoción              |
|                     | PUT    | `/api/promociones/{id}`   | Editar promoción existente         |
| Precios membresía   | GET    | `/api/precios`            | Listar precios de membresía        |
|                     | POST   | `/api/precios`            | Crear nuevo precio                 |
|                     | PUT    | `/api/precios/{id}`       | Editar precio existente            |
|                     | DELETE | `/api/precios/{id}`       | Eliminar precio                    |



