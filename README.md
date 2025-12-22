# 🏟️ CV Orotava – Backend

Backend desarrollado en **Spring Boot** con **MySQL** para la **gestión interna de un club deportivo**.
Permite administrar información relacionada con:

* 👤 Jugadores
* 👕 Tallas de equipaciones
* 💳 Pagos mensuales
* 📅 Temporadas

Toda la información está organizada y filtrada por **temporadas**, facilitando el control histórico y actual del club.

---

## 🛠️ Tecnologías utilizadas

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA**
* **MySQL**
* **Maven**
* **Hibernate**
* **REST API**

---

## 📂 Estructura del proyecto

```text
CVOratava-back/
├── .github/
├── docs/
├── source/
│   ├── backend/
│   │   ├── .mvn/
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/
│   │   │   │   │   └── com.cvorotava.backend/
│   │   │   │   │       ├── config/        # Configuración general (CORS, WebConfig, etc.)
│   │   │   │   │       ├── controller/    # Controladores REST
│   │   │   │   │       ├── dto/            # Data Transfer Objects
│   │   │   │   │       ├── entity/         # Entidades JPA
│   │   │   │   │       ├── enums/          # Enumeraciones
│   │   │   │   │       ├── error/          # Gestión de errores
│   │   │   │   │       ├── exception/      # Excepciones personalizadas
│   │   │   │   │       ├── handler/        # Manejadores de excepciones globales
│   │   │   │   │       ├── pojo/            # Objetos auxiliares
│   │   │   │   │       ├── mapper/          # Conversión Entity ↔ DTO
│   │   │   │   │       ├── repository/      # Repositorios JPA
│   │   │   │   │       ├── service/         # Lógica de negocio
│   │   │   │   │       └── BackendApplication.java
│   │   │   │   └── resources/
│   │   │   │       ├── mysql_config/       # Configuración específica de MySQL
│   │   │   │       ├── static.images/      # Recursos estáticos (imágenes)
│   │   │   │       ├── application.properties
│   │   │   │       └── data.sql            # Datos iniciales
│   │   │   └── test/
│   │   ├── target/
│   │   ├── mvnw
│   │   ├── mvnw.cmd
│   │   └── pom.xml
├── README.md
├── LICENSE
└── .gitignore
```

---

## ⚙️ Instalación y configuración

### 1️⃣ Requisitos previos

Asegúrate de tener instalado:

* **Java 17 o superior**
* **Maven**
* **MySQL**
* **Git**

---

### 2️⃣ Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/CVOratava-back.git
cd CVOratava-back
```

---

### 3️⃣ Configuración de la base de datos

Crea una base de datos en MySQL ejecutando el archivo `/resources/mysql_config/schema.sql` para crearla y dar un usuario con privilegios

Configura el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cvorotava
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

> 📌 El archivo `data.sql` puede usarse para cargar datos iniciales de prueba automáticamente.

---

### 4️⃣ Ejecutar la aplicación

Desde la carpeta `source/backend`:

```bash
mvn spring-boot:run
```

O ejecutando la clase:

```text
BackendApplication.java
```

---

## 🌐 Acceso a la API

Por defecto, la aplicación se ejecuta en:

```
http://localhost:8080
```

Los endpoints REST permiten gestionar:

* Jugadores
* Pagos mensuales
* Tallas de equipaciones
* Temporadas

*(Consulta los controladores para ver el detalle de cada endpoint)*

---

## 🧠 Arquitectura

El proyecto sigue una arquitectura **por capas**:

* **Controller** → Manejo de peticiones HTTP
* **Service** → Lógica de negocio
* **Repository** → Acceso a base de datos
* **DTO / Mapper** → Separación entre modelo interno y API
* **Entity** → Modelo de datos persistente

Incluye además:

* Gestión centralizada de errores
* Excepciones personalizadas
* Configuración global (CORS, WebConfig, etc.)

---

## 📌 Objetivo del proyecto

Este backend está diseñado para ser utilizado **internamente por el club**, facilitando:

* Control de jugadores por temporada
* Gestión de pagos mensuales
* Registro de tallas de equipaciones
* Organización clara y escalable de los datos

---

## 📄 Licencia

Este proyecto se distribuye bajo la licencia incluida en el archivo `LICENSE`.

