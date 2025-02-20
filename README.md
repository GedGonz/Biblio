# Biblio

**Biblio** es una API REST desarrollada con **Spring Boot**, diseñada de forma modular y desacoplada para la gestión de libros y autores. La aplicación permite realizar operaciones CRUD sobre libros y autores, así como funcionalidades adicionales para mejorar la experiencia de gestión.

## Funcionalidades

- **Gestión de libros y autores**: Crear, actualizar, eliminar y consultar libros y autores.
- **Autenticación y seguridad**: Implementa **Spring Security** con control de acceso basado en roles.
- **Persistencia de datos**: Uso de **Spring Data JPA** con una base de datos relacional.
- **Carga de archivos en la nube**: Integración con **Cloudinary** para subir imágenes de portadas de libros y fotos de autores.
- **Generación de reportes**: Exportación de reportes en **PDF** con **JasperReports**.
- **Arquitectura modular y desacoplada**: Organizado en capas para facilitar la mantenibilidad y escalabilidad.

## Tecnologías utilizadas

- **Spring Boot** (Framework principal)
- **Spring Security** (Gestión de autenticación y autorización)
- **Spring Data JPA** (Interacción con base de datos)
- **JasperReports** (Generación de reportes en PDF)
- **Cloudinary** (Almacenamiento de imágenes en la nube)
- **Lombok** (Reducción de código boilerplate)
- **MapStruct** (Mapeo de objetos)
- **log4j2** (Registro de logs en archivos)
- **Gradle** (Gestión de dependencias y construcción del proyecto)
- **Base de datos relacional** (PostgreSQL)

## Instalación y ejecución

1. Clonar el repositorio:
   ```sh
   git clone https://github.com/tu-usuario/biblio.git
   cd biblio

2. Ejecutar la aplicación con:
   ```sh
   cd BiblioSpring.web
   ./gradlew bootRun


## Endpoints principales

| Método     | Endpoint                  | Descripción                       |
|------------|---------------------------|-----------------------------------|
| **GET**    | `/api/book`               | Listar todos los libros           |
| **POST**   | `/api/book/save`          | Crear un nuevo libro              |
| **PUT**    | `/api/book/update/{id}`   | Actualizar un libro               |
| **DELETE** | `/api/book/{id}`          | Eliminar un libro                 |
| **GET**    | `/api/author`             | Listar todos los autores          |
| **POST**   | `/api/author/save`        | Crear un nuevo autor              |
| **PUT**    | `/api/author/update/{id}` | Actualizar un autor               |
| **DELETE** | `/api/author/{id}`        | Eliminar un autor                 |
| **GET**    | `/api/book/{id}`          | Buscar libros por id              |
| **GET**    | `/api/author/{id}`        | Buscar autores por id             |
| **GET**    | `/api/book/download`      | Generar reporte de libros en PDF  |
| **GET**    | `/api/author/download`    | Generar reporte de autores en PDF |
