package com.api.paideia.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.api.paideia.domain.user.User;
import com.api.paideia.dto.LoginRequestDTO;
import com.api.paideia.dto.RegisterDTO;
import com.api.paideia.dto.ResponseUserDTO;
import com.api.paideia.infrastructure.security.TokenService;
import com.api.paideia.repositories.user.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        User user = userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("not found email"));
        if (passwordEncoder.matches(user.getPassword(), body.password())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseUserDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO body) {
        Optional<User> user = userRepository.findByEmail(body.email());

        if (user.isEmpty()) {
            User newUSer = new User();
            newUSer.setName(body.name());
            newUSer.setEmail(body.email());
            newUSer.setPassword(passwordEncoder.encode(body.password()));
            String token = tokenService.generateToken(newUSer);
            this.userRepository.save(newUSer);
            return ResponseEntity.ok(new ResponseUserDTO(newUSer.getName(), token));

        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/register")
    public ModelAndView viewCadastro() {
        ModelAndView mv = new ModelAndView("register-view");
        return mv;

    }

    @GetMapping("/login")

    public ModelAndView viewLogin() {
        ModelAndView mv = new ModelAndView("login-view");
        return mv;
    }

}
