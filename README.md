# Skill 07 - REST API CRUD Operations using ResponseEntity

## Run
```bash
mvn spring-boot:run
```

Base URL: `http://localhost:8080`

## Endpoints

### Create course (POST)
`POST /courses`
```json
{
  "courseId": 10,
  "title": "Data Science",
  "duration": "8 Weeks",
  "fee": 9999.0
}
```

### Get all (GET)
`GET /courses`

### Get by id (GET)
`GET /courses/{id}`

### Update (PUT)
`PUT /courses/{id}`
```json
{
  "courseId": 999,  // ignored; path id is used
  "title": "Updated Title",
  "duration": "10 Weeks",
  "fee": 12000.0
}
```

### Delete (DELETE)
`DELETE /courses/{id}`

### Search by title (GET)
`GET /courses/search/{title}`
