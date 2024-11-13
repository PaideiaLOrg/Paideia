package com.api.paideia.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.discipline.Discipline;
import com.api.paideia.domain.user.User;
import com.api.paideia.dto.DisciplineDTO;
import com.api.paideia.repositories.course.CourseRepository;
import com.api.paideia.repositories.discipline.DisciplineRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/aluno/course")
public class DisciplineController {
    final CourseRepository courseRepository;
    final DisciplineRepository disciplineRepository;

    @GetMapping("/discipline")
    public ModelAndView getDiscipline(Model model) {

        ModelAndView mv = new ModelAndView("discipline-view");

        return mv;
    }

    @PostMapping("/{idCourse}/discipline/register")
    public RedirectView registerDiscipline(@PathVariable String idCourse, @ModelAttribute DisciplineDTO disciplineDTO,
            Model model) {

        System.out.println(idCourse);
        // User user = (User)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou
        // de onde você

        // Course course = courseRepository.findByCourseName(courseName);

        Discipline newDiscipline = new Discipline();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você
        Course course = courseRepository.findByIdCourse(idCourse);
        newDiscipline.setDisciplineName(disciplineDTO.getDisciplineName());
        newDiscipline.setDisciplineProfessor(disciplineDTO.getDisciplineProfessor());
        newDiscipline.setSyllabus(disciplineDTO.getSyllabus());
        newDiscipline.setDisciplineTypeEnum(disciplineDTO.getDisciplineTypeEnum());
        newDiscipline.setDepartment(disciplineDTO.getDepartment());
        newDiscipline.setDisciplineStatus(disciplineDTO.getDisciplineStatus());
        newDiscipline.setGrade(disciplineDTO.getGrade());
        newDiscipline.setDisciplineNotebook(disciplineDTO.getDisciplineNotebook());
        newDiscipline.setCourse(course);
        newDiscipline.setUser(user);
        disciplineRepository.save(newDiscipline);
        return new RedirectView("/aluno/course/{idCourse}");
    }
}
