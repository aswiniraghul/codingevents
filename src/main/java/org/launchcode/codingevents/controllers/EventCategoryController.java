package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event-categories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayCategories(Model model) {
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }

    @GetMapping("/create")
    public String displayCreateEventCategoryForm(Model model) {
        model.addAttribute("category", new EventCategory());
        return "eventCategories/create";
    }

    @PostMapping("/create")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory category, Errors errors) {
        if (errors.hasErrors()) {
            return "/create";
        }
        eventCategoryRepository.save(category);
        return "redirect:/event-categories";
    }
}
