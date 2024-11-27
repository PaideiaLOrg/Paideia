package com.api.paideia.controllers;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.api.paideia.domain.academicResearch.AcademicResearch;
import com.api.paideia.domain.course.Course;
import com.api.paideia.domain.reference.Reference;
import com.api.paideia.domain.user.User;
import com.api.paideia.dto.AcademicResearchDTO;
import com.api.paideia.dto.CourseDTO;
import com.api.paideia.enums.CourseStatusEnum;
import com.api.paideia.enums.DegreeProgramEnum;
import com.api.paideia.enums.KnowledgeAreaEnum;
import com.api.paideia.enums.ResearchTypeEnum;
import com.api.paideia.infrastructure.security.TokenService;
import com.api.paideia.repositories.academicResearch.AcademicResearchRepository;
import com.api.paideia.repositories.course.CourseRepository;
import com.api.paideia.repositories.reference.ReferenceRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/aluno/course")
public class AcademicResearchController {
    final CourseRepository courseRepository;
    final TokenService tokenService;
    final AcademicResearchRepository academicResearchRepository;
    final ReferenceRepository referenceRepository;

    @GetMapping("/researchesview/{idAcademicResearch}")
    public ModelAndView getDiscipline(@PathVariable String idAcademicResearch, Model model) {
        AcademicResearch academicResearch = academicResearchRepository.findByIdAcademicResearch(idAcademicResearch);
        model.addAttribute("academicResearch", academicResearch);
        model.addAttribute("academicResearchDTO", new AcademicResearchDTO(
                academicResearch.getResearchAuthor(), academicResearch.getSupervisor(),
                academicResearch.getResearchTypeEnum(),
                academicResearch.getResearchTitle(), academicResearch.getResearchSubject(),
                academicResearch.getResearchAbstract(),
                academicResearch.getResearchPublication(), academicResearch.getUser(), academicResearch.getCourse()));
        model.addAttribute("researchTypeEnum", ResearchTypeEnum.values());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // ou de onde você
        String token = tokenService.generateToken(user);

        List<Course> courseList = courseRepository.findByUser(user);
        List<Reference> referenceList = referenceRepository.findAllByAcademicResearchId(idAcademicResearch);

        model.addAttribute("courseList", courseList);
        // obtém o usuário
        model.addAttribute("user", user);
        model.addAttribute("token", token);
        model.addAttribute("referenceList", referenceList);

        model.addAttribute("course", new CourseDTO());
        model.addAttribute("degreeProgramOptions", DegreeProgramEnum.values()); // Passa o enum para o Thymeleaf
        model.addAttribute("course_statusOptions", CourseStatusEnum.values());
        model.addAttribute("knowledge_areaOptions", KnowledgeAreaEnum.values());
        ModelAndView mv = new ModelAndView("academic-researches-view");

        return mv;
    }

    @PutMapping("/researchesview/edit/{idAcademicResearch}")
    public ModelAndView pathDiscipline(@PathVariable String idAcademicResearch,
            @ModelAttribute("academicResearchDTO") AcademicResearchDTO academicResearchDTO) {
        AcademicResearch newAcademicResearch = new AcademicResearch();
        newAcademicResearch.setIdAcademicResearch(idAcademicResearch);
        newAcademicResearch.setResearchAuthor(academicResearchDTO.getResearchAuthor());
        newAcademicResearch.setResearchTitle(academicResearchDTO.getResearchTitle());
        newAcademicResearch.setSupervisor(academicResearchDTO.getSupervisor());
        newAcademicResearch.setResearchSubject(academicResearchDTO.getResearchSubject());
        newAcademicResearch.setResearchAbstract(academicResearchDTO.getResearchAbstract());
        newAcademicResearch.setResearchTypeEnum(academicResearchDTO.getResearchTypeEnum());
        newAcademicResearch.setResearchPublication(academicResearchDTO.getResearchPublication());
        newAcademicResearch.setCourse(academicResearchDTO.getCourse());
        newAcademicResearch.setUser(academicResearchDTO.getUser());
        academicResearchRepository.save(newAcademicResearch);
        return new ModelAndView("redirect:/aluno/course/researchesview/" + idAcademicResearch);
    }

    @PostMapping("/{idCourse}/researchesview/register")
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

    @DeleteMapping("/researchesview/delete/{idAcademicResearch}")
    @Transactional
    public RedirectView deleteAcademicResearch(@PathVariable String idAcademicResearch) {

        AcademicResearch academicResearch = academicResearchRepository.findByIdAcademicResearch(idAcademicResearch);
        String idCourse = academicResearch.getCourse().getIdCourse();

        // Desvincula todas as referências associadas a essa pesquisa acadêmica
        List<Reference> references = referenceRepository.findAllByAcademicResearchId(idAcademicResearch);
        referenceRepository.unlinkAcademicResearch(idAcademicResearch);

        // Verifica se cada referência está órfã (sem disciplinas ou pesquisas
        // acadêmicas)
        for (Reference reference : references) {
            boolean isOrphan = reference.getDisciplines().isEmpty()
                    && reference.getAcademicResearches().isEmpty();
            if (isOrphan) {
                referenceRepository.deleteById(reference.getIdReference());
            }
        }

        // Por fim, exclui a pesquisa acadêmica
        academicResearchRepository.deleteById(idAcademicResearch);

        return new RedirectView("/aluno/course/" + idCourse);
    }

}
