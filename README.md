# BarberFlow

> Sistema de gestión para barberías desarrollado con arquitectura de **Microservicios**, utilizando **Spring Boot**, **React**, **MySQL** y **API Gateway**.

Proyecto desarrollado para **Fullstack I** Hecho por Mackener Castro.

---

# Descripción

BarberFlow es una aplicación web que permite administrar una barbería de forma sencilla y organizada mediante una arquitectura basada en microservicios.

El sistema fue diseñado para separar cada módulo en un servicio independiente, permitiendo una mejor escalabilidad, mantenimiento y organización del código.

Entre sus funcionalidades principales se encuentran:

- Administración de clientes
- Administración de barberos
- Gestión de reservas
- Administración de servicios
- Registro de pagos
- Generación de reportes
- Panel principal con estadísticas generales

---

# Tecnologías utilizadas

## Backend

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Validation
- Spring Boot Actuator
- SpringDoc OpenAPI (Swagger)
- Maven

## Frontend

- React
- Vite
- Axios
- React Router DOM
- Bootstrap 5

## Base de Datos

- MySQL
- XAMPP

## Herramientas de desarrollo

- Visual Studio Code
- IntelliJ IDEA
- Git
- GitHub
- Postman

---

# 🏗 Arquitectura

El proyecto fue desarrollado utilizando una arquitectura basada en microservicios.


Clientes
Barberos
Reservas
Servicios
Pagos
Reportes
```

Cada microservicio posee su propia lógica de negocio, controladores, servicios, repositorios y acceso independiente a la base de datos.

---

# Estructura del proyecto

```text
BARBERFLOW
│
├── api-gateway
├── auth-service
├── cliente-service
├── barbero-service
├── reserva-service
├── servicio-service
├── pago-service
├── reporte-service
├── horario-service
├── notificacion-service
├── sucursal-service
│
└── barberflow-frontend
```

---

# ⚙ Funcionalidades

## Clientes

- Registrar clientes
- Editar información
- Buscar clientes
- Eliminar clientes

---

## Barberos

- Registrar barberos
- Editar información
- Buscar barberos
- Eliminar barberos

---

## Reservas

- Crear reservas
- Modificar reservas
- Buscar reservas
- Eliminar reservas

---

## Servicios

- Registrar servicios
- Editar servicios
- Buscar servicios
- Eliminar servicios

---

## Pagos

- Registrar pagos
- Editar pagos
- Consultar pagos
- Eliminar pagos

---

## Reportes

- Registrar reportes
- Consultar reportes
- Editar reportes
- Eliminar reportes

---

# Dashboard

El Dashboard principal permite visualizar información resumida del sistema, mostrando:

- Total de clientes registrados
- Total de barberos registrados
- Total de reservas realizadas
- Total de ingresos registrados

Toda la información es obtenida dinámicamente desde los microservicios mediante el API Gateway.

---

# Validaciones

El proyecto utiliza **Bean Validation** para validar la información antes de ser almacenada.

Entre las validaciones implementadas se encuentran:

- `@NotBlank`
- `@Email`
- `@NotNull`
- `@Positive`
- `@PositiveOrZero`

---

# Documentación de la API

Cada microservicio dispone de documentación automática mediante Swagger.

Ejemplo:

```text
http://localhost:8081/swagger-ui/index.html
```

---

# Pruebas Unitarias

Se implementaron pruebas unitarias utilizando:

- JUnit 5
- Mockito

Servicios con pruebas implementadas:

- ClienteService
- PagoService
- ReservaService

---

# Registro de eventos

Se implementó el uso de **SLF4J** para registrar eventos importantes del sistema, tales como:

- Creación de registros
- Actualización de información
- Eliminación de datos
- Consultas realizadas
- Advertencias y errores

---

# Manejo de excepciones

El sistema incorpora un **Global Exception Handler**, encargado de centralizar el manejo de errores y devolver respuestas consistentes para la API.

También se implementó la excepción personalizada:

- ResourceNotFoundException

---

# Ejecución del proyecto

## 1. Clonar el repositorio

```bash
git clone https://github.com/TU-USUARIO/BarberFlow.git
```

---

## 2. Crear la base de datos

Crear una base de datos llamada:

```text
barberflow
```

---

## 3. Iniciar MySQL

Levantar el servicio desde XAMPP.

---

## 4. Ejecutar los microservicios

Levantar los siguientes proyectos Spring Boot:

- API Gateway
- Auth Service
- Cliente Service
- Barbero Service
- Reserva Service
- Servicio Service
- Pago Service
- Reporte Service
- Horario Service
- Notificación Service
- Sucursal Service

---

## 5. Ejecutar el Frontend

Entrar a:

```text
barberflow-frontend
```

Instalar dependencias:

```bash
npm install
```

Ejecutar:

```bash
npm run dev
```

Abrir en el navegador:

```text
http://localhost:5173
```

---

# Características del proyecto

- Arquitectura basada en Microservicios.
- API Gateway para centralizar el acceso.
- Frontend desarrollado con React.
- Backend desarrollado con Spring Boot.
- Persistencia mediante MySQL.
- Documentación con Swagger.
- Validaciones utilizando Bean Validation.
- Manejo centralizado de excepciones.
- Registro de eventos mediante SLF4J.
- Pruebas unitarias con JUnit y Mockito.

---

# Autor

Proyecto desarrollado por Mackener Castro, Estudiante de Ingeniería en Informática de **Duoc UC** para la asignatura **Fullstack I**.

---

=======
# BarberFlow
>>>>>>> 882b98d0624dc45ba62dce70b497ecbc81104eb1
