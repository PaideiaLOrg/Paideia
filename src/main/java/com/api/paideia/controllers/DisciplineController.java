package com.api.paideia.controllers;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.discipline.Discipline;
import com.api.paideia.domain.user.User;
import com.api.paideia.dto.CourseDTO;
import com.api.paideia.dto.DisciplineDTO;
import com.api.paideia.enums.CourseStatusEnum;
import com.api.paideia.enums.DegreeProgramEnum;
import com.api.paideia.enums.DisciplineStatus;
import com.api.paideia.enums.DisciplineTypeEnum;
import com.api.paideia.enums.KnowledgeAreaEnum;
import com.api.paideia.repositories.course.CourseRepository;
import com.api.paideia.repositories.discipline.DisciplineRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/aluno/course")
public class DisciplineController {
    final CourseRepository courseRepository;
    final DisciplineRepository disciplineRepository;

    @GetMapping("/discipline/{idDiscipline}")
    public ModelAndView getDiscipline(@PathVariable String idDiscipline, Model model) {
        Discipline discipline = disciplineRepository.findByIdDiscipline(idDiscipline);
        model.addAttribute("discipline", discipline);
        model.addAttribute("disciplineDTO",
                new DisciplineDTO(discipline.getDisciplineName(), discipline.getDisciplineProfessor(),
                        discipline.getSyllabus(), discipline.getDisciplineTypeEnum(), discipline.getDepartment(),
                        discipline.getDisciplineStatus(), discipline.getGrade(), discipline.getDisciplineNotebook(),
                        discipline.getCourse(), discipline.getUser()));

        model.addAttribute("disciplineTypes", DisciplineTypeEnum.values());
        model.addAttribute("disciplineStatus", DisciplineStatus.values());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você

        List<Course> courseList = courseRepository.findByUser(user);

        model.addAttribute("courseList", courseList);
        // obtém o usuário
        model.addAttribute("user", user);

        model.addAttribute("course", new CourseDTO());
        model.addAttribute("degreeProgramOptions", DegreeProgramEnum.values()); // Passa o enum para o Thymeleaf
        model.addAttribute("course_statusOptions", CourseStatusEnum.values());
        model.addAttribute("knowledge_areaOptions", KnowledgeAreaEnum.values());

        ModelAndView mv = new ModelAndView("discipline-view");

        return mv;
    }

    @PutMapping("/discipline/edit/{idDiscipline}")
    public ModelAndView pathDiscipline(@PathVariable String idDiscipline,
            @ModelAttribute("disciplineDTO") DisciplineDTO disciplineAtuDisciplineDTO) {
        Discipline newDiscipline = new Discipline();
        newDiscipline.setIdDiscipline(idDiscipline);
        newDiscipline.setDisciplineName(disciplineAtuDisciplineDTO.getDisciplineName());
        newDiscipline.setDisciplineProfessor(disciplineAtuDisciplineDTO.getDisciplineProfessor());
        newDiscipline.setSyllabus(disciplineAtuDisciplineDTO.getSyllabus());
        newDiscipline.setDisciplineTypeEnum(disciplineAtuDisciplineDTO.getDisciplineTypeEnum());
        newDiscipline.setDepartment(disciplineAtuDisciplineDTO.getDepartment());
        newDiscipline.setDisciplineStatus(disciplineAtuDisciplineDTO.getDisciplineStatus());
        newDiscipline.setGrade(disciplineAtuDisciplineDTO.getGrade());
        newDiscipline.setDisciplineNotebook(disciplineAtuDisciplineDTO.getDisciplineNotebook());
        newDiscipline.setCourse(disciplineAtuDisciplineDTO.getCourse());
        newDiscipline.setUser(disciplineAtuDisciplineDTO.getUser());
        disciplineRepository.save(newDiscipline);
        return new ModelAndView("redirect:/aluno/course/discipline/" + idDiscipline);
    }

    @PostMapping("/{idCourse}/discipline/register")
    public RedirectView registerDiscipline(@PathVariable String idCourse,
            @Valid @ModelAttribute("disciplineDTO") DisciplineDTO disciplineDTO, BindingResult result,
            Model model, RedirectAttributes redirectAttributes) {

        System.out.println(idCourse);
        // User user = (User)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou
        // de onde você

        // Course course = courseRepository.findByCourseName(courseName);
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            redirectAttributes.addFlashAttribute("disciplineDTO", new DisciplineDTO());
            redirectAttributes.addFlashAttribute("formErrorDiscipline", true); // Flag para modal
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return new RedirectView("/aluno/course/{idCourse}");
            // para o home
        }
        Discipline newDiscipline = new Discipline();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você
        Course course = courseRepository.findByIdCourse(idCourse);
        newDiscipline.setDisciplineName(disciplineDTO.getDisciplineName());
        newDiscipline.setDisciplineProfessor(disciplineDTO.getDisciplineProfessor());
        newDiscipline.setSyllabus(disciplineDTO.getSyllabus());
        newDiscipline.setDisciplineTypeEnum(disciplineDTO.getDisciplineTypeEnum());
        newDiscipline.setDepartment(disciplineDTO.getDepartment());
        newDiscipline.setDisciplineStatus(disciplineDTO.getDisciplineStatus());
        newDiscipline.setGrade(disciplineDTO.getGrade());
        newDiscipline.setDisciplineNotebook(disciplineDTO.getDisciplineNotebook());
        newDiscipline.setCourse(course);
        newDiscipline.setUser(user);
        disciplineRepository.save(newDiscipline);
        return new RedirectView("/aluno/course/{idCourse}");
    }

    @DeleteMapping("/discipline/delete/{idDiscipline}")
    public RedirectView deleteDiscipline(@PathVariable String idDiscipline) {
        Discipline discipline = disciplineRepository.findByIdDiscipline(idDiscipline);
        String idCourse = discipline.getCourse().getIdCourse();
        disciplineRepository.deleteById(idDiscipline);
        return new RedirectView("/aluno/course/" + idCourse); // Ajuste o redirecionamento conforme necessário
    }

}
