package com.api.paideia.controllers;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.api.paideia.domain.academicResearch.AcademicResearch;
import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.user.User;
import com.api.paideia.dto.AcademicResearchDTO;
import com.api.paideia.dto.CourseDTO;
import com.api.paideia.dto.DisciplineDTO;
import com.api.paideia.enums.CourseStatusEnum;
import com.api.paideia.enums.DegreeProgramEnum;
import com.api.paideia.enums.KnowledgeAreaEnum;
import com.api.paideia.infrastructure.security.TokenService;
import com.api.paideia.repositories.academicResearch.AcademicResearchRepository;
import com.api.paideia.repositories.course.CourseRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/aluno/course")
public class AcademicResearchController {
    final CourseRepository courseRepository;
    final TokenService tokenService;
    final AcademicResearchRepository academicResearchRepository;

    @GetMapping("/researchesview")
    public String getDiscipline(Model model) {

        ModelAndView mv = new ModelAndView("academic-researches-view");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você
        String token = tokenService.generateToken(user);

        List<Course> courseList = courseRepository.findByUser(user);

        model.addAttribute("courseList", courseList);
        // obtém o usuário
        model.addAttribute("user", user);
        model.addAttribute("token", token);

        model.addAttribute("course", new CourseDTO());
        model.addAttribute("degreeProgramOptions", DegreeProgramEnum.values()); // Passa o enum para o Thymeleaf
        model.addAttribute("course_statusOptions", CourseStatusEnum.values());
        model.addAttribute("knowledge_areaOptions", KnowledgeAreaEnum.values());
        return "academic-researches-view";
    }

    @PostMapping("/{idCourse}/research/register")
    public RedirectView registerDiscipline(@PathVariable String idCourse,
            @ModelAttribute AcademicResearchDTO academicResearchDTO,
            Model model) {

        AcademicResearch newAcademicResearch = new AcademicResearch();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você
        Course course = courseRepository.findByIdCourse(idCourse);

        newAcademicResearch.setResearchAuthor(academicResearchDTO.getResearchAuthor());
        newAcademicResearch.setResearchTitle(academicResearchDTO.getResearchTitle());
        newAcademicResearch.setSupervisor(academicResearchDTO.getSupervisor());
        newAcademicResearch.setResearchSubject(academicResearchDTO.getResearchSubject());
        newAcademicResearch.setResearchAbstract(academicResearchDTO.getResearchAbstract());
        newAcademicResearch.setResearchTypeEnum(academicResearchDTO.getResearchTypeEnum());
        newAcademicResearch.setResearchPublication(academicResearchDTO.getResearchPublication());
        newAcademicResearch.setCourse(course);
        newAcademicResearch.setUser(user);

        academicResearchRepository.save(newAcademicResearch);
        return new RedirectView("/aluno/course/{idCourse}");
    }
}