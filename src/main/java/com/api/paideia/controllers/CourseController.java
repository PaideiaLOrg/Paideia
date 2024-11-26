package com.api.paideia.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.api.paideia.domain.academicResearch.AcademicResearch;
import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.discipline.Discipline;
import com.api.paideia.domain.user.User;
import com.api.paideia.dto.AcademicResearchDTO;
import com.api.paideia.dto.CourseDTO;
import com.api.paideia.dto.DisciplineDTO;
import com.api.paideia.enums.CourseStatusEnum;
import com.api.paideia.enums.DegreeProgramEnum;
import com.api.paideia.enums.DisciplineStatus;
import com.api.paideia.enums.DisciplineTypeEnum;
import com.api.paideia.enums.KnowledgeAreaEnum;
import com.api.paideia.enums.ResearchTypeEnum;
import com.api.paideia.infrastructure.security.TokenService;
import com.api.paideia.repositories.academicResearch.AcademicResearchRepository;
import com.api.paideia.repositories.course.CourseRepository;
import com.api.paideia.repositories.discipline.DisciplineRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/aluno")
public class CourseController {

    final CourseRepository courseRepository;
    final DisciplineRepository disciplineRepository;
    final AcademicResearchRepository academicResearchRepository;
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

    @GetMapping("/course/{idCourse}")
    public String course(@PathVariable String idCourse, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você

        Course course = courseRepository.findByIdCourse(idCourse);
        List<AcademicResearch> academicResearchList = academicResearchRepository.findByCourse(course);
        List<Discipline> disciplineList = disciplineRepository.findByCourse(course);
        List<Course> courseList = courseRepository.findByUser(user);

        model.addAttribute("course", new CourseDTO());
        model.addAttribute("courseAll", course);
        model.addAttribute("idCourse", idCourse);
        model.addAttribute("courseList", courseList);
        model.addAttribute("academicResearchList", academicResearchList);
        model.addAttribute("disciplineList", disciplineList);
        model.addAttribute("disciplineDTO", new DisciplineDTO());
        model.addAttribute("academicResearchDTO", new AcademicResearchDTO());
        model.addAttribute("disciplineTypes", DisciplineTypeEnum.values());
        model.addAttribute("disciplineStatus", DisciplineStatus.values());
        model.addAttribute("researchTypeEnum", ResearchTypeEnum.values());

        model.addAttribute("degreeProgramOptions", DegreeProgramEnum.values()); // Passa o enum para o Thymeleaf
        model.addAttribute("course_statusOptions", CourseStatusEnum.values());
        model.addAttribute("knowledge_areaOptions", KnowledgeAreaEnum.values());
        return "course-view";

    }

    @PostMapping("/course/register")
    public String registerCourse(
            @Valid @ModelAttribute("course") CourseDTO courseDTO,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        String referer = request.getHeader("Referer"); // Captura a URL de onde veio a requisição

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("course", courseDTO);
            redirectAttributes.addFlashAttribute("formError", true); // Flag para modal
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:" + (referer != null ? referer : "/aluno/home"); // Redireciona para a página anterior ou
                                                                              // para o home
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Course newCourse = new Course();
        newCourse.setCourseName(courseDTO.getCourseName());
        newCourse.setDegreeProgram(courseDTO.getDegreeProgram());
        newCourse.setEmphasisArea(courseDTO.getEmphasisArea());
        newCourse.setEntryDate(courseDTO.getEntryDate());
        newCourse.setCompletionDate(courseDTO.getCompletionDate());
        newCourse.setCourseStatus(courseDTO.getCourseStatus());
        newCourse.setKnowledgeArea(courseDTO.getKnowledgeArea());
        newCourse.setInstitution(courseDTO.getInstitution());
        newCourse.setInstitute(courseDTO.getInstitute());
        newCourse.setDescription(courseDTO.getDescription());
        newCourse.setUser(user);

        this.courseRepository.save(newCourse);

        return "redirect:" + (referer != null ? referer : "/aluno/home"); // Redireciona para a página anterior ou para
                                                                          // o home
    }

}
