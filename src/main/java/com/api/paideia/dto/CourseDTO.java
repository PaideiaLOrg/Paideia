package com.api.paideia.dto;

import com.api.paideia.enums.CourseStatusEnum;
import com.api.paideia.enums.DegreeProgramEnum;
import com.api.paideia.enums.KnowledgeAreaEnum;

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

    private String courseName;
    private String description;

    @Enumerated(EnumType.STRING)
    private DegreeProgramEnum degreeProgram;

    private String emphasisArea;

    private String entryDate;

    private String completionDate;

    @Enumerated(EnumType.STRING)
    private CourseStatusEnum courseStatus;

    @Enumerated(EnumType.STRING)
    private KnowledgeAreaEnum knowledgeArea;

    private String institution;

    private String institute;

}
