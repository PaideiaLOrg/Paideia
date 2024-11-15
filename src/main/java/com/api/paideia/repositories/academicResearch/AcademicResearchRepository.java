package com.api.paideia.repositories.academicResearch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paideia.domain.academicResearch.AcademicResearch;
import com.api.paideia.domain.course.Course;

public interface AcademicResearchRepository extends JpaRepository<AcademicResearch, String> {

    List<AcademicResearch> findByCourse(Course course);

    AcademicResearch findByIdAcademicResearch(String idAcademicResearch);
}
