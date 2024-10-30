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

    private String course_name;

    @Enumerated(EnumType.STRING)
    private DegreeProgramEnum degreeProgram;

    private String emphasis_area;

    private String entry_date;

    private String completion_date;

    @Enumerated(EnumType.STRING)
    private CourseStatusEnum course_status;

    @Enumerated(EnumType.STRING)
    private KnowledgeAreaEnum knowledge_area;

    private String institution;

    private String institute;

}
