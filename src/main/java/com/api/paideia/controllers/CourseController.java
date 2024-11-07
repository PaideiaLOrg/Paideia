package com.api.paideia.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.api.paideia.domain.academicResearches.AcademicResearches;
import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.discipline.Discipline;
import com.api.paideia.domain.reference.References;
import com.api.paideia.repositories.user.UserRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/aluno")
public class CourseController {

    final UserRepository userRepository;

    @GetMapping("/home")
    public String home(Model model) {

        Course.adicionarCursosNoModel(model);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // Geralmente o nome de usuário ou e-mail

            // Se você tiver um serviço para buscar o usuário completo:
            model.addAttribute(username);
        }

        return "user"; // Nome da view a ser renderizada
    }

    @GetMapping("/course")
    public ModelAndView course(Model model) {

        Course.adicionarCursosNoModel(model);

        Discipline.adicionarCursosNoModel(model);

        AcademicResearches.adicionarCursosNoModel(model);

        ModelAndView mv = new ModelAndView("course-view");

        return mv;

    }

    @GetMapping("/references")
    public ModelAndView reference(Model model) {

        References.getReference(model);

        ModelAndView mv = new ModelAndView("references-view");

        return mv;

    }

}
