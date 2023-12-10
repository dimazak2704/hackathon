package com.jdk.hackathon.Controllers;

import com.jdk.hackathon.Models.Proposal;
import com.jdk.hackathon.Models.Request;
import com.jdk.hackathon.Services.CategoryService;
import com.jdk.hackathon.Services.LocationService;
import com.jdk.hackathon.Services.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/proposal")
public class ProposalController {

    private final ProposalService proposalService;
    private final LocationService locationService;

    private final CategoryService categoryService;


    @Autowired
    public ProposalController(ProposalService proposalService, LocationService locationService, CategoryService categoryService) {
        this.proposalService = proposalService;
        this.locationService = locationService;
        this.categoryService = categoryService;
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("proposals", proposalService.findAll());
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("categories", categoryService.findAll());

        return "proposal/Proporsals";
    }

    @GetMapping("/new")
    public String newRequest(@ModelAttribute("proposal") Proposal proposal,
                             Model model){
        model.addAttribute("Allcategories", categoryService.findAll());
        model.addAttribute("AllLocations", locationService.findAll());

        return "proposal/Proporsals_Registration";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("proposal") Proposal proposal,
                         Model model) {
        model.addAttribute("Allcategories", categoryService.findAll());
        model.addAttribute("AllLocations", locationService.findAll());

        List<String> selectedCategories = proposal.getSelectedCategories();
        List<Category> categories = new ArrayList<>();
        for (String s : selectedCategories){
            categories.add(categoryService.findByValue(s));
        }

        List<String> selectedLocations = proposal.getSelectedLocations();
        List<Location> locations = new ArrayList<>();
        for (String s : selectedLocations){
            locations.add(locationService.findByValue(s));
        }

        proposal.setCategories(categories);
        proposal.setLocations(locations);

        proposalService.save(proposal);

        proposalService.save(proposal);
        return "redirect:/proposal/index";
    }
}
