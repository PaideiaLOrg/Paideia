package com.api.paideia.dto;

import java.time.Year;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReferenceDTO {
    @ElementCollection
    @CollectionTable(name = "reference_authors")
    @Column(name = "reference_author")
    private List<String> referenceAuthors;

    private String referenceTitle;

    private Year projectStartDate;

}
