package com.jdk.hackathon.Controllers;

import com.jdk.hackathon.Models.Category;
import com.jdk.hackathon.Models.Request;
import com.jdk.hackathon.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("requests", requestService.findAll());

        List<Request> requests = requestService.findAll();
        List<Category> categories = requests.get(1).getCategories();
        for (Category category : categories){
            System.out.println(category.getValue());
        }
        return "request/index";
    }
}
