package com.example.skill07;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Course {

    @NotNull(message = "courseId is required")
    @Min(value = 1, message = "courseId must be >= 1")
    private Integer courseId;

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "duration is required")
    private String duration;

    @NotNull(message = "fee is required")
    @Min(value = 0, message = "fee must be >= 0")
    private Double fee;

    public Course() {}

    public Course(Integer courseId, String title, String duration, Double fee) {
        this.courseId = courseId;
        this.title = title;
        this.duration = duration;
        this.fee = fee;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}
