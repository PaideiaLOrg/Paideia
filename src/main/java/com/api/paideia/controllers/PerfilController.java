package com.api.paideia.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.user.User;

@Controller
@RequestMapping("/aluno")
public class PerfilController {

    @GetMapping("/perfil")
    public String course(Model model) {

        Course.adicionarCursosNoModel(model);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você
                                                                                                  // obtém o usuário
        model.addAttribute("user", user);
        return "perfil-view";

    }
}
