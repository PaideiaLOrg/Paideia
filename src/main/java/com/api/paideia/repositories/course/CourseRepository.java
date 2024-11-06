package com.api.paideia.repositories.course;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {
    Course findByCourseName(String courseName);

    List<Course> findByUser(User user);
}
