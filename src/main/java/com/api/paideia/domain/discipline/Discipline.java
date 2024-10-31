package com.api.paideia.domain.discipline;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.user.User;
import com.api.paideia.enums.DisciplineStatus;
import com.api.paideia.enums.DisciplineTypeEnum;

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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "disciplines")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idDiscipline;
    private String disciplineName;
    private String disciplineProfessor;
    private String syllabus;
    @Enumerated(EnumType.STRING)
    private DisciplineTypeEnum disciplineTypeEnum;
    private String department;
    @Enumerated(EnumType.STRING)
    private DisciplineStatus disciplineStatus;
    @Column(nullable = false, precision = 15, scale = 2) // Configura o BigDecimal no banco de dados
    private BigDecimal grade;
    private String disciplineNotebook;
    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
