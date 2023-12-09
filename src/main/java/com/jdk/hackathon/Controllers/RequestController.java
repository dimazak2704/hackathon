package com.jdk.hackathon.Controllers;

import com.jdk.hackathon.Models.Request;
import com.jdk.hackathon.Services.CategoryService;
import com.jdk.hackathon.Services.LocationService;
import com.jdk.hackathon.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;
    private final CategoryService categoryService;
    private final LocationService locationService;

    @Autowired
    public RequestController(RequestService requestService, CategoryService categoryService, LocationService locationService) {
        this.requestService = requestService;
        this.categoryService = categoryService;
        this.locationService = locationService;
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("requests", requestService.findAll());
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("categories", categoryService.findAll());

        return "request/index";
    }

    @GetMapping("/new")
    public String newRequest(@ModelAttribute("request") Request request,
                             Model model){
        model.addAttribute("Allcategories", categoryService.findAll());

        return "request/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("request") Request request,
                         Model model) {
        model.addAttribute("Allcategories", categoryService.findAll());

        requestService.save(request);
        return "redirect:/request/main";
    }
}
