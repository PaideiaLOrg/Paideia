package com.api.paideia.controllers;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.user.User;
import com.api.paideia.dto.CourseDTO;
import com.api.paideia.dto.EditPerfilDTO;
import com.api.paideia.enums.CourseStatusEnum;
import com.api.paideia.enums.DegreeProgramEnum;
import com.api.paideia.enums.KnowledgeAreaEnum;
import com.api.paideia.infrastructure.security.TokenService;
import com.api.paideia.repositories.course.CourseRepository;
import com.api.paideia.repositories.user.UserRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor

@RequestMapping("/aluno")
public class PerfilController {
    final UserRepository userRepository;
    final CourseRepository courseRepository;
    final TokenService tokenService;

    @GetMapping("/perfil")
    public String course(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = tokenService.generateToken(user);

        List<Course> courseList = courseRepository.findByUser(user);

        model.addAttribute("courseList", courseList);
        model.addAttribute("user", user);
        model.addAttribute("token", token);
        model.addAttribute("userDTO", new EditPerfilDTO(user.getName()));
        model.addAttribute("course", new CourseDTO());
        model.addAttribute("degreeProgramOptions", DegreeProgramEnum.values()); // Passa o enum para o Thymeleaf
        model.addAttribute("course_statusOptions", CourseStatusEnum.values());
        model.addAttribute("knowledge_areaOptions", KnowledgeAreaEnum.values());
        return "perfil-view";

    }

    @PutMapping("/perfil/edit")
    public ModelAndView pathUser(@ModelAttribute("userDTO") EditPerfilDTO editPerfilDTO) {
        // Obter o usuário autenticado atual
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Atualizar os dados do usuário
        currentUser.setName(editPerfilDTO.getName());

        // Salvar as alterações no banco de dados
        userRepository.save(currentUser);

        // Atualizar o contexto de autenticação
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                currentUser,
                currentUser.getPassword(),
                SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Redirecionar para a página de perfil
        return new ModelAndView("redirect:/aluno/perfil");
    }

}
