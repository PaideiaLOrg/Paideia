package com.api.paideia.controllers;

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

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.reference.References;
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

    @GetMapping("/course/{idCourse}")
    public String course(@PathVariable String idCourse, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você

        Course course = courseRepository.findByIdCourse(idCourse);
        List<Course> courseList = courseRepository.findByUser(user);
        System.out.println(idCourse);
        model.addAttribute("course", new CourseDTO());
        model.addAttribute("courseAll", course);
        model.addAttribute("idCourse", idCourse);
        model.addAttribute("courseList", courseList);
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

    @GetMapping("/references")
    public ModelAndView reference(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você

        List<Course> courseList = courseRepository.findByUser(user);
        model.addAttribute("course", new CourseDTO());
        model.addAttribute("courseList", courseList);
        ModelAndView mv = new ModelAndView("references-view");

        return mv;
    }

    @PostMapping("/course/register")
    public RedirectView registerCourse(@ModelAttribute CourseDTO courseDTO, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você

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

        return new RedirectView("/aluno/home");
    }

}
