package com.api.paideia.domain.reference;

import org.springframework.ui.Model;

import com.api.paideia.domain.course.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class References {
    private String name;
    private String imageUrl;
    private String link;

    public static void getReference(Model model) {
        Course.adicionarCursosNoModel(model);

        model.addAttribute("references");
    }
}
