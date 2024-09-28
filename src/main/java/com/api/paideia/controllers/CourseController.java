package com.api.paideia.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.discipline.Discipline;

import lombok.val;

@Controller
@RequestMapping("/aluno")
public class CourseController {

    @GetMapping("/home")
    public String home(Model model) {

        Course.adicionarCursosNoModel(model);
        return "user";
    }

    @GetMapping("/course")
    public ModelAndView course(Model model) {

        Course.adicionarCursosNoModel(model);

        Discipline.adicionarCursosNoModel(model);

        ModelAndView mv = new ModelAndView("course-view");

        return mv;

    }

}
