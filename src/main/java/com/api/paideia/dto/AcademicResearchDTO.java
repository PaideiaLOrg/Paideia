package com.api.paideia.dto;

import java.time.LocalDate;

import com.api.paideia.enums.ResearchTypeEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

}
