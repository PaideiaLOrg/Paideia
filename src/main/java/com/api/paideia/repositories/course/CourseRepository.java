package com.api.paideia.repositories.course;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.user.User;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findByUser(User user);
}
