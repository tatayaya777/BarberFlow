# BarberFlow

## Descripción

BarberFlow es una aplicación web desarrollada bajo una arquitectura de microservicios para la gestión de barberías. El sistema permite administrar clientes, barberos, reservas, servicios, pagos, sucursales y demás procesos involucrados en el funcionamiento de una barbería moderna.

El proyecto fue desarrollado utilizando Spring Boot para el backend, React para el frontend y MySQL como base de datos principal.

---

# Integrantes

- Mackener Castro
Asignatura: Desarrollo FullStack I
Institución: Duoc UC

---

# Arquitectura

La solución fue desarrollada utilizando una arquitectura basada en microservicios.

Cada microservicio posee su propia responsabilidad y mantiene una separación clara entre las capas:

- Controller
- Service
- Repository
- Model

El acceso a todos los servicios se realiza mediante un API Gateway.

---

# Microservicios implementados

- API Gateway
- Auth Service
- Cliente Service
- Barbero Service
- Reserva Service
- Servicio Service
- Pago Service
- Horario Service
- Reporte Service
- Notificación Service
- Sucursal Service

---

# Tecnologías utilizadas

Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- MySQL
- Swagger OpenAPI
- JUnit 5
- Mockito

Frontend

- React
- Vite
- Axios

Herramientas

- Git
- GitHub
- Postman
- XAMPP
- Visual Studio Code

---

# Funcionalidades

El sistema permite:

- Registrar clientes.
- Editar clientes.
- Eliminar clientes.
- Gestionar barberos.
- Administrar servicios.
- Registrar reservas.
- Gestionar pagos.
- Administrar sucursales.
- Consultar información mediante API REST.
- Documentar los endpoints utilizando Swagger.

---

# Arquitectura del proyecto

Frontend React

↓

API Gateway

↓

Microservicios independientes

- Cliente
- Barbero
- Reserva
- Servicio
- Pago
- Horario
- Reporte
- Notificación
- Sucursal
- Auth

↓

MySQL

---

# API Gateway

Las solicitudes son centralizadas mediante el API Gateway.

Ejemplo de rutas:

/clientes/**

/barberos/**

/reservas/**

/servicios/**

/pagos/**

/horarios/**

/reportes/**

/sucursales/**

/notificaciones/**

---

# Swagger

Cada microservicio cuenta con documentación Swagger.

Ejemplo:

http://localhost:8081/swagger-ui/index.html

http://localhost:8082/swagger-ui/index.html

http://localhost:8083/swagger-ui/index.html

http://localhost:8084/swagger-ui/index.html

(Modificar los puertos según corresponda).

---

# Base de datos

Motor:

MySQL

Base de datos:

barberflow

Persistencia mediante Spring Data JPA e Hibernate.

---

# Validaciones

El sistema incorpora validaciones utilizando Bean Validation.

Entre ellas:

- @NotBlank
- @Email
- @Valid

Además cuenta con manejo global de excepciones mediante ControllerAdvice.

---

# Registro de eventos

Se implementó SLF4J para registrar eventos importantes del sistema como:

- Creación de registros.
- Actualización.
- Eliminación.
- Errores.
- Advertencias.

---

# Pruebas unitarias

Se implementaron pruebas unitarias utilizando:

- JUnit 5
- Mockito

Las pruebas validan la lógica de negocio de los distintos microservicios utilizando mocks para simular el acceso a la base de datos.

---

# Ejecución del proyecto

## Backend

1. Iniciar MySQL desde XAMPP.
2. Crear la base de datos barberflow.
3. Ejecutar cada microservicio.
4. Ejecutar el API Gateway.

---

## Frontend

Entrar a la carpeta barberflow-frontend.

Instalar dependencias:

npm install

Ejecutar:

npm run dev

---

# Estructura del proyecto

BARBERFLOW

--> api-gateway

--> auth-service

--> cliente-service

--> barbero-service

--> reserva-service

--> servicio-service

--> pago-service

--> horario-service

--> reporte-service

--> notificacion-service

--> sucursal-service

--> barberflow-frontend

---

# Estado del proyecto

Proyecto académico desarrollado para FullStack I Hecho por Mackener Castro, utilizando una arquitectura basada en microservicios.
