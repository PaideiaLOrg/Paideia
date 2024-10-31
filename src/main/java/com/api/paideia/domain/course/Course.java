package com.api.paideia.domain.course;

import com.api.paideia.domain.user.User;
import com.api.paideia.enums.CourseStatusEnum;
import com.api.paideia.enums.DegreeProgramEnum;
import com.api.paideia.enums.KnowledgeAreaEnum;

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
    private String id_course;

    private String courseName;
    private String description;
    @Enumerated(EnumType.STRING)
    private DegreeProgramEnum degree_program;

    private String emphasis_area;

    private String entry_date;

    private String completion_date;

    @Enumerated(EnumType.STRING)
    private CourseStatusEnum course_status;

    @Enumerated(EnumType.STRING)
    private KnowledgeAreaEnum knowledge_area;

    private String institution;

    private String institute;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
