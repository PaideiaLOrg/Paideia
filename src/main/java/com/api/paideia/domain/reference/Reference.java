package com.api.paideia.domain.reference;

import java.time.Year;
import java.util.List;

import com.api.paideia.domain.user.User;
import com.api.paideia.enums.ReferenceTypeEnum;

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

@Entity
@Table(name = "references")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idReference;

    @ElementCollection
    @CollectionTable(name = "reference_authors")
    @Column(name = "reference_author")
    private List<String> referenceAuthors;
    @ElementCollection
    @CollectionTable(name = "reference_keywords")
    @Column(name = "reference_keyword")
    private List<String> referenceKeywords;

    @ElementCollection
    @CollectionTable(name = "publication_authors")
    @Column(name = "publication_author")
    private List<String> publicationAuthors;

    private String referenceTitle;
    private String referenceSubtitle;
    @Enumerated(EnumType.STRING)
    private ReferenceTypeEnum referenceTypeEnum;
    private String referenceAbstract;
    private String publicationTitle;
    private Year projectStartDate;
    private String link;
    private String referenceAnnotation;
    private String referenceCitation;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
