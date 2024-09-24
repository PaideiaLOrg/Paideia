package com.api.paideia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/aluno")
public class TesteController {
    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("user");
        return mv;
    }

}
