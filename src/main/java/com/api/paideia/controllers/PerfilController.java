package com.api.paideia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.api.paideia.domain.course.Course;

@Controller
@RequestMapping("/aluno")
public class PerfilController {

    @GetMapping("/perfil")
    public ModelAndView course(Model model) {

        Course.adicionarCursosNoModel(model);

        ModelAndView mv = new ModelAndView("perfil-view");

        return mv;

    }
}
