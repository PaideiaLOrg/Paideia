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
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DisciplineDTO {

    @NotBlank(message = "O nome da disciplina é obrigatório.")
    private String disciplineName;

    @NotBlank(message = "O nome do professor é obrigatório.")
    private String disciplineProfessor;

    @NotBlank(message = "O conteúdo programático é obrigatório.")
    private String syllabus;

    @NotNull(message = "O tipo da disciplina é obrigatório.")
    @Enumerated(EnumType.STRING)
    private DisciplineTypeEnum disciplineTypeEnum;

    @NotBlank(message = "O departamento é obrigatório.")
    private String department;

    @NotNull(message = "O status da disciplina é obrigatório.")
    @Enumerated(EnumType.STRING)
    private DisciplineStatus disciplineStatus;

    @NotNull(message = "A nota é obrigatória.")
    @DecimalMin(value = "0.01", message = "A nota mínima permitida é 0.00.")
    @DecimalMax(value = "10.00", message = "A nota máxima permitida é 10.00.")
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
