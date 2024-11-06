package com.api.paideia.domain.academicResearches;

import java.time.LocalDate;
import java.util.List;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.user.User;
import com.api.paideia.enums.ResearchTypeEnum;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "academicResearches")
public class AcademicResearch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idAcademicResearch;
    private String researchAuthor;
    private String supervisor;
    @Enumerated(EnumType.STRING)
    private ResearchTypeEnum researchTypeEnum;
    private String researchTitle;
    private String researchSubject;
    private String researchAbstract;

    @ElementCollection
    @CollectionTable(name = "research_keywords") // Define a tabela auxiliar
    @Column(name = "keyword")
    private List<String> researchKeywords; // Lista de palavras-chave

    private LocalDate researchPublication;
    private String researchNotebook;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;
}
