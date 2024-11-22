package com.api.paideia.repositories.discipline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.discipline.Discipline;
import com.api.paideia.domain.user.User;

public interface DisciplineRepository extends JpaRepository<Discipline, String> {
    List<Course> findByUser(User user);

    List<Discipline> findByCourse(Course course);

    Discipline findByIdDiscipline(String idDiscipline);

    void deleteByCourse(Course course);

}
