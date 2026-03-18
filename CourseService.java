package com.example.skill07;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final Map<Integer, Course> store = new ConcurrentHashMap<>();

    public CourseService() {
        // Seed a few demo courses
        store.put(1, new Course(1, "Java", "6 Weeks", 4999.0));
        store.put(2, new Course(2, "Python", "5 Weeks", 3999.0));
        store.put(3, new Course(3, "Spring Boot", "4 Weeks", 5999.0));
    }

    public Optional<Course> addCourse(Course course) {
        if (course == null || course.getCourseId() == null) return Optional.empty();
        if (store.containsKey(course.getCourseId())) return Optional.empty(); // duplicate id
        store.put(course.getCourseId(), course);
        return Optional.of(course);
    }

    public Optional<Course> updateCourse(Integer id, Course updated) {
        if (id == null || updated == null) return Optional.empty();
        if (!store.containsKey(id)) return Optional.empty();
        updated.setCourseId(id); // ensure path id wins
        store.put(id, updated);
        return Optional.of(updated);
    }

    public boolean deleteCourse(Integer id) {
        if (id == null) return false;
        return store.remove(id) != null;
    }

    public Optional<Course> getCourse(Integer id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(store.get(id));
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(store.values())
                .stream()
                .sorted(Comparator.comparing(Course::getCourseId))
                .collect(Collectors.toList());
    }

    public List<Course> searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) return Collections.emptyList();
        String q = title.trim().toLowerCase();
        return store.values().stream()
                .filter(c -> c.getTitle() != null && c.getTitle().toLowerCase().contains(q))
                .sorted(Comparator.comparing(Course::getCourseId))
                .collect(Collectors.toList());
    }
}
