# CVOrotava - Backend 🚀

Este repositorio contiene la implementación del backend de la aplicación **CVOrotava**, desarrollado en **Spring Boot 3.1.2** y utilizando **MySQL** como base de datos relacional. El backend expone una API REST para la gestión de datos y la comunicación con el frontend (almacenado en un repositorio separado).

This repository contains the backend implementation of the **CVOrotava** application, developed using **Spring Boot 3.1.2** and **MySQL** as a relational database. The backend exposes a REST API for data management and communication with the frontend (stored in a separate repository).

---

## 📋 Índice / Table of Contents

1. [⚙️ Requisitos previos / Prerequisites](#️-requisitos-previos--prerequisites)
2. [🛠️ Configuración inicial / Initial Setup](#️-configuración-inicial--initial-setup)
3. [▶️ Ejecución del proyecto / Running the Project](#️-ejecución-del-proyecto--running-the-project)
4. [📂 Estructura del proyecto / Project Structure](#-estructura-del-proyecto--project-structure)
5. [🛠️ Tecnologías utilizadas / Technologies Used](#️-tecnologías-utilizadas--technologies-used)
6. [📢 Endpoints disponibles / Available Endpoints](#-endpoints-disponibles--available-endpoints)
7. [🤝 Contribución / Contribution](#-contribución--contribution)
8. [📜 Licencia / License](#-licencia--license)

---

## ⚙️ Requisitos previos / Prerequisites

### Español:

Antes de ejecutar este proyecto, asegúrate de que tienes instalado lo siguiente:

- **Java Development Kit (JDK)** 17 o superior
- **Maven** 3.8.0 o superior
- **MySQL** 8.0 o superior
- **Postman** (recomendado para el testeo de endpoints, se proporciona un archivo JSON con las colecciones).
- Un IDE como IntelliJ IDEA, Eclipse o VS Code con soporte para Java.

### English:

Before running this project, ensure you have the following installed:

- **Java Development Kit (JDK)** 17 or higher
- **Maven** 3.8.0 or higher
- **MySQL** 8.0 or higher
- **Postman** (recommended for endpoint testing; a JSON collection is provided).
- An IDE such as IntelliJ IDEA, Eclipse, or VS Code with Java support.

---

## 🛠️ Configuración inicial / Initial Setup

### Español:

### 1. Configuración de la base de datos

1. **Crear el usuario y la base de datos**:

   - En la carpeta `src/main/resources/mysql_config`, se encuentra un archivo SQL que configura el usuario y la base de datos necesaria para el proyecto:
     - Archivo: `mysql_config/init.sql`
   - Ejecútalo en tu servidor MySQL para inicializar la base de datos y crear el usuario requerido.

2. **Asegúrate de que los datos coincidan con la configuración de Spring Boot**:

   - En el archivo `application.properties` o `application.yml` (ubicado en `src/main/resources`), verifica las credenciales de conexión a la base de datos:
     ```properties
     server.port=9097
     spring.datasource.url=jdbc:mysql://localhost:3306/cvorotava_ddbb?allowPublicKeyRetrieval=True&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
     spring.datasource.username=admin_cvorotava
     spring.datasource.password=cvorotava1971
     spring.jpa.hibernate.ddl-auto=create-drop
     ```

3. **Configurar Maven**:

   - Una vez clonado el repositorio, ejecuta:
     ```bash
     mvn clean install
     ```

### English:

### 1. Database Setup

1. **Create the user and database**:

   - The `src/main/resources/mysql_config` folder contains an SQL file that sets up the required database and user for the project:
     - File: `mysql_config/init.sql`
   - Run this file on your MySQL server to initialize the database and create the required user.

2. **Ensure credentials match Spring Boot configuration**:

   - In the `application.properties` or `application.yml` file (located in `src/main/resources`), verify database connection credentials:
     ```properties
     server.port=9097
     spring.datasource.url=jdbc:mysql://localhost:3306/cvorotava_ddbb?allowPublicKeyRetrieval=True&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
     spring.datasource.username=admin_cvorotava
     spring.datasource.password=cvorotava1971
     spring.jpa.hibernate.ddl-auto=create-drop
     ```

3. **Set up Maven**:

   - After cloning the repository, run:
     ```bash
     mvn clean install
     ```

---

## ▶️ Ejecución del proyecto / Running the Project

### Español:

### 1. En el entorno local

1. Asegúrate de que el servidor MySQL está en ejecución y que la base de datos ha sido inicializada correctamente.

2. Ejecuta el proyecto con Maven:

   ```bash
   mvn spring-boot:run
   ```

3. El backend estará disponible en: `http://localhost:8080`

### English:

### 1. Running Locally

1. Ensure your MySQL server is running, and the database has been properly initialized.

2. Run the project using Maven:

   ```bash
   mvn spring-boot:run
   ```

3. The backend will be available at: `http://localhost:8080`

---

## 📂 Estructura del proyecto / Project Structure

```
CVOrotava
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.cvorotava.backend
│   │   │       ├── config        # Configuraciones específicas de la aplicación / Application-specific configurations
│   │   │       ├── controller    # Controladores REST / REST controllers
│   │   │       ├── dto           # Objetos de transferencia de datos / Data Transfer Objects
│   │   │       ├── entity        # Entidades de la base de datos / Database entities
│   │   │       ├── enums         # Enumeraciones / Enums
│   │   │       ├── error         # Gestión de errores / Error handling
│   │   │       │   ├── exception # Excepciones personalizadas / Custom exceptions
│   │   │       │   ├── handler   # Manejadores de errores / Error handlers
│   │   │       │   └── pojo      # Mensajes de error / Error message objects
│   │   │       ├── mapper        # Mappers (Mapstruct)
│   │   │       ├── repository    # Repositorios JPA / JPA Repositories
│   │   │       ├── service       # Lógica de negocio / Business logic
│   │   │       └── BackendApplication.java # Clase principal / Main class
│   │   └── resources
│   │       ├── application.properties  # Configuración principal / Main configuration
│   │       ├── static.images           # Almacenaje de las imágenes / Image storage
│   │       └── mysql_config
│   │           └── init.sql            # Script de inicialización de la base de datos / Database initialization script
│   └── test
│       └── java
│           └── com.cvorotava.backend  # Pruebas unitarias / Unit tests
├── pom.xml    # Archivo de configuración de Maven / Maven configuration file
└── README.md  # Documentación del proyecto / Project documentation
```

---

## 🛠️ Tecnologías utilizadas / Technologies Used

- **Spring Boot 3.1.2**: Framework para aplicaciones Java / Java application framework.
- **Spring Data JPA**: Abstracción para bases de datos / Database abstraction.
- **MySQL**: Sistema de bases de datos relacional / Relational database system.
- **Hibernate**: Implementación JPA para gestionar entidades / JPA implementation for entity management.
- **Maven**: Gestión de dependencias / Dependency management.
- **Postman**: Herramienta recomendada para probar endpoints / Recommended tool for testing endpoints.

---

## 📢 Endpoints disponibles / Available Endpoints

### Español:

La colección de endpoints está disponible en formato JSON dentro de la carpeta `docs/endpoints-postman` del proyecto. Importe esta colección en Postman para facilitar el testeo.

### English:

The endpoint collection is available in JSON format within the `docs/endpoints-postman` folder. Import this collection into Postman for easier testing.

---

## 🤝 Contribución / Contribution

1. Haz un fork del repositorio / Fork the repository.
2. Crea una nueva rama con tus cambios / Create a new branch with your changes:
   ```bash
   git checkout -b feature/new-feature
   ```
> ⚠️ El formato para las ramas es [NombreDescriptivoEnCamelCase]#[Numero Issue]. Ej: EquipBtn#67 / Branch format must be [DescriptiveNameInCamelCase]#[Issue Number]. E. g: EquipBtn#67
3. Realiza un commit de tus cambios / Commit your changes:
   ```bash
   git commit -m "✨ Added new feature; closes #[numero issue / issue number]"
   ```
> ⚠️ Usar [gitmoji](https://gitmoji.dev/) como referencia para crear los commits y comentarlos en Inglés / Use [gitmoji](https://gitmoji.dev/) as the reference for creating the commits and comment them in English
4. Haz un push de la rama / Push the branch:
   ```bash
   git push origin feature/new-feature
   ```
5. Abre un Pull Request / Open a Pull Request.

---

## 📜 Licencia / License

Este proyecto está licenciado bajo la [MIT License](LICENSE).

This project is licensed under the [MIT License](LICENSE).

