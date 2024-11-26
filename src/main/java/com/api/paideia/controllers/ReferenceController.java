package com.api.paideia.controllers;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.user.User;
import com.api.paideia.dto.CourseDTO;
import com.api.paideia.dto.ReferenceDTO;
import com.api.paideia.domain.reference.Reference;
import com.api.paideia.repositories.course.CourseRepository;
import com.api.paideia.repositories.reference.ReferenceRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/aluno")
@Controller
public class ReferenceController {

    final CourseRepository courseRepository;
    final ReferenceRepository referenceRepository;

    @GetMapping("/references")
    public ModelAndView reference(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você

        List<Course> courseList = courseRepository.findByUser(user);
        model.addAttribute("course", new CourseDTO());
        model.addAttribute("courseList", courseList);
        model.addAttribute("referenceDTO", new ReferenceDTO());

        ModelAndView mv = new ModelAndView("references-view");

        return mv;
    }

    @PostMapping("/reference/register")
    public ModelAndView registerReference(@ModelAttribute ReferenceDTO referenceDTO, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Reference newReference = new Reference();
        newReference.setReferenceAuthors(referenceDTO.getReferenceAuthors());
        newReference.setReferenceTitle(referenceDTO.getReferenceTitle());
        newReference.setProjectStartDate(referenceDTO.getProjectStartDate());
        referenceRepository.save(newReference);
        return new ModelAndView("redirect:/aluno/references");
    }

}
