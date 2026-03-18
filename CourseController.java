package com.example.skill07;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    // Welcome endpoint (optional helper)
    @GetMapping("/welcome")
    public ResponseEntity<ApiResponse<String>> welcome() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Welcome to Course Management API!", "OK"));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> addCourse(@Valid @RequestBody Course course) {
        var created = service.addCourse(course);
        if (created.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Course not added. Possible reasons: duplicate courseId or invalid data.", null));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Course added successfully.", created.get()));
    }

    // READ (all)
    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(true, "All courses fetched.", service.getAllCourses()));
    }

    // READ (by id)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getById(@PathVariable Integer id) {
        var course = service.getCourse(id);
        if (course.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Course not found for id: " + id, null));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Course found.", course.get()));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(@PathVariable Integer id, @Valid @RequestBody Course course) {
        var updated = service.updateCourse(id, course);
        if (updated.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Course not found for id: " + id, null));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Course updated successfully.", updated.get()));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id) {
        boolean removed = service.deleteCourse(id);
        if (!removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Course not found for id: " + id, null));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Course deleted successfully.", "Deleted id: " + id));
    }

    // SEARCH
    @GetMapping("/search/{title}")
    public ResponseEntity<ApiResponse<List<Course>>> search(@PathVariable String title) {
        var results = service.searchByTitle(title);
        return ResponseEntity.ok(new ApiResponse<>(true, "Search results for title: " + title, results));
    }
}
