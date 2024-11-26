package com.api.paideia.dto;

import java.time.LocalDate;

import com.api.paideia.enums.CourseStatusEnum;
import com.api.paideia.enums.DegreeProgramEnum;
import com.api.paideia.enums.KnowledgeAreaEnum;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CourseDTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDTO {

    @NotBlank(message = "O nome do curso é obrigatório.")
    private String courseName;

    @NotBlank(message = "A descrição é obrigatória.")
    private String description;

    @NotNull(message = "O grau acadêmico é obrigatório.")
    private DegreeProgramEnum degreeProgram;

    @NotBlank(message = "A área de ênfase é obrigatória.")
    private String emphasisArea;

    @NotNull(message = "A data de ingresso é obrigatória.")
    @PastOrPresent(message = "A data de ingresso não pode ser futura.")
    private LocalDate entryDate;

    @NotNull(message = "A data de formação é obrigatória.")
    @FutureOrPresent(message = "A data de formação não pode ser passada.")
    private LocalDate completionDate;

    @NotNull(message = "O status do curso é obrigatório.")
    private CourseStatusEnum courseStatus;

    @NotNull(message = "A área de conhecimento é obrigatória.")
    private KnowledgeAreaEnum knowledgeArea;

    @NotBlank(message = "A instituição é obrigatória.")
    private String institution;

    @NotBlank(message = "O instituto é obrigatório.")
    private String institute;

}
