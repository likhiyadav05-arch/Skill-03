# SKILL-06: Spring MVC Web Request Handling Demo (Library)

This is a **ready-to-run Spring Boot project** that demonstrates:
- `@RestController`
- `@GetMapping`, `@PostMapping`
- `@PathVariable`, `@RequestParam`
- JSON request/response handling (`@RequestBody`)

## Requirements
- Java **17**
- Maven (or use the Maven wrapper if you add it)

## Run the application

### Option A (Terminal)
```bash
mvn spring-boot:run
```

App runs at: `http://localhost:8080`

## Endpoints (Tasks)

1. `GET /welcome` → welcome message
2. `GET /count` → total books (int)
3. `GET /price` → sample book price (double)
4. `GET /books` → list of book titles
5. `GET /books/{id}` → book details (JSON)
6. `GET /search?title=...` → confirmation message
7. `GET /author/{name}` → formatted author message
8. `POST /addbook` → add a Book (JSON) into in-memory list
9. `GET /viewbooks` → view all added books

## Sample requests

### Get book by id
```bash
curl http://localhost:8080/books/1
```

### Search by title
```bash
curl "http://localhost:8080/search?title=Clean%20Code"
```

### Add a book (POST JSON)
```bash
curl -X POST http://localhost:8080/addbook \
  -H "Content-Type: application/json" \
  -d '{"title":"Design Patterns","author":"GoF","price":799.0}'
```

### View added books
```bash
curl http://localhost:8080/viewbooks
```
