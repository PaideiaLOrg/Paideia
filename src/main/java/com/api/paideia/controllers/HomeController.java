package com.api.paideia.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ModelAndView home(HttpServletRequest request) {
        // Verificar se o cookie JWT está presente
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    // Se o cookie JWT está presente, redireciona para /aluno/home
                    return new ModelAndView(new RedirectView("/aluno/home"));
                }
            }
        }

        // Se o cookie não está presente, retorna para a página index (ou a página de
        // login)
        return new ModelAndView("index");
    }

}
