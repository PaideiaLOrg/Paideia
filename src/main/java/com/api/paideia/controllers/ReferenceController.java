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

import com.api.paideia.domain.academicResearch.AcademicResearch;
import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.discipline.Discipline;
import com.api.paideia.domain.user.User;
import com.api.paideia.dto.CourseDTO;
import com.api.paideia.dto.ReferenceDTO;
import com.api.paideia.enums.CourseStatusEnum;
import com.api.paideia.enums.DegreeProgramEnum;
import com.api.paideia.enums.KnowledgeAreaEnum;
import com.api.paideia.enums.ReferenceTypeEnum;
import com.api.paideia.domain.reference.Reference;
import com.api.paideia.repositories.academicResearch.AcademicResearchRepository;
import com.api.paideia.repositories.course.CourseRepository;
import com.api.paideia.repositories.discipline.DisciplineRepository;
import com.api.paideia.repositories.reference.ReferenceRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/aluno")
@Controller
public class ReferenceController {

    final CourseRepository courseRepository;
    final ReferenceRepository referenceRepository;
    final DisciplineRepository disciplineRepository;
    final AcademicResearchRepository academicResearchRepository;

    @GetMapping("/references")
    public ModelAndView reference(Model model) {
        // Obtendo o usuário autenticado
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Buscando os cursos associados ao usuário
        List<Course> courseList = courseRepository.findByUser(user);

        // Buscando todas as disciplinas e pesquisas acadêmicas
        List<Discipline> disciplineList = disciplineRepository.findByUser(user);
        List<AcademicResearch> academicResearchList = academicResearchRepository.findByUser(user);

        List<Reference> referenceList = referenceRepository.findByUser(user);
        // Adicionando os objetos ao modelo
        model.addAttribute("course", new CourseDTO());
        model.addAttribute("courseList", courseList);
        model.addAttribute("referenceList", referenceList);
        model.addAttribute("disciplineList", disciplineList); // Lista de disciplinas
        model.addAttribute("academicResearchList", academicResearchList); // Lista de pesquisas acadêmicas
        model.addAttribute("referenceDTO", new ReferenceDTO());
        model.addAttribute("referenceTypeEnum", ReferenceTypeEnum.values());
        model.addAttribute("degreeProgramOptions", DegreeProgramEnum.values()); // Passa o enum para o Thymeleaf
        model.addAttribute("course_statusOptions", CourseStatusEnum.values());
        model.addAttribute("knowledge_areaOptions", KnowledgeAreaEnum.values());
        ModelAndView mv = new ModelAndView("references-view");

        return mv;
    }

    @PostMapping("/reference/register")
    public ModelAndView registerReference(@ModelAttribute ReferenceDTO referenceDTO, Model model) {
        // Obtendo o usuário autenticado
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Criando a nova referência
        Reference newReference = new Reference();

        // Setando os valores básicos
        newReference.setReferenceAuthors(referenceDTO.getReferenceAuthors());
        newReference.setReferenceKeywords(referenceDTO.getReferenceKeywords());
        newReference.setPublicationAuthors(referenceDTO.getPublicationAuthors());
        newReference.setReferenceTitle(referenceDTO.getReferenceTitle());
        newReference.setReferenceSubtitle(referenceDTO.getReferenceSubtitle());
        newReference.setReferenceTypeEnum(referenceDTO.getReferenceTypeEnum());
        newReference.setReferenceAbstract(referenceDTO.getReferenceAbstract());
        newReference.setPublicationTitle(referenceDTO.getPublicationTitle());
        newReference.setProjectStartDate(referenceDTO.getProjectStartDate());
        newReference.setLink(referenceDTO.getLink());
        newReference.setReferenceAnnotation(referenceDTO.getReferenceAnnotation());
        newReference.setReferenceCitation(referenceDTO.getReferenceCitation());

        newReference.setAcademicResearches(referenceDTO.getAcademicResearches());
        newReference.setDisciplines(referenceDTO.getDisciplines());
        newReference.setCourse(referenceDTO.getAcademicResearches() != null
                ? courseRepository.findByIdCourse(newReference.getAcademicResearches().get(0).getCourse().getIdCourse())
                : courseRepository.findByIdCourse(newReference.getDisciplines().get(0).getCourse().getIdCourse()));
        newReference.setUser(user);
        // Salvando a nova referência
        referenceRepository.save(newReference);

        // Redirecionando para a página de referências
        return new ModelAndView("redirect:/aluno/references");
    }

}
