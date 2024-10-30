package com.api.paideia.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.api.paideia.domain.academicResearches.AcademicResearches;
import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.discipline.Discipline;
import com.api.paideia.domain.user.User;
import com.api.paideia.dto.CourseDTO;
import com.api.paideia.enums.CourseStatusEnum;
import com.api.paideia.enums.DegreeProgramEnum;
import com.api.paideia.enums.KnowledgeAreaEnum;
import com.api.paideia.infrastructure.security.TokenService;
import com.api.paideia.repositories.course.CourseRepository;
import java.util.List;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/aluno")
public class CourseController {

    final CourseRepository courseRepository;
    final TokenService tokenService;

    @GetMapping("/home")
    public String home(Model model) {

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
        return "user"; // Nome da view a ser renderizada
    }

    @GetMapping("/course")
    public String course(Model model) {

        Discipline.adicionarCursosNoModel(model);

        AcademicResearches.adicionarCursosNoModel(model);
        model.addAttribute("course", new CourseDTO());

        model.addAttribute("degreeProgramOptions", DegreeProgramEnum.values()); // Passa o enum para o Thymeleaf
        model.addAttribute("course_statusOptions", CourseStatusEnum.values());
        model.addAttribute("knowledge_areaOptions", KnowledgeAreaEnum.values());
        return "course-view";

    }

    @PostMapping("/course/register")
    public RedirectView registerCourse(@ModelAttribute CourseDTO courseDTO, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você

        Course newCourse = new Course();
        newCourse.setCourse_name(courseDTO.getCourse_name());
        newCourse.setDegree_program(courseDTO.getDegreeProgram());
        newCourse.setEmphasis_area(courseDTO.getEmphasis_area());
        newCourse.setEntry_date(courseDTO.getEntry_date());
        newCourse.setCompletion_date(courseDTO.getCompletion_date());
        newCourse.setCourse_status(courseDTO.getCourse_status());
        newCourse.setKnowledge_area(courseDTO.getKnowledge_area());
        newCourse.setInstitution(courseDTO.getInstitution());
        newCourse.setInstitute(courseDTO.getInstitute());
        newCourse.setUser(user);

        this.courseRepository.save(newCourse);

        return new RedirectView("/aluno/home");
    }

}
