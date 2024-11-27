package com.api.paideia.repositories.academicResearch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paideia.domain.academicResearch.AcademicResearch;
import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.user.User;

public interface AcademicResearchRepository extends JpaRepository<AcademicResearch, String> {

    List<AcademicResearch> findByCourse(Course course);

    List<AcademicResearch> findByUser(User user);

    AcademicResearch findByIdAcademicResearch(String idAcademicResearch);

    void deleteByCourse(Course course);

}
