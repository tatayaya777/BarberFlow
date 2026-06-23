# BarberFlow

# Integrantes

Mackener Castro

# Descripción

BarberFlow es una plataforma que se desarrollo mediante a estructuras de microservicios para la gestión de una barbería. Permite administrar clientes, barberos, reservas, servicios, pagos, horarios, notificaciones y autenticación.

# Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL
* Swagger OpenAPI
* Maven
* JUnit 5
* Mockito

# Microservicios implementados

* auth-service
* cliente-service
* barbero-service
* reserva-service
* servicio-service
* horario-service
* pago-service
* notificacion-service
* reporte-service
* sucursal-service

# Base de datos

Nombre: barberflow

# Ejecución

1. Iniciar MySQL mediante XAMPP.
2. Crear la base de datos barberflow.
3. Ejecutar los microservicios desde Visual Studio Code.
4. Acceder a Swagger mediante:

http://localhost:8081/swagger-ui/index.html

# Ejemplos de rutas

GET /clientes

GET /clientes/{id}

POST /clientes

PUT /clientes/{id}

DELETE /clientes/{id}

# Testing

Se implementaron pruebas unitarias utilizando JUnit 5 y Mockito para validar la lógica de negocio de los microservicios.
