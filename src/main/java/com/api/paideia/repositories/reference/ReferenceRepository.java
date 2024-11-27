package com.api.paideia.repositories.reference;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.paideia.domain.reference.Reference;
import com.api.paideia.domain.user.User;

public interface ReferenceRepository extends JpaRepository<Reference, String> {

    @Query("SELECT r FROM Reference r JOIN r.academicResearches ar WHERE ar.id = :idAcademicResearch")
    List<Reference> findAllByAcademicResearchId(@Param("idAcademicResearch") String idAcademicResearch);

    @Query("SELECT r FROM Reference r JOIN r.disciplines ar WHERE ar.id = :idDiscipline")
    List<Reference> findAllByDisciplineId(@Param("idDiscipline") String idDiscipline);

    List<Reference> findByUser(User user);

    @Modifying
    @Query(value = "DELETE FROM reference_disciplines WHERE id_discipline = :idDiscipline", nativeQuery = true)
    void unlinkDiscipline(@Param("idDiscipline") String idDiscipline);

    @Modifying
    @Query(value = "DELETE FROM reference_academic_research WHERE id_academic_research = :idAcademicResearch", nativeQuery = true)
    void unlinkAcademicResearch(@Param("idAcademicResearch") String idAcademicResearch);
}
