package com.api.paideia.domain.studyArea;

import com.api.paideia.domain.user.User;

import jakarta.persistence.Entity;
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
@Table(name = "study_areas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyArea {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idCourse;
    private String studyField;
    private String fieldDescription;
    private String fieldNotebook;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
