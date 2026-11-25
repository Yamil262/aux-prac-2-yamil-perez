# API REST de Libros — Práctica #2 (SIS-414 G2)

API REST con **Spring Boot 3.4.5 / Java 17** para gestionar libros (CRUD).
No usa base de datos: los datos viven en memoria (`List`) y se pierden al reiniciar.

## Estructura

```
src/main/java/com/biblioteca/libros
├── controller/   BookController
├── service/      BookService
├── dto/          BookRequest, BookResponse
├── model/        Book
├── exception/    BookNotFoundException, GlobalExceptionHandler
└── config/       OpenApiConfig
```

## Ejecutar

- IntelliJ IDEA: File > Open > seleccionar `pom.xml` > Open as Project. Esperar a que Maven descargue
  las dependencias y ejecutar `LibrosApplication`.
- Terminal: `mvn spring-boot:run`

La app arranca en `http://localhost:8080`.

## Endpoints

| Método | Endpoint          | Descripción              | Respuestas    |
|--------|-------------------|--------------------------|---------------|
| POST   | `/api/books`      | Registrar un libro       | 201, 400      |
| GET    | `/api/books`      | Obtener todos los libros | 200           |
| GET    | `/api/books/{id}` | Obtener un libro por ID  | 200, 404      |
| PUT    | `/api/books/{id}` | Actualizar un libro      | 200, 400, 404 |
| DELETE | `/api/books/{id}` | Eliminar un libro        | 204, 404      |

Ejemplo de cuerpo (POST / PUT):

```json
{
  "title": "Cien años de soledad",
  "author": "Gabriel García Márquez",
  "isbn": "978-0307474728",
  "publicationYear": 1967,
  "category": "Novela",
  "available": true
}
```

## Swagger / OpenAPI

- Swagger UI: <http://localhost:8080/swagger-ui.html>
- JSON OpenAPI: <http://localhost:8080/v3/api-docs>
