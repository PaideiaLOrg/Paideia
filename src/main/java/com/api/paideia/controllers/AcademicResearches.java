package com.api.paideia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.api.paideia.domain.course.Course;

@Controller
@RequestMapping("/aluno/course")
public class AcademicResearches {
    @GetMapping("/researchesview")
    public ModelAndView getDiscipline(Model model) {

        ModelAndView mv = new ModelAndView("academic-researches-view");

        return mv;
    }
}
