package com.api.paideia.dto;

import java.math.BigDecimal;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.user.User;
import com.api.paideia.enums.DisciplineStatus;
import com.api.paideia.enums.DisciplineTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DisciplineDTO {
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
