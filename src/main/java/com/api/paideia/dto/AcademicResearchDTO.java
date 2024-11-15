package com.api.paideia.dto;

import java.time.LocalDate;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.user.User;
import com.api.paideia.enums.ResearchTypeEnum;

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
public class AcademicResearchDTO {
    private String researchAuthor;
    private String supervisor;

    @Enumerated(EnumType.STRING)
    private ResearchTypeEnum researchTypeEnum;
    private String researchTitle;
    private String researchSubject;
    private String researchAbstract;
    private LocalDate researchPublication;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;

}
