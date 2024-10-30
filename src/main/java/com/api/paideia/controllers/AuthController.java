package com.api.paideia.controllers;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.api.paideia.domain.user.User;
import com.api.paideia.dto.LoginRequestDTO;
import com.api.paideia.dto.RegisterDTO;
import com.api.paideia.infrastructure.security.TokenService;
import com.api.paideia.repositories.user.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public RedirectView login(@ModelAttribute LoginRequestDTO body, HttpServletResponse response) {
        User user = userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("not found email"));

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);

            response.addHeader("Authorization", "Bearer " + token);
            Cookie jwtCookie = new Cookie("jwt", token);
            jwtCookie.setMaxAge(60 * 60 * 24);
            jwtCookie.setPath("/");
            response.addCookie(jwtCookie);

            return new RedirectView("/aluno/home");
        }
        return new RedirectView("/auth/login");
    }

    @PostMapping("/logoff")
    public RedirectView logoff(HttpServletResponse response) {

        Cookie jwtCookie = new Cookie("jwt", null);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);
        return new RedirectView("/auth/login");
    }

    @PostMapping("/register")
    public RedirectView register(@ModelAttribute RegisterDTO body, Model model, HttpServletResponse response) {
        Optional<User> user = userRepository.findByEmail(body.email());
        System.out.println("Received RegisterDTO: " + body);

        if (user.isEmpty()) {
            if (body.password() == null || body.password().isEmpty()) {
                System.out.println("Received RegisterDTO: " + body);
                boolean success = false; // This can be any condition
                model.addAttribute("alert", success ? "success" : "error");

                return new RedirectView("/auth/register");
            }

            User newUser = new User();
            newUser.setName(body.name());
            newUser.setEmail(body.email());
            newUser.setPassword(passwordEncoder.encode(body.password())); // Verificar se body.password() não é null
                                                                          // aqui
            this.userRepository.save(newUser);
            String token = tokenService.generateToken(newUser);
            boolean success = true; // This can be any condition

            response.addHeader("Authorization", "Bearer " + token);

            Cookie jwtCookie = new Cookie("jwt", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(60 * 60 * 24); // 1
            response.addCookie(jwtCookie);

            model.addAttribute("alert", success ? "success" : "error");

            return new RedirectView("/aluno/home");
        }

        boolean success = false; // This can be any condition
        model.addAttribute("alert", success ? "success" : "error");

        return new RedirectView("/auth/register");

    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("success", false);
        model.addAttribute("body", new RegisterDTO(null, null, null));
        return "register-view";

    }

    @GetMapping("/login")

    public String viewLogin(Model model) {
        model.addAttribute("body", new LoginRequestDTO(null, null));

        return "login-view";
    }

}
