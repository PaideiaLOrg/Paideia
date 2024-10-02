package com.api.paideia.domain.academicResearches;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcademicResearches {

    private String name;
    private String color;
    private String link;

    public static void adicionarCursosNoModel(Model model) {
        List<AcademicResearches> academicResearches = new ArrayList<>();

        academicResearches.add(new AcademicResearches("TCC", "#1E2749", "/aluno/course/researchesview"));
        academicResearches.add(new AcademicResearches("IC", "#1E2749", "/aluno/course/researchesview"));
        academicResearches.add(new AcademicResearches("Artigo", "#1E2749", "/aluno/course/researchesview"));

        model.addAttribute("academicResearches", academicResearches);

    }
}
