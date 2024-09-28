package com.api.paideia.domain.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private String name;
    private String imageUrl;
    private String link;

    public static void adicionarCursosNoModel(Model model) {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("DSM", "/images/software-developer-6521720_640.jpg", "/aluno/course"));
        courses.add(new Course("ADM", "/images/software-developer-6521720_640.jpg", "/aluno/course"));
        model.addAttribute("courses", courses);
    }
}
