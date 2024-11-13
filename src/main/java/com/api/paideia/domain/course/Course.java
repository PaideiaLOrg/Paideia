package com.api.paideia.domain.course;

import org.springframework.ui.Model;

import com.api.paideia.domain.user.User;
import com.api.paideia.enums.CourseStatusEnum;
import com.api.paideia.enums.DegreeProgramEnum;
import com.api.paideia.enums.KnowledgeAreaEnum;

import jakarta.persistence.Column;
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
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idCourse;

    private String courseName;
    @Column(length = 1000)
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

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public static void adicionarCursosNoModel(Model model) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionarCursosNoModel'");
    }

}
