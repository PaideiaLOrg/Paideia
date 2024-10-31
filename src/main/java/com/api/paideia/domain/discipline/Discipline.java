package com.api.paideia.domain.discipline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discipline {

    private String name;
    private String color;
    private String link;

    // public static void adicionarCursosNoModel(Model model) {
    // // Mapa para associar o título com as disciplinas de cada semestre
    // Map<String, List<Discipline>> semesterCourses = new LinkedHashMap<>();

    // // Primeiro semestre
    // List<Discipline> firstSemester = new ArrayList<>();
    // firstSemester.add(new Discipline("Design Digital", "#1E3A8A",
    // "/aluno/course/discipline"));
    // firstSemester.add(new Discipline("Web 2", "#1E40AF",
    // "/aluno/course/discipline"));
    // firstSemester.add(new Discipline("Mobile 2", "#1D4ED8",
    // "/aluno/course/discipline"));
    // firstSemester.add(new Discipline("Design Digital", "#1E3A8A",
    // "/aluno/course/discipline"));
    // firstSemester.add(new Discipline("Web 2", "#1E40AF",
    // "/aluno/course/discipline"));
    // firstSemester.add(new Discipline("Mobile 2", "#1D4ED8",
    // "/aluno/course/discipline"));

    // // Segundo semestre
    // List<Discipline> secondSemester = new ArrayList<>();
    // secondSemester.add(new Discipline("Mobile 1", "#1E2749",
    // "/aluno/course/discipline"));
    // secondSemester.add(new Discipline("Análise de Sistemas", "#1768AC",
    // "/aluno/course/discipline"));
    // secondSemester.add(new Discipline("Banco de Dados", "#06BEE1",
    // "/aluno/course/discipline"));
    // secondSemester.add(new Discipline("Engenharia de Software", "#1E2749",
    // "/aluno/course/discipline"));
    // secondSemester.add(new Discipline("Metodologias Ágeis", "#1768AC",
    // "/aluno/course/discipline"));

    // // Terceiro semestre
    // List<Discipline> thirdSemester = new ArrayList<>();
    // thirdSemester.add(new Discipline("Arquitetura de Software", "#1E2749",
    // "/aluno/course/discipline"));
    // thirdSemester.add(new Discipline("Segurança da Informação", "#1768AC",
    // "/aluno/course/discipline"));
    // thirdSemester.add(new Discipline("Desenvolvimento Mobile", "#06BEE1",
    // "/aluno/course/discipline"));
    // thirdSemester.add(new Discipline("Programação Avançada", "#1E2749",
    // "/aluno/course/discipline"));
    // thirdSemester.add(new Discipline("Cloud Computing", "#1768AC",
    // "/aluno/course/discipline"));

    // // Quarto semestre
    // List<Discipline> fourthSemester = new ArrayList<>();
    // fourthSemester.add(new Discipline("Inteligência Artificial", "#1E2749",
    // "/aluno/course/discipline"));
    // fourthSemester.add(new Discipline("Data Science", "#1768AC",
    // "/aluno/course/discipline"));
    // fourthSemester.add(new Discipline("DevOps", "#06BEE1",
    // "/aluno/course/discipline"));
    // fourthSemester.add(new Discipline("Blockchain", "#1E2749",
    // "/aluno/course/discipline"));
    // fourthSemester.add(new Discipline("Design de Interfaces", "#1768AC",
    // "/aluno/course/discipline"));

    // // Adiciona os semestres ao mapa com seus títulos
    // semesterCourses.put("Semestre 1", firstSemester);
    // semesterCourses.put("Semestre 2", secondSemester);
    // semesterCourses.put("Semestre 3", thirdSemester);
    // semesterCourses.put("Semestre 4", fourthSemester);

    // // Adiciona o mapa ao model
    // model.addAttribute("semesterCourses", semesterCourses);
    // }
}
