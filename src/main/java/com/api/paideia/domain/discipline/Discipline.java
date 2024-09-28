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

    public static void adicionarCursosNoModel(Model model) {
        // Mapa para associar o título com as disciplinas de cada semestre
        Map<String, List<Discipline>> semesterCourses = new LinkedHashMap<>();

        // Primeiro semestre
        List<Discipline> firstSemester = new ArrayList<>();
        firstSemester.add(new Discipline("Design Digital", "red", "/aluno/course"));
        firstSemester.add(new Discipline("Web 2", "orange", "/aluno/course"));
        firstSemester.add(new Discipline("Mobile 2", "blue", "/aluno/course"));
        firstSemester.add(new Discipline("Gestão de Projetos", "pink", "/aluno/course"));
        firstSemester.add(new Discipline("Web 1", "yellow", "/aluno/course"));
        firstSemester.add(new Discipline("Engenharia de Software", "green", "/aluno/course"));

        // Segundo semestre
        List<Discipline> secondSemester = new ArrayList<>();
        secondSemester.add(new Discipline("Mobile 1", "green", "/aluno/course"));
        secondSemester.add(new Discipline("Análise de Sistemas", "purple", "/aluno/course"));
        secondSemester.add(new Discipline("Banco de Dados", "blue", "/aluno/course"));
        secondSemester.add(new Discipline("Engenharia de Software", "red", "/aluno/course"));
        secondSemester.add(new Discipline("Metodologias Ágeis", "orange", "/aluno/course"));

        // Terceiro semestre
        List<Discipline> thirdSemester = new ArrayList<>();
        thirdSemester.add(new Discipline("Arquitetura de Software", "blue", "/aluno/course"));
        thirdSemester.add(new Discipline("Segurança da Informação", "pink", "/aluno/course"));
        thirdSemester.add(new Discipline("Desenvolvimento Mobile", "yellow", "/aluno/course"));
        thirdSemester.add(new Discipline("Programação Avançada", "green", "/aluno/course"));
        thirdSemester.add(new Discipline("Cloud Computing", "red", "/aluno/course"));

        // Quarto semestre
        List<Discipline> fourthSemester = new ArrayList<>();
        fourthSemester.add(new Discipline("Inteligência Artificial", "blue", "/aluno/course"));
        fourthSemester.add(new Discipline("Data Science", "purple", "/aluno/course"));
        fourthSemester.add(new Discipline("DevOps", "pink", "/aluno/course"));
        fourthSemester.add(new Discipline("Blockchain", "orange", "/aluno/course"));
        fourthSemester.add(new Discipline("Design de Interfaces", "yellow", "/aluno/course"));

        // Adiciona os semestres ao mapa com seus títulos
        semesterCourses.put("Semestre 1", firstSemester);
        semesterCourses.put("Semestre 2", secondSemester);
        semesterCourses.put("Semestre 3", thirdSemester);
        semesterCourses.put("Semestre 4", fourthSemester);

        // Adiciona o mapa ao model
        model.addAttribute("semesterCourses", semesterCourses);
    }
}
